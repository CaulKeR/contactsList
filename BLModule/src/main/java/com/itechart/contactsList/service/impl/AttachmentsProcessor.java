package com.itechart.contactsList.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.dto.AttachmentDTO;
import com.itechart.contactsList.service.Processable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class AttachmentsProcessor implements Processable {

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            long userId = Long.valueOf(request.getRequestURI().replaceAll("\\D", ""));
            ArrayList<AttachmentDTO> list = new ArrayList<>();
            File[] listOfFiles = new File("D:\\attachments\\" + userId).listFiles();
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        String[] fileNameParts = listOfFiles[i].getName().split("\\.");
                        AttachmentDTO attachment = new AttachmentDTO(userId, fileNameParts[0], fileNameParts[1],
                                listOfFiles[i].length(), new Date(listOfFiles[i].lastModified()));
                        list.add(attachment);
                    } else if (listOfFiles[i].isDirectory()) {
                        System.out.println("Directory " + listOfFiles[i].getName());
                    }
                }
            } else {
                System.out.println("Empty folder");
            }
            response.getWriter().write(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}