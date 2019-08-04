package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.service.ContactService;
import com.itechart.contactsList.web.Executable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactById implements Executable {

    private static final Logger log = Logger.getLogger(ContactById.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ContactService().getById(Long
                    .parseLong(request.getRequestURI().replaceAll("\\D", "")))));
        } catch (IOException e) {
           log.error(e);
        }
    }
}
