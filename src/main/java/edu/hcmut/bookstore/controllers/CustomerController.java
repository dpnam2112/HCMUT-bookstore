package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.business.Customer;
import edu.hcmut.bookstore.business.OrderInfo;
import edu.hcmut.bookstore.db.DbManager;
import edu.hcmut.bookstore.repository.CustomerRepository;
import edu.hcmut.bookstore.repository.SessionRepository;
import io.javalin.http.Context;

public class CustomerController {
    public static void confirmOrder(Context ctx) throws Exception {
        var sessionRepo = new SessionRepository();
        var customerRepo = new CustomerRepository();
        System.out.println(ctx.body());
        var orderInfo = ctx.bodyAsClass(OrderInfo.class);
        var sessionId = ctx.cookie("session-id");
        var customer = sessionRepo.getCustomer(sessionId);
        customerRepo.saveOrder(customer, orderInfo);
        ctx.result();
    }
}