package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.dto.PhoneDTO;
import com.itechart.contactsList.service.PhoneService;
import com.itechart.contactsList.web.Executable;
import com.itechart.contactsList.web.RequestReader;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreatePhone implements Executable {

    private static final Logger log = Logger.getLogger(CreatePhone.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            new PhoneService().create(new ObjectMapper().readValue(new RequestReader().read(request), PhoneDTO.class));
        } catch (IOException e) {
            log.error(e);
        }
    }
}