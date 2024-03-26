package edu.hcmut.bookstore.repository;

import edu.hcmut.bookstore.business.Customer;
import edu.hcmut.bookstore.business.CustomerCredential;
import edu.hcmut.bookstore.db.DbManager;

public class CustomerRepository {

    public CustomerRepository() {
    }

    private String hashPassword(String password) {
        return password;
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
