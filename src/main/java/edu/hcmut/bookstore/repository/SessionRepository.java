package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.Session;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

public class SessionRepository {
    private final Ignite ignite;

    public SessionRepository(final Ignite ignite) {
        this.ignite = ignite;
    }

    public Integer getUserId(String sessionId) {
        IgniteCache<String, Session> sessionCache = this.ignite.getOrCreateCache("SessionCache");
        var session = sessionCache.get(sessionId);
        if (!session.isValid()) {
            return null;
        }
        return session.userId;
    }

    public boolean removeSession(String sessionId) {
        IgniteCache<String, Session> sessionCache = this.ignite.getOrCreateCache("SessionCache");
        sessionCache.remove(sessionId);
        return true;
    }
}
