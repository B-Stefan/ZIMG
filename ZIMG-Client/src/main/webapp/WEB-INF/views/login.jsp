<%@ page import="ZIMG.models.User" %>
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
          <form method="POST" action="/login">
            <div class="form-group">
              <label for="username">Username</label>
              <input type="text" class="form-control" id="username" name="username" placeholder="Username">
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="password" name="password" placeholder="Password">
            </div>
            <button type="submit" class="btn btn-default" id="login-submit">Submit</button>
          </form>
        </div>
        <div class="col-xs-4 col-md-4"></div>
      </div>
    </div>
  </jsp:body>
</zimg:defaultLayout>
