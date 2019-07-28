package com.itechart.contactsList.dao;

import com.itechart.contactsList.dto.ContactDTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public interface ContactDAO {

    List<ContactDTO> getMainContactsInfo();
    void create(ContactDTO contact);
    ContactDTO getContactById(long id);
    void update(ContactDTO contact);
    void delete(long id);
    List<ContactDTO> searchContacts(ContactDTO contact);
    String getEmailById(long id);
    String getFirstNameByEmail(String email);
    List<ContactDTO> getContactsByBirthDate(Date birthDate);

}