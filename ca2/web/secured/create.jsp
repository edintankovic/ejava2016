<%-- 
    Document   : create
    Created on : Nov 10, 2016, 5:34:01 AM
    Author     : edint_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

    <h1>Submit the post</h1>

    <form action="api/post" method="get">

    Title: <input type="text" name="title" /><br />
    Category:
    <select id = "category" name="category" onchange="selection_change()/>
      <option value="SOCIAL" selected>SOCIAL</option> 
      <option value="FOR_SALE">FOR SALE</option> 
      <option value="JOBS">JOBS</option> 
      <option value="TUITION">TUITION</option> 
    </select>
    <br />
    Content: <input type="text" name="content" /><br />
    <input onclick="sendText(document.getElementById('content').value)" type="submit" />
    </form>
    
    
        <div id="output"></div>
        <script type="text/javascript" src="websocket.js"></script>
    </body>
</html>
