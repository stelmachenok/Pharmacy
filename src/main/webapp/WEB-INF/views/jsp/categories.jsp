<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ex" uri="/WEB-INF/tags/implicit.tld" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <%@ include file="styles.jsp" %>
    <title><spring:message code="title.categories"/></title>
</head>
<body>
<c:import url="navbar.jsp"/>
<script>
    function deleteConfirmation(brandName, id) {
        if (confirm("<spring:message code="question.deleteConfirmation"/> " + brandName + "?")) {
            window.location.href = "<%= pageContext.getServletContext().getContextPath() %>/categories?sort-field=${sortField}&sort-direction=${sortDir}&page-num=${pageNum}&page-size=${pageSize}&action=delete&id=" + id;
        }
    }
</script>
<c:url var="get_url" value="/categories"/>

<table class="table">
    <tr>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="CATEGORY_NAME" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.categoryName"/>
            </a></th>
        <th>
            <a href="<ex:ref pageContext="${get_url}" sortField="DESCRIPTION" sortDir="${!sortDir}" pageNum="${pageNum}" pageSize="${pageSize}"/>">
                <spring:message code="label.description"/>
            </a></th>
        <th><spring:message code="title.edit"/></th>
        <th><spring:message code="title.delete"/></th>
    </tr>

    <c:forEach items="${categories}" var="category">
        <tr>
            <td>${category.categoryName}</td>
            <td>${category.description}</td>
            <td>
                <a href="<ex:ref pageContext="${get_url}" sortField="${sortField}" sortDir="${sortDir}" pageNum="${pageNum}" pageSize="${pageSize}" action="edit" id="${category.id}"/>"
                   class="btn btn-default" role="button">
                    <spring:message code="title.edit"/>
                </a>
            </td>

            <td>
                <button id="delete" type="button" class="btn btn-default"
                        onclick="deleteConfirmation('${category.categoryName}', '${category.id}')">
                    <spring:message code="title.delete"/>
                </button>
            </td>
        </tr>
    </c:forEach>
</table>

<ul class="pagination">
    <c:forEach var="i" begin="1" end="${pagesCount}">
        <li>
            <a href="<ex:ref pageContext="${get_url}" sortField="${sortField}" sortDir="${sortDir}" pageNum="${i}" pageSize="${pageSize}"/>">${i}</a>
        </li>
    </c:forEach>
</ul>

<c:url var="post_url"
       value="/categoryFormExecute"/>
<form:form class="form-horizontal" method="POST" modelAttribute="category" action="${post_url}">
    <spring:bind path="id">
        <form:input type="hidden" value="${category.id}" path="id"/>
    </spring:bind>
    <input name="sortField" type="hidden" value="${sortField}"/>
    <input name="pageNum" type="hidden" value="${pageNum}"/>
    <input name="pageSize" type="hidden" value="${pageSize}"/>
    <input name="action" type="hidden" value="${action}"/>
    <input name="sortDir" type="hidden" value="${sortDir}"/>

    <div class="row">
        <label for="categoryName" class="col-sm-offset-3 col-sm-2 control-label">
            <spring:message code="label.categoryName"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="categoryName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text" size="70"
                                path="categoryName" id="categoryName"/>
                    <form:errors path="categoryName"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <label for="description" class="col-sm-offset-3 col-sm-2  control-label">
            <spring:message code="label.description"/>
        </label>
        <div class="col-sm-2">
            <spring:bind path="description">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input class="form-control" type="text"
                                size="70" path="description" id="description"/>
                    <form:errors path="description"/>
                </div>
            </spring:bind>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-offset-5 col-sm-2 button">
            <button type="submit" class="btn btn-default"><spring:message code="button.submit"/></button>
        </div>
    </div>
</form:form>
<div class="row">
    <label class="col-sm-offset-5 col-sm-2 control-label">${exceptionText}</label>
</div>

</body>
</html>
