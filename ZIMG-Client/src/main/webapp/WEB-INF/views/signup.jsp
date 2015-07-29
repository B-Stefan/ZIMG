<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
  <jsp:body>
    <!--Header-->
    <div class="container" id="login">
      <div class="row">
        <div class="col-xs-4 col-md-4"></div>
        <div class="col-xs-4 col-md-4">
          <img src="/resources/img/WDB_ZIMG_logo.png" id="logo">
          <c:if test='${errMsg != null}'>
            <c:set value="animate shake animated" var="cssShakeClass"/>
            <c:set value="has-error" var="cssFormGroupErrorClass"/>
          </c:if>

          <div class="well ${cssShakeClass}">
            <h2>Signup</h2>
            <br/>
            <form method="POST" action="/signup">
              <div class="form-group ${cssFormGroupErrorClass}">
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
                <label for="username">Email-Address</label>
                <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="first.last@stud.hs-bremen.de">
              </div>
              <div class="form-group ${cssFormGroupErrorClass}">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Your name">
              </div>
              <div class="form-group ${cssFormGroupErrorClass}">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
              </div>
              <button type="submit" class="btn btn-default" id="login-submit">Signup</button>
            </form>

          </div>
        </div>
        <div class="col-xs-4 col-md-4"></div>
      </div>
    </div>
  </jsp:body>
</zimg:defaultLayout>
