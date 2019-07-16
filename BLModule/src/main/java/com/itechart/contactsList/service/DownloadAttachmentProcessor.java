package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.utility.Constants;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class DownloadAttachmentProcessor {

    public String run(long fileId, PrintWriter out) {
        BufferedReader reader = null;
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        try {
            long userId = attachmentDAO.getUserId(fileId);
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(Constants.attachDir + userId +
                        File.separator + fileId), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                out.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return attachmentDAO.getFileName(fileId);
    }
}
