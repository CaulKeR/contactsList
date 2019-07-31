package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.PhoneDAOImpl;

public class DeletePhoneProcessor {

    public void run(long id) {
        new PhoneDAOImpl().delete(id);
    }
}