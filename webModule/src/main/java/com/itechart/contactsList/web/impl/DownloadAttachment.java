package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.DownloadAttachmentProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DownloadAttachment implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (PrintWriter out = response.getWriter()) {
            long fileId = Long.valueOf(request.getRequestURI().replaceAll("\\D", ""));
            DownloadAttachmentProcessor processor = new DownloadAttachmentProcessor();
            response.addHeader("Content-Disposition", "attachment; filename=" + processor.getFileName(fileId));
            processor.run(fileId, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}