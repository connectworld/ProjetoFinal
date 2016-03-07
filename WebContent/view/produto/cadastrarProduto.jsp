<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Cadastrar Produto</title>
    <link href="view/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
	<script src="view/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="view/js/validaForm.js"></script>
	<script type="text/javascript" src="view/js/jquery.maskedinput.js"></script>
	<script type="text/javascript" src="view/js/jquery.maskMoney.js"></script>
	<script type="text/javascript" src="view/js/jquery.alphanumeric.js"></script>
</head>
<body>
<c:import url="../menu.jsp"></c:import>
<div class="spacing">
<div class="container">
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <form method="post" action="salvarProduto" enctype="multipart/form-data">
            <h3 class="labelsEdit">Cadastro de Produto</h3>
            <input type="hidden" name="usuario" value="${usuarioLogado.cod}">
				<div class="form-group">
        			<label for="nome" class="labelsEdit">Nome:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="nome" id="validate-text" placeholder="Nome" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="descricao" class="labelsEdit">Descricao:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="descricao" id="validate-text" placeholder="Descricao" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
    			<div class="form-group">
        			<label for="precoVenda" class="labelsEdit">Preco de Venda:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="precoVenda" id="precoVenda" placeholder="Preco" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="quantidade" class="labelsEdit">Quantidade:</label>
					<div class="input-group" data-validate="number">
						<input type="text" class="form-control" name="quantidade" id="validate-number" placeholder="Quantidade" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
    			<div class="form-group">
        			<label for="imagem" class="labelsEdit">Imagem:</label>
					<div class="input-group">
						<input type="file" class="form-control" name="img" id="validate-text" placeholder="Imagem" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
                <button class="btn btn-lg btn-success btn-block" type="submit">Cadastrar</button>
            </form>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
		$(document).ready(function(){
			$('#precoVenda').numeric();
			$('#validate-number').numeric();
			$('#precoVenda').maskMoney({showSymbol:true, symbol:"R$", decimal:".", thousands:"."});		
		});
</script> 
</body>
</html>
