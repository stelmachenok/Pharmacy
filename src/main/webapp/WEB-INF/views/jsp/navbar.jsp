<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/searchPage">Pharmacy</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/logout"><spring:message code="label.logout"/></a></li>
                <li><a href="/medicaments"><spring:message code="title.medicamentTitle"/></a></li>
                <li><a href="/categories"><spring:message code="title.categories"/></a></li>
                <li><a href="/pharmacies"><spring:message code="title.pharmacies"/></a></li>
                <li><a href="/availabilityOfDrugs"><spring:message code="title.availabilityOfDrugs"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="<ex:ref pageContext="${get_url}" lang="en"/>">
                    <spring:message code="language.english"/></a></li>
                <li><a href="<ex:ref pageContext="${get_url}" lang="ru"/>">
                    <spring:message code="language.russian"/></a></li>
            </ul>
        </div>
    </div>
</nav>
