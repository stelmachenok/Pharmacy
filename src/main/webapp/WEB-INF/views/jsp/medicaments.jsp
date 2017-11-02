<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title> Tag Example</title>
</head>

<body>
<c:forEach var="i" items="${medicaments}">

    Item <c:out value="${i}"/>

</c:forEach>
</body>
</html>