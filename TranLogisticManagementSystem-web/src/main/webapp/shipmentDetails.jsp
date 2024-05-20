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
<table border="1" cellspacing="0" cellpadding="5">
    <thead>
    <tr>
        <th>ID</th>
        <th>Driver</th>
        <th>Vehicle</th>
        <th>Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="list" items="${shipmentList}">
        <tr>
            <td>${list.id}</td>
            <td>${list.driver}</td>
            <td>${list.vehicle}</td>
            <td>${list.date}</td>
            <td>
                <a href="shipmentStart?shipmentId=${list.id}" onclick="return confirm('Are you sure?')">Start Shipment</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
