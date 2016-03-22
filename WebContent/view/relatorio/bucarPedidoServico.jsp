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
    <div class="col-md-4">
		<div class="form_main">
                <h4 class="heading"><strong>Listar Pedidos </strong> </h4>
                <div class="form">
                <form action="contact_send_mail.php" method="post" id="contactFrm" name="contactFrm">
                	<label for="Data Inicial"> Data Inicial:</label>
                    <input type="text" placeholder="Data Inicial" name="dataInicial" class="txt" required="required">
                    <label for="Data Final"> Data Final:</label>
                    <input type="text" placeholder="Data Final" name="dataFinal" class="txt" required="required">
                    <label for="Codigo Pedido"> Codigo Pedido:</label>
                    <input type="text" placeholder="Numero do Pedido" name="codigoPedido" class="txt">
                    <label for="situacao"> Situacao:</label>
                    <select class="txt">
                    	<option value="A">A - Pedidos Aberto</option>
                    	<option value="A">B - Pedidos Baixado</option>
                    	<option value="A">C - Pedidos Cancelados</option>
                    </select>
                     <input type="submit" value="submit" name="submit" class="txt2">
                </form>
            </div>
            </div>
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
