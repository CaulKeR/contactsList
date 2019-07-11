package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.impl.CreateContactProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateContact implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        CreateContactProcessor processor = new CreateContactProcessor();
        processor.run(request, response);
    }
}