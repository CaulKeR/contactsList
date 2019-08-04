package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.dto.EmailDTO;
import com.itechart.contactsList.service.EmailService;
import com.itechart.contactsList.web.Executable;
import com.itechart.contactsList.web.RequestReader;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendEmail implements Executable {

    private static final Logger log = Logger.getLogger(SendEmail.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            new EmailService().sendEmail(new ObjectMapper().readValue(new RequestReader().read(request), EmailDTO.class));
        } catch (IOException e) {
            log.error(e);
        }
    }
}