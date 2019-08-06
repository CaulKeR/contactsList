package com.itechart.contactsList.utility;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class Connector {

    private static final Logger log = Logger.getLogger(Connector.class);
    private Connection connection = null;

    public Connection getConnection() {
        if (connection == null) {
            try {
                Context initContext = new InitialContext();
                DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/Users");
                connection = ds.getConnection();
            } catch (Exception e) {
                log.error("Error in Connector");
                log.error(e);
            }
        }
        return connection;
    }
}