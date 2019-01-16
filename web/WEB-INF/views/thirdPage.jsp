<%--
  Created by IntelliJ IDEA.
  User: Sviridova
  Date: 01.03.2018
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Third page</title>
</head>
<body>
<table>
    <tr>
        <c:forEach var="rs" items="${resSet}">
            ${rs}
        </c:forEach>
        <br/>
        <c:forEach items="${listOfMap}" var="maps">
            <c:forEach items="${maps}" var="mapItem">
                ${mapItem.key} ${mapItem.value}
            </c:forEach>
            <br />
        </c:forEach>

    </tr>
</table>
</body>
</html>
