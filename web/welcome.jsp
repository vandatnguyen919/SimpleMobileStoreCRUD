<%-- 
    Document   : welcome
    Created on : Jan 17, 2024, 10:20:14 PM
    Author     : HIEU
--%>

<%@page import="com.workshop.dao.DAO"%>
<%@page import="com.workshop.dto.Mobile"%>
<%@page import="java.util.List"%>
<%@page import="com.workshop.dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/main.css" rel="stylesheet" type="text/css"/>
    </head>
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
                    <a href="cart" style="margin-right: 16px">My Cart (${message})</a>
                    <img src="https://via.placeholder.com/30" alt="Profile Picture" class="profile-picture" id="profileButton">
                </div>
                <div class="collapse-menu" id="profileMenu">
                    <!-- Your collapsible menu content goes here -->
                    <!--                    <p>Menu Item 1</p>
                                        <p>Menu Item 2</p>-->
                    <form action="main" method="post">
                        <button type="sumit" name="action" value="logout" class="logout-button">Logout</button>
                    </form>
                </div>

            </nav>
        </header>
        <div class="main">
            <div class="sidebar-filter">
                <form action="main" method="post" class="filter-form">

                    <div class="filter-section">
                        <h2 class="filter-title">Price Range</h2>
                        <label for="minPrice">Minimum Price:</label>
                        <input type="number" min="0" max="9999" name="minPrice" id="minPrice">
                        <br>
                        <label for="maxPrice">Maximum Price:</label>
                        <input type="number" min="0" max="9999" name="maxPrice" id="maxPrice">
                    </div>

                    <button type="submit" name="action" value="filterMobile">Apply Filters</button>
                </form>
            </div>
            <div class="card-container">
                <div class="card-list">
                    <%
                        List<Mobile> list = (List) request.getAttribute("mobileList");
                        if (list != null)
                            for (Mobile m : list) {
                    %>
                    <form class="card" action="main" method="get">
                        <img src="https://cdn-v2.didongviet.vn/files/media/catalog/product/i/p/iphone-14-512gb-likenew-didongviet_1.jpg" alt="Card Image">
                        <div class="card-content">
                            <h2 class="card-title" ><%= m.getName()%></h2>
                            <p class="card-description"><%= m.getDescription()%></p>
                            <p class="card-price"><%= m.getPrice()%>$</p>
                            <input type="hidden" name="mobileID" value="<%=m.getMobileID()%>">
                            <button class="card-button" type="submit" name="action" value="addToCart">Add to cart</button>
                        </div>
                    </form>
                    <%}%>
                </div>
            </div>
        </div>
        <script src="js/main.js" type="text/javascript"></script>
    </body>
</html>
