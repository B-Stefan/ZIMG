<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>


        <!--Header-->
        <div class="container">
            <h1>Top 5 users</h1>

            <%--<h1>${user.name}'s uploads:</h1>--%>
            <%--<p>${fn:length(user.images)}</p>--%>
            <!-- Top 5 user list -->
            <div class="row">
                <c:forEach items="${topFiveUserList}" var="user">
                    <div class="container row">
                        <div class="col-lg-3 col-md-3 col-xs-3">
                            <a href ="/user/${user.name}">${user.name}</a>
                        </div>
                        <div class="col-lg-2 col-md-2 col-xs-1">${user.images}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</zimg:defaultLayout>
