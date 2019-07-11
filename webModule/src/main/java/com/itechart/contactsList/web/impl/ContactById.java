package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.impl.ContactByIdProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContactById implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ContactByIdProcessor processor = new ContactByIdProcessor();
        processor.run(request, response);
    }
}
