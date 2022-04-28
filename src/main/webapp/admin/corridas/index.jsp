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
        <c:if test="${param.conv != null}">
            <tr>
                <td colspan="7">
                    <a href="corridas" align="center">Ver todas</a>
                </td>
            </tr>
        </c:if>
        <tr>

            <td colspan="7">
                <form method="GET">
                    <input name="conv" id="conv" type="text" placeholder="Pesquisar por conveniada" style="width:100%"/>
                </form>
            </td>
        </tr>
        <c:forEach var="corrida" items="${requestScope.corridas}">
            <tr>
                <td><c:out value="${corrida.id.toString()}"/></td>
                <td><c:out value="${corrida.nomeConveniada}"/></td>
                <td><c:out value="${corrida.cpfMotorista}"/></td>
                <td><c:out value="${corrida.chassiVeiculo}"/></td>
                <td><c:out value="${corrida.valorTotal.toString()}"/></td>
                <td><c:out value="${corrida.dataInicio.toString()}"/></td>
                <td><a href='?id=<c:out value="${corrida.id.toString()}"/>'>Detalhes</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
