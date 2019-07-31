package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.AvatarService;
import com.itechart.contactsList.web.Executable;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class UploadAvatar implements Executable {

    private static final int MAX_AVATAR_SIZE = 1024 * 1024;  // 1MB

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Request actually don't contains upload file");
        } else {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setFileSizeMax(MAX_AVATAR_SIZE);
                new AvatarService().uploadAvatar(Long.parseLong(request.getRequestURI()
                        .replaceAll("\\D", "")), upload.parseRequest(request));
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        }
    }
}