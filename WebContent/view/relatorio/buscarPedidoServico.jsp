<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Listar Pedidos Servicos</title>
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
            <div class="col-sm-8">

                <h3 class="page-header">Listar Pedido</h3>
                <form role="form" action="listarPedidoServico" method="post">
                    <div class="form-group float-label-control">
                        <label for="Data Inicial">Data Inicial</label>
                        <input type="text" class="form-control" placeholder="Data Inicia" id="dataInicial" name="dataInicial" size="10" required="required">
                    </div>
                    <div class="form-group float-label-control">
                        <label for="Data Inicial">Data Final</label>
                        <input type="text" class="form-control" placeholder="Data Final" id="dataFinal" name="dataFinal" size="10" required="required">
                    </div>
                    <div class="form-group float-label-control">
                        <label for="">Situação</label>
                        <select name="situacao">
                        	<option value="A">A - Aberto</option>
                        	<option value="B">B - Baixado</option>
                        	<option value="C">C - Cancelado</option>
                        </select>
                    </div>
                    <input class="button" name="enviar" value="Gerar" type="submit">
                </form>
            </div>
        </div>
        <div class="container">

        <div class="row">
            <div class="col-sm-8">

                <h3 class="page-header">buscar po Codigo</h3>
                <form role="form" action="buscarPedidoPorCodigo" method="post">
                    <div class="form-group float-label-control">
                        <label for="Data Inicial">Codigo</label>
                        <input type="text" class="form-control" placeholder="codigo" id="codigoPedido" name="codigoPedido" size="10" required="required">
                    </div>
                    <input class="button" name="enviar" value="Gerar" type="submit">
                </form>
            </div>
        </div>
</div>
	</div>
	 <script type="text/javascript">
		$(document).ready(function(){
			$("#dataInicial").mask("99/99/9999");
			$("#dataFinal").mask("99/99/9999");
			$("#codigoPedido").numeric();
		});
</script> 
</body>
</html>
