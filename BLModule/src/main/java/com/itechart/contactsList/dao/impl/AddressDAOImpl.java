package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.AddressDAO;
import com.itechart.contactsList.dto.AddressDTO;
import com.itechart.contactsList.utility.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAOImpl implements AddressDAO {

    @Override
    public long create(AddressDTO address) {
        long lastId = 0L;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement create = connection.prepareStatement("insert into address (country, locality, street," +
                    " house, apartment, postcode) values (?, ?, ?, ?, ?, ?);");
            psSetter(create, address);
            create.executeUpdate();
            PreparedStatement getLastId = connection.prepareStatement("select last_insert_id();");
            ResultSet rs = getLastId.executeQuery();
            rs.next();
            lastId = rs.getLong("LAST_INSERT_ID()");
        } catch (SQLException e) {
            System.err.println("Error in AddressDAO create");
            e.printStackTrace();
        }
        return lastId;
    }

    @Override
    public AddressDTO getAddressById(long id) {
        AddressDTO address = null;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getAddressById = connection.prepareStatement("select * from address where id = ?;");

            getAddressById.setLong(1, id);
            ResultSet rs = getAddressById.executeQuery();
            rs.next();
            address = new AddressDTO(rs.getString("country"), rs.getString("locality"),
                    rs.getString("street"), rs.getString("house"),
                    rs.getShort("apartment"), rs.getString("postcode"));
        } catch (SQLException e) {
            System.err.println("Error in DAO getAddressById");
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void update(AddressDTO address) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement update = connection.prepareStatement("update address set country = ?, locality = ?," +
                    " street = ?, house = ?, apartment = ?, postcode = ? where id = ?;");
            psSetter(update, address);
            update.setLong(7, address.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in AddressDAO update");
            e.printStackTrace();
        }
    }

    @Override
    public long getAddressIdByContactId(long id) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getAddressIdByContactId = connection.prepareStatement("select address.id from contact," +
                    " address where contact.id = ? and contact.address_id = address.id;");
            getAddressIdByContactId.setLong(1, id);
            ResultSet rs = getAddressIdByContactId.executeQuery();
            rs.next();
            return rs.getLong("id");
        } catch (SQLException e) {
            System.err.println("Error in DAO getAddressIdByContactId");
            e.printStackTrace();
        }
        return 0L;
    }

    private void psSetter(PreparedStatement ps, AddressDTO address) {
        try {
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getLocality());
            ps.setString(3, address.getStreet());
            ps.setString(4, address.getHouse());
            ps.setShort(5, address.getApartment());
            ps.setString(6, address.getPostcode());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}