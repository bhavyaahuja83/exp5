package com.mycompany.exp5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students/add")
public class AddStudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = value(request, "name");
        String course = value(request, "course");
        String email = value(request, "email");
        if (name.isBlank() || course.isBlank() || email.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name, course, and email are required.");
            return;
        }

        try {
            studentDAO.insert(name, course, email);
            response.sendRedirect(request.getContextPath() + "/students?message=added");
        } catch (SQLException exception) {
            ServletErrors.showDatabaseError(request, response, exception);
        }
    }

    private String value(HttpServletRequest request, String parameter) {
        String value = request.getParameter(parameter);
        return value == null ? "" : value.trim();
    }
}