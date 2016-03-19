<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Cadastrar Servico</title>
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
            <form method="post" action="salvarServico" enctype="multipart/form-data">
            <input type="hidden" name="usuario" value="${usuarioLogado.cod }">
            <input type="hidden" name="compara" value="salvar">
            <h3 class="labelsEdit">Listar Pedido de Serviço Cadastrado</h3>
				<div class="form-group">
        			<label for="datainicial" class="labelsEdit">Data Inicial:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="dataInicial" id="dataInicial" placeholder="Data Inicial" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="dataFinal" class="labelsEdit">Data Final:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="dataFinal" id="dataFinal" placeholder="Data Final" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
    			<div class="form-group">
        			<label for="preco" class="labelsEdit">Selecionar Situação:</label>
					<div class="input-group">
						  <select class="form-control" name="situacao" id="validate-select"  required>
                 			<option value="">Selecione</option>
								<option value="A">Serviço Aberto</option>
								<option value="B">Serviço Baixado</option>
								<option value="C">Serviço Cancelado</option>
                        </select>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
                <button class="btn btn-lg btn-success btn-block" type="submit">Listar Serviço</button>
            </form>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
		$(document).ready(function(){
			$("#dataInicial").mask("99/99/9999");
			$("#dataFinal").mask("99/99/9999");
		});
</script> 
</body>
</html>
