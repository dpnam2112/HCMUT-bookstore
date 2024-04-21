CREATE TABLE author(
    id INT PRIMARY KEY,
    author_name VARCHAR(20) NOT NULL
);

CREATE TABLE publisher (
    id INT PRIMARY KEY,
    publisher_name VARCHAR(20) NOT NULL
);

CREATE TABLE category (
    id INT PRIMARY KEY,
    name VARCHAR(20)
);

CREATE TABLE book(
    id INT PRIMARY KEY AUTO_INCREMENT,
    author_id INT,
    publisher_id INT,
    title VARCHAR(100) NOT NULL,
    book_cover VARCHAR(100),
    price DECIMAL(10,2) NOT NULL,
    remaining_quantity INT DEFAULT 100 NOT NULL,

    FOREIGN KEY (author_id) REFERENCES author (id),
    FOREIGN KEY (publisher_id) REFERENCES publisher (id)
);

CREATE TABLE customer(
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    customer_name VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    phone_number VARCHAR(10),
    passwd VARCHAR(256) NOT NULL
);

CREATE TABLE cart(
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

# relation
CREATE TABLE cart_item (
    cart_id INT,
    book_id INT,
    quantity INT,

    PRIMARY KEY (cart_id, book_id),
    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (cart_id) REFERENCES cart(id)
);

CREATE TABLE cust_order (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    date_created DATETIME DEFAULT NOW(),
    payment_method VARCHAR(50),
    user_address VARCHAR(100),
    phone_number VARCHAR(50),

    FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE TABLE book_category (
    book_id INT,
    category_id INT,

    FOREIGN KEY (book_id) REFERENCES book(id),
    FOREIGN KEY (category_id) REFERENCES category(id)
);

