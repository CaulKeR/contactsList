package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;

import java.util.List;

public class SearchContactsProcessor {

    public List<ContactDTO> run(ContactDTO contact) {
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        return contactDAO.searchContacts(contact);
    }
}
