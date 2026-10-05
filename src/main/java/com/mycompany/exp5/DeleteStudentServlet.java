package com.mycompany.exp5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students/delete")
public class DeleteStudentServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException | NullPointerException exception) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "A valid student ID is required.");
            return;
        }
        if (id < 1) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "A valid student ID is required.");
            return;
        }

        try {
            if (!studentDAO.delete(id)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found.");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/students?message=deleted");
        } catch (SQLException exception) {
            ServletErrors.showDatabaseError(request, response, exception);
        }
    }
}