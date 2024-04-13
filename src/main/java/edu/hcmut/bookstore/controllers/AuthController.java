package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.business.CustomerCredential;
import edu.hcmut.bookstore.business.Session;
import edu.hcmut.bookstore.db.DbManager;
import edu.hcmut.bookstore.repository.CustomerRepository;
import edu.hcmut.bookstore.repository.SessionRepository;
import edu.hcmut.bookstore.session.SessionManager;
import io.javalin.http.Context;

public class AuthController {
    public static void login(Context ctx) {
        // Check if the user has logged in
        try {
            var sessionRepo = new SessionRepository(DbManager.getIgniteNode());
            var sessionId = ctx.cookie("session-id");
            if (sessionId != null && sessionRepo.getUserId(sessionId) != null) {
                ctx.redirect("http://localhost:3000/");
            }

            var credential = ctx.bodyAsClass(CustomerCredential.class);
            var customerRepo = new CustomerRepository();
            var customer = customerRepo.getCustomerByCredential(credential);

            if (customer == null) {
                ctx.status(401);
                return;
            }

            var session = Session.newSession(customer.getId());
            ctx.cookie("session-id", session.id);
            sessionRepo.addSession(session);

            ctx.status(200);
            ctx.json(customer);
        } catch (Exception exception) {
            System.out.println(exception);
            ctx.status(500);
        }
    }

    public static void getUserInfo(Context ctx) {
        var sessionId = ctx.cookie("session-id");
        try {
            var sessionRepo = new SessionRepository(DbManager.getIgniteNode());
            var userId = sessionRepo.getUserId(sessionId);

            // Retrieve customer object using userId as the identifier
            var customerRepo = new CustomerRepository();
            customerRepo.getCustomerById(userId);

            ctx.json(customerRepo);
        } catch (Exception exception) {
            System.out.println("An exception is raised while getUserInfo is called.");
            System.out.println(exception);
            ctx.status(500);
        }
    }
}
