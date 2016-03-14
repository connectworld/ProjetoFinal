<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Servicos</title>
	<link href="view/bootstrapHome/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
	<script src="view/bootstrapHome/js/bootstrap.min.js"></script>
	<!-- DataTables CSS -->
	<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.8/css/jquery.dataTables.css">
	<!-- jQuery -->
	<script type="text/javascript" charset="utf8" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<!-- DataTables -->
	<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.8/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="view/js/table.js"></script>
</head>
<body>
<c:import url="/view/principal/menu.jsp"></c:import>	
	<div class="tabelas">
	<div align="center"><h3 class="h3">Serviços</h3></div>
		<table id="tableProduto" class="table">
			<thead>
				<tr>
					<th>NOME</th>
					<th>DESCRICAO</th>
					<th>PRECO</th>
					<th>GARANTIA</th>
					<th>OPCAO</th>
				</tr>
			</thead> 
			<c:forEach var="servico" items="${listaServico}" varStatus="id">
					<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'blue' }">
						<td>${servico.nome}</td>
						<td>${servico.descricao}</td>
						<td>${servico.preco}</td>
						<td>
							<fmt:formatDate value="${servico.garantia}" pattern="dd/MM/yyyy" />
						</td>
						<td><a href="chamaServico?cod=${servico.cod}" class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span>Fazer Pedido</a></td>
					</tr>
				
			</c:forEach>	
		</table>
	</div>
</body>
</html>