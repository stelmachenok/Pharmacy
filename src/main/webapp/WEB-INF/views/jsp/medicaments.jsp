<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="styles.jsp" %>
    <title><spring:message code="title.medicamentTitle"/></title>
</head>

<body>
<script>

    function deleteConfirmation(brandName, id) {
        if (confirm("<spring:message code="question.deleteConfirmation"/> " + brandName + "?")) {
            window.location.href = "<%= pageContext.getServletContext().getContextPath() %>/medicaments?sort-field=${sortField}&sort-direction=${sortDir}&page-num=${pageNum}&page-size=${pageSize}&action=delete&id=" + id;
        }
    }
</script>
<c:url var="get_url" value="/medicaments"/>
<%@ include file="navbar.jsp" %>


<table class="table table-striped">
    <tr>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="BRAND_NAME" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.brandName"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="ACTIVE_INGREDIENT" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.activeIngredient"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="DOSAGE" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.dosage"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="PACKING_FORM" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.packingForm"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="INTERNATIONAL_NONPROPRIENTARY_NAME" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.internationalNonproprietaryName"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="RELEASE_FORM" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.releaseForm"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="MEDICAMENT_CATEGORY" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.medicamentCategory"/>
            </a></th>

        <th><spring:message code="title.edit"/></th>
        <th><spring:message code="title.delete"/></th>

    </tr>

    <c:forEach items="${medicaments}" var="medicament">
        <tr>
            <td>${medicament.brandName}</td>
            <td>${medicament.activeIngredient}</td>
            <td>${medicament.dosage}</td>
            <td><spring:message code="label.packingForm.${medicament.packingForm}"/></td>
            <td>${medicament.internationalNonproprietaryName}</td>
            <td><spring:message code="label.releaseForm.${medicament.releaseForm}"/></td>
            <td>${medicament.category.categoryName}</td>

            <td>
                <a href="<ex:ref pageContext="${get_url}" sortField="${sortField}" sortDir="${sortDir}" pageNum="${pageNum}" pageSize="${pageSize}" action="edit" id="${medicament.id}"/>"
                   class="btn btn-default" role="button">
                    <spring:message code="title.edit"/>
                </a>
            </td>

            <td>
                <button id="delete" type="button" class="btn btn-default"
                        onclick="deleteConfirmation('${medicament.brandName}', '${medicament.id}')">
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

<c:url var="post_url" value="/formExecute"/>
<div class="row">
    <label class="col-sm-offset-5 col-sm-2 control-label">${errorText}</label>
</div>
<form:form class="form-horizontal" method="POST" modelAttribute="medicament" action="${post_url}">
    <spring:bind path="id">
        <form:input type="hidden" value="${medicament.id}" path="id"/>
    </spring:bind>
    <input name="sortField" type="hidden" value="${sortField}"/>
    <input name="pageNum" type="hidden" value="${pageNum}"/>
    <input name="pageSize" type="hidden" value="${pageSize}"/>
    <input name="action" type="hidden" value="${action}"/>
    <input name="sortDir" type="hidden" value="${sortDir}"/>

    <div class="row">
        <label for="brandName" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.brandName"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="brandName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="brandName" id="brandName"/>
                    <form:errors path="brandName"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="activeIngredient" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.activeIngredient"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="activeIngredient">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text"
                                size="70" path="activeIngredient" id="activeIngredient"/>
                    <form:errors path="activeIngredient"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="dosage" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.dosage"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="dosage">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="dosage" id="dosage"/>
                    <form:errors path="dosage"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="packingForm" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.packingForm"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select itemLabel="translatedName" itemValue="fieldName" items="${packingFormValues}"
                             class="form-control" name="packingForm" path="packingForm">
                </form:select>
            </div>
        </div>
    </div>

    <div class="row">
        <label for="internationalNonproprietaryName" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.internationalNonproprietaryName"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="internationalNonproprietaryName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="internationalNonproprietaryName" id="internationalNonproprietaryName"/>
                    <form:errors path="internationalNonproprietaryName"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="releaseForm" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.releaseForm"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select itemLabel="translatedName" itemValue="fieldName" items="${releaseFormValues}"
                             class="form-control" name="releaseForm" path="releaseForm">
                </form:select>
            </div>
        </div>
    </div>

    <div class="row">
        <label for="categoryDtoId" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.medicamentCategory"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select id="categoryMedicament" class="form-control" name="categoryDtoId" path="categoryDtoId">
                    <form:option value="" label="--- Select ---"/>
                    <form:options itemLabel="categoryName" itemValue="id" items="${categories}"/>
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

</body>
</html>