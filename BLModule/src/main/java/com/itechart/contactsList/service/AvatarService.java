package com.itechart.contactsList.service;

import com.itechart.contactsList.utility.Directories;
import org.apache.commons.fileupload.FileItem;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AvatarService {

    public void getAvatar(long userId, PrintWriter out) {
        try (FileInputStream reader = new FileInputStream(Directories.PHOTO_DIRECTORY + userId)) {
            int temp;
            while ((temp = reader.read()) != -1) {
                out.write(temp);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void uploadAvatar(long userId, List<FileItem> formItems) {
        try {
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String filePath = Directories.PHOTO_DIRECTORY + userId;
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        System.out.println("Photo added: " + filePath);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}