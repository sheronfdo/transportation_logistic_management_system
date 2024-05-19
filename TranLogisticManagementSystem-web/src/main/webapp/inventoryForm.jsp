<%--
  Created by IntelliJ IDEA.
  User: Jamith
  Date: 5/19/2024
  Time: 3:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inventory Form</title>
</head>
<body>
<form action="inventory/new" method="post">
    <table>
        <tr>
            <th>Name</th>
            <td>
                <input type="text" name="itemName">
            </td>
        </tr>
        <tr>
            <th>Price</th>
            <td>
                <input type="text" name="itemPrice">
            </td>
        </tr>
        <tr>
            <th>Quantity</th>
            <td>
                <input type="text" name="itemQty">
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="ADD ITEM">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
