<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
	<fmt:bundle basename="msgs">
	<head>
		<title>
			Corridas Feitas
		</title>
	</head>

	
		<body>
      <%
				String contextPath = request.getContextPath().replace("/", "");
			%>

      <a href="adicionarPacote.jsp" type="submit" class="botao-adicionarpacote">
          Adicionar +
      </a>
            
			<div align="center">
				<table border="1">
					<caption>
						Lista de Carros
					</caption>
					<tr>
						<th>Chassi</th>
						<th>Placa</th>
						<th>Modelo</th>
						<th>Ocupação</th>
					</tr>
					<c:if test="${requestScope.veiculos != 'NULL' }">
						<c:forEach var="veiculo" items="${requestScope.veiculos}">
								<tr>
									<td><a href="/motoristas/carros/${veiculo.chassi}">${veiculo.chassi}</a></td>
									<td>${veiculo.placa}</td>
									<td>${veiculo.modelo}</td>
									<td>${veiculo.maxOcupacao}</td>
								</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>