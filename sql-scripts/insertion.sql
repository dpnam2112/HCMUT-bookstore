INSERT INTO category (id, name) VALUES
(1, 'Fiction'),
(2, 'Non-fiction'),
(3, 'Science Fiction'),
(4, 'Fantasy'),
(5, 'Mystery'),
(6, 'Thriller'),
(7, 'Romance'),
(8, 'Biography'),
(9, 'History'),
(10, 'Self-Help'),
(11, 'Cookbooks'),
(12, 'Poetry');

INSERT INTO author (id, author_name) VALUES
	(1, 'Paulo Coelho'),
	(2, 'Haruki Murakami'),
    (3, 'J K Rowling'),
    (4, 'Raymond Murphy'),
    (5, 'John Perkins'),
    (6, 'Mark Twain'),
    (7, 'Lewis Carrol');

INSERT INTO publisher (id, publisher_name) VALUES
	(1, 'NXB Tre'),
    (2, 'NXB Kim Dong'),
    (3, 'NXB Hoi Nha Van');

INSERT INTO book (author_id, publisher_id, title, book_cover, price) VALUES
    (1, 1, 'Nhà Giả Kim', 'https://cdn0.fahasa.com/media/catalog/product/i/m/image_195509_1_36793.jpg', 61000),
    (2, 2, 'Rừng Na Uy', 'https://cdn0.fahasa.com/media/catalog/product/i/m/image_240307.jpg', 117000),
    (3, 3, 'Harry Potter và Hòn Đá Phù Thủy', 'https://cdn0.fahasa.com/media/catalog/product/8/9/8934974179672.jpg', 120000),
    (4, 2, 'English grammar in use', 'https://cdn0.fahasa.com/media/catalog/product/9/7/9781108430425.jpg', 169100),
    (5, 1, 'Cuộc Thú Tội Của Một Sát Thủ Kinh Tế', 'https://m.media-amazon.com/images/I/A1BtZbzG3qL._SL1500_.jpg', 225000),
    (2, 2, 'Kafka bên bờ biển', 'https://cdn0.fahasa.com/media/catalog/product/i/m/image_195509_1_32831.jpg', 123000),
    (3, 3, 'Harry Potter và Chiếc Cốc Lửa', 'https://cdn0.fahasa.com/media/catalog/product/8/9/8934974180555.jpg', 248000),
    (6, 1, 'Cuộc Phiêu Lưu Của Tom Sawyer', 'https://cdn0.fahasa.com/media/catalog/product/9/7/9786043495942.jpg', 60000),
    (7, 2, 'Alice ở xứ sở Thần Tiên', 'alice_cover.jpg', 80000);

INSERT INTO book_category (book_id, category_id) VALUES
(1, 1),  -- Nhà Giả Kim - Fiction
(2, 1),  -- Rừng Na Uy - Fiction
(3, 4),  -- Harry Potter và Hòn Đá Phù Thủy - Fantasy
(4, 8),  -- English grammar in use - Biography
(5, 11), -- Cuộc Thú Tội Của Một Sát Thủ Kinh Tế - Self-Help
(6, 1),  -- Kafka bên bờ biển - Fiction
(7, 4),  -- Harry Potter và Chiếc Cốc Lửa - Fantasy
(8, 1),  -- Cuộc Phiêu Lưu Của Tom Sawyer - Fiction
(9, 4),  -- Alice ở xứ sở Thần Tiên - Fantasy
(1, 3),  -- Nhà Giả Kim - Science Fiction
(3, 1),  -- Harry Potter và Hòn Đá Phù Thủy - Fiction
(4, 9),  -- English grammar in use - History
(5, 2),  -- Cuộc Thú Tội Của Một Sát Thủ Kinh Tế - Non-Fiction
(6, 3),  -- Kafka bên bờ biển - Science Fiction
(7, 1),  -- Harry Potter và Chiếc Cốc Lửa - Fiction
(8, 1),  -- Cuộc Phiêu Lưu Của Tom Sawyer - Fiction
(9, 3),  -- Alice ở xứ sở Thần Tiên - Science Fiction
(2, 2),  -- Rừng Na Uy - Non-Fiction
(2, 3),  -- Rừng Na Uy - Science Fiction
(3, 2);  -- Harry Potter và Hòn Đá Phù Thủy - Non-Fiction
