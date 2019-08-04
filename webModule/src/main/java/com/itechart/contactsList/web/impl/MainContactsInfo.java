package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.service.ContactService;
import com.itechart.contactsList.web.Executable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainContactsInfo implements Executable {

    private static final Logger log = Logger.getLogger(MainContactsInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Integer count = Integer.parseInt(request.getParameter("count"));
            Integer page = Integer.parseInt(request.getParameter("page"));
            response.getWriter().write(new ObjectMapper().writeValueAsString(new ContactService().getContacts(count, page)));
        } catch (IOException e) {
            log.error(e);
        }
    }
}
