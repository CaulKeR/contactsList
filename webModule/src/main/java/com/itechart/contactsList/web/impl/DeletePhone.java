package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.DeletePhoneProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePhone implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        new DeletePhoneProcessor().run(Long.parseLong(request.getRequestURI().replaceAll("\\D", "")));
    }
}