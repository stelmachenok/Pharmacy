<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <%@ include file="styles.jsp" %>
    <title><spring:message code="title.login"/></title>
</head>
<body>
<c:import url="navbar.jsp"/>

<c:url var="post_url"
       value="/login"/>
<div class="row">
    <label class="col-sm-offset-5 col-sm-2 control-label">${error}</label>
</div>
<form:form class="form-horizontal" method="POST" modelAttribute="user" action="${post_url}">
    <div class="row">
        <label for="login" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.login"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="login">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="login" id="login"/>
                    <form:errors path="login"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="password" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.password"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="password">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="password" id="j_password"/>
                    <form:errors path="password"/>
                </div>
            </spring:bind>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-5 col-sm-2 button">
            <button type="submit" class="btn btn-default"><spring:message code="button.login"/></button>
        </div>
    </div>
</form:form>



</body>
</html>
