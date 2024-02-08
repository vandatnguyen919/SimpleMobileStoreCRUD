<%-- 
    Document   : mobile
    Created on : Jan 20, 2024, 2:56:04 PM
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

                            <label for="imagePath">Image URL:</label>
                            <input type="text" id="imagePath" name="imagePath">

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
                            <th>Image URL</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="m" items="${requestScope.mobileList}" varStatus="loopStatus">
                        <form action="main" method="post" class="update-mobile-form">
                            <tr>
                                <td>${loopStatus.index + 1}</td>
                                <td>${m.mobileID}</td>
                                <td><input type="text" name="name" value="${m.name}"></td>
                                <td><input type="number" min="0" max="9999" name="price" value="${m.price}"></td>
                                <td><input type="number" min="0" max="1000" name="quantity" value="${m.quantity}"></td>
                                <td><input type="number" min="0" name="yearOfProduction" value="${m.yearOfProduction}"></td>
                                <td><input type="text" name="description" value="${m.description}"></td>
                                <td><input type="checkbox" name="notSale" value="true" ${m.notSale == 1 ? 'checked' : ''}></td>
                                <td><input type="text" name="imagePath" value="${m.imagePath}"></td>
                                <td>
                                    <input type="hidden" name="mobileID" value="${m.mobileID}">
                                    <c:url value="main" var="deleteURL">
                                        <c:param name="action" value="deleteMobile"/>
                                        <c:param name="mobileID" value="${m.mobileID}"/>
                                    </c:url>
                                    <a href="${deleteURL}" style="color: #f00;">X</a>
                                </td>
                                <td><button type="submit" name="action" value="updateMobile">Update</button></td>
                            </tr>
                        </form>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="js/main.js" type="text/javascript"></script>
    </body>
</html>
