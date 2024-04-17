package edu.hcmut.bookstore;

import edu.hcmut.bookstore.controllers.AuthController;
import edu.hcmut.bookstore.controllers.BookController;
import edu.hcmut.bookstore.db.DbManager;
import edu.hcmut.bookstore.repository.BookRepository;
import io.javalin.Javalin;

public class BookstoreApplication {
		public static void main(String[] args) throws Exception {
			DbManager.getIgniteNode();

			var app = Javalin.create(cfg -> {
					cfg.bundledPlugins.enableCors(cors -> {
				cors.addRule(it -> {
					it.allowHost("http://localhost:3000");
					it.allowCredentials = true;
				});
			});
		})
				.get("/test", BookController::getBook)
				.post("/api/login", AuthController::login)
				.get("/api/books", BookController::getBooks)
				.get("/api/cart", BookController::getBooksFromUserCart);


		app.start(8080);
	}

}
