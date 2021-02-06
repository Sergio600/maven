<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import="com.sergio.domain.Order"%>
<%@ page import="com.sergio.service.OrderService"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  <%Order order = OrderService.createOrder(session.getAttribute("customer").toString());%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <div>Welcome, <%=order.getCustomer()%>!!!</div>
    <div>Make your order!</div>
    <div>

         <c:forEach var="productOfCustomer" items="${order.getProducts()}">
                <p>${productOfCustomer.getName()} (${productOfCustomer.getPrice}$)</p>
         </c:forEach>

    </div>

    <div>
        <form method="POST" action="cart">

            <select name="goods" size="1">

                <c:forEach var="product" items="${products}">
                    <option value="${product.key}"> ${product.key} (${product.value}$) </option>
                </c:forEach>

             </select>

            <input type="hidden" name="id" value="<%=order.getId()%>"></input>

            </br>

            <input type="submit" value="add item"></input>
        </form>

            <form method="POST" action="cart">
                <input type="submit" value="submit"></input>
                <input type="hidden" name="id" value="<%=order.getId()%>"></input>
            </form>

    </div>
</body>

</html>
