package com.itechart.contactsList.dao;

import com.itechart.contactsList.dto.AddressDTO;

public interface AddressDAO {

    long create(AddressDTO address);
    void update(AddressDTO address);
    AddressDTO getAddressById(long id);
    long getAddressIdByContactId(long id);

}