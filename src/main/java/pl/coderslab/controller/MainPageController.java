package pl.coderslab.controller;

import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "SolutionController", urlPatterns = "/")
public class SolutionController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            String parameter = getServletContext().getInitParameter("number-solutions");
            List<Solution> solutions = Solution.loadAll(Integer.parseInt(parameter));
            request.setAttribute("solutions",solutions);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
