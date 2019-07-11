package com.itechart.contactsList.service.impl;

import com.itechart.contactsList.service.Processable;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

public class AddAttachmentProcessor implements Processable {

    private final String UPLOAD_DIRECTORY = "D:\\attachments\\";
    private final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    public void run(HttpServletRequest request, HttpServletResponse response) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Request actually don't contains upload file");
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = UPLOAD_DIRECTORY + request.getRequestURI().replaceAll("\\D", "");
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
                        String filePath = uploadPath + File.separator + fileName;
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
}