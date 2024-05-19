package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.*;
import edu.hcmut.bookstore.db.DbManager;
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
            var prepStmt = conn.prepareStatement("select id, title, book_cover, price, remaining_quantity, author_id, publisher_id from book where id = ?");
            prepStmt.setInt(1, id);
            var resultSet = prepStmt.executeQuery();
            resultSet.next();
            book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("book_cover"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("remaining_quantity"),
                    resultSet.getInt("author_id"),
                    resultSet.getInt("publisher_id")
            );

            cacheRepo.setBook(book.getId(), book);
            return book;
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }

    /** Add a book to the database. The input object of type Book must have the id of -1.
     * @param book is the object containing data to be added to the database.
     * @return a boolean value indicating whether the operation is successfully executed.
     * */
    public boolean addBook(Book book) {
        if (book == null || book.getId() != -1) {
            return false;
        }

        var dataSrc = DbManager.getMySqlDataSrc();
        try (var conn = dataSrc.getConnection()) {
            var insertStmt = conn.prepareStatement(
                    "insert into book (author_id, publisher_id, title, book_cover, price, remaining_quantity) " +
                            "values (?, ?, ?, ?, ?, ?)"
            );

            insertStmt.setLong(1, book.getAuthorId());
            insertStmt.setLong(2, book.getPublisherId());
            insertStmt.setString(3, book.getTitle());
            insertStmt.setString(4, book.getBookCover());
            insertStmt.setDouble(5, book.getPrice());
            insertStmt.setLong(6, book.getRemainingQuantity());

            return insertStmt.execute();
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    public boolean addAuthor(Author author) {
        if (author == null) {
            return false;
        }

        var dataSrc = DbManager.getMySqlDataSrc();

        try (var conn = dataSrc.getConnection()) {
            var insertNewAuthorStmt = conn.prepareStatement(
                    "insert into author (author_name) values (?);"
            );

            insertNewAuthorStmt.setString(1, author.getAuthorName());

            int rowCount = insertNewAuthorStmt.executeUpdate();
            return rowCount == 1;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    public boolean addPublisher(Publisher publisher) {
        if (publisher == null) {
            return false;
        }

        var dataSrc = DbManager.getMySqlDataSrc();

        try (var conn = dataSrc.getConnection()) {
            var newPublisherStmt = conn.prepareStatement(
                    "insert into publisher (publisher_name) values (?)"
            );

            newPublisherStmt.setString(1, publisher.getPublisherName());

            int rowCount = newPublisherStmt.executeUpdate();
            return rowCount == 1;
        } catch (Exception exp) {
            exp.printStackTrace();
            return false;
        }
    }

    public List<Book> queryBooks(String name, ArrayList<Integer> categories, Long startIdx, int count) throws Exception {
        if (startIdx == null || startIdx < 0 || count < 1) {
            return null;
        }

        var bookList = new ArrayList<Book>();

        var dataSrc = DbManager.getMySqlDataSrc();
        var conn = dataSrc.getConnection();
        PreparedStatement prepStmt;
        if (categories.isEmpty()) {
            prepStmt = conn.prepareStatement(
                    "select id, title, book_cover, price, remaining_quantity, author_id, publisher_id " +
                            "from book " +
                            "where title like ? " +
                            "limit ?, ?");

            name = "%" + name + "%";
            prepStmt.setString(1, name);
            prepStmt.setLong(2, startIdx);
            prepStmt.setInt(3, count);
        } else {
            StringBuilder query = new StringBuilder(
                    "select distinct book.id as id, " +
                    "book.title as title, " +
                    "book.book_cover as book_cover, " +
                    "book.price as price, " +
                    "book.remaining_quantity as remaining_quantity, " +
                    "book.author_id as author_id, " +
                    "book.publisher_id as publisher_id " +
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
                    resultSet.getInt("remaining_quantity"),
                    resultSet.getInt("author_id"),
                    resultSet.getInt("publisher_id")
            );
            bookList.add(book);
        }

        return bookList;
    }

    /** Get books from row 'start' to row 'end'.
     *  For some cases, we only need to retrieve a specific range of rows,
     *  e.g, retrieve books in page 4, page 5, .etc
     **/
    public List<Book> getBooksInRange(Long startIdx, int count) {
        if (startIdx == null || startIdx < 0 || count < 1) {
            return null;
        }

        var bookList = new ArrayList<Book>();

        var dataSrc = DbManager.getMySqlDataSrc();
        try (var conn = dataSrc.getConnection()) {
            var prepStmt = conn.prepareStatement("select id, title, book_cover, price, remaining_quantity, publisher_id, author_id from book limit ?, ?");

            prepStmt.setLong(1, startIdx);
            prepStmt.setInt(2, count);

            var resultSet = prepStmt.executeQuery();
            while (resultSet.next()) {
                var book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("book_cover"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("remaining_quantity"),
                    resultSet.getInt("author_id"),
                    resultSet.getInt("publisher_id")
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
        var query = "select book.id as id, title, book_cover, price, remaining_quantity, quantity " +
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

    public boolean updateBook(Book book) throws Exception {
        if (book == null) {
            return false;
        }

        var dataSrc = DbManager.getMySqlDataSrc();
        var conn = dataSrc.getConnection();
        var prepStmt = conn.prepareStatement(
            "update book " +
                "set author_id = ?, publisher_id = ?, title = ?, book_cover = ?, price = ?, remaining_quantity = ? " +
                "where id = ?"
        );

        prepStmt.setInt(1, book.getAuthorId());
        prepStmt.setString(2, book.getTitle());
        prepStmt.setString(3, book.getBookCover());
        prepStmt.setDouble(4, book.getPrice());
        prepStmt.setInt(5, book.getRemainingQuantity());
        prepStmt.setInt(6, book.getId());

        int rowCount = prepStmt.executeUpdate();
        if (rowCount != 1) {
            return false;
        }

        var bookCache = new BookCacheRepository();

        // keep the consistency between the database and the cache
        bookCache.setBook(book.getId(), book);
        return true;
    }

    public List<Category> getCategories() throws Exception {
        var dataSrc = DbManager.getMySqlDataSrc();
        var conn = dataSrc.getConnection();
        var prepStmt = conn.prepareStatement(
                "select id, name from category"
        );
        var resSet = prepStmt.executeQuery();
        var categories = new ArrayList<Category>();
        while (resSet.next()) {
            categories.add(new Category(resSet.getInt("id"), resSet.getString("name")));
        }
        return categories;
    }

    public Author getAuthor(int id) throws Exception {
        var dataSrc = DbManager.getMySqlDataSrc();
        var conn = dataSrc.getConnection();
        var prepStmt = conn.prepareStatement(
                "select id, author_name from author where id = ?"
        );
        prepStmt.setInt(1, id);
        var resSet = prepStmt.executeQuery();
        if (!resSet.next()) return null;
        return new Author(resSet.getInt("id"), resSet.getString("author_name"));
    }

    public Publisher getPublisher(int id) throws Exception {
        var dataSrc = DbManager.getMySqlDataSrc();
        var conn = dataSrc.getConnection();
        var prepStmt = conn.prepareStatement(
                "select id, publisher_name from publisher where id = ?"
        );
        prepStmt.setInt(1, id);
        var resSet = prepStmt.executeQuery();
        if (!resSet.next()) return null;
        return new Publisher(resSet.getInt("id"), resSet.getString("publisher_name"));
    }

    public List<Publisher> getAllPublishers() throws Exception {
        var dataSrc = DbManager.getMySqlDataSrc();
        var conn = dataSrc.getConnection();
        var prepStmt = conn.prepareStatement(
                "select id, publisher_name from publisher"
        );
        var resSet = prepStmt.executeQuery();
        var publishers = new ArrayList<Publisher>();
        while (resSet.next()) {
            var publisher = new Publisher(resSet.getInt("id"), resSet.getString("publisher_name"));
            publishers.add(publisher);
        }
        return publishers;
    }

    public List<Author> getAllAuthors() throws Exception {
        var dataSrc = DbManager.getMySqlDataSrc();
        var conn = dataSrc.getConnection();
        var prepStmt = conn.prepareStatement(
                "select id, author_name from author"
        );
        var resSet = prepStmt.executeQuery();
        var authorList = new ArrayList<Author>();
        while (resSet.next()) {
            var author = new Author(resSet.getInt("id"), resSet.getString("author_name"));
            authorList.add(author);
        }
        return authorList;
    }
}