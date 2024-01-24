<%-- 
    Document   : signup
    Created on : Jan 10, 2024, 10:44:51 PM
    Author     : HIEU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>My Web</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/reset.css" rel="stylesheet" type="text/css"/>
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="login-container">
            <h2>Sign Up</h2>
            <form action="main" method="post">
                <div class="form-group">
                    <label for="fullname">Full Name:</label>
                    <input type="text" id="fullname" name="fullName" required>
                </div>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="form-group">
                    <label for="con-password">Confirm password:</label>
                    <input type="password" id="con-password" name="conPassword" required>
                </div>
                <div class="notification" style="height: 18px; margin-bottom: 15px">
                    <p id="notify" style="color: red;">${notification}</p>
                </div>
                <div class="form-group submit-btn">
                    <a href="login">&lt;<span>Back</span></a>
                    <button type="submit" name="action" value="signup">Sign Up</button>
                </div>
            </form>
        </div>
        <script src="js/login.js" type="text/javascript"></script>
    </body>
</html>

