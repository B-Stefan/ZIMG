<%@ page session="true"%>
<%@ page import="ZIMG.models.User" %>
<%@ page import="ZIMG.models.Image" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
  <jsp:body>
    <div class="container">
      <div class="row">
        <div class="col-xs-12 col-md-12">
          <h1>Your favorites:</h1>
        </div>
      </div>
      <div class="row">
        <c:forEach items="${favorites}" var="favorite">
          <div class="col-xs-6 col-md-3">
            <zimg:imagePreview className="thumbnail" image="${favorite}"/>
          </div>
        </c:forEach>
      </div>
    </div>
  </jsp:body>
</zimg:defaultLayout>
