package com.itechart.contactsList.dao;

import com.itechart.contactsList.dto.ContactDTO;

import java.util.List;

public interface ContactDAO {

    List<ContactDTO> getAll();
    List<ContactDTO> getMainContactsInfo();
    void create(ContactDTO contact);
    void update(ContactDTO contact);

}