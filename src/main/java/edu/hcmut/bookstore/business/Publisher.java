package edu.hcmut.bookstore.business;

public class Publisher {
    private int id;
    private String publisherName;

    // Constructor
    public Publisher(int id, String publisherName) {
        this.id = id;
        this.publisherName = publisherName;
    }

    public Publisher() {

    }

    public Publisher(String publisherName) {
        this.id = -1;
        this.publisherName = publisherName;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}