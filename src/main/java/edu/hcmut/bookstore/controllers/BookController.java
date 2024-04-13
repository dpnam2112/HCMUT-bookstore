package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.repository.BookRepository;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.Arrays;

public class BookController {
    public static void getBook(Context ctx) throws Exception {
        var bookRepo = new BookRepository();
        ctx.json(bookRepo.getBookById(1));
    }

    public static void getBooks(Context ctx) throws Exception {
        var bookRepo = new BookRepository();

        var pageQueryParam = ctx.queryParam("page");        // page number
        var perPageQueryParam = ctx.queryParam("per_page"); // number of books per page
        var nameQueryParam = ctx.queryParam("name");        // query string used to find books whose names match the query string
        var catgQueryParam = ctx.queryParam("categories");  // categories of books

        Long pageNumber = (pageQueryParam == null) ? null : Long.parseLong(pageQueryParam);
        Integer perPage = (perPageQueryParam == null) ? null : Integer.parseInt(perPageQueryParam);
        String bookName = (nameQueryParam == null) ? "" : nameQueryParam;

        // parse the category ids from query parameter
        String[] categoriesStr = (catgQueryParam == null) ? null : catgQueryParam.split(",");
        var catgIds = new ArrayList<Integer>();
        for (var catg : categoriesStr) {
            catgIds.add(Integer.valueOf(catg));
        }

        if (pageNumber == null || perPage == null) {
            ctx.status(400);
            ctx.result("Missing query parameter: 'page' and 'per_page'.");
            return;
        }

        // Calculate the starting index from the page number
        Long start = (pageNumber - 1) * perPage;
        var books = bookRepo.queryBooks(bookName, catgIds, start, perPage);
        ctx.json(books);
    }
}
