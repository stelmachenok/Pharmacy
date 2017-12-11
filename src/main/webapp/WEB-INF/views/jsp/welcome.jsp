<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.welcome"/>}</title>
    <%@ include file="styles.jsp" %>
</head>
<body>
<span style="float: right">
    <a href="<%= pageContext.getServletContext().getContextPath() %>/?lang=en">en</a>
    |
    <a href="<%= pageContext.getServletContext().getContextPath() %>/?lang=ru">ru</a>
</span>

<c:url var="get_url" value=""/>

<a href="<ex:ref pageContext="${get_url}/medicaments" sortField="BRAND_NAME" sortDir="true" pageNum="1" pageSize="10"/>"><spring:message
        code="title.medicaments"/></a>
<br/>
<a href="<%= pageContext.getServletContext().getContextPath() %>/categories"><spring:message
        code="title.categories"/></a>
<br/>
<a href="<%= pageContext.getServletContext().getContextPath() %>/pharmacies"><spring:message
        code="title.pharmacies"/></a>
<br/>
<a href="<%= pageContext.getServletContext().getContextPath() %>/availabilityOfDrugs"><spring:message
        code="title.availability_of_drugs"/></a>
</body>
</html>
