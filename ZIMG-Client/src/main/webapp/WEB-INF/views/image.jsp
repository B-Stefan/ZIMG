<%@ page session="true"%>
<%@ page import="ZIMG.models.User" %>
<%@ page import="ZIMG.models.Image" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<zimg:defaultLayout>
    <jsp:body>
        <!--Header-->
        <div class="container" id="imageDetail">
            <!--Images -->
            <div class="row">
                <div class="col-xs-8 col-md-8">
                    <a href="/image/${image.id}" id="imageLink">
                        <img class="img-responsive img-rounded" src="/uploads/${image.filename}"/>
                        <div id="mark-as-favorite" ${isFavorite ? "class=\"marked\"" : "" }><span class="glyphicon glyphicon-star"></span></div>
                    </a>

                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <h3>${CommentHeadline}</h3>
                        </div>
                    </div>

                    <c:forEach items="${image.comments}" var="comment">
                        <!-- Comment Entry One -->
                        <div class="row comment-entry">
                            <div class="col-xs-2 col-md-2">
                                <p><a href="/user/${comment.user.name}">${comment.user.name}</a></p>
                            </div>
                            <div class="col-xs-10 col-md-10">
                                <p>${comment.comment}</p>
                                <hr />
                            </div>
                        </div>
                    </c:forEach>

                    <div class="row">
                        <div class="col-xs-12 col-md-12">
                            <h4>Write a new comment:</h4>

                            <form method="post" action="/image/${image.id}/comment">
                                <input type="hidden"
                                       name="${_csrf.parameterName}"
                                       value="${_csrf.token}" />
                                <textarea class="form-control" id="comment-textarea" name="comment" rows="3"></textarea>

                                <button type="submit" class="btn btn-default" id="comment-submit">Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4 col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <span class="spacer">Uploader:</span><a href="/user/${image.uploader.name}"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${image.uploader.name}</a> <br/>
                            <hr />
                            <span class="spacer">Date:</span><span class="glyphicon glyphicon-time" aria-hidden="true"></span> <fmt:formatDate value="${image.createdAt}" pattern="dd.MM.yyyy - HH:mm" />

                            <hr />

                            <div class="upvote-button">
                                <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span> <span class="upvote-count">${fn:length(image.upvotes)}</span> Upvotes
                            </div>

                            <hr />
                            <div id="tag-box">
                                <c:forEach items="${image.tags}" var="tag">
                                    <div class="tag"><span class="glyphicon glyphicon-tag"></span>${tag.tag}</div>
                                </c:forEach>
                            </div>

                            <input type="text" class="form-control" id="add-tag-textfield" placeholder="Add new Tag ...">
                        </div>
                    </div>
                </div>
            </div>


        </div>
    </jsp:body>
</zimg:defaultLayout>
