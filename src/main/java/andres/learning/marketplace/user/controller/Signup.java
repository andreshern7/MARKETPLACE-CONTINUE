package andres.learning.marketplace.user.controller;

import andres.learning.marketplace.user.model.ResponseUser;
import andres.learning.marketplace.user.service.DataProcessing;

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
public class Signup extends HttpServlet {

    @Resource(name = "jdbc/clients")
    DataSource connectionPool;
    DataProcessing model;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        model = new DataProcessing(connectionPool);
        PrintWriter output = response.getWriter();
        output.println(SignupUser(request, response));
    }

    private ResponseUser SignupUser(HttpServletRequest request, HttpServletResponse response) {

        ResponseUser userResponse = null;
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

            userResponse = model.createClient(name, lastName, address, email, username, password);
            System.out.println(userResponse);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userResponse;
    }
}
