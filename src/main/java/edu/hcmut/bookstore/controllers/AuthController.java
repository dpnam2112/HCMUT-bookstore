package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.business.CustomerCredential;
import edu.hcmut.bookstore.business.SessionInfo;
import edu.hcmut.bookstore.db.DbManager;
import edu.hcmut.bookstore.repository.CustomerRepository;
import edu.hcmut.bookstore.repository.SessionRepository;
import io.javalin.http.Context;

public class AuthController {
    public static void login(Context ctx) throws Exception {
        var sessionRepo = new SessionRepository(DbManager.getIgniteNode());
        var sessionId = ctx.cookie("session-id");

        // Get customer object from the session to check if the user has logged in before
        if (sessionId != null) {
            var customer = sessionRepo.getCustomer(sessionId);
            if (customer == null) {
                ctx.removeCookie(sessionId);
            } else {
                ctx.json(customer);
                return;
            }
        }

        // Retrieve user's information from login credentials.
        var credential = ctx.bodyAsClass(CustomerCredential.class);
        var customerRepo = new CustomerRepository();
        var customer = customerRepo.getCustomerByCredential(credential);

        if (customer == null) {
            ctx.status(401);
            return;
        }

        // Create new session and add session information to the cache.
        var session = SessionInfo.newSession(customer);
        ctx.cookie("session-id", session.id);
        sessionRepo.addSession(session);

        ctx.status(200);
        ctx.json(customer);
    }

    public static void getUserInfo(Context ctx) throws Exception {
        var sessionId = ctx.cookie("session-id");
        var sessionRepo = new SessionRepository(DbManager.getIgniteNode());
        var customer = sessionRepo.getCustomer(sessionId);
        if (customer == null) {
            ctx.status(401);
            return;
        }
        ctx.json(customer);
    }

}
