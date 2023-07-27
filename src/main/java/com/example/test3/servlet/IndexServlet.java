package com.example.test3.servlet;

import com.example.test3.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet", value = "/")
public class IndexServlet extends HttpServlet {

    private static String index = "/WEB-INF/view/index.jsp";

    @Override
    public void init() {
        System.out.println("**************Servlet is init**************");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDAO personDAO = new PersonDAO();
        request.setAttribute("people", personDAO.index());
        request.getRequestDispatcher(index).forward(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("**************Servlet is destroy**************");
    }
}
