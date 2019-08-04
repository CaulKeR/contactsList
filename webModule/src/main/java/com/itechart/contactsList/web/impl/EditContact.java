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

public class EditContact implements Executable {

    private static final Logger log = Logger.getLogger(EditContact.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            new ContactService().edit(new ObjectMapper().readValue(new RequestReader().read(request), ContactDTO.class));
        } catch (IOException e) {
            log.error(e);
        }
    }

}