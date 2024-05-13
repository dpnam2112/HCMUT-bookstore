package edu.hcmut.bookstore.requestPayload;
import java.util.List;

public class BookIdQuantityPair {
    private List<Integer> bookIds;
    private List<Integer> bookCounts;

    public BookIdQuantityPair() {
        // Default constructor
        // the length of bookIds and bookCounts must be equal.
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }

    public List<Integer> getBookCounts() {
        return bookCounts;
    }

    public void setBookCounts(List<Integer> bookCounts) {
        this.bookCounts = bookCounts;
    }
}

