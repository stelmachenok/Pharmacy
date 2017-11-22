<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="form" uri="http://java.sun.com/jsf/html" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="title.medicaments"/></title>
</head>

<body>
<span style="float: right">
    <a href="<%= pageContext.getServletContext().getContextPath() %>/medicaments?lang=en">en</a>
    |
    <a href="<%= pageContext.getServletContext().getContextPath() %>/medicaments?lang=ru">ru</a>
</span>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <th><a href="<%= pageContext.getServletContext().getContextPath() %>
        /medicaments?sort-field=BRAND_NAME&page-num=${pageNum}&page-size=${pageSize}">
            <spring:message code="label.brandName"/>
        </a></th>
        <th><a href="<%= pageContext.getServletContext().getContextPath() %>
        /medicaments?sort-field=ACTIVE_INGREDIENT&page-num=${pageNum}&page-size=${pageSize}">
            <spring:message code="label.activeIngredient"/>
        </a></th>
        <th><a href="<%= pageContext.getServletContext().getContextPath() %>
        /medicaments?sort-field=DOSAGE&page-num=${pageNum}&page-size=${pageSize}">
            <spring:message code="label.dosage"/>
        </a></th>
        <th><a href="<%= pageContext.getServletContext().getContextPath() %>
        /medicaments?sort-field=PACKING_FORM&page-num=${pageNum}&page-size=${pageSize}">
            <spring:message code="label.packingForm"/>
        </a></th>
        <th><a href="<%= pageContext.getServletContext().getContextPath() %>
        /medicaments?sort-field=INTERNATIONAL_NONPROPRIENTARY_NAME&page-num=${pageNum}&page-size=${pageSize}">
            <spring:message code="label.internationalNonproprietaryName"/>
        </a></th>
        <th><a href="<%= pageContext.getServletContext().getContextPath() %>
        /medicaments?sort-field=RELEASE_FORM&page-num=${pageNum}&page-size=${pageSize}">
            <spring:message code="label.releaseForm"/>
        </a></th>

        <th><spring:message code="title.edit"/></th>
        <th><spring:message code="title.delete"/></th>

    </tr>

    <c:forEach items="${medicaments}" var="medicament">
    <tr>
        <td>${medicament.brandName}</td>
        <td>${medicament.activeIngredient}</td>
        <td>${medicament.dosage}</td>
        <td>${medicament.packingForm}</td>
        <td>${medicament.internationalNonproprietaryName}</td>
        <td>${medicament.releaseForm}</td>

        <td>
            <a href="<%= pageContext.getServletContext().getContextPath() %>/medicaments?action=edit&id=${medicament.id}">
                <spring:message code="title.edit"/>
            </a>
        </td>

        <td>
            <a href="<%= pageContext.getServletContext().getContextPath() %>/medicaments?action=delete&id=${medicament.id}">
                <spring:message code="title.delete"/>
            </a>
        </td>
    </tr>
    </c:forEach>

    <table border="0" cellspacing="0" cellpadding="2">
        <c:forEach var="i" begin="1" end="${pagesCount}">
            <tr>
                <td>
                    <a href="<%= pageContext.getServletContext().getContextPath() %>/medicaments?page-num=${i}&page-size=${pageSize}">
                        <spring:message code="title.page"/> ${i}
                    </a>
                </td>
            </tr>
        </c:forEach>


        <c:url var="post_url" value="/formExecute?page-num=${pageNum}&page-size=${pageSize}"/>
        <form:form method="POST" modelAttribute="medicament" action="${post_url}">
            <table border="0">

                <tr>
                    <td><b><spring:message code="label.brandName"/></b></td>
                    <td>
                        <spring:bind path="brandName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" value="АВОДАРТ" size="70" path="brandName"/>
                                <form:errors path="brandName"/>
                            </div>
                        </spring:bind>
                    </td>
                </tr>

                <tr>
                    <td><b><spring:message code="label.activeIngredient"/></b></td>
                    <td>
                        <spring:bind path="activeIngredient">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" value="Дутастерид" size="70" path="activeIngredient"/>
                                <form:errors path="activeIngredient"/>
                            </div>
                        </spring:bind>
                    </td>
                </tr>

                <tr>
                    <td><b><spring:message code="label.dosage"/></b></td>
                    <td>
                        <spring:bind path="dosage">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" value="0.5" size="70" path="dosage"/>
                                <form:errors path="dosage"/>
                            </div>
                        </spring:bind>
                    </td>
                </tr>

                <tr>
                    <td><b><spring:message code="label.packingForm"/></b></td>
                    <td>
                        <form:select name="packingForm" path="packingForm">
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
                    </td>
                </tr>

                <tr>
                    <td><b><spring:message code="label.internationalNonproprietaryName"/></b></td>
                    <td>
                        <spring:bind path="internationalNonproprietaryName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input type="text" value="Дутастерид" size="70"
                                            path="internationalNonproprietaryName"/>
                                <form:errors path="internationalNonproprietaryName"/>
                            </div>
                        </spring:bind>
                    </td>
                </tr>

                <tr>
                    <td><spring:message code="label.releaseForm"/></td>
                    <td>
                        <form:select name="releaseForm" path="releaseForm">
                            <option value="WITHOUT_RECIPE">WITHOUT RECIPE</option>
                            <option value="USUAL_RECIPE">USUAL RECIPE</option>
                            <option value="PINK_RECIPE">PINK RECIPE</option>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2"><input type="submit" value="<spring:message code="button.submit"/>"/></td>
                </tr>
            </table>
        </form:form>
    </table>
    <b>${exceptionText}</b>
</body>
</html>