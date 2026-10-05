package com.mycompany.exp5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

final class ServletErrors {
    private ServletErrors() {
    }

    static void showDatabaseError(HttpServletRequest request, HttpServletResponse response,
                                  SQLException exception) throws ServletException, IOException {
        request.getServletContext().log("Student database operation failed", exception);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        request.setAttribute("errorMessage", "The student records could not be loaded or saved. "
                + "Check the Tomcat database configuration and server log.");
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}