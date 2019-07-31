package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.AttachmentDAO;
import com.itechart.contactsList.dto.AttachmentDTO;
import com.itechart.contactsList.utility.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttachmentDAOImpl implements AttachmentDAO {

    @Override
    public long create(String fileName, long userId) {
        long lastId = 0L;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement createPs = connection.prepareStatement("insert into attachment (file_name, userId)" +
                    " values (?,?);");
            createPs.setString(1, fileName);
            createPs.setLong(2, userId);
            createPs.executeUpdate();
            PreparedStatement getLastIdPs = connection.prepareStatement("select last_insert_id();");
            ResultSet rs = getLastIdPs.executeQuery();
            if (rs.next()) {
                lastId = rs.getLong("LAST_INSERT_ID()");
            }
        } catch (SQLException e) {
            System.err.println("Error in AttachmentDAO create");
            e.printStackTrace();
        }
        return lastId;
    }

    @Override
    public String getFileName(long id) {
        String fileName = null;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getFileNamePs = connection.prepareStatement("select file_name from attachment where " +
                    "id = ? and deleteDate is null;");
            getFileNamePs.setLong(1, id);
            ResultSet rs = getFileNamePs.executeQuery();
            if (rs.next()) {
                fileName = rs.getString("file_name");
            }
        } catch (SQLException e) {
            System.err.println("Error in AttachmentDAO getFileName");
            e.printStackTrace();
        }
        return fileName;
    }

    @Override
    public void delete(long id) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement deletePs = connection.prepareStatement("update attachment set deleteDate = curdate()" +
                    " where id = ?;");
            deletePs.setLong(1, id);
            deletePs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in AttachmentDAO delete");
            e.printStackTrace();
        }
    }

    @Override
    public long getUserId(long id) {
        long userId = 0L;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getUserIdPs = connection.prepareStatement("select userId from attachment where id = ?" +
                    " and deleteDate is null;");
            getUserIdPs.setLong(1, id);
            ResultSet rs = getUserIdPs.executeQuery();
            if (rs.next()) {
                userId = rs.getLong("userId");
            }
        } catch (SQLException e) {
            System.err.println("Error in AttachmentDAO getUserId");
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public AttachmentDTO getAttachmentById(long id) {
        AttachmentDTO attachment = new AttachmentDTO();
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getByIdPs = connection.prepareStatement("select * from attachment where id = ? and" +
                    " deleteDate is null;");
            getByIdPs.setLong(1, id);
            ResultSet rs = getByIdPs.executeQuery();
            rs.next();
            attachment = new AttachmentDTO(rs.getLong("id"), rs.getString("file_name"),
                    rs.getLong("userId"), rs.getString("comment"));
        } catch (SQLException e) {
            System.err.println("Error in DAO getAttachmentById");
            e.printStackTrace();
        }
        return attachment;
    }

    @Override
    public void update(AttachmentDTO attachment) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement updatePs = connection.prepareStatement("update attachment set file_name = ?, comment = ? " +
                        "where id = ?;");
            updatePs.setString(1, attachment.getTitle() + "." +attachment.getType());
            updatePs.setString(2, attachment.getComment());
            updatePs.setLong(3, attachment.getId());
            updatePs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in DAO update");
            e.printStackTrace();
        }
    }
}
