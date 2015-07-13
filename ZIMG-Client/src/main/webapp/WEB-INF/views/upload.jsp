<%@ page import="ZIMG.models.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>

<zimg:defaultLayout>
  <jsp:body>
    <!--Header-->
    <div class="container">
      <h1>Upload</h1>
      <form method="POST" enctype="multipart/form-data"
            action="/upload">
        File to upload: <input type="file" name="file"><br /> Name:
        <input type="text" name="uploadname"><br /> <br />
        <input type="submit" value="Upload"> Press here to upload the file!
      </form>
    </div>
  </jsp:body>
</zimg:defaultLayout>
