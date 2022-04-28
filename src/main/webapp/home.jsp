<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	    <head>
	        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <%
        String contextPath = request.getContextPath().replace("/", "");
    %>
	        <title>
                taxi
	        </title>
	        <link href="styles.css" rel="stylesheet" type="text/css"/>
	    </head>
	    <body>
			<div >				
				<div class="hello">
				<c:if test="${sessionScope.usuarioLogado.papel == 'MOT' }">
					<p>Seja bem-vindo(a) ${sessionScope.usuarioLogado.nome}!</p>
                    <a href="motoristas/corridas">Corridas Feitas</a>
                    <a href="motoristas/corridasPendentes">Corridas Pendentes</a>
                    <a href="motoristas/carros">Meus carros</a>
				</c:if>

                <c:if test="${sessionScope.usuarioLogado.papel == 'CONV' }">
					<p>Seja bem-vindo(a) ${sessionScope.usuarioLogado.nome}!</p>
                    <a href="/<%= contextPath%>/clients/faturaMes">Faturas</a>
                    <a href="">Corridas Pendentes</a>
                    <a href="">Agendar Corridas</a>
                    <a href="">Passageiros</a>
				</c:if>

                <c:if test="${sessionScope.usuarioLogado.papel == 'ADMIN' }">
					<p>Seja bem-vindo(a) ${sessionScope.usuarioLogado.nome}!</p>
                    <a href="">Motoristas</a>
                    <a href="admin/conveniadas">Conveniadas</a>
                    <a href="admin/agendamentos">Agendamentos</a>
                    <a href="admin/corridas">Corridas</a>
				</c:if>
				</div>
				<br>
			</div>			
	    </body>
</html>