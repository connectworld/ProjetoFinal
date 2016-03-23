<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Impressão</title>
	 		<link href="view/bootstrapHome/css/bootstrap.min.css" rel="stylesheet"
			media="screen" />
			<script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
			<script src="view/bootstrapHome/js/bootstrap.min.js"></script>	
			</head>
<body style="background-color: white">
<div align="center">
<div align="left" style="border: 2px solid black"">
	<div style="padding: 10px">
		<p>
			RELATORIO DE SERVICOS 
		</p>
	</div>
	<div style="padding: 10px">
		<h3>Dados da Empresa</h3>
		<table class="table">
			<tr>
				<td style="padding: 10px">DATA: ${data}</td>
			</tr>
			<tr>
				<td style="padding: 10px">FLAVIO REFRIGERAÇÃO</td>
			</tr>
			<tr>
				<td style="padding: 10px">AV: RAIMUND DINIZ, IPSEPE N: 40 - RECIPE PE</td>
			</tr>
			<tr>
				<td style="padding: 10px">SITE: WWW.F-REFRIGERAÇAO.WEBNODE.COM.BR</td>
			</tr>
		</table>
	</div>
	<br>
	<div style="padding: 10px">
		<h3>Servicos</h3>
		<table border="1" class="table">
			<tr>
				<th>COD</th>
				<th>CLIENTE</th>
				<th>CPF CLIENTE</th>
				<th>DATA PEDIDO</th>
				<th>AUTOR</th>
				<th>SITUACAO</th>
				<th>VALOR</th>
			</tr>
			
			<c:forEach var="pedido" items="${listaPedidoServico}" varStatus="id">
			<tr>
				<td style="padding: 10px">${pedido.cod}</td>
				<td style="padding: 10px">${pedido.cliente.nome}</td>
				<td style="padding: 10px">${pedido.cliente.cpf}</td>
				<td style="padding: 10px">
					<fmt:formatDate value="${pedido.data}" pattern="dd/MM/yyyy" />
				</td>
				<td style="padding: 10px">${pedido.codigo.nome}</td>
				<td style="padding: 10px">${pedido.situacao}</td>
				<td style="padding: 10px">${pedido.valor}</td>
			</tr>
			</c:forEach>	
		</table>
	</div>
	<br>
	<br><br><br><br><br>
	<button id="btn" class="btn btn-primary">Imprimir</button> &nbsp;
	<a href="exibirRelatorioServico"><button class="btn btn-primary">Fechar</button></a>
	
</div>
</div>
<script type="text/javascript">
		document.getElementById('btn').onclick = function() {
        	window.print();
      	};
	</script>
</body>
</html>