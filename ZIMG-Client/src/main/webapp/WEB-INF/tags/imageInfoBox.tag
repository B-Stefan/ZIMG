<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ attribute name="image"
              required="true"
              type="ZIMG.models.Image" %>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="panel panel-default">
    <div class="panel-body">
        <span class="spacer">Uploader:</span><a href="/user/${image.uploader.name}"><span class="glyphicon glyphicon-user" aria-hidden="true"></span>  ${image.uploader.name}</a> <br/>
        <hr />
        <span class="spacer">Date:</span><span class="glyphicon glyphicon-time" aria-hidden="true"></span> <fmt:formatDate value="${image.createdAt}" pattern="dd.MM.yyyy - HH:mm" />
    </div>
</div>