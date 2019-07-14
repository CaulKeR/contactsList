package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.dto.AttachmentDTO;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AttachmentsProcessor {

    public List<AttachmentDTO> run(long userId) {
        ArrayList<AttachmentDTO> list = new ArrayList<>();
        File[] listOfFiles = new File("D:\\attachments\\" + userId).listFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
                    long id = Long.valueOf(listOfFiles[i].getName());
                    String fileName = attachmentDAO.getFileName(id);
                    if (fileName != null) {
                        AttachmentDTO attachment = new AttachmentDTO(id, userId, fileName,listOfFiles[i].length(),
                                new Date(listOfFiles[i].lastModified()));
                        list.add(attachment);
                    }
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
        } else {
            System.out.println("Empty folder");
        }
        return list;
    }
}