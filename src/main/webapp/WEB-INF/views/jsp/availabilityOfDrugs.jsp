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
<h5><p class="text-center"><spring:message code="title.yourPharmacy"/> : ${pharmacyName}</p></h5>


<%@ include file="logout.jsp" %>
<c:url var="get_url" value="/availabilityOfDrugs"/>

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

    <c:forEach items="${availableMedicaments}" var="available">
        <tr>
            <td>Medicament Brand Name</td>
            <td>Count</td>
            <td>Last Update</td>

            <td>
                <a id="delete" onclick="">
                    <spring:message code="title.delete"/>
                </a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
