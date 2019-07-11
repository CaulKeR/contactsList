package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.impl.EditContactProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditContact implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        EditContactProcessor processor = new EditContactProcessor();
        processor.run(request, response);
    }

}