package edu.hcmut.bookstore.business;

public class BookDetail {
    private Book book;
    private Publisher publisher;
    private Author author;

    public BookDetail() {

    }

    public BookDetail(Book book, Publisher publisher, Author author) {
        this.book = book;
        this.publisher = publisher;
        this.author = author;
    }

    // Getters
    public Book getBook() {
        return book;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Author getAuthor() {
        return author;
    }

    // Setters
    public void setBook(Book book) {
        this.book = book;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}

