package com.itechart.contactsList.dao.impl;

import com.itechart.contactsList.dao.AddressDAO;
import com.itechart.contactsList.dto.AddressDTO;
import com.itechart.contactsList.service.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDAOImpl implements AddressDAO {

    private PreparedStatement create = null;

    @Override
    public void create(AddressDTO address) {
        try {
            Connection connection = Connector.getConnection();
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
            create.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error in AddressDAO create");
        }
    }

}
