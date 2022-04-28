<%--
  Created by IntelliJ IDEA.
  User: gsvboas
  Date: 28/04/2022
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Corrida <c:out value="${param.id}"/></title>
</head>
<body>
    <table style="width:80%">
        <tr>
            <td>
                <label>
                    <b>Motorista</b>
                </label>
                <c:out value="${corrida.nomeMotorista}"/>
            </td>
            <td>
                <label>
                    <b>CPF</b>
                </label>
                <c:out value="${corrida.cpfMotorista}"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <b>Veiculo</b>
                </label>
                <c:out value="${corrida.marcaVeiculo} ${corrida.modeloVeiculo} ${corrida.corVeiculo}"/>
            </td>
            <td>
                <label>
                    <b>Placa</b>
                </label>
                <c:out value="${corrida.placaVeiculo}"/>
            </td>
        </tr>
        <tr>
            <td>
                <label>
                    <b>Partida</b>
                </label>
                <c:out value="Às ${corrida.dataInicio.toString()} em ${corrida.localDePartida}"/>
            </td>
            <td>
                <label>
                    <b>Chegada</b>
                </label>
                <c:if test="${corrida.dataFim != null}">
                    <c:out value="Corrida finalizada às ${corrida.dataFim.toString()}, destino: ${corrida.localDeChegada}"/>
                </c:if>
                <c:if test="${corrida.dataFim == null}">
                    <c:out value="Corrida não finalizada, destino: ${corrida.localDeChegada}"/>
                </c:if>
            </td>
        </tr>
    </table>

</body>
</html>
