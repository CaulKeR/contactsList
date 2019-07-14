package com.itechart.contactsList.service;

import com.itechart.contactsList.dao.AttachmentDAO;
import com.itechart.contactsList.dao.impl.AttachmentDAOImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class AddAttachmentProcessor {

    private final String UPLOAD_DIRECTORY = "D:\\attachments\\";
    private final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public void run(String uri, HttpServletRequest request) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = UPLOAD_DIRECTORY + uri.replaceAll("\\D", "");
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
                        String filePath = uploadPath + File.separator + modifyFileName(fileName);
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

    private String modifyFileName(String fileName) {
        AttachmentDAOImpl attachmentDAO = new AttachmentDAOImpl();
        String newName = String.valueOf(attachmentDAO.create(fileName));
        System.out.println(newName);
        return newName;
    }
}