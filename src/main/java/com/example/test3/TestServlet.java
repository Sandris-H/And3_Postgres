package com.example.test3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "TestServlet", value = "/TestServlet")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Test for " + name + "</h1>");


        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int id = 0;
        String nname = " - ";
        int age = 0;
        String surname = " - ";
        try{
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://192.168.0.223:5432/people",
                    "postgres", ""
            );
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from people");
            rs.next();
            id = rs.getInt(1);
            nname = rs.getString(2);
            age = rs.getInt(3);
            surname = rs.getString(4);

        }catch (SQLException e){
            e.printStackTrace();
        }
        out.println("<h1>" + "id  " + "name  " + "age  " + "surname" + "</h1>");
        out.println("<h1>" + "----------------------------------------------------------" + "</h1>");
        out.println("<h1>" + id + "   " + nname + "   "  + age + "   "  + surname + "</h1>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
