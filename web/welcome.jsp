<%-- 
    Document   : welcome
    Created on : Jan 17, 2024, 10:20:14 PM
    Author     : HIEU
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:include page="header.jsp"/>
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
                    <c:forEach var="m" items="${requestScope.mobileList}">
                        <form class="card" action="main" method="get">
                            <div class="card-image"><img src="${m.imagePath}" alt="Card Image"></div>
                            <div class="card-content">
                                <h2 class="card-title" >${m.name}</h2>
                                <p class="card-description">${m.description}</p>
                                <p class="card-price">${m.price}$</p>
                                <input type="hidden" name="mobileID" value="${m.mobileID}">
                                <button class="card-button" type="submit" name="action" value="addToCart">Add to cart</button>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
        <script src="js/main.js" type="text/javascript"></script>
    </body>
</html>
