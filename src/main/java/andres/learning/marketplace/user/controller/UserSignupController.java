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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@WebServlet(name = "SignUp", value = "/SignUp")
public class UserSignupController extends HttpServlet {

    @Resource(name = "jdbc/marketplace")
    DataSource connectionPool;

    ClientAuthenticationService service;
    public void init(){
        service = new ClientAuthenticationService(connectionPool);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Signup.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean createdUser = SignupUser(request, response);
        System.out.println("SIGNUP CONTROLLER POST: "+createdUser);
        System.out.println("SIGNUP CONTROLLER POST: "+ClientCredentials.checkClientAuthentication(request));
        if (createdUser) {
            response.sendRedirect("App");
        } else {
            PrintWriter output = response.getWriter();
            response.setContentType("text/html");
            output.println("Has occurred an error");
            output.println("<h2><a href=\"Signup.html\">Try again</a></h2>");
        }
    }

    private boolean SignupUser(HttpServletRequest request, HttpServletResponse response) {

        boolean createdUser = false;
        Client client = null;
        try {
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            request.setCharacterEncoding("UTF-8");
            String lastName = request.getParameter("lastName");
            request.setCharacterEncoding("UTF-8");
            String address = request.getParameter("address");
            request.setCharacterEncoding("UTF-8");
            String email = request.getParameter("email");
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            request.setCharacterEncoding("UTF-8");
            String password = request.getParameter("password");
            client = service.createClient(name, lastName, address, email, username, password);
            if (client != null) {
                response.addCookie(ClientCredentials.clientCookieValidation(client));
                ClientCredentials.clientSessionData(client, request);
                System.out.println("SIGNUP CONTROLLER: " + client);
                createdUser = true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return createdUser;
    }
}
