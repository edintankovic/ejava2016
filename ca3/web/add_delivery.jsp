<%-- 
    Document   : add_delivery
    Created on : Nov 12, 2016, 12:27:12 PM
    Author     : edint_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Delivery</title>
    </head>
    <body>
        <p:panel header="Add Delivery From">
            <form method="POST" action="api/delivery">
                Name: <input type="text" name="name" />
                <br/>
                Address: <input type="text" name="address" />
                <br/>
                Phone: <input type="text" name="phone" />
                <br />
                <input type="submit" value="Submit" />
            </form>
        </p:panel>
    </body>
</html>
