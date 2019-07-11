package com.itechart.contactsList.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.service.Processable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MainContactsInfoProcessor implements Processable {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            List<ContactDTO> list = contactDAO.getMainContactsInfo();
            response.getWriter().write(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
