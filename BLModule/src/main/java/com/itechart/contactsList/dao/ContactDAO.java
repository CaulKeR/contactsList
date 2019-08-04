package com.itechart.contactsList.dao;

import com.itechart.contactsList.dto.ContactDTO;
import java.sql.Date;
import java.util.List;

public interface ContactDAO {

    List<ContactDTO> getMainContactsInfo(Integer count, Integer page);
    void create(ContactDTO contact);
    ContactDTO getContactById(Long id);
    void update(ContactDTO contact);
    void delete(Long id);
    List<ContactDTO> searchContacts(ContactDTO contact);
    String getEmailById(Long id);
    String getFirstNameByEmail(String email);
    List<ContactDTO> getContactsByBirthDate(Date birthDate);
    Integer getCountOfContacts();
}