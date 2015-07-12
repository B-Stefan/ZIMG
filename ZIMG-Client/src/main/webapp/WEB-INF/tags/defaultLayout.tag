<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<!DOCTYPE html>
<html>
<head>
  <title>ZIMG</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>

<body>
<div id="pageheader">
  <zimg:navbar/>
  <jsp:invoke fragment="header"/>
</div>
<div id="body">
  <jsp:doBody/>
</div>
<div id="pagefooter">
  <jsp:invoke fragment="footer"/>
</div>
</body>
</html>