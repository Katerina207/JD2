<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Catalog</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/catalog" method="post">
        <div class="form-group">
            <label for="boilingPoint">Boiling point:</label>
            <select name="boilingPoint" id="boilingPoint">
                <option></option>
                <c:forEach var="boilingPoint" items="${requestScope.boilingPoints}">
                    <option value="${boilingPoint}">${boilingPoint}</option>
                </c:forEach>
            </select>
            <br>
            <label for="range">Range:</label>
            <select name="range" id="range">
                <option></option>
                <c:forEach var="range" items="${requestScope.ranges}">
                    <option value="${range}">${range}</option>
                </c:forEach>
            </select>
            <br>
            <label for="minRefCapacity">Min refrigeration capacity:
                <input id="minRefCapacity" type="number" name="minRefCapacity">
            </label>
            <br>
            <label for="maxRefCapacity">Max refrigeration capacity:
                <input id="maxRefCapacity" type="number" name="maxRefCapacity">
            </label>
            <br>
            <input type="submit" name="search" value="Search">
        </div>
    </form>
    <table class="table table-striped">
        <tr>
            <th>Unit</th>
            <th>Price</th>
        </tr>
        <c:forEach var="unit" items="${requestScope.units}">
            <tr>
                <td><a href="${pageContext.request.contextPath}/unit-info?id=${unit.id}">${unit.name} </a></td>
                <td> ${unit.price} </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>