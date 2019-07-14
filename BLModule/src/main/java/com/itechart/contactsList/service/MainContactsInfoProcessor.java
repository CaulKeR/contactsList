package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import java.util.List;

public class MainContactsInfoProcessor {

    public List<ContactDTO> run() {
        ContactDAOImpl contactDAO = new ContactDAOImpl();
        return contactDAO.getMainContactsInfo();
    }

}