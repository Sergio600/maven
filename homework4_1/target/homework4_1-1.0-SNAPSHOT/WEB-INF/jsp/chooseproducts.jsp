<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import="com.sergio.domain.Order"%>
<%@ page import="com.sergio.service.OrderService"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <style>
        <%@include file="/WEB-INF/css/style.css"%>
     </style>

  <%Order order = OrderService.createOrGetOrder(session.getAttribute("customer").toString());%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Choose items</title>
</head>

<body>
    <div class="container">
        <div class="header">Welcome, <%=order.getCustomer()%>!!!</div>
        </br>
        <div>Make your order!</div>


        <div>
            <form method="post" id="addprod" action="chooseproducts">
                <select name="selected" size="1">
                    <c:forEach var="product" items="${products}">
                        <option value="${product.key}"> ${product.key} (${product.value}$) </option>
                    </c:forEach>

                 </select>

                <input type="hidden" name="id" value="<%=order.getId()%>"></input>
                <input type="submit" name="additem" value="add item"></input>

            </form>

                <form method="POST" id="addprod" action="cart">
                    <input type="submit" value="submit"></input>
                     <input type="submit" name="exit" value="exit"></input>
                    <input type="hidden" name="id" value="<%=order.getId()%>"></input>
                     <input type="hidden" name="customer" value="<%=order.getCustomer()%>"></input>
                </form>

            <div>
             <% int index =0; %>
                <c:forEach var="pickedProduct" items="${order.getProducts()}">
                    <p> ${pickedProduct.getName()} ${pickedProduct.getPrice()}</p>
                     <button type="submit" value="<%=index++%>" name="remove" form="addprod">remove</button>
                </c:forEach>
            </div>

        </div>

    </div>
</body>

</html>
