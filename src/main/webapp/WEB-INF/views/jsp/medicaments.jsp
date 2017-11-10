<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="form" uri="http://java.sun.com/jsf/html" %>--%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Medicaments</title>
</head>

<body>
<table border="1" cellspacing="0" cellpadding="2">
    <tr>
        <td>Brand Name</td>
        <td>Active Ingredient</td>
        <td>Dosage</td>
        <td>Packing Form</td>
        <td>International Nonproprietary Name</td>
        <td>Release Form</td>
    </tr>

    <c:forEach items="${medicaments}" var="medicamentDto">
        <tr>
            <td>${medicamentDto.brandName}</td>
            <td>${medicamentDto.activeIngredient}</td>
            <td>${medicamentDto.dosage}</td>
            <td>${medicamentDto.packingForm}</td>
            <td>${medicamentDto.internationalNonproprietaryName}</td>
            <td>${medicamentDto.releaseForm}</td>
        </tr>
    </c:forEach>

    <b>${exceptionText}</b>

    <form:form method="POST" action="/formExecute" modelAttribute="medicament">
        <table border="0">

            <tr>
                <td><b>Brand Name</b></td>
                <td>
                    <spring:bind path="brandName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" name="brandName"
                                        value="АВОДАРТ" size="70" path="brandName" class="form-control"/>
                            <form:errors path="brandName" class="control-label"/>
                        </div>
                    </spring:bind>
                </td>
            </tr>

            <tr>
                <td><b>Active Ingredient</b></td>
                <td>
                    <spring:bind path="activeIngredient">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" name="activeIngredient"
                                        value="Дутастерид" size="70" path="activeIngredient" class="form-control"/>
                            <form:errors path="activeIngredient" class="control-label"/>
                        </div>
                    </spring:bind>
                </td>
            </tr>

            <tr>
                <td><b>Dosage</b></td>
                <td>
                    <spring:bind path="dosage">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" name="dosage"
                                        value="0.5" size="70" path="dosage" class="form-control"/>
                            <form:errors path="dosage" class="control-label"/>
                        </div>
                    </spring:bind>
                </td>
            </tr>

            <tr>
                <td><b>Packing Form</b></td>
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
                <td><b>International Nonproprietary Name</b></td>
                <td>
                    <spring:bind path="internationalNonproprietaryName">
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" name="internationalNonproprietaryName"
                                        value="Дутастерид" size="70" path="internationalNonproprietaryName"
                                        class="form-control"/>
                            <form:errors path="internationalNonproprietaryName" class="control-label"/>
                        </div>
                    </spring:bind>
                </td>
            </tr>

            <tr>
                <td>Release Form</td>
                <td>
                    <form:select name="releaseForm" path="releaseForm">
                        <option value="WITHOUT_RECIPE">WITHOUT RECIPE</option>
                        <option value="USUAL_RECIPE">USUAL RECIPE</option>
                        <option value="PINK_RECIPE">PINK RECIPE</option>
                    </form:select>
                </td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form:form>
</table>
</body>
</html>