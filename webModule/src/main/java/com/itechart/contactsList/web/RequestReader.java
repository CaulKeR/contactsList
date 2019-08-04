package com.itechart.contactsList.web;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestReader {

    private static final Logger log = Logger.getLogger(RequestReader.class);

    public String read(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try {
            request.setCharacterEncoding("UTF-8");
            String tempLine;
            while ((tempLine = request.getReader().readLine()) != null) {
                sb.append(tempLine);
            }
            log.info("Object before parsing: " + sb.toString());
        } catch (IOException e) {
            log.error(e);
        }
        return sb.toString();
    }
}