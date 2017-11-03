<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <title> Tag Example</title>
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

    <c:forEach items="${medicaments}" var="medicament">
        <tr>
            <td>${medicament.brandName}</td>
            <td>${medicament.activeIngredient}</td>
            <td>${medicament.dosage}</td>
            <td>${medicament.packingForm}</td>
            <td>${medicament.internationalNonproprietaryName}</td>
            <td>${medicament.releaseForm}</td>
        </tr>
    </c:forEach>

    <b>${exceptionText}</b>

    <form action="/formExecute" method="GET">
        <table border="0">

            <tr>
                <td><b>Brand Name</b></td>
                <td><input type="text" name="brandName"
                           value="АВОДАРТ" size="70"/></td>
            </tr>

            <tr>
                <td><b>Active Ingredient</b></td>
                <td><input type="text" name="activeIngredient"
                           value="Дутастерид" size="70"/></td>
            </tr>

            <tr>
                <td><b>Dosage</b></td>
                <td><input type="text" name="dosage"
                           value="0.5" size="70"/></td>
            </tr>

            <tr>
                <td><b>Packing Form</b></td>
                <td>
                    <select name="packingForm">
                        <option value="CAPSULE">TABLET</option>
                        <option value="POWDER">POWDER</option>
                        <option value="TABLET">CAPSULE</option>
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
                    </select>
                </td>
            </tr>

            <tr>
                <td><b>International Nonproprietary Name</b></td>
                <td><input type="text" name="internationalNonproprietaryName"
                           value="Дутастерид" size="70"/></td>
            </tr>

            <tr>
                <td>Release Form</td>
                <td>
                    <select name="releaseForm">
                        <option value="WITHOUT_RECIPE">WITHOUT RECIPE</option>
                        <option value="USUAL_RECIPE">USUAL RECIPE</option>
                        <option value="PINK_RECIPE">PINK RECIPE</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="2"><input type="submit" value="Submit"/></td>
            </tr>
        </table>
    </form>
</table>
</body>
</html>