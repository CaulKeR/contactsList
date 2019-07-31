package com.itechart.contactsList.web.impl;

import com.itechart.contactsList.web.Executable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReferenceToIndex implements Executable {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("/index.html").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}