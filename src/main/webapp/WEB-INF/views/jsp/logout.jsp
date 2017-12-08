<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="styles.jsp" %>

<c:url var="get_url" value="/logout"/>
<a href="<ex:ref pageContext="${get_url}"/>">
    <spring:message code="label.logout"/>
</a>
