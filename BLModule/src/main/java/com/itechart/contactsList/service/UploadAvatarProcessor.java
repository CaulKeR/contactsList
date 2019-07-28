package com.itechart.contactsList.service;

import com.itechart.contactsList.utility.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class UploadAvatarProcessor {

    public void run(long userId, HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Constants.MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(Constants.MAX_AVATAR_SIZE);
        try {
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String filePath = Constants.photoDir + userId;
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
