package com.mycompany.exp5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students/edit")
public class EditStudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = parseId(request.getParameter("id"));
        if (id < 1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "A valid student ID is required.");
            return;
        }
        try {
            Student student = studentDAO.findById(id);
            if (student == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found.");
                return;
            }
            request.setAttribute("student", student);
            request.getRequestDispatcher("/edit-student.jsp").forward(request, response);
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
}