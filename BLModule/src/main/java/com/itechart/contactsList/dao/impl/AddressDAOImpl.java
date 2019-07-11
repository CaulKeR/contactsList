package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.AddressDAO;
import com.itechart.contactsList.dto.AddressDTO;
import com.itechart.contactsList.utility.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAOImpl implements AddressDAO {

    private PreparedStatement create = null;
    private PreparedStatement getLastId = null;
    private PreparedStatement getAddressById = null;
    private PreparedStatement update = null;
    private PreparedStatement getAddressIdByContactId = null;

    @Override
    public long create(AddressDTO address) {
        long lastId = 0L;
        Connection connection = null;
        try {
            Connector connector = new Connector();
            connection = connector.getConnection();
            if (create == null) {
                create = connection.prepareStatement("insert into address (country, locality, street, house, apartment," +
                        "postcode) values (?, ?, ?, ?, ?, ?);");
            }
            create.setString(1, address.getCountry());
            create.setString(2, address.getLocality());
            create.setString(3, address.getStreet());
            create.setString(4, address.getHouse());
            create.setShort(5, address.getApartment());
            create.setString(6, address.getPostcode());
            create.executeUpdate();
            if (getLastId == null) {
                getLastId = connection.prepareStatement("select last_insert_id();");
            }
            ResultSet rs = getLastId.executeQuery();
            rs.next();
            lastId = rs.getLong("LAST_INSERT_ID()");
        } catch (SQLException e) {
            System.err.println("Error in AddressDAO create");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error in DAO create");
                e.printStackTrace();
            }
        }
        return lastId;
    }

    @Override
    public AddressDTO getAddressById(long id) {
        AddressDTO address = null;
        Connection connection = null;
        try {
            Connector connector = new Connector();
            connection = connector.getConnection();
            if (getAddressById == null) {
                getAddressById = connection.prepareStatement("select * from address where id = ?;");
            }
            getAddressById.setLong(1, id);
            ResultSet rs = getAddressById.executeQuery();
            rs.next();
            address = new AddressDTO(rs.getString("country"), rs.getString("locality"),
                    rs.getString("street"), rs.getString("house"),
                    rs.getShort("apartment"), rs.getString("postcode"));
        } catch (SQLException e) {
            System.err.println("Error in DAO getAddressById");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error in DAO getAddressById");
                e.printStackTrace();
            }
        }
        return address;
    }

    @Override
    public void update(AddressDTO address) {
        Connection connection = null;
        try {
            Connector connector = new Connector();
            connection = connector.getConnection();
            if (update == null) {
                update = connection.prepareStatement("update address set country = ?, locality = ?, street = ?, house = ?," +
                        " apartment = ?, postcode = ? where id = ?;");
            }
            update.setString(1, address.getCountry());
            update.setString(2, address.getLocality());
            update.setString(3, address.getStreet());
            update.setString(4, address.getHouse());
            update.setShort(5, address.getApartment());
            update.setString(6, address.getPostcode());
            update.setLong(7, address.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error in AddressDAO update");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error in DAO update");
                e.printStackTrace();
            }
        }
    }

    @Override
    public long getAddressIdByContactId(long id) {
        Connection connection = null;
        try {
            Connector connector = new Connector();
            connection = connector.getConnection();
            if (getAddressIdByContactId == null) {
                getAddressIdByContactId = connection.prepareStatement("select address.id from contact, address where" +
                        " contact.id = ? and contact.address_id = address.id;");
            }
            getAddressIdByContactId.setLong(1, id);
            ResultSet rs = getAddressIdByContactId.executeQuery();
            rs.next();
            return rs.getLong("id");
        } catch (SQLException e) {
            System.err.println("Error in DAO getAddressIdByContactId");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println("Error in DAO getAddressIdByContactId");
                e.printStackTrace();
            }
        }
        return 0L;
    }

}
