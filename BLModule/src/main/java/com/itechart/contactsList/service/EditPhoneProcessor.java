package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.PhoneDAOImpl;
import com.itechart.contactsList.dto.PhoneDTO;

public class EditPhoneProcessor {

    public void run(PhoneDTO phone) {
        new PhoneDAOImpl().update(phone);
    }
}