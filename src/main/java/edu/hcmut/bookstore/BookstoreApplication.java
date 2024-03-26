package edu.hcmut.bookstore;

import edu.hcmut.bookstore.controllers.AuthController;
import edu.hcmut.bookstore.controllers.BookController;
import edu.hcmut.bookstore.db.DbManager;
import io.javalin.Javalin;

public class BookstoreApplication {

	public static void main(String[] args) throws Exception {
		DbManager.getIgniteNode();
		var app = Javalin.create(cfg -> {
				}).get("/test", BookController::getBook)
						.post("/login", AuthController::login);

		app.start(8080);
	}
}