<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<%--<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>--%>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
<%--    <h2><a href="index.html">Home</a></h2>--%>

    <c:if test="${param.action == 'create'}">
        <h2><spring:message code="meal.create"/></h2>
    </c:if>
    <c:if test="${param.action != 'create'}">
        <h2><spring:message code="meal.edit"/></h2>
    </c:if>

<%--    <h2>${param.action == 'create' ? 'Create Meal' : 'Edit meal'}</h2>--%>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>

<c:url var="addAction" value="/meal/save"/>
<form:form action="${addAction}" commandName="meal">

    <form method="post" action="meals">
<%--        <input type="hidden" name="id" value="${meal.id}">--%>
        <dd> <form:input path="id"/> </dd>
        <dl>
            <dt><spring:message code="meals.date"/>:</dt>
            <dd> <form:input path="dateTime"/> </dd>
            <dd><input type="datetime-local" value="${meal.dateTime}" name="dateTime"></dd>


<%--            <tr>
                <td>
                    <form:label path="name">
                        <spring:message text="Name"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="name"/>
                </td>
            </tr>--%>

        </dl>
        <dl>
            <dt><spring:message code="meals.description"/>:</dt>
            <%--<dd><input type="text" value="${meal.description}" size=40 name="description"></dd>--%>
            <dd> <form:input path="description"/> </dd>
        </dl>
        <dl>
            <dt><spring:message code="meals.calories"/>:</dt>
<%--            <dd><input type="number" value="${meal.calories}" name="calories"></dd>--%>
            <dd> <form:input path="calories"/> </dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</form:form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
