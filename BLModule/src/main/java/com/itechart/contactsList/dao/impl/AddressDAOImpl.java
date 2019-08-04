package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.AddressDAO;
import com.itechart.contactsList.dto.AddressDTO;
import com.itechart.contactsList.utility.Connector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressDAOImpl implements AddressDAO {

    private static final Logger log = Logger.getLogger(AddressDAOImpl.class);

    @Override
    public Long create(AddressDTO address) {
        long lastId = 0L;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement create = connection.prepareStatement("insert into address (country, locality, street," +
                    " house, apartment, postcode) values (?, ?, ?, ?, ?, ?);");
            setPs(create, address);
            create.executeUpdate();
            PreparedStatement getLastId = connection.prepareStatement("select last_insert_id();");
            ResultSet rs = getLastId.executeQuery();
            rs.next();
            lastId = rs.getLong("LAST_INSERT_ID()");
        } catch (SQLException e) {
            log.error("Error in AddressDAO create for" + address.print());
            log.error(e);
        }
        return lastId;
    }

    @Override
    public AddressDTO getAddressById(Long id) {
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
            log.error("Error in DAO getAddressById for id=" + id);
            log.error(e);
        }
        return address;
    }

    @Override
    public void update(AddressDTO address) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement update = connection.prepareStatement("update address set country = ?, locality = ?," +
                    " street = ?, house = ?, apartment = ?, postcode = ? where id = ?;");
            setPs(update, address);
            update.setLong(7, address.getId());
            update.executeUpdate();
        } catch (SQLException e) {
            log.error("Error in AddressDAO update for " + address.print());
            log.error(e);
        }
    }

    @Override
    public Long getAddressIdByContactId(Long id) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getAddressIdByContactId = connection.prepareStatement("select address.id from contact," +
                    " address where contact.id = ? and contact.address_id = address.id;");
            getAddressIdByContactId.setLong(1, id);
            ResultSet rs = getAddressIdByContactId.executeQuery();
            rs.next();
            return rs.getLong("id");
        } catch (SQLException e) {
            log.error("Error in DAO getAddressIdByContactId for id=" + id);
            log.error(e);
        }
        return 0L;
    }

    private void setPs(PreparedStatement ps, AddressDTO address) {
        try {
            ps.setString(1, address.getCountry());
            ps.setString(2, address.getLocality());
            ps.setString(3, address.getStreet());
            ps.setString(4, address.getHouse());
            ps.setShort(5, address.getApartment());
            ps.setString(6, address.getPostcode());
        } catch (SQLException e) {
            log.error(e);
        }
    }
}