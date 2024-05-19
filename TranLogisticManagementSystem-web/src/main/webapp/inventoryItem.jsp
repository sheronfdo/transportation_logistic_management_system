<%--
  Created by IntelliJ IDEA.
  User: Jamith
  Date: 5/19/2024
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Inventory Items</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="5">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Stock Qty</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${itemList}">
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.stockQty}</td>
            <td>${item.price}</td>
            <td>
                <a href="order?itemId=${item.id}" onclick="return confirm('Are you sure?')">Order</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
