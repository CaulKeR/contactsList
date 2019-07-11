package com.itechart.contactsList.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.service.Processable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class EditContactProcessor implements Processable {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) {
        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String tempLine = null;
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = request.getReader();
            while ((tempLine = reader.readLine()) != null) {
                sb.append(tempLine);
            }
            System.out.println(sb.toString());
            ContactDTO contactDTO = mapper.readValue(sb.toString(), ContactDTO.class);
            contactDTO.print();
            contactDAO.update(contactDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
