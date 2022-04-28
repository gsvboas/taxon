<%--
  Created by IntelliJ IDEA.
  User: gsvboas
  Date: 27/04/2022
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Criar Agendamento</title>
</head>
<body>
    <form method="POST">
        <table style="width:20%">
            <tr style="width:100%">
                <td>
                    <input type="text" placeholder="CNPJ solicitante" name="cnpj"/>
                </td>
                <td>
                    <input type="date" placeholder="Data" name="data"/>
                </td>
                <td>
                    <input type="time" placeholder="Horário" name="hora"/>
                </td>
            </tr>
            <tr style="width:100%">
                <td colspan="3">
                    <input type="button" value="+ Adicionar Passageiro" style="width:100%" onclick="adicionaPassageiro()"/>
                </td>
            </tr>
            <tr style="width:100%">
                <td colspan="3">
                    <input type="submit" value="Consolidar Agendamento" style="width:100%"/>
                </td>
            </tr>
        </table>

        <script type="text/javascript">
            adicionaPassageiro = function(){

            }
        </script>
    </form>
</body>
</html>
