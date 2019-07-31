package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.PhoneDAOImpl;
import com.itechart.contactsList.dto.PhoneDTO;
import java.util.List;

public class PhoneService {

    public void create(PhoneDTO phone) {
        try {
            new PhoneDAOImpl().create(phone);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        new PhoneDAOImpl().delete(id);
    }

    public void edit(PhoneDTO phone) {
        new PhoneDAOImpl().update(phone);
    }

    public PhoneDTO getById(long id) {
        return new PhoneDAOImpl().getPhoneById(id);
    }

    public List<PhoneDTO> getAll(long userId) {
        return new PhoneDAOImpl().getAllPhonesByContactId(userId);
    }
}