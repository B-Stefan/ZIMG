<%@ page session="true"%>
<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <span class="spacer">Uploader:</span><a href="/user/${image.uploader.name}"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>  ${image.uploader.name}</a> <br/>
                                <hr />
                                <span class="spacer">Date:</span><span class="glyphicon glyphicon-time" aria-hidden="true"></span> <fmt:formatDate value="${image.createdAt}" pattern="dd.MM.yyyy - HH:mm" />
                                <hr />
                                <div class="upvote-button">
                                    <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> 400 Upvotes
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</zimg:defaultLayout>
