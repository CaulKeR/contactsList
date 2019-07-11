package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.impl.AddAttachmentProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAttachment implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AddAttachmentProcessor processor = new AddAttachmentProcessor();
        processor.run(request, response);
    }
}
