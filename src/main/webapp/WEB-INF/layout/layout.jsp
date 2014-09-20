	<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <sitemesh:write property="head"/>
</head>
<body>
<div class="page">
    <div class="content">
        <div id="view-holder">
            <sitemesh:write property="body"/>
        </div>
    </div>
</div>
</body>
</html>