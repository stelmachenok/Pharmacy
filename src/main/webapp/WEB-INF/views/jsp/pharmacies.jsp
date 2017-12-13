<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.pharmacies"/></title>
    <%@ include file="styles.jsp" %>
</head>
<body>
<script>
    function deleteConfirmation(brandName, id) {
        if (confirm("<spring:message code="question.deleteConfirmation"/> " + brandName + "?")) {
            window.location.href = "<%= pageContext.getServletContext().getContextPath() %>/pharmacies?sort-field=${sortField}&sort-direction=${sortDir}&page-num=${pageNum}&page-size=${pageSize}&action=delete&id=" + id;
        }
    }
</script>

<c:url var="get_url" value="/pharmacies"/>

<span id="time"></span>
<span style="float: right">
    <a href="<ex:ref pageContext="${get_url}" lang="en"/>">en</a>
    |
    <a href="<ex:ref pageContext="${get_url}" lang="ru"/>">ru</a>
</span>

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
                <a href="<ex:ref pageContext="${get_url}" sortField="${sortField}" sortDir="${sortDir}" pageNum="${pageNum}" pageSize="${pageSize}" action="edit" id="${pharmacy.id}"/>">
                    <spring:message code="title.edit"/>
                </a>
            </td>

            <td>
                <a id="delete" onclick="deleteConfirmation('${pharmacy.pharmacyName}', '${pharmacy.id}')">
                    <spring:message code="title.delete"/>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

<c:forEach var="i" begin="1" end="${pagesCount}">
    <div class="row">
        <a class="col-sm-2"
           href="<ex:ref pageContext="${get_url}" sortField="${sortField}" sortDir="${sortDir}" pageNum="${i}" pageSize="${pageSize}"/>">
            <spring:message code="title.page"/> ${i}
        </a>
    </div>
</c:forEach>

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
        <label for="pharmacyName" class="col-sm-2 control-label">
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
        <label for="address" class="col-sm-2 control-label">
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
        <label for="pharmacistName" class="col-sm-2 control-label">
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
        <label for="contactNumber" class="col-sm-2 control-label">
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
        <label for="category" class="col-sm-2 control-label">
            <spring:message code="label.category"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select itemLabel="resPath" itemValue="fieldName" items="${pharmacyCategoryValues}"
                             class="form-control" name="category" path="category">
                </form:select>
                <%--<form:select class="form-control" name="category" path="category">--%>
                    <%--<option value="FIRST"><spring:message code="label.pharmacyCategory.FIRST"/></option>--%>
                    <%--<option value="SECOND"><spring:message code="label.pharmacyCategory.SECOND"/></option>--%>
                    <%--<option value="THIRD"><spring:message code="label.pharmacyCategory.THIRD"/></option>--%>
                    <%--<option value="FOURTH"><spring:message code="label.pharmacyCategory.FOURTH"/></option>--%>
                    <%--<option value="FIFTH"><spring:message code="label.pharmacyCategory.FIFTH"/></option>--%>
                <%--</form:select>--%>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-offset-2 col-sm-2 button">
            <button type="submit" class="btn btn-default"><spring:message code="button.submit"/></button>
        </div>
    </div>
</form:form>
<b>${exceptionText}</b>

</body>
</html>
