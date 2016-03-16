<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Selecionar Servico</title>
	<link href="view/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
    <script type="text/javascript" src="view/js/jquery.alphanumeric.js"></script>
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
	<c:choose>
		<c:when test="${not empty mensagem}">
			<div class="alert alert-success">
  				<button type="button" class="close" data-dismiss="alert">
  					<span class="glyphicon glyphicon-remove"></span>
  				</button>
  				<strong>${mensagem}</strong>
			</div>
		</c:when>
	</c:choose>
	<div class="col-xs-2" style="margin-top: 5%; margin-left: 2%;">
		<form action="addServicoPedido">
	 		<label for="cod">Cod</label>
	  		<input class="form-control" id="codigo" name="cod" type="text" maxlength="10">
	  		<button type="submit"><span class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span>Add</button>
  		</form>
	</div>
	<div class="tabelas">
	<div align="center"><h3 class="h3">PEDIDO DE SERVICO</h3></div>
	<div align="center"><h3 class="h3">SELECIONE O SERVICO</h3></div>
		<table id="tableProduto" class="table">
			<thead>
				<tr>
					<th>COD</th>
					<th>NOME</th>
					<th>DESCRIÇÃO</th>
					<th>PRECO</th>
					<th>GARANTIA</th>
				</tr>
			</thead> 
			<c:forEach var="servico" items="${listaServico}" varStatus="id">
					<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'blue' }">
						<td>${servico.cod }</td>
						<td>${servico.nome}</td>
						<td>${servico.descricao}</td>
						<td>${servico.preco}</td>
						<td>
							<fmt:formatDate value="${servico.garantia}" pattern="dd/MM/yyyy" />
						</td>
						<td><a href="retornapedidoServicoAdmin?cod=${servico.cod}" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span>Selecionar</a></td>
					</tr>	
			</c:forEach>	
		</table>
		<a class="btn icon-btn btn-info" href="pedidoServicoEtapa2"><span class="glyphicon btn-glyphicon glyphicon-share img-circle text-info"></span>Próximo</a>
		<br><br>
		<h3>Servicos Selecionados</h3>
		<table class="table" id="servicoSelecionado">
			<thead>
				<tr>
					<th>COD</th>
					<th>NOME</th>
					<th>DESCRIÇÃO</th>
					<th>PRECO</th>
					<th>GARANTIA</th>
				</tr>
			</thead> 
			<c:forEach var="servicoAdd" items="${listaServicoAdd}" varStatus="id">
					<tr>
						<td>${servicoAdd.cod }</td>
						<td>${servicoAdd.nome}</td>
						<td>${servicoAdd.descricao}</td>
						<td>${servicoAdd.preco}</td>
						<td>
							<fmt:formatDate value="${servicoAdd.garantia}" pattern="dd/MM/yyyy" />
						</td>
						<td><a class="btn icon-btn btn-warning" href="removerServicoPedido?cod=${servicoAdd.cod}"><span class="glyphicon btn-glyphicon glyphicon-minus img-circle text-warning"></span>Remove</a></td>
					</tr>	
			</c:forEach>	
		</table>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#codigo').numeric();	
		});
	</script> 
</body>
</html>