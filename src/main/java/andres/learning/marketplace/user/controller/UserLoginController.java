package andres.learning.marketplace.user.controller;

import andres.learning.marketplace.user.model.Client;
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
public class UserLoginController extends HttpServlet {

    @Resource(name = "jdbc/marketplace")
    DataSource connectionPool;

    ClientAuthenticationService service;
    public void init(){
        service = new ClientAuthenticationService(connectionPool);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Login.html").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Client userToLogin = loginUser(request);
        if (userToLogin != null) {
            response.addCookie(ClientCredentials.clientCookieValidation(userToLogin));
            ClientCredentials.clientSessionData(userToLogin, request);
            response.sendRedirect("App");
        } else {
            response.sendRedirect("Signup.html");
        }
    }

    private Client loginUser(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Client userToLogin = null;
        try {
            userToLogin = service.userLogin(username, password);
        } catch (Exception e) {
        }
        return userToLogin;
    }

}

