package com.itechart.contactsList.dao;

import com.itechart.contactsList.dto.AddressDTO;

public interface AddressDAO {

    Long create(AddressDTO address);
    void update(AddressDTO address);
    AddressDTO getAddressById(Long id);
    Long getAddressIdByContactId(Long id);

}