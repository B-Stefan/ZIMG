<%@ page import="ZIMG.models.User" %>
<%@ page import="ZIMG.models.Image" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<zimg:defaultLayout>
  <jsp:body>
    <!--Header-->
    <div class="container">
      <!--Images -->
      <div class="row">
          <div class="col-xs-8 col-md-8">
              <a href="/image/${image.id}" class="thumbnail"><img src="/resources/upload/${image.filename}" /></a>
          </div>
        <div class="col-xs-4 col-md-4">
          Uploader: <a href="/user/${image.uploader.name}">${image.uploader.name}</a> <br/>
          Date: ${image.createdAt}
        </div>
      </div>
    </div>
  </jsp:body>
</zimg:defaultLayout>
