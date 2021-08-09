package andres.learning.marketplace.app.controller;

import andres.learning.marketplace.app.service.AppOrderService;
import andres.learning.marketplace.products.model.Product;
import andres.learning.marketplace.products.service.ProductService;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "Controller", value = "/Product")
public class AppOrderController extends HttpServlet {
    @Resource(name="jdbc/marketplace")
    DataSource connectionPool;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductService service = new ProductService(connectionPool);
        int productId = Integer.parseInt(request.getParameter("product"));
        request.setAttribute("productById", service.getById(productId));
        request.getRequestDispatcher("ProductView.jsp").forward(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{

        AppOrderService service = new AppOrderService(connectionPool);
        int clientId = 1;
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product orderedProduct = service.createOrder(clientId, productId);
        request.setAttribute("orderedProduct", orderedProduct);
        request.getRequestDispatcher("OrderView.jsp").forward(request, response);
    }
}
