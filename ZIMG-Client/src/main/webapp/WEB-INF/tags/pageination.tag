<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page"
              required="true"
              type="org.springframework.data.domain.Page<java.lang.Object>" %>
<%@ attribute name="baseUrl"
              required="true"
              type="java.lang.String" %>

<div>

    <c:if test="${page.hasPrevious}">
        <a href="${baseUrl}?page= ${page.previousPageable.pageNumber}">Back</a>
    </c:if>
    <a href="${baseUrl}">${page.pageNumber}</a>
    <c:if test="${page.hasPrevious}">
        <a href="${baseUrl}?page= ${page.nextPageable.pageNumber}">Next</a>
    </c:if>
</div>