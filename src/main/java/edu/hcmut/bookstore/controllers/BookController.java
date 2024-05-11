package edu.hcmut.bookstore.controllers;

import edu.hcmut.bookstore.business.Author;
import edu.hcmut.bookstore.business.Book;
import edu.hcmut.bookstore.business.BookDetail;
import edu.hcmut.bookstore.business.Publisher;
import edu.hcmut.bookstore.repository.BookRepository;
import edu.hcmut.bookstore.repository.SessionRepository;
import io.javalin.http.Context;
import java.util.ArrayList;

public class BookController {
    public static void getBook(Context ctx) throws Exception {
        var bookRepo = new BookRepository();
        ctx.json(bookRepo.getBookById(1));
    }

    public static void addBook(Context ctx) throws Exception {
        var bookRepo = new BookRepository();
        var bookPayload = ctx.bodyAsClass(Book.class);
        if (bookPayload.getId() != -1) {
            ctx.status(400);
            return;
        }
        bookRepo.addBook(bookPayload);
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
        if (categoriesStr != null) {
            for (var catg : categoriesStr) {
                catgIds.add(Integer.valueOf(catg));
            }
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

    public static void getBooksFromUserCart(Context ctx) throws Exception {
        var bookRepo = new BookRepository();
        var sessionRepo = new SessionRepository();
        var customer = sessionRepo.getCustomer(ctx.cookie("session-id"));
        if (customer == null) {
            System.out.println(ctx.cookie("session-id"));
            ctx.json(new ArrayList<Book>());
            return;
        }
        var cartItems = bookRepo.getCartItemsOfUser(1L);
        ctx.json(cartItems);
    }

    public static void newAuthor(Context ctx) throws Exception {
        var bookRepo = new BookRepository();
        var authorPayload = ctx.bodyAsClass(Author.class);
        if (!bookRepo.addAuthor(authorPayload)) {
            ctx.status(500);
        }
    }

    public static void newPublisher(Context ctx) throws Exception {
        var bookRepo = new BookRepository();
        var publisherPayload = ctx.bodyAsClass(Publisher.class);
        if (!bookRepo.addPublisher(publisherPayload)) {
            ctx.status(500);
        }
    }

    public static void updateBook(Context ctx) throws Exception {
        var book = ctx.bodyAsClass(Book.class);

        if (book.getId() < 1) {
            ctx.status(400);
            return;
        }

        var bookRepo = new BookRepository();
        if (!bookRepo.updateBook(book)) {
            ctx.status(500);
            return;
        }
    }

    public static void getBookCategories(Context ctx) throws Exception {
        var bookRepo = new BookRepository();
        var categories = bookRepo.getCategories();
        ctx.json(categories);
    }

    public static void getBookDetail(Context ctx) throws Exception {
        // path param: bookId
        var bookRepo = new BookRepository();
        var book = bookRepo.getBookById(Integer.parseInt(ctx.pathParam("bookId")));
        var publisher = bookRepo.getPublisher(book.getId());
        var author = bookRepo.getAuthor(book.getId());
        ctx.json(new BookDetail(book, publisher, author));
    }


}