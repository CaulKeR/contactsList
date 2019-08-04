package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.AvatarService;
import com.itechart.contactsList.web.Executable;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAvatar implements Executable {

    private static final Logger log = Logger.getLogger(GetAvatar.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.addHeader("Content-Disposition", "form-data");
            response.addHeader("Content-Type", "image/png");
            new AvatarService().getAvatar(Long.parseLong(request.getRequestURI().replaceAll("\\D", "")),
                    response.getWriter());
        } catch (IOException e) {
            log.error(e);
        }
    }
}
