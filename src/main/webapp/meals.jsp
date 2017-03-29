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
    .greantext {
        color: green;
    }
</style>

<body>
<h2>Meal list</h2>

<br>

<table class="tg">
    <tr>
        <th width="200">Дата и время</th>
        <th width="120">Оисание</th>
        <th width="120">Калории</th>
    </tr>

<c:forEach items="${requestScope.values().toArray()[4]}" var="attr">

        <c:if test="${attr.isExceed()}">
            <tr class="redtext">
                <td>${attr.getDateTime()}</td>
                <td>${attr.getDescription()}</td>
                <td>${attr.getCalories()}</td>
            </tr>
        </c:if>
        <c:if test="${!attr.isExceed()}">
            <tr class="greantext">
            <td>${attr.getDateTime()}</td>
            <td>${attr.getDescription()}</td>
            <td>${attr.getCalories()}</td>
            </tr>
        </c:if>

</c:forEach>
</table>
<br>
<br>
<br>
</body>
</html>
