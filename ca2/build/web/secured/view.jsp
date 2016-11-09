<%-- 
    Document   : view
    Created on : Nov 10, 2016, 5:33:39 AM
    Author     : edint_000
--%>

<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Show All Users</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>User Id</th>
                <th>Title</th>
                <th>Category</th>
                <th>Content</th>
                <th>Time</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${post}" var="user">
                <tr>
                    <td><c:out value="${post.userid}" /></td>
                    <td><c:out value="${post.title}" /></td>
                    <td><c:out value="${post.category}" /></td>
                    <td><c:out value="${post.content}" /></td>
                    <td><fmt:formatDate pattern="yyyy-MMM-dd" value="${post.dt}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <p><a href="create.jsp">Add Post</a></p>
</body>
</html>