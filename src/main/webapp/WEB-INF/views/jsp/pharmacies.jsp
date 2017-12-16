<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <%@ include file="styles.jsp" %>
    <title><spring:message code="title.pharmacies"/></title>
</head>
<body>
<script>
    function deleteConfirmation(brandName, id) {
        if (confirm("<spring:message code="question.deleteConfirmation"/> " + brandName + "?")) {
            window.location.href = "<%= pageContext.getServletContext().getContextPath() %>/pharmacies?sort-field=${sortField}&sort-direction=${sortDir}&page-num=${pageNum}&page-size=${pageSize}&action=delete&id=" + id;
        }
    }
</script>
<c:import url="navbar.jsp"/>

<c:url var="get_url" value="/pharmacies"/>

<table class="table">
    <tr>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="PHARMACY_NAME" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.pharmacyName"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="ADDRESS" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.address"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="PHARMACIST_NAME" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.pharmacistName"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="CONTACT_NUMBER" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.contactNumber"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="CATEGORY" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.category"/>
            </a></th>

        <th><spring:message code="title.edit"/></th>
        <th><spring:message code="title.delete"/></th>

    </tr>

    <c:forEach items="${pharmacies}" var="pharmacy">
        <tr>
            <td>${pharmacy.pharmacyName}</td>
            <td>${pharmacy.address}</td>
            <td>${pharmacy.pharmacistName}</td>
            <td>${pharmacy.contactNumber}</td>
            <td><spring:message code="label.pharmacyCategory.${pharmacy.category}"/></td>

            <td>
                <a href="<ex:ref pageContext="${get_url}" sortField="${sortField}" sortDir="${sortDir}" pageNum="${pageNum}" pageSize="${pageSize}" action="edit" id="${pharmacy.id}"/>"
                   class="btn btn-default" role="button">
                    <spring:message code="title.edit"/>
                </a>
            </td>

            <td>
                <button id="delete" type="button" class="btn btn-default"
                        onclick="deleteConfirmation('${pharmacy.pharmacyName}', '${pharmacy.id}')">
                    <spring:message code="title.delete"/>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<ul class="pagination">
    <c:forEach var="i" begin="1" end="${pagesCount}">
        <li>
            <a href="<ex:ref pageContext="${get_url}" sortField="${sortField}" sortDir="${sortDir}" pageNum="${i}" pageSize="${pageSize}"/>">${i}</a>
        </li>
    </c:forEach>
</ul>

<c:url var="post_url"
       value="/pharmaciesFormExecute"/>
<form:form class="form-horizontal" method="POST" modelAttribute="pharmacy" action="${post_url}">
    <spring:bind path="id">
        <form:input type="hidden" value="${pharmacy.id}" path="id"/>
    </spring:bind>
    <spring:bind path="login">
        <form:input type="hidden" value="${pharmacy.login}" path="login"/>
    </spring:bind>
    <spring:bind path="password">
        <form:input type="hidden" value="${pharmacy.password}" path="password"/>
    </spring:bind>
    <input name="sortField" type="hidden" value="${sortField}"/>
    <input name="pageNum" type="hidden" value="${pageNum}"/>
    <input name="pageSize" type="hidden" value="${pageSize}"/>
    <input name="action" type="hidden" value="${action}"/>
    <input name="sortDir" type="hidden" value="${sortDir}"/>

    <div class="row">
        <label for="pharmacyName" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.pharmacyName"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="pharmacyName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="pharmacyName" id="pharmacyName"/>
                    <form:errors path="pharmacyName"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="address" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.address"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="address">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text"
                                size="70" path="address" id="address"/>
                    <form:errors path="address"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="pharmacistName" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.pharmacistName"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="pharmacistName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="pharmacistName" id="pharmacistName"/>
                    <form:errors path="pharmacistName"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="contactNumber" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.contactNumber"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="contactNumber">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="contactNumber" id="contactNumber"/>
                    <form:errors path="contactNumber"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="category" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.category"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select itemLabel="translatedName" itemValue="fieldName" items="${pharmacyCategoryValues}"
                             class="form-control" name="category" path="category">
                </form:select>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-offset-5 col-sm-2 button">
            <button type="submit" class="btn btn-default"><spring:message code="button.submit"/></button>
        </div>
    </div>
</form:form>
<div class="row">
    <label class="col-sm-offset-5 col-sm-2 control-label">${exceptionText}</label>
</div>

</body>
</html>
