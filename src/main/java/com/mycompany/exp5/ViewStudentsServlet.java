package com.mycompany.exp5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/students")
public class ViewStudentsServlet extends HttpServlet {
    private final StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("students", studentDAO.findAll());
            request.getRequestDispatcher("/students.jsp").forward(request, response);
        } catch (SQLException exception) {
            ServletErrors.showDatabaseError(request, response, exception);
        }
    }
}