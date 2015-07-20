<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ page import="ZIMG.models.Image" %>

<zimg:defaultLayout>
    <jsp:body>
        <%-- HEAD --%>
        <h1>Top 10 images</h1>

        <c:forEach items="${findTopTenImages}" var="image">
            <div class="container row">
                <div class="col-lg-2 col-md-2 col-xs-1">${user.id}</div>
                <div class="col-lg-3 col-md-3 col-xs-3">
                    <a href ="/user/${user.name}">${user.name}</a>
                </div>
            </div>
        </c:forEach>
    </jsp:body>
</zimg:defaultLayout>