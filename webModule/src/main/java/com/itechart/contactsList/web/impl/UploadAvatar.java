package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.AvatarService;
import com.itechart.contactsList.web.Executable;
import com.itechart.contactsList.web.FileUploader;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UploadAvatar implements Executable {

    private static final Logger log = Logger.getLogger(UploadAvatar.class);
    private static final int MAX_AVATAR_SIZE = 1024 * 1024;  // 1MB

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            new AvatarService().uploadAvatar(Long.parseLong(request.getRequestURI()
                    .replaceAll("\\D", "")), new FileUploader().upload(request, MAX_AVATAR_SIZE)
                    .parseRequest(request));
        } catch (FileUploadException e) {
            log.error(e);
        }
    }
}