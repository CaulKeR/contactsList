package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.AddAttachmentProcessor;
import com.itechart.contactsList.web.Executable;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAttachment implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Request actually don't contains upload file");
        } else {
            AddAttachmentProcessor processor = new AddAttachmentProcessor();
            processor.run(request.getRequestURI(), request);
        }
    }
}