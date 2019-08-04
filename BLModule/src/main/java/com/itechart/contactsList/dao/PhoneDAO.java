package com.itechart.contactsList.dao;

import com.itechart.contactsList.dto.PhoneDTO;

import java.util.List;

public interface PhoneDAO {

    void create(PhoneDTO phone);
    void update(PhoneDTO phone);
    void delete(long id);
    List<PhoneDTO> getAllPhonesByContactId(Long id);
    PhoneDTO getPhoneById(Long id);
}