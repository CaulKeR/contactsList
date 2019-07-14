package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;

public class ContactByIdProcessor {

    public ContactDTO run(long id) {
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        return contactDAO.getContactById(id);
    }
}
