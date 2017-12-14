<%--
  Created by IntelliJ IDEA.
  User: mast
  Date: 14.12.2017
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

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
    <tr class="medicaments-search-table-body">
        <td>${searchObj.brandName}</td>
        <td>${searchObj.phone}</td>
        <td>${searchObj.count}</td>
    </tr>
</table>

</body>
</html>
