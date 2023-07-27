package com.example.test3.servlet;

import com.example.test3.dao.PersonDAO;
import com.example.test3.model.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
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

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PersonDAO personDAO = new PersonDAO();
//        request.setCharacterEncoding("UTF8");
//        if (!requestIsValid(request)) {
//            doGet(request, response);
//        } else {
//            final String name = request.getParameter("name");
//            final String surname = request.getParameter("surname");
//            final String age = request.getParameter("age");
//
//            final Person person = new Person(name, surname, Integer.parseInt(age));
//            personDAO.save(person);
//            doGet(request, response);
//        }
//
//    }

    private boolean requestIsValid(final HttpServletRequest request) {
        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String age = request.getParameter("age");
        return name != null && !name.isEmpty() &&
                surname != null && surname.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches("[+]?\\d+");
    }

    @Override
    public void destroy() {
        System.out.println("**************Servlet is destroy**************");
    }
}
