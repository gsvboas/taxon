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
        <c:if test="${!request.getRequestURL().endsWith('agendamentos/criar')}">
            <a href="agendamentos/criar">Criar Agendamento</a>
        </c:if>
    </nav>
</header>

</body>
</html>
