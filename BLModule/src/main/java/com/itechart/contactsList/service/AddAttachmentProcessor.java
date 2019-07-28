package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import com.itechart.contactsList.utility.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class AddAttachmentProcessor {

    public void run(String uri, HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(Constants.MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(Constants.MAX_FILE_SIZE);
        upload.setSizeMax(Constants.MAX_REQUEST_SIZE);
        long userId =  Long.valueOf(uri.replaceAll("\\D", ""));
        String uploadPath = Constants.attachDir + userId;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + modifyFileName(fileName, userId);
                        File storeFile = new File(filePath);
                        item.write(storeFile);
                        System.out.println("File added: " + filePath);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String modifyFileName(String fileName, long userId) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        String newName = String.valueOf(attachmentDAO.create(fileName, userId));
        System.out.println(newName);
        return newName;
    }
}