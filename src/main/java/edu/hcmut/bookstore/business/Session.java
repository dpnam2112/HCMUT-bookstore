package edu.hcmut.bookstore.business;

import java.time.LocalDateTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


public class Session {
    public final String id;
    public final LocalDateTime timeCreated;
    public final int userId;

    private static String generateSessionId(String data) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add data bytes to digest
            md.update(data.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // Convert bytes to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle NoSuchAlgorithmException appropriately
            return null;
        }
    }

    Session(String id, LocalDateTime datetime, int userId) {
        this.id = id;
        this.timeCreated = datetime;
        this.userId = userId;
    }

    public static Session newSession(int userId) {
        var datetime = LocalDateTime.now();
        return new Session(generateSessionId(UUID.randomUUID().toString()), datetime, userId);
    }

    public boolean isValid() {
        return false;
    }
}
