package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.UploadAvatarProcessor;
import com.itechart.contactsList.web.Executable;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadAvatar implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Request actually don't contains upload file");
        } else {
            UploadAvatarProcessor processor = new UploadAvatarProcessor();
            processor.run(Long.parseLong(request.getRequestURI().replaceAll("\\D", "")), request);
        }
    }
}
