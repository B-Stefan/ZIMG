<%@ page session="true"%>
<%@ page import="ZIMG.models.User" %>
<%@ page import="ZIMG.models.Image" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-12">
                    <h1>Images for ${tag.tag}:</h1>
                </div>
            </div>
            <div class="row">
                <c:forEach items="${tag.images}" var="image">
                    <div class="col-xs-6 col-md-3">
                        <zimg:imagePreview className="thumbnail" image="${image}"/>
                    </div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</zimg:defaultLayout>
