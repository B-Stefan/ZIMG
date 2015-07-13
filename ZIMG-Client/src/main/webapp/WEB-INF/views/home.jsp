<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>
        <!--Header-->
        <div class="container">
            <h1>Hello, ${name}!</h1>
            <!--Images -->
            <c:forEach items="${userList}" var="user">
                <div class="container row">
                    <div class="col-lg-2 col-md-2 col-xs-1">${user.id}</div>
                    <div class="col-lg-3 col-md-3 col-xs-3">
                        <a href ="/user/${user.name}">${user.name}</a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</zimg:defaultLayout>
