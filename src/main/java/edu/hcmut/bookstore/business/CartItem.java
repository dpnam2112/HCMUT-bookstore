package edu.hcmut.bookstore.business;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Book book;
    private int count;

    // Constructor
    public CartItem(Book book, int count) {
        this.book = book;
        this.count = count;
    }

    // Getter for book
    public Book getBook() {
        return book;
    }

    // Setter for book
    public void setBook(Book book) {
        this.book = book;
    }


    // Getter for count
    public int getCount() {
        return count;
    }

    // Setter for count
    public void setCount(int count) {
        this.count = count;
    }

}
