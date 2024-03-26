package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.repository.BookRepository;
import io.javalin.http.Context;

public class BookController {
    public static void getBook(Context ctx) throws Exception{
        var bookRepo = new BookRepository();
        ctx.json(bookRepo.getBookById(1));
    }
}
