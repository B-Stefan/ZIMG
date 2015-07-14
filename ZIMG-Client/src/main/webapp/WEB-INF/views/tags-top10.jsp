<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>
        <!--Header-->
        <div class="container">
            <h1>Top 10 tags</h1>
            <!-- Top 10 tags list -->
            <div class="row">
                <c:forEach items="${topTenTagList}" var="tag">
                    <div class="col-lg-2 col-md-2 col-xs-1">${tag.tag}</div>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</zimg:defaultLayout>
