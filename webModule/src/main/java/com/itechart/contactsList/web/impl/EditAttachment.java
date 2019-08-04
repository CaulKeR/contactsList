package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.dto.AttachmentDTO;
import com.itechart.contactsList.service.AttachmentService;
import com.itechart.contactsList.web.Executable;
import com.itechart.contactsList.web.RequestReader;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditAttachment implements Executable {

    private static final Logger log = Logger.getLogger(EditAttachment.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            new AttachmentService().edit(new ObjectMapper().readValue(new RequestReader().read(request), AttachmentDTO.class));
        } catch (IOException e) {
            log.error(e);
        }
    }
}