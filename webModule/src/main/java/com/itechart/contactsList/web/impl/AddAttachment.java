package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.AttachmentService;
import com.itechart.contactsList.web.Executable;
import com.itechart.contactsList.web.FileUploader;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAttachment implements Executable {

    private static final Logger log = Logger.getLogger(AddAttachment.class);
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;  // 40MB

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            new AttachmentService().upload(new FileUploader().upload(request, MAX_FILE_SIZE).parseRequest(request),
                    Long.parseLong(request.getRequestURI().replaceAll("\\D", "")));
        } catch (FileUploadException e) {
            log.error(e);
        }
    }
}