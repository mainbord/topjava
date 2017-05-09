<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>

    <c:choose>
        <c:when test="${sessionScope.action == 'create'}">
            <h2><spring:message code="meal.create"/></h2>
        </c:when>
        <c:otherwise>
            <h2><spring:message code="meal.edit"/></h2>
        </c:otherwise>
    </c:choose>

    <hr>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>

<c:url var="addAction" value="/meal/save"/>
<form:form action="${addAction}" commandName="meal">

    <form method="post" action="meals">


        <dl>
            <dd>
                <spring:message text="ID"/>
            </dd>
            <dd>
                <form:input path="id" readonly="true" size="8" disabled="true"/>
                <form:hidden path="id"/>
            </dd>
        </dl>
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
        <button type="submit"><spring:message code="meal.save"/></button>
        <button onclick="window.history.back()"><spring:message code="meal.cancel"/></button>
    </form>
</form:form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
