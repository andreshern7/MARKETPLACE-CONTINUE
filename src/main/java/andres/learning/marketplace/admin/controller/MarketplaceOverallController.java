package andres.learning.marketplace.admin.controller;

import andres.learning.marketplace.products.model.Product;
import andres.learning.marketplace.products.service.ProductService;
import andres.learning.marketplace.user.service.ClientCredentials;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;

//@WebServlet(name = "MarketplaceOverallController", value = "/MarketplaceOverallController")
@WebServlet(name = "MarketplaceOverallController", value = "/Admin/Management")
public class MarketplaceOverallController extends HttpServlet {
    @Resource(name = "jdbc/marketplace")
    DataSource connectionPool;
    ProductService service;

    public void init(){
        service = new ProductService(connectionPool);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (ClientCredentials.checkClientAuthentication(request)) {
            ArrayList<Product> productList = service.allProducts();
            System.out.println("APP CONTROLLER: "+productList);
            request.setAttribute("products", productList);
            request.getRequestDispatcher("/Admin/Management/MarketplaceOverallView.jsp").forward(request, response); //Always put / before url destination
        } else {
            response.sendRedirect("Login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
