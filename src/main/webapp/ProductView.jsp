<%@ page import="andres.learning.marketplace.products.model.Product" %>
<%@ page import="andres.learning.marketplace.user.model.Client" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>PRODUCT</title>
    </head>
    <body style="text-align: center">
        <%
            Client client = (Client)request.getSession(true).getAttribute("clientData");
        %>
        <h4 style="text-align: left">Client Name: <%=client.getName()%></h4>
        <h4 style="text-align: left">Client Username: <%=client.getUsername()%></h4>
        <h4 style="text-align: left">Client Email: <%=client.getEmail()%></h4>
        <%
            Product productFounded = (Product) request.getAttribute("productById");
            String productName = productFounded.getName();
            String picturePath = productFounded.getPhotoFileName();
            String description = productFounded.getDescription();
            int price = productFounded.getPrice();
        %>
        <h1><%=productName%></h1>
        <image src="pictures/<%=picturePath%>" width="150"/>
        <p>DESCRIPTION: <%=description%></p>
        <p>Price: <%=price%></p>
        <form action="Product" method="POST">  <!--AppOrderController-->
            <input type="hidden" name="productId" value="<%=productFounded.getId()%>"/>
            <input type="submit" value="Pay😁😁"/>
        </form>
    </body>
</html>
