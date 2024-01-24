<%-- 
    Document   : login
    Created on : Jan 10, 2024, 10:42:49 PM
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
            <h2>Login</h2>
            <form action="main" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <div class="notification" style="height: 18px; margin-bottom: 15px">
                    <p id="notify" style="color: red;">${notification}</p>
                </div>
                <div class="form-group submit-btn">
                    <a href="signup">Not a member?</a>
                    <button type="submit" name="action" value="login">Login</button>
                </div>
            </form>
        </div>
        <script src="js/login.js" type="text/javascript"></script>
    </body>
</html>

