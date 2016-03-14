<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Editando Servico</title>
    <link href="view/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
	<script src="view/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="view/js/cadastrarProduto.js"></script>
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
            <form method="post" action="atualizarServico" enctype="multipart/form-data">
            <input type="hidden" name="cod" value="${servico.cod}">
            <input type="hidden" name="usuario" value="${usuarioLogado.cod }">
            <input type="hidden" name="compara" value="salvar">
            <h3 class="labelsEdit">Editar de Servico</h3>
				<div class="form-group">
        			<label for="nome" class="labelsEdit">Nome:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="nome" value="${servico.nome}" id="validate-text" placeholder="Nome" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="descricao" class="labelsEdit">Descricao:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="descricao" value="${servico.descricao}" id="validate-text" placeholder="Descricao" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
    			<div class="form-group">
        			<label for="precoVenda" class="labelsEdit">Preco:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="preco" value="${servico.preco}" id="preco" placeholder="Preco" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="garantia" class="labelsEdit">Garantia:</label>
					<div class="input-group">
						<input type="text" id="garantia" class="form-control" value="<fmt:formatDate value="${servico.garantia}" pattern="dd/MM/yyyy" />"  name="garantia"  placeholder="dd/mm/aaaa" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
                <button class="btn btn-lg btn-success btn-block" type="submit">Atualizar</button>
            </form>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
		$(document).ready(function(){
			$('#preco').numeric();
			$('#preco').maskMoney({showSymbol:true, symbol:"R$", decimal:".", thousands:"."});
			$("#garantia").mask("99/99/9999");
		});
</script> 
</body>
</html>
