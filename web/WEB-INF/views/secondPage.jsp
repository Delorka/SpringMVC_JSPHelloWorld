<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Second Page</title>
</head>
<body>
Введенное имя: ${userJSP.name};
<br/>
Введенный пароль: ${userJSP.password};
<br/>
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
