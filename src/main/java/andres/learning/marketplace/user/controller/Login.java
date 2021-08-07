package andres.learning.marketplace.user.controller;

import andres.learning.marketplace.user.model.ResponseUser;
import andres.learning.marketplace.user.service.ClientAuthenticationService;
import andres.learning.marketplace.user.service.ClientCredentials;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    @Resource(name = "jdbc/marketplace")
    DataSource connectionPool;
    ClientAuthenticationService service;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Login.html").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        service = new ClientAuthenticationService(connectionPool);
        ResponseUser userToLogin = loginUser(request, response);
        if (userToLogin != null) {
            response.addCookie(ClientCredentials.createClientCredentials(userToLogin));
            response.sendRedirect("App");
        } else {
            response.sendRedirect("Signup.html");
        }
    }

    private ResponseUser loginUser(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        ResponseUser userToLogin = null;
        try {
            userToLogin = service.userLogin(username, password);
        } catch (Exception e) {
        }
        return userToLogin;
    }

}

