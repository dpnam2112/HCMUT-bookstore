package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.Book;
import edu.hcmut.bookstore.db.DbManager;

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
}