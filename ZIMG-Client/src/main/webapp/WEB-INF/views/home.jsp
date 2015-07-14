<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>
        <%-- HEAD --%>
        <div class="container" id="overview">
            <c:forEach items="${imageList}" var="image">
                <div class="row">
                    <div class="col-xs-8 col-md-8">
                        <a href="/image/${image.id}"><img class="img-responsive img-rounded overview-entry" src="/resources/upload/${image.filename}" /></a>
                    </div>
                </div>
            </c:forEach>

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
