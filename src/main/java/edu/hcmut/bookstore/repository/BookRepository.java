package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.Book;
import edu.hcmut.bookstore.business.CartItem;
import edu.hcmut.bookstore.db.DbManager;
import org.apache.ignite.cache.query.annotations.QueryGroupIndex;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    BookCacheRepository cacheRepo = new BookCacheRepository();
    public Book getBookById(int id) {
        Book book = cacheRepo.getBookById(id);
        if (book != null) {
            System.out.println("Fetched a book from the cache");
            return book;
        }

        var dataSrc = DbManager.getMySqlDataSrc();

        try (var conn = dataSrc.getConnection()) {
            var prepStmt = conn.prepareStatement("select id, title, book_cover, price, remaining_quantity from book where id = ?");
            prepStmt.setInt(1, id);
            var resultSet = prepStmt.executeQuery();
            resultSet.next();
            book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("book_cover"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("remaining_quantity")
            );

            cacheRepo.setBook(book.getId(), book);
            return book;
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }



    public List<Book> queryBooks(String name, ArrayList<Integer> categories, Long startIdx, int count) {
        if (startIdx == null || startIdx < 0 || count < 1) {
            return null;
        }

        var bookList = new ArrayList<Book>();

        var dataSrc = DbManager.getMySqlDataSrc();
        try (var conn = dataSrc.getConnection()) {
            PreparedStatement prepStmt;
            if (categories == null) {
                prepStmt = conn.prepareStatement(
                        "select id, title, book_cover, price, remaining_quantity " +
                                "from book " +
                                "where title like ?" +
                                "limit ?, ?");

                name = "%" + name + "%";
                prepStmt.setString(1, name);
                prepStmt.setLong(2, startIdx);
                prepStmt.setInt(3, count);
            } else {
                StringBuilder query = new StringBuilder(
                        "select distinct book.id as id," +
                        "book.title as title, " +
                        "book.book_cover as book_cover, " +
                        "book.price as price, " +
                        "book.remaining_quantity as remaining_quantity " +
                        "from book " +
                        "join book_category on book.id = book_category.book_id " +
                        "where title like ? ");

                for (var catgId : categories) {
                    query.append("and book_category.category_id = ").append(catgId).append(" ");
                }

                query.append("limit ?, ?");

                prepStmt = conn.prepareStatement(query.toString());

                name = "%" + name + "%";
                prepStmt.setString(1, name);
                prepStmt.setLong(2, startIdx);
                prepStmt.setInt(3, count);
            }

            var resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                var book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("book_cover"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("remaining_quantity")
                );
                bookList.add(book);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }

        return bookList;
    }

    /** Get books from row 'start' to row 'end'.
     *  For some cases, we only need to retrieve a specific range of rows,
     *  e.g, retrieve books in page 4, page 5, .etc
     *  */
    public List<Book> getBooksInRange(Long startIdx, int count) {
        if (startIdx == null || startIdx < 0 || count < 1) {
            return null;
        }

        var bookList = new ArrayList<Book>();

        var dataSrc = DbManager.getMySqlDataSrc();
        try (var conn = dataSrc.getConnection()) {
            var prepStmt = conn.prepareStatement("select id, title, book_cover, price, remaining_quantity from book limit ?, ?");

            prepStmt.setLong(1, startIdx);
            prepStmt.setInt(2, count);

            var resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                var book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("book_cover"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("remaining_quantity")
                );
                bookList.add(book);
            }
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }

        return bookList;
    }

    public List<CartItem> getCartItemsOfUser(Long id) throws Exception {
        // 'quantity' is the quantity of books in the customer's cart
        var query = "select book.id as id, author_id, publisher_id, title, book_cover, price, remaining_quantity, quantity " +
                "from book " +
                "join cart_item on cart_item.book_id = book.id " +
                "join customer on customer.cart_id = cart_item.cart_id " +
                "where customer.id = ?";

        var cartItems = new ArrayList<CartItem>();
        if (id == null) {
            return cartItems;
        }

        var dataSrc = DbManager.getMySqlDataSrc();
        var conn = dataSrc.getConnection();
        var prepStmt = conn.prepareStatement(query);
        prepStmt.setLong(1, id);

        var resultSet = prepStmt.executeQuery();
        while (resultSet.next()) {
            var book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("book_cover"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("remaining_quantity")
            );

            var cart_quantity = resultSet.getInt("quantity");
            cartItems.add(new CartItem(book, cart_quantity));
        }

        return cartItems;
    }
}