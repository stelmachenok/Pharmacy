<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <%@ include file="styles.jsp" %>
    <title><spring:message code="title.availabilityOfDrugs"/></title>
</head>
<body>
<script>
    function deleteConfirmation(brandName, id) {
        if (confirm("<spring:message code="question.deleteConfirmation"/> " + brandName + "?")) {
            window.location.href = "<%= pageContext.getServletContext().getContextPath() %>/availabilityOfDrugs?sort-field=${sortField}&sort-direction=${sortDir}&page-num=${pageNum}&page-size=${pageSize}&action=delete&id=" + id;
        }
    }
</script>
<c:url var="get_url" value="/availabilityOfDrugs"/>
<%@ include file="navbar.jsp" %>

<h5><p class="text-center"><spring:message code="title.yourPharmacy"/> : ${pharmacyName}</p></h5>


<table class="table table-striped">
    <tr>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="MEDICAMENT_ID" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.brandName"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="COUNT" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.count"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="LAST_UPDATE" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.lastUpdate"/>
            </a></th>

        <th><spring:message code="title.delete"/></th>

    </tr>

    <c:forEach items="${availabilities}" var="availability">
        <tr>
            <td>${availability.brandName}</td>
            <td>${availability.count}</td>
            <td>${availability.lastUpdate}</td>

            <td>
                <button id="delete" type="button" class="btn btn-default"
                        onclick="deleteConfirmation('${availability.brandName}', '${availability.id}')">
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
       value="/availabilityFormExecute"/>
<form:form class="form-horizontal" method="POST" modelAttribute="availability" action="${post_url}">
    <spring:bind path="pharmacyId">
        <form:input type="hidden" value="${pharmacyId}" path="pharmacyId"/>
    </spring:bind>
    <input name="sortField" type="hidden" value="${sortField}"/>
    <input name="pageNum" type="hidden" value="${pageNum}"/>
    <input name="pageSize" type="hidden" value="${pageSize}"/>
    <input name="sortDir" type="hidden" value="${sortDir}"/>

    <div class="row">
        <label for="medicamentId" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.brandName"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select itemLabel="label" itemValue="id" items="${medicaments}" id="medicamentId"
                             class="form-control" name="medicamentId" path="medicamentId">
                </form:select>
            </div>
        </div>
    </div>

    <div class="row">
        <label for="count" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.count"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="count">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text"
                                size="70" path="count" id="count"/>
                    <form:errors path="count"/>
                </div>
            </spring:bind>
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
