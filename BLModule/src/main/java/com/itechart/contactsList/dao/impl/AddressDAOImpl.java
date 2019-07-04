package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.AddressDAO;
import com.itechart.contactsList.dto.AddressDTO;
import com.itechart.contactsList.service.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAOImpl implements AddressDAO {

    private PreparedStatement create = null;
    private PreparedStatement getLastId = null;
    private PreparedStatement getAddressById = null;

    @Override
    public long create(AddressDTO address) {
        long lastId = 0L;
        try {
            Connector connector = new Connector();
            Connection connection = connector.getConnection();
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
                getLastId = connection.prepareStatement("select (max(id)) from address;");
            }
            ResultSet rs = getLastId.executeQuery();
            rs.next();
            lastId = rs.getLong("(max(id))");
        } catch (SQLException e) {
            System.err.println("Error in AddressDAO create");
            e.printStackTrace();
        }
        return lastId;
    }

    @Override
    public AddressDTO getAddressById(long id) {
        AddressDTO address = null;
        try {
            Connector connector = new Connector();
            Connection connection = connector.getConnection();
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
            System.err.println("Error in DAO getAll");
            e.printStackTrace();
        }
        return address;
    }

}
