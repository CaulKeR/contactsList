package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.PhoneDAOImpl;
import com.itechart.contactsList.dto.PhoneDTO;

public class CreatePhoneProcessor {

    public void run(PhoneDTO phone) {
        try {
            new PhoneDAOImpl().create(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
