package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.utility.Constants;

import java.io.File;

public class ContactByIdProcessor {

    public ContactDTO run(long id) {
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        ContactDTO contact = contactDAO.getContactById(id);
        File folder = new File(Constants.photoDir);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile() && listOfFiles[i].getName().equals(String.valueOf(contact.getId()))) {
                contact.setCustomAvatar(true);
                break;
            }
        }
        return contact;
    }
}