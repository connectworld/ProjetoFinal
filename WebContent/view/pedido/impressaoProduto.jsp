<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Impressão</title>
	<link href="view/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen" />
	<script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
	<script src="view/bootstrap/js/bootstrap.min.js"></script>
</head>
<body style="background-color: white">
<div>
<div align="left">
	<div>
		<p>
			Vendedor : ${usuario.nome};
		</p>
		<p>
			Data : ${pedido.data};
		</p>
	</div>
	<div>
		<h3>Dados do Cliente</h3>
		<table border="1">
			<tr>
				<td>Nome:${pedido.cliente.nome}</td>
			</tr>
			<tr>
				<td>Cpf:${pedido.cliente.cpf}</td>
			</tr>
			<tr>
				<td>CEP:${pedido.cliente.cep}</td>
			</tr>
			<tr>
				<td>Logradouro:${pedido.cliente.rua} - Nº ${pedido.cliente.numero}</td>
			</tr>
			<tr>
				<td>Bairro:${pedido.cliente.bairro}</td>
			</tr>
			<tr>
				<td>Contato1:${pedido.cliente.contato1}</td>
			</tr>
		</table>
	</div>
	<br><br>
	<div>
		<h3>Produto</h3>
		<table border="1">
			<tr>
				<th class="thMenor">COD</th>
					<th >NOME</th>
					<th >DESCRIÇÃO</th>
					<th >CADASTRANTE</th>
					<th >PRECO VENDA</th>
					<th >QUANTIDADE</th>
					<th >IMAGEM</th>
			</tr>
			<tr>
			<c:forEach var="produtoAdd" items="${listaProdutoAdd}" varStatus="id">
					<tr>
						<td>${produtoAdd.cod }</td>
						<td>${produtoAdd.nome}</td>
						<td>${produtoAdd.descricao}</td>
						<td>${produtoAdd.precoVenda}</td>
						<td>${produtoAdd.quantidade}</td>
						<td>
							<img alt="img" src="view/img/${produtoAdd.imagem}" style="width: 30%;" />
					</tr>	
			</c:forEach>	
			</tr>
		</table>
	</div>
	<br><br>
	<div>
		<h3>Total R$: ${pedido.valor}</h3>
	</div>
	<br><br>
	<h3> Sua Assinatura : -----------------------------------------</h3>
	<h3> Assinatura do Vendedor : -----------------------------------------</h3>
	<h3> Data da Baixa : ___/_____/______</h3>
	<br>
	<h3>Cortar <br>
	----------------------------------------------------------------------------------------------------------------------------------
	</h3>
	<button id="btn" class="btn btn-primary">Imprimir</button>
</div>
</div>
<script type="text/javascript">
		document.getElementById('btn').onclick = function() {
        	window.print();
      	};
	</script>
</body>
</html>