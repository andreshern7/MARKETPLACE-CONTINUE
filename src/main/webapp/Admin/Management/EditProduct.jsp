<%@ page import="andres.learning.marketplace.products.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product</title>
</head>
    <body style="text-align: center">
        <%
            String productId = request.getParameter("id");
            String productName = request.getParameter("productName");
            String productPictureFile = request.getParameter("productPictureFile");
            String productDescription = request.getParameter("productDescription");
            String productPrice = request.getParameter("productPrice");
            System.out.println(productDescription);
        %>
        <form action="Options" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="operation" value="EDIT"/>
            <input type="hidden" name="id" value="<%=productId%>" required=""/>
            <label for="productName">Product Name</label>
            <input type="text" name="productName" value="<%=productName%>" required=""/>
            <br>
            <label for="productImage">Image</label>
            <input type="file" name="productImage" required=""/>
            <br>
            <label for="productDescription">Description</label>
            <input type="text" name="productDescription" value="<%=productDescription%>" required=""/>
            <br>
            <label for="productPrice">Price</label>
            <input type="text" name="productPrice" value="<%=productPrice%>" required="">
            <br>
            <input type="submit">
        </form>
    </body>
</html>
