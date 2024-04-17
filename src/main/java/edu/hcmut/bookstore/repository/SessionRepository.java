package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.Customer;
import edu.hcmut.bookstore.business.SessionInfo;
import edu.hcmut.bookstore.db.DbManager;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

public class SessionRepository {
    private final Ignite ignite;

    public SessionRepository(final Ignite ignite) {
        this.ignite = ignite;
    }

    public SessionRepository() throws Exception {
        this.ignite = DbManager.getIgniteNode();
    }

    /** get user's id to which the session associates.
     * @param sessionId id of the session
     * @return id of the user if there exists the session whose id is sessionId in the cache,
     * and the session is still valid. Otherwise, return false.
     * */
    public Customer getCustomer(String sessionId) {
        IgniteCache<String, SessionInfo> sessionCache = this.ignite.getOrCreateCache("SessionCache");
        var session = sessionCache.get(sessionId);
        if (!session.isValid()) {
            return null;
        }
        return session.customer;
    }

    /** remove session from the cache.
     * @param sessionId id of the session.
     * @return true if the operation is executed successfully, false otherwise.
     * */
    public boolean removeSession(String sessionId) {
        IgniteCache<String, SessionInfo> sessionCache = this.ignite.getOrCreateCache("SessionCache");
        sessionCache.remove(sessionId);
        return true;
    }

    /** add a session object to the cache.
     * @param session the session object to be added.
     * Each session is unique in the cache.
     */
    public void addSession(SessionInfo session) {
        IgniteCache<String, SessionInfo> sessionCache = this.ignite.getOrCreateCache("SessionCache");
        sessionCache.put(session.id, session);
    }
}
