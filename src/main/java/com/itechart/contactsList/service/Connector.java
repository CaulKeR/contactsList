package com.itechart.contactsList.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.Properties;

public class Connector {

    private static Connection connection = null;

    public static Connection getConnection(){

        if (connection == null) {
            try {
                Properties properties = new Properties();
                properties.load(Objects.requireNonNull(Connector.class.getClassLoader()
                        .getResourceAsStream("\\connection.properties")));
                Class.forName(properties.getProperty("driver")).getDeclaredConstructor();
                connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
                        properties.getProperty("password"));
            } catch (Exception e) {
                System.err.println("Error in Connector");
            }
        }
        return connection;
    }

}
