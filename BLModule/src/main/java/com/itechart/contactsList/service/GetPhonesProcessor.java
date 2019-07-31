package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.PhoneDAOImpl;
import com.itechart.contactsList.dto.PhoneDTO;
import java.util.List;

public class GetPhonesProcessor {

    public List<PhoneDTO> run(long userId) {
        return new PhoneDAOImpl().getAllPhonesByContactId(userId);
    }
}