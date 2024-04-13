package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.Customer;
import edu.hcmut.bookstore.business.CustomerCredential;
import edu.hcmut.bookstore.db.DbManager;

import javax.sql.DataSource;
import java.sql.Connection;

public class CustomerRepository {
    Connection conn;
    public CustomerRepository() throws Exception{
        this.conn = DbManager.getMySqlDataSrc().getConnection();
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
        var query = "select id, username, customer_name, email, phone_number " +
                "from customer where id = ?";
        var userLookup = this.conn.prepareStatement(query);
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

    public Customer getCustomerByCredential(CustomerCredential credential) {
        var dataSrc = DbManager.getMySqlDataSrc();

        try (var conn = dataSrc.getConnection()) {
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
        } catch (Exception exception) {
            System.out.println(exception);
            return null;
        }
    }
}
