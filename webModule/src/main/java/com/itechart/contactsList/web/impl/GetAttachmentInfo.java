package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.service.AttachmentService;
import com.itechart.contactsList.web.Executable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAttachmentInfo implements Executable {

    private static final Logger log = Logger.getLogger(GetAttachmentInfo.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] parts = request.getRequestURI().split("/");
            response.getWriter().write(new ObjectMapper().writeValueAsString(new AttachmentService().getById(Long
                    .parseLong(parts[parts.length - 1]))));
        } catch (IOException e) {
            log.error(e);
        }
    }
}