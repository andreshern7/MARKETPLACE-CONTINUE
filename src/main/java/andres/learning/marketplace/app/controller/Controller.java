package andres.learning.marketplace.app.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet {
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
                String productName = result.getString("name");
                String picturePath = result.getString("photoFileName");
                String description = result.getString("description");
                String price = result.getString("price");

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

}
