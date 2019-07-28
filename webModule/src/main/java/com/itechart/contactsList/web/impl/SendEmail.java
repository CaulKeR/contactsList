package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itechart.contactsList.dto.EmailDTO;
import com.itechart.contactsList.service.SendEmailProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendEmail implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String tempLine;
            StringBuffer sb = new StringBuffer();
            while ((tempLine = request.getReader().readLine()) != null) {
                sb.append(tempLine);
            }
            System.out.println(sb.toString());
            new SendEmailProcessor().run(new ObjectMapper().readValue(sb.toString(), EmailDTO.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
