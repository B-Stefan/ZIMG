<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:attribute name="header">
        <p>Header part </p>
    </jsp:attribute>
    <jsp:attribute name="footer">
      <p id="copyright">Footer Bereich</p>
    </jsp:attribute>
    <jsp:body>
        <!--Header-->
        <div class="container">
            <h1>ZIMG</h1>
            <p>Awesome Images uploaded by awesome People!</p>
            <!--Images -->
            <c:forEach items="${userList}" var="user">
                <div class="container row">
                    <div class="col-lg-2 col-md-2 col-xs-1">${user.id}</div>
                    <div class="col-lg-3 col-md-3 col-xs-3">${user.name}</div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>
</zimg:defaultLayout>