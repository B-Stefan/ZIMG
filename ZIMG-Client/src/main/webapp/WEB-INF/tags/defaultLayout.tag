<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="resources/css/headerFooterStyle.css">
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/script.js" />"></script>
</head>

<body>
<div id="pageheader">
    <zimg:navbar/>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="pagefooter">
    <div id="footerbar">
        <div>ZIMG | Copyright © 2015</div>
        <div><a href="http://www.hs-bremen.de/">Made with <span id="heart">&hearts;</span> in Bremen</a></div>
    </div>
</div>
</body>
</html>