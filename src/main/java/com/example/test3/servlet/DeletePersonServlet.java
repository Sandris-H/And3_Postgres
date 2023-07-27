package com.example.test3.servlet;

import com.example.test3.dao.PersonDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeletePersonServlet", value = "/delete")
public class DeletePersonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDAO personDAO = new PersonDAO();
        final int id = Integer.parseInt(request.getParameter("id"));
        personDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
