package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.service.ContactService;
import com.itechart.contactsList.web.Executable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetCountOfContacts implements Executable {

    private static final Logger log = Logger.getLogger(GetCountOfContacts.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ContactService().getCountOfContacts()));
        } catch (IOException e) {
            log.error(e);
        }
    }
}