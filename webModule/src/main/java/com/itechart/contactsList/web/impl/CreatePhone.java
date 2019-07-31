package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.dto.PhoneDTO;
import com.itechart.contactsList.service.PhoneService;
import com.itechart.contactsList.web.Executable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePhone implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String tempLine;
            StringBuilder sb = new StringBuilder();
            while ((tempLine = request.getReader().readLine()) != null) {
                sb.append(tempLine);
            }
            System.out.println(sb.toString());
            new PhoneService().create(mapper.readValue(sb.toString(), PhoneDTO.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
