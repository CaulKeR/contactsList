package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.utility.Constants;
import java.io.*;

public class DownloadAttachmentProcessor {

    public void run(long fileId, PrintWriter out) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        try (FileInputStream reader = new FileInputStream(Constants.attachDir + attachmentDAO.getUserId(fileId) +
                    File.separator + fileId)) {
            int temp;
            while ((temp = reader.read()) != -1) {
                out.write(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName(long fileId){
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        return attachmentDAO.getFileName(fileId);
    }
}