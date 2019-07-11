package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.impl.DeleteContactProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteContact implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        DeleteContactProcessor processor = new DeleteContactProcessor();
        processor.run(request, response);
    }
}
