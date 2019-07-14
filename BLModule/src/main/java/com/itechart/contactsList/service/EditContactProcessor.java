package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
public class EditContactProcessor {

    public void run(ContactDTO contact) {
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contact.print();
        contactDAO.update(contact);
    }
}
