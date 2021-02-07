<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import="com.sergio.domain.Order"%>
<%@ page import="com.sergio.repository.OrderRepository"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%Order order = OrderService.createOrGetOrder(session.getAttribute("customer").toString());%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Selected item</title>
</head>

<body>
    <div>Dear, <%=order.getCustomer()%>>, your order is: </div>

    <% int index =0; %>

     <c:forEach var="pickedProduct" items="${order.getProducts()}">
           <p> <%= ++index %>) ${pickedProduct.getName()} ${pickedProduct.getPrice()}$</p>
     </c:forEach>


    <div>Total price is: <%=order.getTotalPrice()%>$</div>
</body>

</html>
