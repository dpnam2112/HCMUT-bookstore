package edu.hcmut.bookstore.business;

import org.apache.ignite.cache.query.annotations.QueryTextField;

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private int id;

    @QueryTextField
    private String title;

    private String bookCover;

    private double price;

    private int remainingQuantity;

    private int authorId;

    private int publisherId;

    // Constructors
    public Book() {
    }

    public Book(int id, String title, String bookCover, double price, int remainingQuantity, int authorId, int publisherId) {
        this.id = id;
        this.title = title;
        this.bookCover = bookCover;
        this.price = price;
        this.remainingQuantity = remainingQuantity;
        this.authorId = authorId;
        this.publisherId = publisherId;
    }

    public Book(int id, String title, String bookCover, double price, int remainingQuantity) {
        this.id = id;
        this.title = title;
        this.bookCover = bookCover;
        this.price = price;
        this.remainingQuantity = remainingQuantity;     
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public void setRemainingQuantity(int remainingQuantity) {
        this.remainingQuantity = remainingQuantity;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getPublisherId() {
        return publisherId;
    }
}
