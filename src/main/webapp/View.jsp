<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="andres.learning.marketplace.products.model.Product"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="andres.learning.marketplace.user.model.Client" %>
<html>
    <head>
        <title>Marketplace</title>
    </head>
    <body style="text-align: center">
        <%
            Client client = (Client)request.getSession(true).getAttribute("clientData");
            ArrayList<Product> allProducts = new ArrayList<Product>();
        %>
                <h4 style="text-align: left">Client Name: <%=client.getName()%></h4>
                <h4 style="text-align: left">Client Username: <%=client.getUsername()%></h4>
                <h4 style="text-align: left">Client Email: <%=client.getEmail()%></h4>
        <%

            allProducts = (ArrayList<Product>)request.getAttribute("products");
            for (Product product: allProducts) {
                String productName = product.getName();
                String picturePath = product.getPhotoFileName();
                String description = product.getDescription();
                int price = product.getPrice();
        %>

                <h1><%=productName%></h1>
                <image src="pictures/<%=picturePath%>" width="200"/>
                    <form action="Product">  <!--AppOrderController-->
                        <input type="hidden" name="product" value="<%=product.getId()%>"/>
                        <input type="submit" value="Go to order😁😁"/>
                    </form>
                <br>
                <br>
        <%    }%>

    </body>
</html>
