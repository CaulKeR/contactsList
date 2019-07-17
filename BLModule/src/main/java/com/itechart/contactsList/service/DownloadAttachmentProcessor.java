package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.utility.Constants;
import java.io.*;

public class DownloadAttachmentProcessor {

    public String run(long fileId) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(
                    Constants.attachDir + attachmentDAO.getUserId(fileId) + File.separator + fileId)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
                result.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public String getFileName(long fileId){
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        return attachmentDAO.getFileName(fileId);
    }
}