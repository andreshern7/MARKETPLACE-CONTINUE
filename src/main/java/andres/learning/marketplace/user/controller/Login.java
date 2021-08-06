package andres.learning.marketplace.user.controller;

import andres.learning.marketplace.user.model.ResponseUser;
import andres.learning.marketplace.user.service.DataProcessing;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Resource(name = "jdbc/clients")
    DataSource connectionPool;
    DataProcessing model;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
        response.setContentType("text/plain");
        response.getWriter().println("HELLO FROM LOGIN CONTROLLER");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Controller.checkValidUser(request, response)) {
            response.sendRedirect("App"); //It's a client approach to forward
            //request.getRequestDispatcher("App").forward(request, response);
        } else {
            model = new DataProcessing(connectionPool);
            ResponseUser userToLogin = loginUser(request, response);
            if (!(userToLogin == null)) {
                Cookie validUser = new Cookie("valid.user", "VALID");
                validUser.setMaxAge(600);
                response.addCookie(validUser);
                response.sendRedirect("App");
            } else {
                response.sendRedirect("Signup.html");
            }

        }
    }

    private ResponseUser loginUser(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ResponseUser userToLogin = null;
        try {
            userToLogin = model.userLogin(username, password);
        } catch (SQLException e) {
        }
        return userToLogin;
    }

}

