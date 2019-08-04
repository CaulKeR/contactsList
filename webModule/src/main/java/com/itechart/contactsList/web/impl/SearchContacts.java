package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.dto.ContactDTO;
import com.itechart.contactsList.service.ContactService;
import com.itechart.contactsList.web.Executable;
import com.itechart.contactsList.web.RequestReader;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchContacts implements Executable {

    private static final Logger log = Logger.getLogger(SearchContacts.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(new ContactService().search(mapper.readValue(
                    new RequestReader().read(request), ContactDTO.class))));
        } catch (IOException e) {
            log.error(e);
        }
    }
}