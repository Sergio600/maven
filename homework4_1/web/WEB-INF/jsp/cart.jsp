<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<%@ page import="com.sergio.domain.Order"%>
<%@ page import="com.sergio.repository.OrderRepository"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Selected item</title>
</head>

<body>
    <div>Dear, ${name}, your order is: </div>

    <% int index =0; %>

     <c:forEach var="pickedProduct" items="${products}">
           <p> <%= ++index %>) ${pickedProduct.getName()} (${pickedProduct.getPrice()}$)</p>
     </c:forEach>


    <div>Total price is: ${totalPrice}</div>
</body>

</html>
