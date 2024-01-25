<%-- 
    Document   : cart
    Created on : Jan 23, 2024, 12:54:02 PM
    Author     : HIEU
--%>

<%@page import="java.util.List"%>
<%@page import="com.workshop.dto.CartDetails"%>
<%@page import="com.workshop.dto.User"%>
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
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("login");
                return;
            }
        %>
        <header class="header">
            <nav>
                <div class="logo"><a href="welcome"><span class="dat">DAT</span>store.</a></div>
                <div class="user-menu">
                    <span style="margin-left: 16px; color: green">${message}</span>
                    <a href="cart" style="margin-right: 16px">My Cart</a>
                    <img src="https://via.placeholder.com/30" alt="Profile Picture" class="profile-picture" id="profileButton">
                </div>
                <div class="collapse-menu" id="profileMenu">
                    <!-- Your collapsible menu content goes here -->
                    <!--                    <p>Menu Item 1</p>
                                        <p>Menu Item 2</p>-->
                    <p>Hi, <%= user.getFullName()%></p>
                    <form action="main" method="post">
                        <button type="sumit" name="action" value="logout" class="logout-button">Logout</button>
                    </form>
                </div>
            </nav>
        </header>
        <div class="main">
            <div class="cart-container">
                <div id="cart">
                    <h2>Shopping Cart</h2>

                    <%
                        List<CartDetails> cartList = (List) request.getAttribute("cartList");
                        float totalPrice = 0;
                        if (cartList != null) {
                            for (CartDetails cd : cartList) {
                                totalPrice += cd.getTotalPrice();
                    %>
                    <div class="item">
                        <img src="https://cdn-v2.didongviet.vn/files/media/catalog/product/i/p/iphone-14-512gb-likenew-didongviet_1.jpg" alt="Card Image">
                        <div class="item-info">
                            <p><%= cd.getName()%></p>
                        </div>
                        <div class="item-info">
                            <p>$<%= cd.getPrice()%> x </p>
                        </div>
                        <div class="item-info">
                            <div class="cart-details-quantity">
                                <form action="main" method="get" class="dec-button-form">
                                    <input type="hidden" name="cartID" value="<%= cd.getCartID()%>">
                                    <input type="hidden" name="mobileID" value="<%= cd.getMobileID()%>">
                                    <button <%= cd.getTotalQuantity() <= 1 ? "disabled" : "" %> class="dec-button" type="submit" name="action" value="decreaseCartQuantity">-</button>
                                </form>
                                <span class="total-quantity"><%=cd.getTotalQuantity()%></span>
                                <form action="main" method="get" class="inc-button-form">
                                    <input type="hidden" name="cartID" value="<%= cd.getCartID()%>">
                                    <input type="hidden" name="mobileID" value="<%= cd.getMobileID()%>">
                                    <button class="inc-button" type="submit" name="action" value="increaseCartQuantity">+</button>
                                </form>
                            </div>
                        </div>
                        <form action="main" method="post" class="item">
                            <input type="hidden" name="cartID" value="<%= cd.getCartID()%>">
                            <input type="hidden" name="mobileID" value="<%= cd.getMobileID()%>">
                            <button class="delete-button" type="submit" name="action" value="deleteFromCart" >X</button>
                        </form>
                    </div>
                    <%      }
                        }
                    %>
                    <div class="total-price">
                        <p>Total: $<%= totalPrice%></p>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/main.js" type="text/javascript"></script>
    </body>
</html>
