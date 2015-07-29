<%@tag description="Default Nav bar" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!--Navbar-->
<!-- if eingeloggt -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" id="logo" href="/home"><img src="/resources/img/WDB_ZIMG_logo.png" style="width:80px;height:45px"></a>
    </div>
    <div>
      <sec:authorize access="isAnonymous()">
        <ul class="nav navbar-nav ">
          <li><a href="/login"><span class="glyphicon glyphicon-home"></span> Login</a></li>
          <li><a href="/signup"><span class="glyphicon glyphicon-picture"></span> Signup</a></li>
        </ul>

      </sec:authorize>
      <sec:authorize access="hasRole('USER')">
        <ul class="nav navbar-nav">
          <li><a href="/home"><span class="glyphicon glyphicon-home"></span> Home</a></li>
          <li><a href="/images-top10"><span class="glyphicon glyphicon-picture"></span> Top Images</a></li>
          <li><a href="/user-top5"><span class="glyphicon glyphicon-user"></span> Top Users</a></li>
          <li><a href="/tags-top10"><span class="glyphicon glyphicon-tags"></span> Top Tags</a></li>

          <li><a href="/upload"><span class="glyphicon glyphicon-cloud-upload"></span> Upload</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
              <sec:authentication property="principal.username" />
              <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a href="/user/Fabimon">My Profil</a></li>
              <li><a href="/favorites">My Favorites</a></li>
              <li role="separator" class="divider"></li>
              <li><a href="/logout">Logout</a></li>
            </ul>
          </li>
        </ul>
      </sec:authorize>

    </div>
  </div>
</nav>