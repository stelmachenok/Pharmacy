<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.availabilityOfDrugs"/></title>
    <%@ include file="styles.jsp" %>
</head>
<body>
<c:url var="get_url" value="/availabilityOfDrugs"/>
<c:set var="selectedMedicament" scope="session" value="${selectedMedicamentDto}"/>
<script>
    $(document).ready(function () {
        $("#medicamentId").val(${selectedMedicament});
        $("#action").value = ${selectedMedicament};
    });

    function changeSelectValue(id) {
        //$("#myRef").attr("action", id);
        $("#action").value = id;
        var str = '<%= pageContext.getServletContext().getContextPath() %>/availabilityOfDrugs?action=';
        str += id;
        alert('<%= pageContext.getServletContext().getContextPath() %>/availabilityOfDrugs?action='+id);
        window.location.href = '<%= pageContext.getServletContext().getContextPath() %>/availabilityOfDrugs?action='+id;
    }
</script>


<h5><p class="text-center"><spring:message code="title.yourPharmacy"/> : ${pharmacyName}</p></h5>


<%@ include file="logout.jsp" %>


<span id="time"></span>
<span style="float: right">
    <a href="<ex:ref pageContext="${get_url}" lang="en"/>">en</a>
    |
    <a href="<ex:ref pageContext="${get_url}" lang="ru"/>">ru</a>
</span>
<table class="table">
    <tr>
        <th>
            <a href="<ex:ref pageContext="${get_url}"/>">
                <spring:message code="label.brandName"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}"/>">
                <spring:message code="label.count"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}"/>">
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
                <a id="delete" onclick="">
                    <spring:message code="title.delete"/>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>


<c:url var="post_url"
       value="/availabilityFormExecute"/>
<form:form class="form-horizontal" method="POST" modelAttribute="availability" action="${post_url}">
    <spring:bind path="pharmacyId">
        <form:input type="hidden" value="${pharmacyId}" path="pharmacyId"/>
    </spring:bind>
    <input name="sortField" type="hidden" value="${sortField}"/>
    <input name="pageNum" type="hidden" value="${pageNum}"/>
    <input name="pageSize" type="hidden" value="${pageSize}"/>
    <input id="action" name="action" type="hidden" value="${selectedMedicament}"/>
    <input name="sortDir" type="hidden" value="${sortDir}"/>

    <div class="row">
        <label for="medicamentId" class="col-sm-2 control-label">
            <spring:message code="label.brandName"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select id="medicamentId" class="form-control" name="medicamentId" path="medicamentId">
                    <c:forEach items="${medicaments}" var="medicament">
                        <option onclick="changeSelectValue('${medicament.id}')" value="${medicament.id}">
                                ${medicament.brandName}, ${medicament.category.categoryName}, ${medicament.activeIngredient}
                        </option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
    </div>

    <div class="row">
        <label for="count" class="col-sm-2 control-label">
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
        <div class="col-sm-offset-2 col-sm-2 button">
            <button type="submit" class="btn btn-default"><spring:message code="button.submit"/></button>
        </div>
    </div>
</form:form>


</body>
</html>
