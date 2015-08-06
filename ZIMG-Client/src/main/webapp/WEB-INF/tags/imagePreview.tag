<%@ tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@ taglib prefix="zimg" tagdir="/WEB-INF/tags" %>
<%@ attribute name="image"
              required="true"
              type="ZIMG.models.Image" %>
<%@ attribute name="className"
              type="java.lang.String" %>

<a href="/image/${image.id}">

    <img file-name="${image.filename}"
         image-id="${image.id}"
         class="img-responsive img-rounded overview-entry ${className}"
         src="/thumbnail/${image.id}"/>
</a>