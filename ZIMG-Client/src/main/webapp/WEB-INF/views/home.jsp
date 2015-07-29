<%@ page session="true"%>
<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>
        <%-- HEAD --%>
        <div class="container" id="overview">
            <c:forEach items="${imagePage.content}" var="image">
                <div class="row">
                    <div class="col-xs-8 col-md-8">
                       <zimg:imagePreview image="${image}"/>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</zimg:defaultLayout>
