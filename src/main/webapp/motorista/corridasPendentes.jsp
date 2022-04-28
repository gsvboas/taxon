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
      <form align="center" action="${pageContext.request.contextPath}/motoristas/corridasPendentes">
				<label for="start"> <strong>Escolha o mês que deseja ver as suas Corridas!</strong></label>
				<input type="month" min="2018-03" value="${year}-${month}" name="monthYear">
				<input type="submit" class="submit"/>
			</form>

      <p align="center" >Corridas Pendentes: ${totalCorridasPendentes}</p>

			<div align="center">
				<table border="1">
					<caption>
						Lista de Corridas
					</caption>
					<tr>
						<th>ID</th>
						<th>Data de início</th>
						<th>Data de fim</th>
					</tr>
					<c:if test="${requestScope.corridas != 'NULL' }">
						<c:forEach var="corrida" items="${corridasPendentes}">
								<tr>
									<td><a href="/motoristas/apresentaDetalhamentoCorrida/">${corrida.id}</a></td>
									<td>${corrida.iniciaAs}</td>
                  <c:choose>
                    <c:when test="${corrida.terminaAs == null}">
                      <td>${corrida.terminaAs}</td>
                    </c:when>
                    <c:otherwise>
                      <td>Pendente</td>
                    </c:otherwise>
                  </c:choose>
								</tr>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</body>
	</fmt:bundle>
</html>