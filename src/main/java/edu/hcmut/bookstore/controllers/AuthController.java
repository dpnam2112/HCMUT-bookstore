package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.business.CustomerCredential;
import edu.hcmut.bookstore.repository.CustomerRepository;
import io.javalin.http.Context;

public class AuthController {
    public static void login(Context ctx) {
        var credential = ctx.bodyAsClass(CustomerCredential.class);
        var customerRepo = new CustomerRepository();
        var customer = customerRepo.getCustomerByCredential(credential);
        if (customer == null) {
            ctx.result("Invalid credential");
            return;
        }
        ctx.json(customer);
    }
}
