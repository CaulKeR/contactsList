package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.impl.MainContactsInfoProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainContactsInfo implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        MainContactsInfoProcessor processor = new MainContactsInfoProcessor();
        processor.run(request, response);
    }
}
