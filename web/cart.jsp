<%-- 
    Document   : cart
    Created on : Jan 23, 2024, 12:54:02 PM
    Author     : HIEU
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <link href="css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="css/main.css" rel="stylesheet" type="text/css"/>

    <body>
        <jsp:include page="header.jsp" flush="true"/>
            <div class="main">
                <div class="cart-container">
                    <div id="cart">
                        <h2>Shopping Cart</h2>


                    <c:set value="0" var="totalPrice" />
                    <c:forEach var="cd" items="${requestScope.cartList}">
                        <div class="item">
                            <img src="${cd.imagePath}" alt="Card Image">
                            <div class="item-info">
                                <p>${cd.name}</p>
                            </div>
                            <div class="item-info">
                                <p>$${cd.price} x </p>
                            </div>
                            <div class="item-info">
                                <div class="cart-details-quantity">
                                    <form action="main" method="get" class="dec-button-form">
                                        <input type="hidden" name="cartID" value="${cd.cartID}">
                                        <input type="hidden" name="mobileID" value="${cd.mobileID}">
                                        <button ${ cd.getTotalQuantity() <= 1 ? "disabled" : ""} class="dec-button" type="submit" name="action" value="decreaseCartQuantity">-</button>
                                    </form>
                                    <span class="total-quantity">${cd.totalQuantity}</span>
                                    <form action="main" method="get" class="inc-button-form">
                                        <input type="hidden" name="cartID" value="${cd.cartID}">
                                        <input type="hidden" name="mobileID" value="${cd.mobileID}">
                                        <button class="inc-button" type="submit" name="action" value="increaseCartQuantity">+</button>
                                    </form>
                                </div>
                            </div>
                            <form action="main" method="post" class="item">
                                <input type="hidden" name="cartID" value="${cd.cartID}">
                                <input type="hidden" name="mobileID" value="${cd.mobileID}">
                                <button class="delete-button" type="submit" name="action" value="deleteFromCart" >X</button>
                            </form>
                        </div>
                        <c:set var="totalPrice" value="${totalPrice + cd.totalPrice}" />
                    </c:forEach>
                    <div class="total-price">
                        <p>Total: $${totalPrice}</p>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/main.js" type="text/javascript"></script>
    </body>
</html>
