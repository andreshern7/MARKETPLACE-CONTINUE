package andres.learning.marketplace.admin.controller;

import andres.learning.marketplace.admin.service.AdminOperationsService;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;
import java.io.IOException;

@WebServlet(name = "AdminOptions", value = "/Admin/Management/Options")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 3, // 3MB   //1024 1KB    1024 * 1024 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

public class AdminOptions extends HttpServlet {

    @Resource(name = "jdbc/marketplace")
    DataSource connectionPool;
    AdminOperationsService service;
    public void init(){
        service = new AdminOperationsService(connectionPool);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("operation").equals("EDIT")) {
            response.getWriter().print(request.getParameter("id"));
            response.getWriter().print("\n" + request.getParameter("productName"));
            response.getWriter().print("\n" + request.getParameter("productPictureFile"));
            response.getWriter().print("\n" + request.getParameter("productDescription"));
            response.getWriter().print("\n" + request.getParameter("productPrice"));
            request.getRequestDispatcher("EditProduct.jsp").forward(request, response);
        }else{
            int productId = Integer.parseInt(request.getParameter("id"));
            service.deleteProduct(productId);
            response.getWriter().print("Product with id:"+productId+" has been deleted correctly");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("operation").equals("CREATE")) {
            String productName = request.getParameter("productName");
            // Receive file uploaded to the Servlet from the HTML5 form
            Part productImageFile = request.getPart("productImage");

            String productDescription = request.getParameter("productDescription");
            int productPrice = Integer.parseInt(request.getParameter("productPrice"));

            System.out.println(productName);
            System.out.println(productDescription);
            System.out.println(productPrice);

            service.createProduct(productName, productImageFile, productDescription, productPrice);

            response.getWriter().print("The file uploaded successfully.");
        }else {
            int productId = Integer.parseInt(request.getParameter("id"));
            String productName = request.getParameter("productName");
            Part productImageFile = request.getPart("productImage");
            String productDescription = request.getParameter("productDescription");
            int productPrice = Integer.parseInt(request.getParameter("productPrice"));

            service.editProduct(productId, productName, productImageFile, productDescription, productPrice);
        }
            response.sendRedirect("/marketplace/Admin/Management");
    }



}
