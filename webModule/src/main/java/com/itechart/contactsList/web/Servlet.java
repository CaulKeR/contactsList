package com.itechart.contactsList.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", urlPatterns = {"/api"})
public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        String command = request.getParameter("command");
        switch (command) {
            case "getAll" :
                getAll(request, response);
                break;
            case "getMainContactsInfo" :
                getMainContactsInfo(request, response);
                break;
            case "createContact" :
                createContact(request, response);
                break;
            case "getContactById" :
                getContactById(request, response);
                break;
            case "deleteContact" :
                deleteContact(request, response);
                break;
        }
    }

    private void getAll(HttpServletRequest request, HttpServletResponse response){
        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            List<ContactDTO> list = contactDAO.getAll();
            response.getWriter().write(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getMainContactsInfo(HttpServletRequest request, HttpServletResponse response){
        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            List<ContactDTO> list = contactDAO.getMainContactsInfo();
            response.getWriter().write(mapper.writeValueAsString(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createContact(HttpServletRequest request, HttpServletResponse response){
        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            String tempLine = null;
            StringBuffer sb = new StringBuffer();
            BufferedReader reader = request.getReader();
            while ((tempLine = reader.readLine()) != null) {
                sb.append(tempLine);
            }
            System.out.println(sb.toString());
            ContactDTO contactDTO = mapper.readValue(sb.toString(), ContactDTO.class);
            contactDTO.print();
            contactDAO.create(contactDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getContactById(HttpServletRequest request, HttpServletResponse response){
        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            ContactDTO contact = contactDAO.getContactById(Long.valueOf(request.getParameter("id")));
            response.getWriter().write(mapper.writeValueAsString(contact));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteContact(HttpServletRequest request, HttpServletResponse response){
        try {
            ContactDAOImpl contactDAO = new ContactDAOImpl();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            System.out.println(request.getParameter("id"));
            contactDAO.delete(Long.valueOf(request.getParameter("id")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}