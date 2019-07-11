package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.impl.AttachmentsProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Attachments implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        AttachmentsProcessor processor = new AttachmentsProcessor();
        processor.run(request, response);
    }
}
