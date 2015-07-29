<%@ page session="true"%>
<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
  <jsp:body>
    <!--Header-->
    <div class="container" id="login">
      <div class="row text-center">
          <c:if test='${errMsg != null}'>
            <c:set value="animate shake animated" var="cssShakeClass"/>
            <c:set value="has-error" var="cssFormGroupErrorClass"/>
          </c:if>
          <div class="${cssShakeClass}">

            <img width="1000" src="/resources/img/error.png"/>
          </div>

      </div>
    </div>
  </jsp:body>
</zimg:defaultLayout>
