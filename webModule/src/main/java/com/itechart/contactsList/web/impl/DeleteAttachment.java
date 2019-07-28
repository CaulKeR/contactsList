package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.DeleteAttachmentProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAttachment implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        new DeleteAttachmentProcessor().run(Long.parseLong(request.getRequestURI().replaceAll("\\D", "")));
    }
}