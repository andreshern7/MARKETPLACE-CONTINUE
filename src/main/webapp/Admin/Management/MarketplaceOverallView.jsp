<%@ page import="andres.learning.marketplace.products.model.Product" %>
<%@ page import="andres.learning.marketplace.user.model.Client" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Marketplace Overall Information</title>
</head>
<body>
        <%
            ArrayList<Product> allProducts;
            String pictureRootPath = "http://localhost:9999/marketplace/";
        %>
        <table border="2">
            <tr>
                <td style="text-align: center ">Product Id</td>
                <td style="text-align: center ">Product Name</td>
                <td style="text-align: center ">Product Photo File</td>
                <td style="text-align: center ">Product Description</td>
                <td style="text-align: center ">Product Price</td>
                <td style="text-align: center ">Edit Product</td>
                <td style="text-align: center ">Delete Product</td>
            </tr>
        <%
            allProducts = (ArrayList<Product>) request.getAttribute("products");
            for (Product product : allProducts) {
                int productId= product.getId();
                String productName = product.getName();
                String pictureFileName = product.getPhotoFileName();
                String description = product.getDescription();
                int price = product.getPrice();
        %>
            <tr>
                <td style="text-align: center "><%=productId%></td>
                <td style="text-align: center "><%=productName%></td>
                <td style="text-align: center "><image src="<%=pictureRootPath%>pictures/<%=pictureFileName%>" width="100"/></td>
                <td style="text-align: center "><%=description%></td>
                <td style="text-align: center "><%=price%></td>
                <form action="Management/Options">
                    <input type="hidden" name="id" value="<%=productId%>">
                    <input type="hidden" name="productName" value="<%=productName%>">
                    <input type="hidden" name="productPictureFile" value="<%=pictureFileName%>">
                    <input type="hidden" name="productDescription" value="<%=description%>">
                    <input type="hidden" name="productPrice" value="<%=price%>">
                    <td style="text-align: center "><button name="operation" value="EDIT">Edit</button></td>
                    <!--<td style="text-align: center "><button ></button></td>-->
                    <td style="text-align: center "><button name="operation" value="DELETE">Delete</button></td>
                </form>
            </tr>
        <% }%>
        </tr>
        <tr><td colspan="20"><br/></td></tr>
        <tr>
        <tr>
            <!--<td><a href="/Admin/Management/CreateProduct.jsp">Link</a></td>  "http://localhost:9999/marketplace/Admin/Admin/Management/CreateProduct.jsp"-->
            <!--<td><a href="Admin/Management/CreateProduct.jsp">Link3</a></td>  "http://localhost:9999/marketplace/Admin/Admin/Management/CreateProduct.jsp" -->
            <!--<td><a href="/Management/CreateProduct.jsp">Link</a></td>    "http://localhost:9999/Management/CreateProduct.jsp" -->
            <td><a href="Management/CreateProduct.jsp">Create Product</a></td>      <!-- "http://localhost:9999/marketplace/Admin/Management/CreateProduct.jsp" WORKS -->

        </tr>
        </table>
        <!--<form id="editForm" action="Options">
            <input type="hidden" name="id" value="">
        </form>-->
</body>
</html>

