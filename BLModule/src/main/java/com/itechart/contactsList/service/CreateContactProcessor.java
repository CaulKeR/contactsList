package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
public class CreateContactProcessor {

    public void run(ContactDTO contact) {
        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            contact.print();
            contactDAO.create(contact);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
