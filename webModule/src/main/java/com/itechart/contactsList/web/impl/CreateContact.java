package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.web.Executable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

public class CreateContact implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
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
            contactDAO.create(contactDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
