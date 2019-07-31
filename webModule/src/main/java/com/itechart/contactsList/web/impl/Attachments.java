package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.service.AttachmentService;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Attachments implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            response.getWriter().write(mapper.writeValueAsString(new AttachmentService().getAll(Long.parseLong(request
                        .getRequestURI().replaceAll("\\D", "")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
