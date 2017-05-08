<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <c:if test="${param.action == 'create'}">
        <h2><spring:message code="meal.create"/></h2>
    </c:if>
    <c:if test="${param.action != 'create'}">
        <h2><spring:message code="meal.edit"/></h2>
    </c:if>
    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>

<c:url var="addAction" value="/meal/save"/>
<form:form action="${addAction}" commandName="meal">

    <form method="post" action="meals">
        <dd> <form:input  path="id" readonly="true" disabled="true"/> </dd>
        <dl>
            <dt><spring:message code="meals.date"/>:</dt>
            <dd> <form:input path="dateTime"/> </dd>
        </dl>
        <dl>
            <dt><spring:message code="meals.description"/>:</dt>
            <dd> <form:input path="description"/> </dd>
        </dl>
        <dl>
            <dt><spring:message code="meals.calories"/>:</dt>
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
