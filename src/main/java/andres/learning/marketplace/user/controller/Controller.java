package andres.learning.marketplace.user.controller;

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


@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
    /*I noticed that the pool have to be inside a Servlet
    When I try to create inside a  JAVA normal class, it does not work
    @Resource(name = "jdbc/users")
    DataSource connectionPool;*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter output = response.getWriter();
        output.println("<html><body style='text-align: center;'");

        int productId = Integer.parseInt(request.getParameter("product"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart",
                    "root", "mysqlandres");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE productId=?;");
            preparedStatement.setInt(1, productId);
            ResultSet result=preparedStatement.executeQuery();
            if(result.next()){
                output.println("<h1>PRODUCT</h1>");
                String productName = result.getString("productName");
                String description = result.getString("description");
                String price = result.getString("price");
                String picturePath = result.getString("fileName");

                output.println("<h3>"+productName+"</h3>");
                output.println("<image src='pictures/"+picturePath+"' width=150 />");
                output.println("<p>DESCRIPTION: "+description+"</p>");
                output.println("<p>PRICE: "+price+"</p>");
            }
            else{
                output.println("<h3>Picture not found</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        output.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static boolean checkValidUser(HttpServletRequest request, HttpServletResponse response) {
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
