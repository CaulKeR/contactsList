package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.service.EmailService;
import com.itechart.contactsList.web.Executable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetTemplates implements Executable {

    private static final Logger log = Logger.getLogger(GetTemplates.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new ObjectMapper().writeValueAsString(new EmailService().getTemplates()));
        } catch (IOException e) {
            log.error(e);
        }
    }
}