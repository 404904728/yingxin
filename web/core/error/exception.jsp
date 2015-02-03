<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%response.setStatus(200);%>
<%
    Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Exception - 异常信息</title>
</head>

<body>
	<h2>Exception - 异常信息</h2>
	<h3><%=ex.getMessage()%></h3>
</body>
</html>