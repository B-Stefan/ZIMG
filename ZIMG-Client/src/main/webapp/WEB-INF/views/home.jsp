<%@ page import="ZIMG.models.User" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Sample Application</title>
</head>
    <body>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.name}</td>
            </tr>
        </c:forEach>
    </body>
</html>