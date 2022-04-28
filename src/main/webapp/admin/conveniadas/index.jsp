<%--
  Created by IntelliJ IDEA.
  User: gsvboas
  Date: 27/04/2022
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Agendamentos</title>
</head>
<body>

<header>
    <nav>
        <c:if test="${!request.getRequestURL().endsWith('conveniadas/criar')}">
            <a href="conveniadas/criar">Criar Conveniada</a>
        </c:if>
    </nav>
</header>

<table>
    <c:forEach var="conveniada" items="${conveniadas}">
        <tr>
            <td>
                <c:out value="${conveniada.cnpj}"/>
            </td>
            <td>
                <c:out value="${conveniada.nome}"/>
            </td>
            <td>
                <c:out value="${conveniada.setor}"/>
            </td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
