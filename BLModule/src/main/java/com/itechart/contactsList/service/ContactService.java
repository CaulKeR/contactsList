package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.utility.Directories;
import java.io.File;
import java.util.List;

public class ContactService {

    public List<ContactDTO> getContacts(int count, int page) {
        return new ContactDAOImpl().getMainContactsInfo(count, page);
    }

    public ContactDTO getById(long id) {
        ContactDTO contact = new ContactDAOImpl().getContactById(id);
        File[] listOfFiles = new File(Directories.PHOTO_DIRECTORY).listFiles();
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
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        new ContactDAOImpl().delete(id);
    }

    public void edit(ContactDTO contact) {
        new ContactDAOImpl().update(contact);
    }

    public int getCountOfContacts() {
        return new ContactDAOImpl().getCountOfContacts();
    }

    public List<ContactDTO> search(ContactDTO contact) {
        return new ContactDAOImpl().searchContacts(contact);
    }
}
