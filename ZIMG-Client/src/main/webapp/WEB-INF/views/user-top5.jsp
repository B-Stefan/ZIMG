<%@ page session="true"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>

        <div class="container topUsers">
            <%-- HEAD --%>
            <h1>Top 5 users</h1>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Username</th>
                    <th>Last 3 images</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${topFiveUserList}" varStatus="loop" var="user">
                    <c:set value="carousel-${carouselId}" var="carouselId"/>
                    <tr>
                        <td>${loop.index+1}</td>
                        <td><a href="/user/${user.name}">${user.name}</a></td>
                        <td>
                            <div class="centered">
                                <div id="${carouselId}" class="carousel slide" data-interval="false" data-ride="carousel">
                                    <!-- Indicators -->
                                    <ol class="carousel-indicators">
                                        <c:forEach items="${user.images}" var="image" varStatus="imgLoop" begin="0" end="2">
                                            <li data-target="#${carouselId}" data-slide-to="${imgLoop.index}" class="active"></li>
                                        </c:forEach>
                                    </ol>

                                    <!-- Wrapper for slides -->
                                    <div class="carousel-inner" role="listbox">
                                        <c:forEach items="${user.images}" var="image" varStatus="imgLoop" begin="0" end="2">
                                        <c:if test="${imgLoop.index == 0}">
                                            <c:set var="activeCSS" value="active"/>
                                        </c:if>
                                        <c:if test="${imgLoop.index != 0}">
                                            <c:set var="activeCSS" value=""/>
                                        </c:if>

                                            <div class="item ${activeCSS}">
                                                <img src="/thumbnail/${image.id}" alt="${image.filename}"/>
                                            </div>
                                        </c:forEach>

                                    </div>

                                    <!-- Left and right controls -->
                                    <a class="left carousel-control" href="#${carouselId}" role="button" data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                        <span class="sr-only">Previous</span>
                                    </a>
                                    <a class="right carousel-control" href="#${carouselId}" role="button" data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                        <span class="sr-only">Next</span>
                                    </a>
                                </div>
                            </div>

                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </jsp:body>
</zimg:defaultLayout>
