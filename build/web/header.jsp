<%-- 
    Document   : header
    Created on : Feb 3, 2024, 11:42:47 AM
    Author     : HIEU
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.workshop.dao.DAO"%>
<%@page import="com.workshop.dto.Cart"%>
<%@page import="com.workshop.dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.user}" />
        <c:set var="totalQuantity" value="0"/>
        <c:choose>
            <c:when test="${user ne null}">
                <c:set var="cart" value="${DAO.getCart(user.userID)}" />
                <c:if test="${cart ne null}">
                    <c:set var="totalQuantity" value="${cart.totalQuantity}" />
                </c:if>
            </c:when>
            <c:otherwise>
                <jsp:forward page="login.jsp"/>
            </c:otherwise>
        </c:choose>

        <header class="header">
            <nav>
                <div class="logo"><a href="welcome"><span class="dat">DAT</span>store.</a></div>
                <div class="user-menu">
                    <a href="cart" style="margin-right: 16px">My Cart (${totalQuantity})</a>
                    <img src="https://via.placeholder.com/30" alt="Profile Picture" class="profile-picture" id="profileButton">
                </div>
                <div class="collapse-menu" id="profileMenu">
                    <!-- Your collapsible menu content goes here -->
                    <p>${user.fullName}</p>
                    <form action="main" method="post">
                        <button type="sumit" name="action" value="logout" class="logout-button">Logout</button>
                    </form>
                </div>
            </nav>
        </header>
    </body>
</html>
