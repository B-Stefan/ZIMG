<%@ page session="true"%>
<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<zimg:defaultLayout>
    <jsp:body>
        <%-- HEAD --%>
        <div class="container" id="overview">
            <c:forEach items="${imagePage.content}" var="image">
                <div class="row">
                    <div class="col-xs-8 col-md-8">
                       <zimg:imagePreview image="${image}"/>
                    </div>
                    <div class="col-xs-4 col-md-4">
                        <zimg:imageInfoBox image="${image}"/>
                    </div>
                </div>
            </c:forEach>
            <div class="row">
                <div class="col-xs-12 col-md-12">
                    <nav>
                        <ul class="pager">
                            <li class="previous ${pagePreviousDisable ? "disabled" : "" }"><a href="/home/${pagePrevious}">Previous</a></li>
                            <li class="next ${pageNextDisable ? "disabled" : "" }"><a href="/home/${pageNext}">Next</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </jsp:body>
</zimg:defaultLayout>
