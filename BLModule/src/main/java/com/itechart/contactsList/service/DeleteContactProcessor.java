package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;

public class DeleteContactProcessor {

    public void run(long id) {
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        contactDAO.delete(id);
    }
}
