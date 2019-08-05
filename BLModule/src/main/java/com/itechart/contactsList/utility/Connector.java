package com.itechart.contactsList.utility;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class Connector {

    private Connection connection = null;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Context initContext = new InitialContext();
                DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/Users");
                connection = ds.getConnection();
            } catch (Exception e) {
                System.err.println("Error in Connector");
                e.printStackTrace();
            }
        }
        return connection;
    }
}