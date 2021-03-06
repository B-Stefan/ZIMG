<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<zimg:defaultLayout>
    <jsp:body>
        <%-- HEAD --%>

        <div class="container">
            <h1>Top 10 tags</h1>
            <!-- Top 10 tags list -->
            <div class="row">
                <div class="col-lg-6">

                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tag</th>
                                <th>Number of images</th>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <th>Actions</th>
                                </sec:authorize>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${topTenTagsList}" varStatus="loop" var="tag">
                                <tr>
                                    <td>${loop.index+1}</td>
                                    <td><a href="/tag/${tag.tag}">${tag.tag}</a> </td>
                                    <td>${fn:length(tag.images)} </td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <td><a href="/tags-top10/delete/${tag.id}" class="btn btn-danger">Delete</a></td>
                                    </sec:authorize>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>

                </div>
                <div class="col-lg-6"></div>

            </div>
        </div>
    </jsp:body>
</zimg:defaultLayout>
