package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.service.GetPhonesProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetPhones implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            response.getWriter().write(mapper.writeValueAsString(new GetPhonesProcessor().run(Long.parseLong(request
                    .getRequestURI().replaceAll("\\D", "")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
