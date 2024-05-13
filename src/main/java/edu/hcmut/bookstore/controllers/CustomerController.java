package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.business.OrderInfo;
import edu.hcmut.bookstore.repository.BookRepository;
import edu.hcmut.bookstore.repository.CustomerRepository;
import edu.hcmut.bookstore.repository.SessionRepository;
import edu.hcmut.bookstore.requestPayload.BookIdQuantityPair;
import io.javalin.http.Context;

import java.util.ArrayList;

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

    public static void updateUserCart(Context ctx) throws Exception {
        var sessionRepo = new SessionRepository();
        var customerRepo = new CustomerRepository();

        var sessionId = ctx.cookie("session-id");
        var customer = sessionRepo.getCustomer(sessionId);

        if (customer == null) {
            ctx.status(403);
            return;
        }

        var payload = ctx.bodyAsClass(BookIdQuantityPair.class);

        if (payload.getBookIds().size() != payload.getBookCounts().size()) {
            ctx.status(400);
            ctx.result("The length of bookIds must be equal to the length of bookCounts.");
            return;
        }

        if (!customerRepo.updateCart(customer.getId(), payload)) {
            ctx.status(500);
            return;
        }

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