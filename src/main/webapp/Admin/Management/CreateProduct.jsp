<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Product</title>
</head>
    <body style="text-align: center">
        <form action="Options" method="POST" enctype="multipart/form-data">
            <input type="hidden" name="operation" value="CREATE"/>
            <label for="productName">Product Name</label>
            <input type="text" name="productName" required=""/>
            <br>
            <label for="productImage">Image</label>
            <input type="file" name="productImage" required=""/>
            <br>
            <label for="productDescription">Description</label>
            <input type="text" name="productDescription" required=""/>
            <br>
            <label for="productPrice">Price</label>
            <input type="text" name="productPrice" required="">
            <br>
            <input type="submit">
        </form>
    </body>
</html>
