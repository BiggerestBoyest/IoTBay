<%-- 
    Document   : index
    Created on : 18 Mar 2024, 1:15:51 pm
    Author     : thean
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1 align="center">IoTBay Login</h1>
        <div>
            <form action=".\account.jsp" align="center">
                <label for="username">Username:</label><br>
                <input type="text" id="Username" name="username"><br>
                <label for="pwd">Password:</label><br>
                <input type="password" id="pwd" name="pwd"><br>
                <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>
