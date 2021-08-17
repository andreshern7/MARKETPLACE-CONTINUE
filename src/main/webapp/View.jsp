<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="andres.learning.marketplace.products.model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="andres.learning.marketplace.user.model.Client" %>
<html>
<head>
    <title>Marketplace</title>
</head>
<link rel="stylesheet" type="text/css" href="style.css">
<body>

<%
    Client client = (Client) request.getSession(true).getAttribute("clientData");
    ArrayList<Product> allProducts;
%>
<div class="user-information">
<h4 style="text-align: left">Client Name: <%=client.getName()%></h4>
<h4 style="text-align: left">Client Username: <%=client.getUsername()%></h4>
<h4 style="text-align: left">Client Email: <%=client.getEmail()%></h4>
</div>
<div class="products">
<%

    allProducts = (ArrayList<Product>) request.getAttribute("products");
    for (Product product : allProducts) {
        String productName = product.getName();
        String picturePath = product.getPhotoFileName();
%>
    <div class="products__item">
        <h2><%=productName%></h2>
        <div class="products__item--image">
            <img src="pictures/<%=picturePath%>" width="200px" class="product-image">
        </div>
        <form action="Product">  <!--AppOrderController-->
            <input type="hidden" name="product" value="<%=product.getId()%>"/>
            <input type="submit" value="Go to order😁😁"/>
        </form>
    </div>
    <% }%>
</div>

</body>
</html>
