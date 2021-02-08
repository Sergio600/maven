<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import="com.sergio.domain.Order"%>
<%@ page import="com.sergio.repository.OrderRepository"%>
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
    <title>Selected item</title>
</head>

<body>
<div class="container">
    <div class="header">Dear, <%=order.getCustomer()%>, your order is: </div>

    <% int index =0; %>

     <c:forEach var="pickedProduct" items="${order.getProducts()}">
           <p> <%= ++index %>) ${pickedProduct.getName()} ${pickedProduct.getPrice()}$</p>
     </c:forEach>


    <div class="totalPrice">Total price is: <%=order.getTotalPrice()%>$</div>

    <form method="POST" action="/homework4_1">
           <input type="submit" name="add" value="add more"></input>
    </form>

     <form method="POST" action="cart">
           <input type="submit" name="exit" value="exit"></input>
     </form>

</div>
</body>

</html>
