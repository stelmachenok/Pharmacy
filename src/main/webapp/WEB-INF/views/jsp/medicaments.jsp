<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="form" uri="http://java.sun.com/jsf/html" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.medicaments"/></title>
    <%@ include file="styles.jsp" %>
</head>

<body>
<script>
    // по окончанию загрузки страницы
    $(document).ready(function () {
        setInterval(function () {
            $('#time').load('/getTime');
        }, 1000);
    });

//    var date = new Date(('/getTime'));
//    var curr_date = date.getDate();
//    var curr_month = date.getMonth() + 1;
//    var curr_year = date.getFullYear();
//    $('#time').load(curr_year + "-" + curr_month + "-" + curr_date);
//    }, 5000);

    function deleteConfirmation(brandName, id) {
        if (confirm("<spring:message code="question.deleteConfirmation"/> " + brandName + "?")) {
            window.location.href = "<%= pageContext.getServletContext().getContextPath() %>/medicaments?sort-field=${sortField}&sort-direction=${sortDir}&page-num=${pageNum}&page-size=${pageSize}&action=delete&id=" + id;
        }
    }

    function changeSelectedCategory(id) {
        $('#categoryMedicament').val(id);
    }
</script>

<%@ include file="logout.jsp" %>
<c:url var="get_url" value="/medicaments"/>

<span id="time"></span>
<span style="float: right">
    <a href="<ex:ref pageContext="${get_url}" lang="en"/>">en</a>
    |
    <a href="<ex:ref pageContext="${get_url}" lang="ru"/>">ru</a>
</span>
<table class="table">
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

    <c:forEach items="${medicaments}" var="available">
        <tr>
            <td>${available.brandName}</td>
            <td>${available.activeIngredient}</td>
            <td>${available.dosage}</td>
            <td>${available.packingForm}</td>
            <td>${available.internationalNonproprietaryName}</td>
            <td>${available.releaseForm}</td>
            <td>${available.category.categoryName}</td>

            <td>
                <a onclick="changeSelectedCategory('${available.categoryDtoId}')"
                   href="<ex:ref pageContext="${get_url}" sortField="${sortField}" sortDir="${sortDir}" pageNum="${pageNum}" pageSize="${pageSize}" action="edit" id="${available.id}"/>">
                    <spring:message code="title.edit"/>
                </a>
            </td>

            <td>
                <a id="delete" onclick="deleteConfirmation('${available.brandName}', '${available.id}')">
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
       value="/formExecute"/>
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
        <label for="brandName" class="col-sm-2 control-label">
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
        <label for="activeIngredient" class="col-sm-2 control-label">
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
        <label for="dosage" class="col-sm-2 control-label">
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
        <label for="packingForm" class="col-sm-2 control-label">
            <spring:message code="label.packingForm"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select class="form-control" name="packingForm" path="packingForm">
                    <option value="CAPSULE">CAPSULE</option>
                    <option value="POWDER">POWDER</option>
                    <option value="TABLET">TABLET</option>
                    <option value="DRAGEES">DRAGEES</option>
                    <option value="GRANULE">GRANULE</option>
                    <option value="CARAMEL">CARAMEL</option>
                    <option value="OINTMENT">OINTMENT</option>
                    <option value="CREAM">CREAM</option>
                    <option value="PASTE">PASTE</option>
                    <option value="GEL">GEL</option>
                    <option value="SOLUTION">SOLUTION</option>
                    <option value="TINCTURE">TINCTURE</option>
                    <option value="SUSPENSION">SUSPENSION</option>
                    <option value="EMULSION">EMULSION</option>
                    <option value="DROP">DROP</option>
                    <option value="SYRUP">SYRUP</option>
                    <option value="POTION">POTION</option>
                    <option value="AEROSOL">AEROSOL</option>
                </form:select>
            </div>
        </div>
    </div>

    <div class="row">
        <label for="internationalNonproprietaryName" class="col-sm-2 control-label">
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
        <label for="releaseForm" class="col-sm-2 control-label">
            <spring:message code="label.releaseForm"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select class="form-control" name="releaseForm" path="releaseForm">
                    <option value="WITHOUT_RECIPE">WITHOUT RECIPE</option>
                    <option value="USUAL_RECIPE">USUAL RECIPE</option>
                    <option value="PINK_RECIPE">PINK RECIPE</option>
                </form:select>
            </div>
        </div>
    </div>

    <div class="row">
        <label for="categoryDtoId" class="col-sm-2 control-label">
            <spring:message code="label.medicamentCategory"/>
        </label>
        <div class="col-sm-2">
            <div class="form-group">
                <form:select id="categoryMedicament" class="form-control" name="categoryDtoId" path="categoryDtoId">
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.categoryName}</option>
                    </c:forEach>
                </form:select>
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