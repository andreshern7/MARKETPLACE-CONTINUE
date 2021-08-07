package andres.learning.marketplace.user.service;

import andres.learning.marketplace.user.model.ResponseUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ClientCredentials extends HttpServlet {
    /*I noticed that the pool have to be inside a Servlet
    When I try to create inside a  JAVA normal class, it does not work
    @Resource(name = "jdbc/users")
    DataSource connectionPool;*/


    public static Cookie createClientCredentials(ResponseUser clientToVerify){
        Cookie validUser =null;
        if (!(clientToVerify == null)) {
            validUser = new Cookie("valid.user", "VALID");
            validUser.setMaxAge(600);
        }
        return validUser;
    }

    public static boolean checkClientAuthentication(HttpServletRequest request) {
        Cookie[] requestCookies = request.getCookies();
        boolean validUser = false;
        if (!(requestCookies == null)) {
            for (Cookie cookie : requestCookies) {
                if (cookie.getName().equals("valid.user")) {
                    if (cookie.getValue().equals("VALID")) {
                        validUser = true;
                    }
                }
            }
        }
        return validUser;
    }

}
