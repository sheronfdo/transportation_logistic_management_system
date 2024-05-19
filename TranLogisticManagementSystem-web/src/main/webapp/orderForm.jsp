<%--
  Created by IntelliJ IDEA.
  User: Jamith
  Date: 5/19/2024
  Time: 8:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Item Id - ${item}</title>
</head>
<body>
    <h2>Item Id - ${item.id}</h2>
    <h2>Item Name - ${item.name}</h2>
    <h2>Item Price - ${item.price}</h2>
    <form action="order" method="post">
        <input type="hidden" name="itemId" value="${item.id}">
        <table>
            <tr>
                <th>Quantity</th>
                <td>
                    <input type="text" name="itemQty" value="1">
                </td>
            </tr>
            <tr>
                <th>Address</th>
                <td>
                    <input type="text" name="orderAddress">
                </td>
            </tr>
            <tr>
                <th>Latitude</th>
                <td>
                    <input type="text" name="orderLatitude">
                </td>
            </tr>
            <tr>
                <th>Longitude</th>
                <td>
                    <input type="text" name="orderLongitude">
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="MAKE ORDER">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
