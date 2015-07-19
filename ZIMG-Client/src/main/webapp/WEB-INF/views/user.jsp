<%@ page import="ZIMG.models.User" %>
<%@ page import="ZIMG.models.Image" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>
        <%-- HEAD --%>
        <h1>${user.name}'s uploads:</h1>

        <%-- IMAGES --%>
        <div class="container">
            <p>${fn:length(user.images)}</p>
            <div class="row">
                <c:forEach items="${images}" var="image">
                    <div class="col-xs-6 col-md-3">
                        <a href="/image/${image.id}" class="thumbnail"><img src="/resources/upload/${image.filename}" /></a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</zimg:defaultLayout>
