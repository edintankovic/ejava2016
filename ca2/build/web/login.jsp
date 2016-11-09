<%-- 
    Document   : login
    Created on : Nov 9, 2016, 10:17:09 PM
    Author     : edint_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <h:head>
        <title>Login Form</title>
    </h:head>
    <h:body>
        <p:panel header="Login From">
            <form method="POST" action="j_security_check">
                Username: <input type="text" name="j_username" />
                Password: <input type="password" name="j_password" />
                <br />
                <input type="submit" value="Login" />
                <input type="reset" value="Reset" />
            </form>
        </p:panel>
    </h:body>
</html>
