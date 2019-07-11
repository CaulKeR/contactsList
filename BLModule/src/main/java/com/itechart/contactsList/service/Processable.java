package com.itechart.contactsList.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Processable {

    void run(HttpServletRequest request, HttpServletResponse response);

}