<%@ page session="true"%>
<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
    <jsp:body>
        <%-- HEAD --%>
        <div class="container" id="upload">
            <h1>Upload</h1>
            <form method="POST" enctype="multipart/form-data" action="/upload?${_csrf.parameterName}=${_csrf.token}">
                <input type="file" name="file">
                <input type="submit" class="btn btn-default" id ="upload-submit" value="Upload">
            </form>
        </div>
    </jsp:body>
</zimg:defaultLayout>
