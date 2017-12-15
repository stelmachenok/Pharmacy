<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <%@ include file="styles.jsp" %>
    <title><spring:message code="title.error403"/></title>

</head>
<body>
<c:import url="navbar.jsp"></c:import>
<spring:message code="message.error403"/>
<c:url var="get_url" value="/searchPage"/>
<a href="${get_url}"><spring:message code="message.returnToWelcomePage"/></a>

</body>
</html>
