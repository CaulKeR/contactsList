package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.PhoneDAOImpl;
import com.itechart.contactsList.dto.PhoneDTO;
import org.apache.log4j.Logger;

import java.util.List;

public class PhoneService {

    private static final Logger log = Logger.getLogger(PhoneService.class);

    public void create(PhoneDTO phone) {
        try {
            new PhoneDAOImpl().create(phone);
        } catch (Exception e) {
            log.error(e);
        }
    }

    public void delete(Long id) {
        new PhoneDAOImpl().delete(id);
    }

    public void edit(PhoneDTO phone) {
        new PhoneDAOImpl().update(phone);
    }

    public PhoneDTO getById(Long id) {
        return new PhoneDAOImpl().getPhoneById(id);
    }

    public List<PhoneDTO> getAll(Long userId) {
        return new PhoneDAOImpl().getAllPhonesByContactId(userId);
    }
}