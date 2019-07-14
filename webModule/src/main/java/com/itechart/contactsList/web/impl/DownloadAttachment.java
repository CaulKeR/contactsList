package com.itechart.contactsList.web.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.service.MainContactsInfoProcessor;
import com.itechart.contactsList.web.Executable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DownloadAttachment implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.addHeader("content-disposition", "attachment; filename=test.txt");
//            MainContactsInfoProcessor processor = new MainContactsInfoProcessor();
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file = new File("D:\\attachments\\3\\2");
            response.getWriter().write(file.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
