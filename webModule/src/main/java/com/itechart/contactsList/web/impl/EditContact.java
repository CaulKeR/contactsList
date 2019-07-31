package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.service.ContactService;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditContact implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String tempLine;
            StringBuilder sb = new StringBuilder();
            while ((tempLine = request.getReader().readLine()) != null) {
                sb.append(tempLine);
            }
            System.out.println(sb.toString());
            new ContactService().edit(mapper.readValue(sb.toString(), ContactDTO.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}