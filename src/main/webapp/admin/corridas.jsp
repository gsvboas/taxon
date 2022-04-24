<%--
  Created by IntelliJ IDEA.
  User: gsvboas
  Date: 24/04/2022
  Time: 14:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Corridas</title>
</head>
<body>
    <table>
        <c:forEach var="corrida" items="${requestScope.corridas}">
            <tr>
                <td><c:out value="${corrida.id.toString()}"/></td>
                <td><c:out value="${corrida.cpfMotorista}"/></td>
                <td><c:out value="${corrida.chassiVeiculo}"/></td>
                <td><c:out value="${corrida.valorTotal.toString()}"/></td>
                <td><c:out value="${corrida.dataInicio.toString()}"/></td>
                <td><a href='corridas?id=<c:out value="${corrida.id.toString()}"/>'>Detalhes</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
