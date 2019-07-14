package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.service.MainContactsInfoProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainContactsInfo implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("application/json");
            MainContactsInfoProcessor processor = new MainContactsInfoProcessor();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            response.getWriter().write(mapper.writeValueAsString(processor.run()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
