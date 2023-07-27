package com.example.test3.servlet;

import com.example.test3.dao.PersonDAO;
import com.example.test3.model.Person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UpdatePersonServlet", value = "/update")
public class UpdatePersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String id = request.getParameter("id");

//        if (Utils.idIsInvalid(id, users)) {
//            resp.sendRedirect(req.getContextPath() + "/");
//            return;
//        }
        PersonDAO personDAO = new PersonDAO();
        final Person person = personDAO.show(Integer.parseInt(id));
        request.setAttribute("person", person);

        request.getRequestDispatcher("/WEB-INF/view/update.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        final String id = request.getParameter("id");
        final String name = request.getParameter("name");
        final String surname = request.getParameter("surname");
        final String age = request.getParameter("age");


        PersonDAO personDAO = new PersonDAO();
        final Person person = personDAO.show(Integer.parseInt(id));
        person.setName(name);
        person.setSurname(surname);
        person.setAge(Integer.parseInt(age));
        personDAO.update(person.getId(), person);

        response.sendRedirect("/");
    }
}
