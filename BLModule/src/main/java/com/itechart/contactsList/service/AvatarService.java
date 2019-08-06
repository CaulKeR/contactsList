package com.itechart.contactsList.service;

import com.itechart.contactsList.utility.ServerDirectories;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AvatarService {

    private static final Logger log = Logger.getLogger(AvatarService.class);

    public void getAvatar(Long userId, PrintWriter out) {
        try (FileInputStream reader = new FileInputStream(ServerDirectories.PHOTO_DIRECTORY + userId)) {
            int temp;
            while ((temp = reader.read()) != -1) {
                out.write(temp);
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public void uploadAvatar(Long userId, List<FileItem> formItems) {
        try {
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String filePath = ServerDirectories.PHOTO_DIRECTORY + userId;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        log.info("Photo added: " + filePath);
                    }
                }
            } else {
                log.error("formItems are null for userId=" + userId);
            }
        } catch (Exception e) {
            log.error(e);
        }
    }
}