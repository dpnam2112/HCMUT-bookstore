package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.business.Customer;
import edu.hcmut.bookstore.business.OrderInfo;
import edu.hcmut.bookstore.db.DbManager;
import edu.hcmut.bookstore.repository.BookRepository;
import edu.hcmut.bookstore.repository.CustomerRepository;
import edu.hcmut.bookstore.repository.SessionRepository;
import io.javalin.http.Context;

public class CustomerController {
    public static void confirmOrder(Context ctx) throws Exception {
        var sessionRepo = new SessionRepository();
        var customerRepo = new CustomerRepository();
        var orderInfo = ctx.bodyAsClass(OrderInfo.class);
        var sessionId = ctx.cookie("session-id");
        var customer = sessionRepo.getCustomer(sessionId);
        customerRepo.saveOrder(customer, orderInfo);
        ctx.result();
    }

    public static void addBookToCart(Context ctx) throws Exception {
        var sessionRepo = new SessionRepository();
        var customerRepo = new CustomerRepository();

        var sessionId = ctx.cookie("session-id");
        var customer = sessionRepo.getCustomer(sessionId);

        if (customer == null) {
            ctx.status(403);
            return;
        }

        var bookIdFormParam = ctx.formParam("bookId");
        var bookCountFormParam = ctx.formParam("bookCount");

        if (bookIdFormParam == null || bookCountFormParam == null) {
            ctx.status(400);
            ctx.result("require form parameter: 'bookId', 'bookCount'");
            return;
        }

        var bookId = Integer.parseInt(bookIdFormParam);
        var bookCount = Integer.parseInt(bookCountFormParam);

        if (bookId < 1 || bookCount < 1) {
            ctx.status(400);
            ctx.result("Both 'bookId' and 'bookCount' must be at least 1.");
            return;
        }

        customerRepo.addBookToCart(customer.getId(), bookId, bookCount);
        ctx.status(200);
    }

    public static void getUserCart(Context ctx) throws Exception {
        var sessionRepo = new SessionRepository();
        var bookRepo = new BookRepository();

        var sessionId = ctx.cookie("session-id");
        var customer = sessionRepo.getCustomer(sessionId);

        if (customer == null) {
            ctx.status(403);
            return;
        }

        var cartItems = bookRepo.getCartItemsOfUser(customer.getId());
        ctx.json(cartItems);
    }
}