<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<span style="float: right">
    <a href="<%= pageContext.getServletContext().getContextPath() %>/?lang=en">en</a>
    |
    <a href="<%= pageContext.getServletContext().getContextPath() %>/?lang=ru">ru</a>
</span>

<a href="<%= pageContext.getServletContext().getContextPath() %>/medicaments"><spring:message
        code="title.medicaments"/></a>
<br/>
<a href="<%= pageContext.getServletContext().getContextPath() %>/categories"><spring:message
        code="title.categories"/></a>
<br/>
<a href="<%= pageContext.getServletContext().getContextPath() %>/pharmacies"><spring:message
        code="title.pharmacies"/></a>
<br/>
<a href="<%= pageContext.getServletContext().getContextPath() %>/availability_of_drugs_in_pharmacy"><spring:message
        code="title.availability_of_drugs"/></a>
</body>
</html>
