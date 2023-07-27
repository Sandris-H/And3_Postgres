package com.example.test3.servlet;

import com.example.test3.dao.PersonDAO;
import com.example.test3.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddPersonServlet", value = "/add")
public class AddPersonServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDAO personDAO = new PersonDAO();
        request.setCharacterEncoding("UTF8");
        if (!requestIsValid(request)) {
            doGet(request, response);
        } else {
            final String name = request.getParameter("name");
            final String surname = request.getParameter("surname");
            final String age = request.getParameter("age");

            final Person person = new Person(name, surname, Integer.parseInt(age));
            personDAO.save(person);
            response.sendRedirect(request.getContextPath() + "/");
        }

    }

    private boolean requestIsValid(final HttpServletRequest request) {
        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String age = request.getParameter("age");
        return name != null && !name.isEmpty() &&
                surname != null && surname.length() > 0 &&
                age != null && age.length() > 0 &&
                age.matches("[+]?\\d+");
    }
}
