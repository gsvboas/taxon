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
      <a href="${pageContext.request.contextPath}/motoristas/carro" type="submit" class="botao-adicionarpacote">
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
						<th> </th>
					</tr>
					<c:if test="${requestScope.veiculos != 'NULL' }">
						<c:forEach var="veiculo" items="${requestScope.veiculos}">
								<tr>
									<td><a href="${pageContext.request.contextPath}/motoristas/carro?chassi=${veiculo.chassi}">${veiculo.chassi}</a></td>
									<td>${veiculo.placa}</td>
									<td>${veiculo.modelo}</td>
									<td>${veiculo.maxOcupacao}</td>
									<td><a href="${pageContext.request.contextPath}/motoristas/deletaCarro?chassi=${veiculo.chassi}">Deletar</a></td>
								</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>