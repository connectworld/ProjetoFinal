<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Cadastrar Usuario</title>
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
            <form method="post" action="salvarUsuario" enctype="multipart/form-data">
            <h3 class="labelsEdit">Cadastro de Usuário</h3>
				<div class="form-group">
        			<label for="nome" class="labelsEdit">Nome:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="nome" id="validate-text" placeholder="Nome" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="login" class="labelsEdit">Login:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="login" id="validate-text" placeholder="Login" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="senha" class="labelsEdit">Senha:</label>
					<div class="input-group">
						<input type="password" class="form-control" name="senha" id="validate-text" placeholder="Senha" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
            		<label for="Nivel" class="labelsEdit">Nivel:</label>
					<div class="input-group">
                        <select class="form-control" name="nivelUsuario" id="validate-select"  required>
                 			<option value="">Selecione</option>
                            <c:forEach items="${listaNivelUsuario}" var="nivel">
								<option value="${nivel.cod}">${nivel.nome}</option>
							</c:forEach>
                        </select>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="Email" class="labelsEdit">Email</label>
					<div class="input-group" data-validate="email">
						<input type="text" class="form-control" name="email" id="validate-email" placeholder="Email" required>
						<span class="input-group-addon danger"><span class="glyphicon glyphicon-remove"></span></span>
					</div>
				</div>
				<div class="form-group">
        			<label for="contato" class="labelsEdit">Contato:</label>
					<div class="input-group">
						<input type="text" class="form-control" name="telefone" id="telefone" placeholder="Telefone" required>
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
			$("#cpf").mask("999.999.999-99");
			$("#telefone").mask("(999)99999-9999");
		});
</script> 
</body>
</html>
