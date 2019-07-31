package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.service.ContactService;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainContactsInfo implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            int count = Integer.parseInt(request.getParameter("count"));
            int page = Integer.parseInt(request.getParameter("page"));
            response.getWriter().write(mapper.writeValueAsString(new ContactService().getContacts(count, page)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
