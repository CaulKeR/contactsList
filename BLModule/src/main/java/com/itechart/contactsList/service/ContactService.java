package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.utility.ServerDirectories;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

public class ContactService {

    private static final Logger log = Logger.getLogger(ContactService.class);

    public List<ContactDTO> getContacts(Integer count, Integer page) {
        return new ContactDAOImpl().getMainContactsInfo(count, page);
    }

    public ContactDTO getById(Long id) {
        ContactDTO contact = new ContactDAOImpl().getContactById(id);
        File[] listOfFiles = new File(ServerDirectories.PHOTO_DIRECTORY).listFiles();
        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile() && listOfFile.getName().equals(String.valueOf(contact.getId()))) {
                    contact.setCustomAvatar(true);
                    break;
                }
            }
        }
        return contact;
    }

    public void create(ContactDTO contact) {
        try {
            new ContactDAOImpl().create(contact);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void delete(Long id) {
        new ContactDAOImpl().delete(id);
    }

    public void edit(ContactDTO contact) {
        new ContactDAOImpl().update(contact);
    }

    public Integer getCountOfContacts() {
        return new ContactDAOImpl().getCountOfContacts();
    }

    public List<ContactDTO> search(ContactDTO contact) {
        return new ContactDAOImpl().searchContacts(contact);
    }
}
