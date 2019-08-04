package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.ContactService;
import com.itechart.contactsList.web.Executable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteContact implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        new ContactService().delete(Long.parseLong(request.getRequestURI().replaceAll("\\D", "")));
    }
}