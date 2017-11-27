<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel='stylesheet' href='/webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
    <script src="/webjars/jquery/3.2.1/jquery.js"></script>
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
<a href="<%= pageContext.getServletContext().getContextPath() %>/availabilityOfDrugs"><spring:message
        code="title.availability_of_drugs"/></a>
</body>
</html>
