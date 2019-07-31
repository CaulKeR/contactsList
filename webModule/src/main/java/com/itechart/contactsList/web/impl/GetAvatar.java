package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.service.GetAvatarProcessor;
import com.itechart.contactsList.web.Executable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetAvatar implements Executable {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.addHeader("Content-Disposition", "form-data");
            response.addHeader("Content-Type", "image/png");
            long userId = Long.parseLong(request.getRequestURI().replaceAll("\\D", ""));
            new GetAvatarProcessor().run(userId, response.getWriter());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
