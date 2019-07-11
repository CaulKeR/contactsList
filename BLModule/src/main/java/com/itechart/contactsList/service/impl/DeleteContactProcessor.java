package com.itechart.contactsList.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.service.Processable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteContactProcessor implements Processable {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) {
        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            contactDAO.delete(Long.valueOf(request.getRequestURI().replaceAll("\\D", "")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
