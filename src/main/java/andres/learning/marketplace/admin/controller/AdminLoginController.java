package andres.learning.marketplace.admin.controller;

import andres.learning.marketplace.admin.service.AdminAuthenticationService;
import andres.learning.marketplace.user.model.Client;
import andres.learning.marketplace.user.service.ClientCredentials;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AdminLogin", value = "/Admin/Login")
public class AdminLoginController extends HttpServlet {
    @Resource(name = "jdbc/marketplace")
    DataSource connectionPool;

    AdminAuthenticationService service;
    public void init(){
        service = new AdminAuthenticationService(connectionPool);
    }
    int loginAdminAttempt = 3;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ADMIN LOGIN CONTROLLER GET");
        request.getRequestDispatcher("Login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(loginAdminAttempt>0) {
            System.out.println("ADMIN LOGIN CONTROLLER POST");
            Client admin = loginAdmin(request);
            System.out.println(loginAdminAttempt);
            if (admin != null) {
                System.out.println("ADMIN LOGIN CONTROLLER CORRECT");
                response.addCookie(ClientCredentials.clientCookieValidation(admin));
                response.sendRedirect("Management");
            } else {
                loginAdminAttempt -= 1;
                request.getRequestDispatcher("Login.html").forward(request, response);
            }
        }else{
            PrintWriter output = response.getWriter();
            output.print("<h1>Reset Admin Account</h1>");
            output.print("<p>Contact with the app creator to reestablish your account :v</p>");

        }
    }

    private Client loginAdmin(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Client userToLogin = null;
        try {
            userToLogin = service.userLogin(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userToLogin;
    }

}
