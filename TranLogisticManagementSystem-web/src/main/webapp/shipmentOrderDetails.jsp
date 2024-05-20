<%--
  Created by IntelliJ IDEA.
  User: Jamith
  Date: 5/20/2024
  Time: 2:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Shipment Details</title>
</head>
<body>

<h1>${shipmentStartResponse.description}</h1>
<h1>Shipment Id - ${shipmentStartResponse.shipmentId}</h1>

<table border="1" cellspacing="0" cellpadding="5">
    <thead>
    <tr>
        <th>ID</th>
        <th>Order</th>
        <th>Delivery Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${shipmentStartResponse.shipmentOrderDataList}">
        <tr>
            <td>${list.id}</td>
            <td>${list.order}</td>
            <td>${list.deliveryStatus}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="route?shipmentId=${shipmentStartResponse.shipmentId}&lat=6.500&lon=6.500">Get Route Optimize Deliver Order List</a>
</body>
</html>
