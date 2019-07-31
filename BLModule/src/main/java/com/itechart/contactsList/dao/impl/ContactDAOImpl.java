package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dto.AddressDTO;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.dao.ContactDAO;
import com.itechart.contactsList.utility.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAOImpl implements ContactDAO {

    @Override
    public List<ContactDTO> getMainContactsInfo(int count, int page) {
        List<ContactDTO> contactsList = new ArrayList<>();
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getMainContactsInfoPs = connection.prepareStatement("select c.id, c.first_name," +
                    " c.surname, c.patronymic, c.birth_date, a.country, a.locality, a.street, a.house, a.apartment," +
                    " a.postcode, c.сurrent_workplace from contact c, address a where c.address_id = a.id and" +
                    " c.deleteDate is null limit ?, ?;");
            getMainContactsInfoPs.setInt(1, (page - 1) * count);
            getMainContactsInfoPs.setInt(2, count);
            contactsList = getRs(getMainContactsInfoPs.executeQuery());
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
            PreparedStatement createPs = connection.prepareStatement("insert into contact (first_name, surname," +
                    " patronymic,birth_date, sex, nationality, family_status, website, email, сurrent_workplace," +
                    " address_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            createPs = setPs(createPs, contact, addressId);
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
            PreparedStatement getContactById = connection.prepareStatement("select * from contact where id = ? and" +
                    " deleteDate is null;");
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
            PreparedStatement updatePs = connection.prepareStatement("update contact set first_name = ?, surname = ?," +
                    " patronymic = ?, birth_date = ?, sex = ?, nationality = ?, family_status = ?, website = ?, " +
                    "email = ?, сurrent_workplace = ?, address_id = ? where id = ?;");
            updatePs = setPs(updatePs, contact, address.getId());
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
            PreparedStatement deletePs = connection.prepareStatement("update contact set deleteDate = curdate() " +
                    "where id = ?;");
            deletePs.setLong(1, id);
            deletePs.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in DAO delete");
            e.printStackTrace();
        }
    }

    @Override
    public List<ContactDTO> searchContacts(ContactDTO contact) {
        String searchRequest = "select c.id, c.first_name, c.surname, c.patronymic, c.birth_date, a.country, " +
                "a.locality, a.street, a.house, a.apartment, a.postcode, c.сurrent_workplace from contact c, " +
                "address a where c.address_id = a.id and c.deleteDate is null";
        if (contact.getFirstName() != null && !contact.getFirstName().equals("")) {
            searchRequest += " and first_name = \'" + contact.getFirstName() + "\'";
        }
        if (contact.getSurname() != null && !contact.getSurname().equals("")) {
            searchRequest += " and surname = \'" + contact.getSurname() + "\'";
        }
        if (contact.getPatronymic() != null && !contact.getPatronymic().equals("")) {
            searchRequest += " and patronymic = \'" + contact.getPatronymic() + "\'";
        }
        if (contact.getDate1() != null) {
            searchRequest += " and birth_date >= \'" + contact.getDate1() + "\'";
        }
        if (contact.getDate2() != null) {
            searchRequest += " and birth_date <= \'" + contact.getDate2() + "\'";
        }
        if (contact.getSex() != null && !contact.getSex().equals("")) {
            searchRequest += " and sex = \'" + contact.getSex() + "\'";
        }
        if (contact.getNationality() != null && !contact.getNationality().equals("")) {
            searchRequest += " and nationality = \'" + contact.getNationality() + "\'";
        }
        if (contact.getFamilyStatus() != null && !contact.getFamilyStatus().equals("")) {
            searchRequest += " and family_status = \'" + contact.getFamilyStatus() + "\'";
        }
        if (contact.getCurrentWorkplace() != null && !contact.getCurrentWorkplace().equals("")) {
            searchRequest += " and сurrent_workplace = \'" + contact.getCurrentWorkplace() + "\'";
        }
        searchRequest += " ;";
        System.out.println(searchRequest);
        List<ContactDTO> contactsList = new ArrayList<>();
        try (Connection connection = new Connector().getConnection()) {
            contactsList = getRs(connection.prepareStatement(searchRequest).executeQuery());
        } catch (SQLException e) {
            System.err.println("Error in DAO searchContacts");
            e.printStackTrace();
        }
        return contactsList;
    }

    @Override
    public String getEmailById(long id) {
        String email = null;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getEmailByIdPs = connection.prepareStatement("select email from contact where id = ? and" +
                    " deleteDate is null;");
            getEmailByIdPs.setLong(1, id);
            ResultSet rs = getEmailByIdPs.executeQuery();
            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            System.err.println("Error in DAO getEmailById");
            e.printStackTrace();
        }
        return email;
    }

    @Override
    public String getFirstNameByEmail(String email) {
        String firstName = null;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getFirstNameByEmailPs = connection.prepareStatement("select first_name from contact where " +
                    "email = ? and deleteDate is null;");
            getFirstNameByEmailPs.setString(1, email);
            ResultSet rs = getFirstNameByEmailPs.executeQuery();
            if (rs.next()) {
                firstName = rs.getString("first_name");
            }
        } catch (SQLException e) {
            System.err.println("Error in DAO getFirstNameByEmail");
            e.printStackTrace();
        }
        return firstName;
    }

    @Override
    public List<ContactDTO> getContactsByBirthDate(Date birthDate) {
        List<ContactDTO> contacts = null;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getContactsByBirthDatePs = connection.prepareStatement("select id, first_name, surname," +
                    " patronymic, email from contact where birth_date = ? and deleteDate is null;");
            getContactsByBirthDatePs.setDate(1, birthDate);
            ResultSet rs = getContactsByBirthDatePs.executeQuery();
            while (rs.next()) {
                ContactDTO contactDTO = new ContactDTO(rs.getLong("id"), rs.getString("first_name"),
                        rs.getString("surname"), rs.getString("patronymic"),
                        rs.getString("email"));
                contacts.add(contactDTO);
            }
        } catch (SQLException e) {
            System.err.println("Error in DAO getContactsByBirthDate");
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public int getCountOfContacts() {
        int count = 0;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getCountOfContactsPs = connection.prepareStatement("select count(*) from contact where" +
                    " deleteDate is null;");
            ResultSet rs = getCountOfContactsPs.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            System.err.println("Error in DAO getCountOfContacts");
            e.printStackTrace();
        }
        return count;
    }

    private PreparedStatement setPs(PreparedStatement ps, ContactDTO contact, long addressId) {
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
        return ps;
    }

    private List<ContactDTO> getRs(ResultSet rs) {
        List<ContactDTO> list = new ArrayList<>();
        try {
            while (rs.next()) {
                ContactDTO contactDTO = new ContactDTO(rs.getLong("id"), rs.getString("first_name"),
                        rs.getString("surname"), rs.getString("patronymic"),
                        rs.getDate("birth_date"), rs.getString("сurrent_workplace"));
                AddressDTO address = new AddressDTO(rs.getString("country"),
                        rs.getString("locality"), rs.getString("street"),
                        rs.getString("house"), rs.getShort("apartment"),
                        rs.getString("postcode"));
                contactDTO.setAddress(address);
                list.add(contactDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
