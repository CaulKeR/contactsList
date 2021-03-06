package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.AttachmentService;
import com.itechart.contactsList.web.Executable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAttachment implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        new AttachmentService().delete(Long.parseLong(request.getRequestURI().replaceAll("\\D", "")));
    }
}