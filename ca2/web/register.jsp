<%-- 
    Document   : register
    Created on : Nov 9, 2016, 10:52:45 PM
    Author     : edint_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <p:panel header="Login From">
            <form method="POST" action="api/register">
                Username: <input type="text" name="userid" />
                Password: <input type="password" name="password" />
                Confirm Password: <input type="password" name="confirm_password" />
                <br />
                <input type="submit" value="Submit" />
            </form>
        </p:panel>
    </body>
</html>
