package service.jdbc;


import db.connection.DBConnection;
import db.connection.PropertyInf;
import entity.User;
import enums.СhoiceProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;


public class ServiceUser {

    private PropertyInf propertyInf = new PropertyInf();
    private final String insertUser = propertyInf.getProperties(СhoiceProperties.SQL).getProperty("INSERT_USER");

    public void addUserInDB(List<User> users) {

        try (Connection connection = DBConnection.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement statement = connection.prepareStatement(insertUser)) {

                int k = 1;
                int n = 0;


                for (int i = 0; i < users.size(); i++) {
                    String[] s = new String[]{users.get(i).getTelephoneNumber(), users.get(i).getAddress(), users.get(i).getSalary(), users.get(i).getFio()};
                    for (String q : s) {
                        statement.setString(k++, q);
                    }
                     k = 1;

                    statement.addBatch();
                    n++;

                    if (n % 100 == 0 || n == users.size()) {
                    statement.executeBatch(); // Execute every 100 items.
                    }

                }
                connection.commit();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
