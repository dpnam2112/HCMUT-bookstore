package edu.hcmut.bookstore.business;

import java.io.Serializable;

public class Author implements Serializable {
    private int id;
    private String authorName;

    public Author() {

    }

    public Author(int id, String authorName) {
        this.id = id;
        this.authorName = authorName;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for authorName
    public String getAuthorName() {
        return authorName;
    }

    // Setter for authorName
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}