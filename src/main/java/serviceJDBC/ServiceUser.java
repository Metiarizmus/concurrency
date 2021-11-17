package serviceJDBC;

import DBConnection.*;
import entity.User;
import entity.СhoiceProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class ServiceUser {

    private PropertyInf propertyInf = new PropertyInf();

    public void addUserInDB(User user) {

        try (Connection connection = DBConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(propertyInf.getProperties(СhoiceProperties.SQL).getProperty("INSERT_USER"))) {

                String[] s = new String[]{user.getTelephoneNumber(), user.getAddress(), user.getSalary(), user.getFio()};

                int k = 1;
                for (String value : s) {
                    statement.setString(k++, value);
                }

                statement.addBatch();
                statement.executeBatch();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
