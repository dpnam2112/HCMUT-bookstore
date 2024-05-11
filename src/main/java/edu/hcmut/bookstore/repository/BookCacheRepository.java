package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.Book;
import edu.hcmut.bookstore.db.DbManager;
import org.apache.ignite.IgniteCache;

public class BookCacheRepository {
    IgniteCache<Integer, Book> cache;
    BookCacheRepository() {
        try {
            this.cache = DbManager.getIgniteNode().getOrCreateCache("BookCache");
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
    Book getBookById(int id) {
        Book book = this.cache.get(id);
        return book;
    }

    Book setBook(int id, Book book) {
        this.cache.put(id, book);
        return book;
    }
}