<%--
  Created by IntelliJ IDEA.
  User: Jamith
  Date: 5/20/2024
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Route Optimized Orders</title>
</head>
<body>
<table border="1" cellspacing="0" cellpadding="5">
    <thead>
    <tr>
        <th>ID</th>
        <th>Date</th>
        <th>Customer Name</th>
        <th>Total Amount</th>
        <th>Address</th>
        <th>Latitude</th>
        <th>Longitude</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${routeOptimizedOrders}">
        <tr>
            <td>${item.id}</td>
            <td>${item.date}</td>
            <td>${item.user}</td>
            <td>${item.totalAmount}</td>
            <td>${item.address}</td>
            <td>${item.latitude}</td>
            <td>${item.longitude}</td>
            <td>${item.status}</td>
            <td>
                <a href="orderComplete?orderId=${item.id}" onclick="return confirm('Are you sure?')">Complete Order</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
