<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <%@ include file="styles.jsp" %>
    <title><spring:message code="title.search"/></title>
    <script src="js/searchAction.js"></script>
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="row">
    <input id="searchInput" class="col-sm-offset-5 control-label" type="text" size="40">

    <button id="searchButton" class="btn btn-default" onclick="searching(document.getElementById('searchInput').value)">
        <spring:message code="button.search"/></button>
</div>
<div class="row">
    <label class="col-sm-offset-5 control-label"><spring:message code="label.searchedMedicamentsCount"/>: </label><label
        class="control-label" id="searched-medicaments-count"></label>
</div>

<table class="table table-striped">
    <thead>
    <tr>
        <th><spring:message code="label.label"/></th>
        <th><spring:message code="label.count"/></th>
        <th><spring:message code="label.lastUpdate"/></th>
        <th><spring:message code="label.pharmacyName"/></th>
        <th><spring:message code="label.address"/></th>
        <th><spring:message code="label.contactNumber"/></th>
    </tr>
    </thead>
    <tbody class="medicaments-search-table-body">

    </tbody>


</table>
</body>
</html>
