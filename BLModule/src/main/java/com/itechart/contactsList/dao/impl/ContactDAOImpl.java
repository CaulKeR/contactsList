package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dto.AddressDTO;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.dao.ContactDAO;
import com.itechart.contactsList.utility.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

    private PreparedStatement getMainContactsInfoPs = null;
    private PreparedStatement createPs = null;
    private PreparedStatement updatePs = null;
    private PreparedStatement getContactById = null;
    private PreparedStatement deletePs = null;
    private PreparedStatement searchPs = null;

    @Override
    public List<ContactDTO> getMainContactsInfo() {
        List<ContactDTO> contactsList = new ArrayList<>();
        try (Connection connection = new Connector().getConnection()) {
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

    @Override
    public void create(ContactDTO contact) {
        try (Connection connection = new Connector().getConnection()) {
            AddressDAOImpl addressDAO = new AddressDAOImpl();
            long addressId = addressDAO.create(contact.getAddress());
            if (createPs == null) {
                createPs = connection.prepareStatement("insert into contact (first_name, surname, patronymic," +
                        "birth_date, sex, nationality, family_status, website, email, сurrent_workplace, address_id)" +
                        " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            }
            psSetter(createPs, contact, addressId);
            createPs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in DAO create");
            e.printStackTrace();
        }
    }

    @Override
    public ContactDTO getContactById(long id) {
        ContactDTO contact = new ContactDTO();
        try (Connection connection = new Connector().getConnection()) {
            if (getContactById == null) {
                getContactById = connection.prepareStatement("select * from contact where id = ? and deleteDate is null;");
            }
            getContactById.setLong(1, id);
            ResultSet rs = getContactById.executeQuery();
            rs.next();
            contact = new ContactDTO(rs.getLong("id"), rs.getString("first_name"),
                    rs.getString("surname"), rs.getString("patronymic"),
                    rs.getDate("birth_date"), rs.getString("sex"),
                    rs.getString("nationality"), rs.getString("family_status"),
                    rs.getString("website"), rs.getString("email"),
                    rs.getString("сurrent_workplace"), rs.getDate("deleteDate"));
            AddressDAOImpl addressDAO = new AddressDAOImpl();
            contact.setAddress(addressDAO.getAddressById(rs.getLong("address_id")));
        } catch (SQLException e) {
            System.err.println("Error in DAO getContactById");
            e.printStackTrace();
        }
        return contact;
    }

    @Override
    public void update(ContactDTO contact) {
        try (Connection connection = new Connector().getConnection()) {
            AddressDAOImpl addressDAO = new AddressDAOImpl();
            AddressDTO address = contact.getAddress();
            address.setId(addressDAO.getAddressIdByContactId(contact.getId()));
            addressDAO.update(address);
            if (updatePs == null) {
                updatePs = connection.prepareStatement("update contact set first_name = ?, surname = ?, patronymic = ?," +
                        "birth_date = ?, sex = ?, nationality = ?, family_status = ?, website = ?, email = ?," +
                        " сurrent_workplace = ?, address_id = ? where id = ?;");
            }
            psSetter(createPs, contact, address.getId());
            updatePs.setLong(12, contact.getId());
            updatePs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in DAO update");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = new Connector().getConnection()) {
            if (deletePs == null) {
                deletePs = connection.prepareStatement("update contact set deleteDate = curdate() where id = ?;");
            }
            deletePs.setLong(1, id);
            deletePs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in DAO delete");
            e.printStackTrace();
        }
    }

    @Override
    public List<ContactDTO> searchContacts(ContactDTO contact) {
        return null;
    }

//    @Override
//    public List<ContactDTO> searchContacts(ContactDTO contactDTO) {
//        List<ContactDTO> contactsList = new ArrayList<>();
//        try (Connection connection = new Connector().getConnection()) {
//            if (searchPs == null) {
//                searchPs = connection.prepareStatement("select c.id, c.first_name, c.surname, c.patronymic," +
//                        " c.birth_date, a.country, a.locality, a.street, a.house, a.apartment, a.postcode, " +
//                        "c.сurrent_workplace from contact c, address a where c.address_id = a.id and c.deleteDate is null;");
//            }
//            ResultSet rs = searchPs.executeQuery();
//            while(rs.next()) {
//                ContactDTO contact = new ContactDTO(rs.getLong("id"), rs.getString("first_name"),
//                        rs.getString("surname"), rs.getString("patronymic"),
//                        rs.getDate("birth_date"), rs.getString("сurrent_workplace"));
//                AddressDTO address = new AddressDTO(rs.getString("country"),
//                        rs.getString("locality"), rs.getString("street"),
//                        rs.getString("house"), rs.getShort("apartment"),
//                        rs.getString("postcode"));
//                contact.setAddress(address);
//                contactsList.add(contact);
//            }
//        } catch (SQLException e) {
//            System.err.println("Error in DAO getMainContactsInfo");
//            e.printStackTrace();
//        }
//        return contactsList;
//    }

    private void psSetter(PreparedStatement ps, ContactDTO contact, long addressId) {
        try {
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getSurname());
            ps.setString(3, contact.getPatronymic());
            ps.setDate(4, contact.getBirthDate());
            ps.setString(5, contact.getSex());
            ps.setString(6, contact.getNationality());
            ps.setString(7, contact.getFamilyStatus());
            ps.setString(8, contact.getWebsite());
            ps.setString(9, contact.getEmail());
            ps.setString(10, contact.getCurrentWorkplace());
            ps.setLong(11, addressId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
