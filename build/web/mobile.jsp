<%-- 
    Document   : mobile
    Created on : Jan 20, 2024, 2:56:04 PM
    Author     : HIEU
--%>

<%@page import="com.workshop.dto.Mobile"%>
<%@page import="java.util.List"%>
<%@page import="com.workshop.dao.DAO"%>
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
            } else if (user.getRole() != 2) {
                response.sendError(401);
                return;
            }
        %>
        <header class="header">
            <nav>
                <div class="logo"><a href="welcome"><span class="dat">DAT</span>store.</a></div>
                <img src="https://via.placeholder.com/30" alt="Profile Picture" class="profile-picture" id="profileButton">
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
            <div class="mobile-sidebar">

                <div class="add-mobile-sidebar">
                    <h2>Add a mobile</h2>
                    <form action="main" method="post" class="add-mobile-form">
                        <label for="mobileID">Mobile ID:</label>
                        <input type="text" id="mobileID" name="mobileID" required>

                        <label for="mobileName">Name:</label>
                        <input type="text" id="mobileName" name="name" required>

                        <label for="mobilePrice">Price:</label>
                        <input type="number" min="0" max="9999" id="mobilePrice" name="price" required>

                        <label for="mobileQuantity">Quantity:</label>
                        <input type="number" min="0" max="1000" id="mobileQuantity" name="quantity" required>                    

                        <label for="yearOfProduction">Year of production:</label>
                        <input type="number" min="0" id="yearOfProduction" name="yearOfProduction" required>                    

                        <label for="mobileDescription">Description:</label>
                        <textarea id="mobileDescription" name="description" rows="4"></textarea>

                        <div class="not-sale">
                            <label for="notSale">Not Sale?</label>
                            <input type="checkbox" id="notSale" name="notSale" value="true">
                        </div>

                        <button type="submit" name="action" value="addMobile">Add Product</button>
                    </form>
                </div>
            </div>
            <div class="mobile-main">
                <form action="main" method="get" class="search-mobile-form">
                    <input type="text" class="search-input" placeholder="Search..." name="q">
                    <button type="submit" name="action" value="searchMobile" class="search-button">Search</button>
                    <span style="margin-left: 16px; color: green">${message}</span>
                </form>
                <table class="mobile-table">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>ID</th> 
                            <th>Name</th> 
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Year of production</th>
                            <th>Description</th> 
                            <th>Not sale?</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count = 0;
                            List<Mobile> list = (List) request.getAttribute("mobileList");
                            if (list != null)
                                for (Mobile m : list) {
                                    count++;
                        %>
                    <form action="main" method="post" class="update-mobile-form">
                        <tr>
                            <td><%= count%></td>
                            <td><%= m.getMobileID()%></td>
                            <td><input type="text" name="name" value="<%= m.getName()%>"></td>
                            <td><input type="number" min="0" max="9999" name="price" value="<%= m.getPrice()%>"></td>
                            <td><input type="number" min="0" max="1000" name="quantity" value="<%= m.getQuantity()%>"></td>
                            <td><input type="number" min="0" name="yearOfProduction" value="<%= m.getYearOfProduction()%>"></td>
                            <td><input type="text" name="description" value="<%= m.getDescription()%>"></td>
                            <td><input type="checkbox" name="notSale" value="true" <%= m.getNotSale() == 1 ? "checked" : ""%>></td>
                            <td>
                                <input type="hidden" name="mobileID" value="<%= m.getMobileID()%>">
                                <a href="main?action=deleteMobile&mobileID=<%= m.getMobileID()%>" style="color: #f00;">X</a>
                            </td>
                            <td><button type="submit" name="action" value="updateMobile">Update</button></td>
                        </tr>
                    </form>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="js/main.js" type="text/javascript"></script>
    </body>
</html>
