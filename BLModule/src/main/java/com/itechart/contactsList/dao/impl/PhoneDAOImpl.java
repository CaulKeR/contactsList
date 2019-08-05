package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.PhoneDAO;
import com.itechart.contactsList.dto.PhoneDTO;
import com.itechart.contactsList.utility.Connector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDAOImpl implements PhoneDAO {

    private static final Logger log = Logger.getLogger(PhoneDAOImpl.class);

    @Override
    public void create(PhoneDTO phone) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement createPs = connection.prepareStatement("insert into phone (contact_id, country_code," +
                    " operators_code, phone_number, phone_type, comment) values (?, ?, ?, ?, ?, ?);");
            createPs.setLong(1, phone.getContactId());
            createPs.setShort(2, phone.getCountryCode());
            createPs.setInt(3, phone.getOperatorsCode());
            createPs.setInt(4, phone.getPhoneNumber());
            createPs.setString(5, phone.getType());
            createPs.setString(6, phone.getComment());
            createPs.executeUpdate();
        } catch (SQLException e) {
            log.error("Error in DAO create for " + phone.print());
            log.error(e);
        }
    }

    @Override
    public void update(PhoneDTO phone) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement updatePs = connection.prepareStatement("update phone set country_code = ?, " +
                    "operators_code = ?, phone_number = ?, phone_type = ?, comment = ? where id = ?;");
            updatePs.setShort(1, phone.getCountryCode());
            updatePs.setInt(2, phone.getOperatorsCode());
            updatePs.setInt(3, phone.getPhoneNumber());
            updatePs.setString(4, phone.getType());
            updatePs.setString(5, phone.getComment());
            updatePs.setLong(6, phone.getId());
            updatePs.executeUpdate();
        } catch (SQLException e) {
            log.error("Error in DAO update for " + phone.print());
            log.error(e);
        }
    }

    @Override
    public void delete(long id) {
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement deletePs = connection.prepareStatement("update phone set deleteDate = curdate() where" +
                    " id = ?;");
            deletePs.setLong(1, id);
            deletePs.executeUpdate();
        } catch (SQLException e) {
            log.error("Error in DAO delete for id=" + id);
            log.error(e);
        }
    }

    @Override
    public List<PhoneDTO> getAllPhonesByContactId(Long id) {
        List<PhoneDTO> phonesList = new ArrayList<>();
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getAllPhonesByContactIdPs = connection.prepareStatement("select * from phone where" +
                    " contact_id = ? and deleteDate is null;");
            getAllPhonesByContactIdPs.setLong(1, id);
            ResultSet rs = getAllPhonesByContactIdPs.executeQuery();
            while (rs.next()) {
                PhoneDTO phone = new PhoneDTO(rs.getLong("id"), rs.getLong("contact_id"),
                        rs.getShort("country_code"), rs.getInt("operators_code"),
                        rs.getInt("phone_number"), rs.getString("phone_type"),
                        rs.getString("comment"));
                phonesList.add(phone);
            }
        } catch (SQLException e) {
            log.error("Error in DAO getAllPhonesByContactId for id=" + id);
            log.error(e);
        }
        return phonesList;
    }

    @Override
    public PhoneDTO getPhoneById(Long id) {
        PhoneDTO phone = null;
        try (Connection connection = new Connector().getConnection()) {
            PreparedStatement getPhoneByIdPs = connection.prepareStatement("select * from phone where id = ? and" +
                    " deleteDate is null;");
            getPhoneByIdPs.setLong(1, id);
            ResultSet rs = getPhoneByIdPs.executeQuery();
            rs.next();
            phone = new PhoneDTO(rs.getLong("id"), rs.getLong("contact_id"),
                    rs.getShort("country_code"), rs.getInt("operators_code"),
                    rs.getInt("phone_number"), rs.getString("phone_type"),
                    rs.getString("comment"));
        } catch (SQLException e) {
            log.error("Error in DAO getContactById for id=" + id);
            log.error(e);
        }
        return phone;
    }
}