<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals title</title>
</head>

<style>
    .redtext {
        color: red;
    }
    .greentext {
        color: green;
    }


    .tg {
        border-collapse: collapse;
        border-spacing: 0;
        border-color: #ccc;
    }

    .tg td {
        font-family: Arial, sans-serif;
        font-size: 14px;
        padding: 10px 5px;
        border-style: solid;
        border-width: 1px;
        overflow: hidden;
        word-break: normal;
        border-color: #ccc;
        background-color: #fff;
    }

    .tg th {
        font-family: Arial, sans-serif;
        font-size: 14px;
        font-weight: normal;
        padding: 10px 5px;
        border-style: solid;
        border-width: 1px;
        overflow: hidden;
        word-break: normal;
        border-color: #ccc;
        color: #333;
        background-color: #f0f0f0;
    }
</style>

<body>
<h2>Meal list</h2>

<br>

<table class="tg">
    <tr>
        <th width="50">id</th>
        <th width="200">Дата и время</th>
        <th width="120">Оисание</th>
        <th width="120">Калории</th>
        <th width="120"></th>
        <th width="120"></th>
    </tr>

    <c:forEach items="${meals}" var="meal">
        <tr>
            <form action="${pageContext.request.contextPath}/meals" method="post">
            <c:choose>
                <c:when test="${meal.isExceed()}">
                    <td class="redtext">${meal.id}</td>
                    <td class="redtext">${meal.dateTime}</td>
                    <td class="redtext">${meal.description}</td>
                    <td class="redtext">${meal.calories}</td>
                </c:when>
                <c:otherwise>
                    <td class="greentext">${meal.id}</td>
                    <td class="greentext">${meal.dateTime}</td>
                    <td class="greentext">${meal.description}</td>
                    <td class="greentext">${meal.calories}</td>
                </c:otherwise>
            </c:choose>

                <td><a href="meals?action=edit&mealId=<c:out value="${meal.id}"/>">edit</a></td>
                <td><a href="meals?action=delete&mealId=<c:out value="${meal.id}"/>">Delete</a></td>


<%--            <td><input type="submit" value="Редактировать"></td>
            <td><input type="submit" value="Удалить"></td>--%>
            </form>
        </tr>
    </c:forEach>
</table>

<br>

<h2>Add a Meal</h2>

<%--<c:url var="addAction" value="/people/add"/>--%>

<form action="${pageContext.request.contextPath}/meals" method="post">

    <c:if test="${!empty meal.id}">
        <label>id</label> <input type="text" id="id" name="id" value=${meal.id}>
    </c:if>

<%--    <label>id</label> <input type="text" id="id" name="id" value=${meal.id}>--%>
    <br>
    <label>Дата</label> <input type="datetime-local" id="date" name="date" value=${meal.dateTime}>
    <br>
    <label>Описание</label> <input type="text" id="description" name="description" value=${meal.description}>
    <br>
    <label>Калории</label> <input type="text" id="calories" name="calories" value=${meal.calories}>
    <br>
    <p><td><input type="submit" value="Добавить"></td></p>
</form>
</body>
</html>
