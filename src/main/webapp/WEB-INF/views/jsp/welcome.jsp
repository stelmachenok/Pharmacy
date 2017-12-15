<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@ include file="styles.jsp" %>
    <title><spring:message code="title.welcome"/></title>
</head>
<body>
<c:import url="navbar.jsp"></c:import>
<span style="float: right">
    <a href="<%= pageContext.getServletContext().getContextPath() %>/?lang=en">en</a>
    |
    <a href="<%= pageContext.getServletContext().getContextPath() %>/?lang=ru">ru</a>
</span>

<c:url var="get_url" value=""/>

<a href="<ex:ref pageContext="${get_url}/medicaments" sortField="BRAND_NAME" sortDir="true" pageNum="1" pageSize="10"/>">
    <spring:message code="title.medicamentTitle"/>
</a>
<br/>
<a href="<ex:ref pageContext="${get_url}/categories" sortField="CATEGORY_NAME" sortDir="true" pageNum="1" pageSize="10"/>">
    <spring:message code="title.categories"/>
</a>
<br/>
<a href="<ex:ref pageContext="${get_url}/pharmacies" sortField="PHARMACY_NAME" sortDir="true" pageNum="1" pageSize="10"/>">
    <spring:message code="title.pharmacies"/></a>
<br/>

<a href="<ex:ref pageContext="${get_url}/availabilityOfDrugs" sortField="MEDICAMENT_ID" sortDir="true" pageNum="1" pageSize="10"/>">
    <spring:message code="title.availability_of_drugs"/>
</a>
</body>
</html>
