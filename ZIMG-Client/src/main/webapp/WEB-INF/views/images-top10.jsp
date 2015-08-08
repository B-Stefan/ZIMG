<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ page import="ZIMG.models.Image" %>

<zimg:defaultLayout>
    <jsp:body>
        <%-- HEAD --%>
        <div class="container" id="overview">
            <h1>Top 10 images</h1>
            <c:forEach items="${images}" var="image">
                <div class="row">
                    <div class="col-xs-8 col-md-8">
                        <zimg:imagePreview image="${image}"/>
                    </div>
                    <div class="col-xs-4 col-md-4">
                     <zimg:imageInfoBox image="${image}"/>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</zimg:defaultLayout>