package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.AttachmentService;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadAttachment implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            long fileId = Long.parseLong(request.getRequestURI().replaceAll("\\D", ""));
            AttachmentService service = new AttachmentService();
            response.addHeader("Content-Disposition", "attachment; filename=" + service.getFileName(fileId));
            service.download(fileId, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}