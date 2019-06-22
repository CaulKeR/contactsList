package by.itj.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itechart.contactsList.dao.impl.ContactDAOImpl;
import com.itechart.contactsList.dto.ContactDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Servlet", urlPatterns = {"/servlet"}
)
public class Servlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding( "UTF-8" );
        String command = request.getParameter("command");
        switch (command) {
            case "getAll" :
                getAll(request, response);
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

}