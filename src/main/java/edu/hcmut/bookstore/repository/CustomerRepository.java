package edu.hcmut.bookstore.repository;

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
            var addOrderQuery = conn.prepareStatement("insert into cust_order (customer_id, payment_method, user_address, cart_id, phone_number)" +
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
}
