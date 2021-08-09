<%@ page import="andres.learning.marketplace.products.model.Product" %>
<%@ page import="andres.learning.marketplace.user.model.Client" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Product productPayed = (Product) request.getAttribute("orderedProduct");
        Client clientOrdering = (Client) request.getSession(true).getAttribute("clientData");
    %>

    <table border="1">
        <tr>
            <td style="text-align: center ">Client Name</td>
            <td style="text-align: center ">Client Last Name</td>
            <td style="text-align: center ">Client Email</td>
            <td style="text-align: center ">Client Address</td>
        </tr>
        <tr>
            <td style="text-align: center "><%=clientOrdering.getName()%></td>
            <td style="text-align: center "><%=clientOrdering.getLastName()%></td>
            <td style="text-align: center "><%=clientOrdering.getEmail()%></td>
            <td style="text-align: center "><%=clientOrdering.getAddress()%></td>
        </tr>
        <tr><td colspan="20"><br/></td></tr>
        <tr>
            <td style="text-align: center ">Product Name</td>
            <td style="text-align: center ">Product Price</td>
        </tr>
        <tr>
            <td style="text-align: center "><%=productPayed.getName()%></td>
            <td style="text-align: center "><%=productPayed.getPrice()%></td>
        </tr>
    </table>
</body>
</html>
