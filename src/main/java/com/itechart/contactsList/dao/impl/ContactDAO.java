package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.MySQLContactDAO;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.service.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements MySQLContactDAO {

    private PreparedStatement getAllPs = null;

    public List<ContactDTO> getAll() {
        List<ContactDTO> contactsList = new ArrayList<>();
        try {
            Connection connection = Connector.getConnection();
            if (getAllPs == null) {
                getAllPs = connection.prepareStatement("select * from contact");
            }
            ResultSet rs = getAllPs.executeQuery();
            while(rs.next()) {
                ContactDTO contact = new ContactDTO(rs.getLong("id"), rs.getString("first_name"),
                        rs.getString("surname"), rs.getString("patronymic"),
                        rs.getDate("birth_date"), rs.getString("sex"),
                        rs.getString("nationality"), rs.getString("family_status"),
                        rs.getString("website"), rs.getString("email"),
                        rs.getString("сurrent_workplace"));
                contactsList.add(contact);
            }
        } catch (SQLException e) {
            System.err.println("Error in DAO");
        }
        return contactsList;
    }

}
