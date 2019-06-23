package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dto.AddressDTO;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.dao.ContactDAO;
import com.itechart.contactsList.service.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

    private PreparedStatement getAllPs = null;
    private PreparedStatement getMainContactsInfoPs = null;
    private PreparedStatement createPs = null;
    private PreparedStatement updatePs = null;

    @Override
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
                        rs.getString("сurrent_workplace"), rs.getDate("deleteDate"));
                contactsList.add(contact);
            }
        } catch (SQLException e) {
            System.err.println("Error in DAO getAll");
            e.printStackTrace();
        }
        return contactsList;
    }

    @Override
    public List<ContactDTO> getMainContactsInfo() {
        List<ContactDTO> contactsList = new ArrayList<>();
        try {
            Connection connection = Connector.getConnection();
            if (getMainContactsInfoPs == null) {
                getMainContactsInfoPs = connection.prepareStatement("select c.id, c.first_name, c.surname, c.patronymic," +
                        " c.birth_date, a.country, a.locality, a.street, a.house, a.apartment, a.postcode, " +
                        "c.сurrent_workplace from contact c, address a where c.address_id = a.id and c.deleteDate is null;");
            }
            ResultSet rs = getMainContactsInfoPs.executeQuery();
            while(rs.next()) {
                ContactDTO contact = new ContactDTO(rs.getLong("id"), rs.getString("first_name"),
                        rs.getString("surname"), rs.getString("patronymic"),
                        rs.getDate("birth_date"), rs.getString("сurrent_workplace"));
                AddressDTO address = new AddressDTO(rs.getString("country"),
                        rs.getString("locality"), rs.getString("street"),
                        rs.getString("house"), rs.getShort("apartment"),
                        rs.getString("postcode"));
                contact.setAddress(address);
                contactsList.add(contact);
            }
        } catch (SQLException e) {
            System.err.println("Error in DAO getMainContactsInfo");
            e.printStackTrace();
        }
        return contactsList;
    }

    public void create(ContactDTO contact) {
        try {
            Connection connection = Connector.getConnection();
            AddressDAOImpl addressDAO = new AddressDAOImpl();
            addressDAO.create(contact.getAddress());
            if (createPs == null) {
                createPs = connection.prepareStatement("insert into contact (first_name, surname, patronymic," +
                        "birth_date, sex, nationality, family_status, website, email, сurrent_workplace, address_id)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            }
            createPs.setString(1, contact.getFirstName());
            createPs.setString(2, contact.getSurname());
            createPs.setString(3, contact.getPatronymic());
            createPs.setDate(4, contact.getBirthDate());
            createPs.setString(5, contact.getSex());
            createPs.setString(6, contact.getNationality());
            createPs.setString(7, contact.getFamilyStatus());
            createPs.setString(8, contact.getWebsite());
            createPs.setString(9, contact.getEmail());
            createPs.setString(10, contact.getCurrentWorkplace());
            createPs.setLong(11, contact.getAddress().getId());
            createPs.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error in DAO create");
            e.printStackTrace();
        }
    }

    @Override
    public void update(ContactDTO contact) {
        try {
            Connection connection = Connector.getConnection();
            if (updatePs == null) {
                updatePs = connection.prepareStatement("update contact set first_name = ?, surname = ?, patronymic = ?," +
                        "birth_date = ?, sex = ?, nationality = ?, family_status = ?, website = ?, email = ?," +
                        " сurrent_workplace = ?, address_id = ? where id = ?;");
            }
            updatePs.setString(1, contact.getFirstName());
            updatePs.setString(2, contact.getSurname());
            updatePs.setString(3, contact.getPatronymic());
            updatePs.setDate(4, contact.getBirthDate());
            updatePs.setString(5, contact.getSex());
            updatePs.setString(6, contact.getNationality());
            updatePs.setString(7, contact.getFamilyStatus());
            updatePs.setString(8, contact.getWebsite());
            updatePs.setString(9, contact.getEmail());
            updatePs.setString(10, contact.getCurrentWorkplace());
            updatePs.setLong(11, contact.getAddress().getId());
            updatePs.setLong(12, contact.getId());
            updatePs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in DAO update");
            e.printStackTrace();
        }
    }

}
