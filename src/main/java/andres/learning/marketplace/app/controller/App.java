package andres.learning.marketplace.app.controller;

import andres.learning.marketplace.products.model.Product;
import andres.learning.marketplace.products.service.ProductService;
import andres.learning.marketplace.user.service.ClientCredentials;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "App", value = "/App")
public class App extends HttpServlet {
    @Resource(name = "jdbc/marketplace")
    DataSource connectionPool;
    ProductService service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (ClientCredentials.checkClientAuthentication(request)) {
            service = new ProductService(connectionPool);
            ArrayList<Product> productList = service.allProducts();
            System.out.println("APP CONTROLLER: "+productList);

            HttpSession session = request.getSession(true);
            System.out.println("APP CONTROLLER: "+session.getAttribute("clientData"));

            request.setAttribute("products", productList);
            request.getRequestDispatcher("/View.jsp").forward(request, response); //Always put / before url destination
        } else {
            response.sendRedirect("Login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
