<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <div>Welcome, ${name}!!!</div>
    <div>Make your order!</div>
    <div>
        <form method="POST" action="cart">
            <select name="goods" size="1">

               ${html}

             </select>
            <input type="hidden" name="id" value="1"></input>
            </br>
            <input type="submit"></input>
        </form>
    </div>
</body>

</html>
