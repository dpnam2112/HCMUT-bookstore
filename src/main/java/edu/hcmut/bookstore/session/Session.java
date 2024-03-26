package edu.hcmut.bookstore.session;

import java.io.Serializable;

public class Session implements Serializable {
    public final int userId;
    public final String sessionKey;

    Session(int userId, String sessionKey) {
        this.userId = userId;
        this.sessionKey = sessionKey;
    }
}
