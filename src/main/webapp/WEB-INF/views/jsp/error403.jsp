<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.error403"/></title>
    <%@ include file="styles.jsp" %>
</head>
<spring:message code="message.error403"/>
<c:url var="get_url" value="/"/>
<a href="${get_url}"><spring:message code="message.returnToWelcomePage"/></a>
<body>

</body>
</html>
