package com.itechart.contactsList.web;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class FileUploader {

    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    public ServletFileUpload upload(HttpServletRequest request, long maxFileSize) {
        ServletFileUpload upload = null;
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Request actually don't contains upload file");
        } else {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEMORY_THRESHOLD);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
            upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(maxFileSize);
            upload.setSizeMax(MAX_REQUEST_SIZE);
        }
        return upload;
    }
}