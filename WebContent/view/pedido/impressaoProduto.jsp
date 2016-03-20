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
		
			Pedido Realizado por: ${pedido.codigo.nome}
		</p>
		<p>
			Data : <fmt:formatDate value="${pedido.data}" pattern="dd/MM/yyyy" />
		</p>
		<p>
			PEDIDO PRODUTO - Nº ${pedido.cod}
		</p>
	</div>
	<div style="padding: 10px">
		<h3>Dados do Cliente</h3>
		<table class="table">
			<tr>
				<td style="padding: 10px">Nome:${pedido.cliente.nome}</td>
			</tr>
			<tr>
				<td style="padding: 10px">Cpf:${pedido.cliente.cpf}</td>
			</tr>
			<tr>
				<td style="padding: 10px">CEP:${pedido.cliente.cep}</td>
			</tr>
			<tr>
				<td style="padding: 10px">Logradouro: ${pedido.cliente.rua} - Nº ${pedido.cliente.numero}</td>
			</tr>
			<tr>
				<td style="padding: 10px">Bairro:${pedido.cliente.bairro}</td>
			</tr>
			<tr>
				<td style="padding: 10px">Contato1:${pedido.cliente.contato1}</td>
			</tr>
		</table>
	</div>
	<br>
	<div style="padding: 10px">
		<h3>Produtos</h3>
		<table border="1" class="table">
			<tr>
				<th>COD</th>
				<th>NOME</th>
				<th>DESCRIÇÃO</th>
				<th>PREÇO DA VENDA</th>
			</tr>
			
			<c:forEach var="produto" items="${listaProdutoAdd}" varStatus="id">
			<tr>
				<td style="padding: 10px">${produto.cod}</td>
				<td style="padding: 10px">${produto.nome}</td>
				<td style="padding: 10px">${produto.descricao}</td>
				<td style="padding: 10px">${produto.precoVenda}</td>
			</tr>
			</c:forEach>	
		</table>
	</div>
	<br>
	<div style="padding: 10px">
		<h3>Dados Gerais</h3>
		<table class="table">
			<tr>
				<td style="padding: 10px">Valor Total R$: ${pedido.valor}</td>
			</tr>
			<tr>
				<td style="padding: 10px">Assinatura do Cliente: _____________________________________</td>
			</tr>
			<tr>
				<td style="padding: 10px">Assinatura do Operador:_____________________________________</td>
			</tr>
			<tr>
				<td style="padding: 10px">Data :___/____/______ </td>
			</tr>
		</table>
	</div>
	<br><br><br><br><br>
	<button id="btn" class="btn btn-primary">Imprimir</button> &nbsp;
	<a href="pedidoProdutoAdmin"><button class="btn btn-primary">Fechar</button></a>
	
</div>
</div>
<script type="text/javascript">
		document.getElementById('btn').onclick = function() {
        	window.print();
      	};
	</script>
</body>
</html>