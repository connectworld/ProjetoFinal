<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Niveis de Usuarios</title>
	<link href="view/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
	<script src="view/bootstrap/js/bootstrap.min.js"></script>
	<!-- DataTables CSS -->
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.8/css/jquery.dataTables.css">
	<!-- jQuery -->
	<script type="text/javascript" charset="utf8" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.8/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="view/js/table.js"></script>
</head>
<body>
	<c:import url="../menu.jsp"></c:import>
	<div class="tabelas">
		<c:choose>
			<c:when test="${not empty mensagem}">
				<div class="alert alert-success">
  					<button type="button" class="close" data-dismiss="alert"><span class="glyphicon glyphicon-remove"></span></button>
  					<strong>${mensagem}</strong>
				</div>
			</c:when>
		</c:choose>
		<div align="center"><h3 class="h3">Escolha as Telas de Permissão</h3></div>
			<table id="tableProduto" class="table">
				<thead>
					<tr>
						<th>COD</th>
						<th>DESCRIÇÃO</th>
						<th>SELELECIONAR</th>
					</tr>
				</thead> 
				<c:forEach var="tela" items="${listaTelas}" varStatus="id">
						<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'blue' }">
							<td>${tela.cod }</td>
							<td>${tela.descricao}</td>
							<td><a href="selecionarTela?cod=${tela.cod}" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span>Selecionar</a></td>
						</tr>	
				</c:forEach>	
			</table>
		</div>
		<div class="tabelas">
		<div align="center"><h3 class="h3">Telas Selecionadas</h3></div>
			<table id="servicoSelecionado" class="table">
				<thead>
					<tr>
						<th>COD</th>
						<th>NOME</th>
						<th>REMOVER</th>
					</tr>
				</thead> 
				<c:forEach var="telaAdd" items="${listaTelaAdd}" varStatus="id">
						<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'blue' }">
							<td>${telaAdd.cod }</td>
							<td>${telaAdd.descricao}</td>
							<td><a class="btn icon-btn btn-warning" href="removerTela?cod=${telaAdd.cod}"><span class="glyphicon btn-glyphicon glyphicon-minus img-circle text-warning"></span>Remover</a></td>
						</tr>	
				</c:forEach>	
			</table>
		</div>
		<br>
		<div align="center">
				<a class="btn icon-btn btn-info" href="cadastrarNivelEtapa2"><span class="glyphicon btn-glyphicon glyphicon-share img-circle text-info"></span>Próxima Etapa do Pedido</a>
		</div>
</body>
</body>
</html>