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
            <c:forEach items="${images}" varStatus="loop" var="image">
                <div class="row col-lg-12 col-md-12 col-sd-12">
                    <h4># ${loop.index+1}</h4>
                </div>
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