package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.Book;
import edu.hcmut.bookstore.business.Customer;
import edu.hcmut.bookstore.business.CustomerCredential;
import edu.hcmut.bookstore.business.OrderInfo;
import edu.hcmut.bookstore.db.DbManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerRepository {
    public CustomerRepository() throws Exception{
    }

    private String hashPassword(String password) {
        return password;
    }

    /** Find the customer whose id is 'id'.
     * @param id id used to find the customer.
     * @return Customer object representing the customer, or null if there does not exist any
     * customer whose id is 'id'.
     * */
    public Customer getCustomerById(long id) throws Exception {
        var conn = DbManager.getMySqlDataSrc().getConnection();

        var query = "select id, username, customer_name, email, phone_number " +
                "from customer where id = ?";
        var userLookup = conn.prepareStatement(query);
        userLookup.setLong(1, id);

        var resultSet = userLookup.executeQuery();
        if (!resultSet.next()) {
            return null;
        }

        var customer = new Customer(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("customer_name"),
                resultSet.getString("email"),
                resultSet.getString("phone_number")
        );

        return customer;
    }

    public Customer getCustomerByCredential(CustomerCredential credential) throws Exception {
        var dataSrc = DbManager.getMySqlDataSrc();

        var conn = dataSrc.getConnection();
        var query = "select id, username, customer_name, email, phone_number " +
                        "from customer " +
                        "where username = '" + credential.getUsername() + "' and passwd = '" + hashPassword(credential.getPassword()) + "';";
        var userLookup = conn.prepareStatement(query);

        var resultSet = userLookup.executeQuery();
        if (!resultSet.next()) {
            return null;
        }

        var customer = new Customer(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getString("customer_name"),
                resultSet.getString("email"),
                resultSet.getString("phone_number")
        );

        return customer;
    }

    /** Handle the logic of creating new order when the user confirms the order.
     * A new empty cart is also created for the user.
     *
     * @param customer represents the customer.
     * @param orderInfo represents the order's information.
     * */
    public void saveOrder(Customer customer, OrderInfo orderInfo) throws Exception {
        var conn = DbManager.getMySqlDataSrc().getConnection();
        var _getCartIdQuery = "select id from cart where customer_id = ?";
        var getCartIdQuery = conn.prepareStatement(_getCartIdQuery);
        getCartIdQuery.setLong(1, customer.getId());

        var resultSet = getCartIdQuery.executeQuery();
        if (!resultSet.next()) {
            return;
        }

        conn.setAutoCommit(false);
        int cartId = resultSet.getInt(1);
        try {
            var addOrderQuery = conn.prepareStatement("insert into cust_order (customer_id, payment_method, user_address, cart_id, phone_number) " +
                    "values (?, ?, ?, ?, ?);");

            addOrderQuery.setLong(1, customer.getId());
            addOrderQuery.setString(2, orderInfo.getPaymentMethod());
            addOrderQuery.setString(3, orderInfo.getAddress());
            addOrderQuery.setInt(4, cartId);
            addOrderQuery.setString(5, orderInfo.getPhoneNumber());

            var newCartQuery = conn.prepareStatement("insert into cart (customer_id) values (?)", Statement.RETURN_GENERATED_KEYS);
            newCartQuery.setLong(1, customer.getId());
            int affectedRowCount = newCartQuery.executeUpdate();

            if (affectedRowCount == 0) {
                throw new SQLException("Failed to create new cart.");
            }

            try (ResultSet generatedKey = newCartQuery.getGeneratedKeys()) {
                if (!generatedKey.next()) {
                    throw new SQLException("Failed to create new cart.");
                }

                var updateUserCartQuery = conn.prepareStatement("update customer set cart_id = ? where id = ?");
                updateUserCartQuery.setLong(1, generatedKey.getInt(1));
                updateUserCartQuery.setLong(2, customer.getId());
                affectedRowCount = updateUserCartQuery.executeUpdate();

                if (affectedRowCount != 1) {
                    throw new SQLException("Failed to update cart id for user whose id is " + customer.getId());
                }
            }

            addOrderQuery.executeUpdate();
            conn.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            conn.rollback();
        }
    }

    /** Add a new book to cart with a determined quantity.
     * @return true if the operation is successful, false otherwise.
     * */
    public boolean addBookToCart(long customerId, int bookId, int bookCount) throws Exception {
        var conn = DbManager.getMySqlDataSrc().getConnection();
        conn.setAutoCommit(false);

        // Find user's cart id
        var getCartIdQuery = conn.prepareStatement("select id from cart where customer_id = ?");
        getCartIdQuery.setLong(1, customerId);
        var res = getCartIdQuery.executeQuery();

        if (!res.next()) {
            return false;
        }

        var cartId = res.getInt("id");

        var addNewBookStmt = conn.prepareStatement("insert into cart_item (cart_id, book_id, quantity) values (?, ?, ?)");
        addNewBookStmt.setInt(1, cartId);
        addNewBookStmt.setInt(2, bookId);
        addNewBookStmt.setInt(3, bookCount);
        try {
            int rowCount = addNewBookStmt.executeUpdate();
        } catch (SQLException exp) {
            // If the type of book is already in the cart
            var updateQuantity = conn.prepareStatement("update cart_item set quantity = quantity + ? where cart_id = ? and book_id = ?");
            int rowAffected = updateQuantity.executeUpdate();
            if (rowAffected != 1) {
                conn.rollback();
                return false;
            }
        }

        conn.commit();
        return true;
    }
}
