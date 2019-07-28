package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;

public class GetEmailProcessor {

    public ContactDTO run(long id) {
        String email = new ContactDAOImpl().getEmailById(id);
        return new ContactDTO(email);
    }
}