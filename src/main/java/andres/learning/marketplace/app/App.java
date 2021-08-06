package andres.learning.marketplace.app;

import andres.learning.marketplace.user.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "App", value = "/App")
public class App extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controller controller = new Controller();
        PrintWriter output = response.getWriter();
        if(controller.checkValidUser(request, response)){
            output.println("WELCOME TO MY PC ACCESSORIES MARKETPLACE");
        }else{
            response.sendRedirect("Login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
