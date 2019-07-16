package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.AttachmentDAO;
import com.itechart.contactsList.utility.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttachmentDAOImpl implements AttachmentDAO {

    private PreparedStatement createPs = null;
    private PreparedStatement getLastIdPs = null;
    private PreparedStatement getFileNamePs = null;
    private PreparedStatement deletePs = null;
    private PreparedStatement getUserIdPs = null;

    @Override
    public long create(String fileName, long userId) {
        Connection connection = null;
        long lastId = 0L;
        try {
            Connector connector = new Connector();
            connection = connector.getConnection();
            if (createPs == null) {
                createPs = connection.prepareStatement("insert into attachment (file_name, userId) values (?,?);");
            }
            createPs.setString(1, fileName);
            createPs.setLong(2, userId);
            createPs.executeUpdate();
            if (getLastIdPs == null) {
                getLastIdPs = connection.prepareStatement("select last_insert_id();");
            }
            ResultSet rs = getLastIdPs.executeQuery();
            if (rs.next()) {
                lastId = rs.getLong("LAST_INSERT_ID()");
            }
        } catch (SQLException e) {
            System.err.println("Error in AttachmentDAO create");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error in AttachmentDAO create");
                e.printStackTrace();
            }
        }
        return lastId;
    }

    @Override
    public String getFileName(long id) {
        Connection connection = null;
        String fileName = null;
        try {
            Connector connector = new Connector();
            connection = connector.getConnection();
            if (getFileNamePs == null) {
                getFileNamePs = connection.prepareStatement("select file_name from attachment where id = ? and deleteDate" +
                        " is null;");
            }
            getFileNamePs.setLong(1, id);
            ResultSet rs = getFileNamePs.executeQuery();
            if (rs.next()) {
                fileName = rs.getString("file_name");
            }
        } catch (SQLException e) {
            System.err.println("Error in AttachmentDAO getFileName");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error in AttachmentDAO getFileName");
                e.printStackTrace();
            }
        }
        return fileName;
    }

    @Override
    public void delete(long id) {
        Connection connection = null;
        try {
            Connector connector = new Connector();
            connection = connector.getConnection();
            if (deletePs == null) {
                deletePs = connection.prepareStatement("update attachment set deleteDate = curdate() where id = ?;");
            }
            deletePs.setLong(1, id);
            deletePs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in AttachmentDAO delete");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error in AttachmentDAO delete");
                e.printStackTrace();
            }
        }
    }

    @Override
    public long getUserId(long id) {
        Connection connection = null;
        long userId = 0L;
        try {
            Connector connector = new Connector();
            connection = connector.getConnection();
            if (getUserIdPs == null) {
                getUserIdPs = connection.prepareStatement("select userId from attachment where id = ? and deleteDate" +
                        " is null;");
            }
            getUserIdPs.setLong(1, id);
            ResultSet rs = getUserIdPs.executeQuery();
            if (rs.next()) {
                userId = rs.getLong("userId");
            }
        } catch (SQLException e) {
            System.err.println("Error in AttachmentDAO getUserId");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error in AttachmentDAO getUserId");
                e.printStackTrace();
            }
        }
        return userId;
    }
}
