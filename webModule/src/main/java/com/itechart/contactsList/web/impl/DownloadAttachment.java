package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.AttachmentService;
import com.itechart.contactsList.web.Executable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadAttachment implements Executable {

    private static final Logger log = Logger.getLogger(DownloadAttachment.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            Long fileId = Long.parseLong(request.getRequestURI().replaceAll("\\D", ""));
            AttachmentService service = new AttachmentService();
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment; filename=" + service.getFileName(fileId));
            service.download(fileId, out);
        } catch (IOException e) {
            log.error(e);
        }
    }
}