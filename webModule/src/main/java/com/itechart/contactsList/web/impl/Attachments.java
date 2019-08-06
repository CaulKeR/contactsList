package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.service.AttachmentService;
import com.itechart.contactsList.web.Executable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Attachments implements Executable {

    private static final Logger log = Logger.getLogger(Attachments.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(new AttachmentService().getAll(Long.parseLong(request
                    .getRequestURI().replaceAll("\\D", "")))));
        } catch (IOException e) {
            log.error(e);
        }
    }
}