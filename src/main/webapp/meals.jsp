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
        <th width="200">Дата и время</th>
        <th width="120">Оисание</th>
        <th width="120">Калории</th>
    </tr>

    <c:forEach items="${meals}" var="attr">
        <c:choose>
            <c:when test="${attr.isExceed()}">
                <tr class="redtext">
                    <td>${attr.getDateTime()}</td>
                    <td>${attr.getDescription()}</td>
                    <td>${attr.getCalories()}</td>
                </tr>
            </c:when>
            <c:otherwise>
                <tr class="greantext">
                    <td>${attr.getDateTime()}</td>
                    <td>${attr.getDescription()}</td>
                    <td>${attr.getCalories()}</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</table>

<br>
<br>
<br>




</body>
</html>
