package com.mycompany.exp5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students/update")
public class UpdateStudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = parseId(request.getParameter("id"));
        String name = value(request, "name");
        String course = value(request, "course");
        String email = value(request, "email");
        if (id < 1 || name.isBlank() || course.isBlank() || email.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "A valid ID, name, course, and email are required.");
            return;
        }

        try {
            if (!studentDAO.update(id, name, course, email)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found.");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/students?message=updated");
        } catch (SQLException exception) {
            ServletErrors.showDatabaseError(request, response, exception);
        }
    }

    private int parseId(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException exception) {
            return -1;
        }
    }

    private String value(HttpServletRequest request, String parameter) {
        String value = request.getParameter(parameter);
        return value == null ? "" : value.trim();
    }
}