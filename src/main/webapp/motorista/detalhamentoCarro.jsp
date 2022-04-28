<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html>
	<fmt:bundle basename="msgs">
	<head>
		<title>
			Carro
		</title>
	</head>

	
		<body>
      <a href="${pageContext.request.contextPath}/motoristas/carros" type="submit" class="botao-adicionarpacote">
          Voltar
      </a>
            
			<form align="center" class="login-form" method="post" action="${pageContext.request.contextPath}/motoristas/adicionaCarro">
        <div>
          <label for="chassi">Chassi</label>
          <input type="text" name="chassi" value="${veiculo.chassi}" maxlength="17">
        </div>
        <div>
          <label for="placa">Placa</label>
          <input type="text" name="placa" value="${veiculo.placa}" maxlength="7">
        </div>
        <div>
          <label for="marca">Marca</label>
          <input type="text" name="marca" value="${veiculo.marca}" maxlength="50">
        </div>
        <div>
          <label for="modelo">Modelo</label>
          <input type="text" name="modelo" value="${veiculo.modelo}" maxlength="50">
        </div>
        <div>
          <label for="ano">Ano</label>
          <input type="text" name="ano" value="${veiculo.ano}" maxlength="4">
        </div>
        <div>
          <label for="cor">Cor</label>
          <input type="text" name="cor" value="${veiculo.cor}" maxlength="255">
        </div>
        <div>
          <label for="maxOcupacao">Máx. Ocupação</label>
          <input type="text" name="maxOcupacao" value="${veiculo.maxOcupacao}" maxlength="2">
        </div>
        <div>
          <label for="garagemCep">Garagem CEP</label>
          <input type="text" name="garagemCep" value="${veiculo.garagemCep}" maxlength="8">
        </div>
        <div>
          <label for="garagemNum">Garagem Número</label>
          <input type="text" name="garagemNum" value="${veiculo.garagemNum}" maxlength="4">
        </div>
        <div>
          <label for="garagemNumVaga">Garagem Vaga</label>
          <input type="text" name="garagemNumVaga" value="${veiculo.garagemNumVaga}" maxlength="4">
        </div>
        <c:choose>
          <c:when test="${veiculo != null}">
            <input class="btn" type="submit" class="submit" name="clienteOK" value="Atualizar"/>
          </c:when>
          <c:otherwise>
            <input class="btn" type="submit" class="submit" name="clienteOK" value="Adicionar"/>
          </c:otherwise>
        </c:choose>
			</form>
		</body>
	</fmt:bundle>
</html>
