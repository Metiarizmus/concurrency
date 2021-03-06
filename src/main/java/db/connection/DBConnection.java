package db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import enums.–°hoiceProperties;

public class DBConnection {

    private static final PropertyInf inf = new PropertyInf();
    private static final String URL;
    private static final String LOGIN;
    private static final String PASSWORD;

    static {
        URL = inf.getProperties(–°hoiceProperties.CONNECTION).getProperty("URL");
        LOGIN = inf.getProperties(–°hoiceProperties.CONNECTION).getProperty("LOGIN");
        PASSWORD = inf.getProperties(–°hoiceProperties.CONNECTION).getProperty("PASSWORD");
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(ex);
        }

    }




}