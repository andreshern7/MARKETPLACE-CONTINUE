<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="andres.learning.marketplace.products.model.Product"%>
<%@ page import="java.util.ArrayList" %>
<html>
    <head>
        <title>Marketplace</title>
    </head>
    <body style="text-align: center">
        <%!
            ArrayList<Product> allProducts = new ArrayList<Product>();
        %>
        <%
            allProducts = (ArrayList<Product>)request.getAttribute("products");
            for (Product product: allProducts) {
                String productName = product.getName();
                String picturePath = product.getPhotoFileName();
                String description = product.getDescription();
                int price = product.getPrice();
        %>
                <h1><%=productName%></h1>
                <image src="pictures/<%=picturePath%>" width="150"/>
                <p>DESCRIPTION: <%=description%></p>
                <p>Price: <%=price%></p>
                    <form action="Product">
                        <input type="hidden" name="product" value="<%=product.getId()%>"/>
                        <input type="submit" value="Go to order😁😁"/>
                    </form>
        <%    }%>

    </body>
</html>
