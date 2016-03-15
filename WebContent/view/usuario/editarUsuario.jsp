<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Usuario</title>
<link href="view/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	media="screen" />
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
					<form method="post" action="atualizarUsuario"
						enctype="multipart/form-data">
						<input type="hidden" name="cod" value="${usuario.cod}">
						<input type="hidden" name="senha" value="${usuario.senha}">
						 <input type="hidden" name="usuario" value="${usuarioLogado.cod}">
						<h3 class="labelsEdit">Editando Usuário</h3>
						<div class="form-group">
							<label for="nome" class="labelsEdit">Nome:</label>
							<div class="input-group">
								<input type="text" class="form-control" name="nome"
									value="${usuario.nome}" id="validate-text" placeholder="Nome"
									required> <span class="input-group-addon danger"><span
									class="glyphicon glyphicon-remove"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="login" class="labelsEdit">Login:</label>
							<div class="input-group">
								<input type="text" class="form-control" name="login"
									value="${usuario.login}" id="validate-text" placeholder="Login"
									required> <span class="input-group-addon danger"><span
									class="glyphicon glyphicon-remove"></span></span>
							</div>
						</div>

						<div class="form-group">
							<label for="Nivel" class="labelsEdit">Nivel:</label>
							<div class="input-group">
								<select class="form-control" name="nivelUsuario"
									id="validate-select" required>
									<option value="">Selecione</option>
									<c:forEach items="${listaNivelUsuario}" var="nivel">
										<option value="${nivel.cod}"
											<c:if test="${nivel.cod eq usuario.nivelUsuario.cod}">selected="selected"</c:if>>
											${nivel.nome}</option>
									</c:forEach>
								</select> <span class="input-group-addon danger"><span
									class="glyphicon glyphicon-remove"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="Email" class="labelsEdit">Email</label>
							<div class="input-group" data-validate="email">
								<input type="text" class="form-control" name="email" value="${usuario.email}"
									id="validate-email" placeholder="Email" required> <span
									class="input-group-addon danger"><span
									class="glyphicon glyphicon-remove"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="contato" class="labelsEdit">Contato:</label>
							<div class="input-group">
								<input type="text" class="form-control" name="telefone" value="${usuario.telefone}"
									id="telefone" placeholder="Telefone" required> <span
									class="input-group-addon danger"><span
									class="glyphicon glyphicon-remove"></span></span>
							</div>
						</div>
						<div class="form-group">
							<label for="imagem" class="labelsEdit">Imagem:</label>
							<div class="input-group">
							<input type="hidden" class="form-control" name="foto" id="validate-text" value="${usuario.foto}" placeholder="Foto" required>
								<img alt="imagem" src="/view/img/${usuario.foto}">
							</div>
						</div>
						<button class="btn btn-lg btn-success btn-block" type="submit">Alterar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#cpf").mask("999.999.999-99");
			$("#telefone").mask("(999)99999-9999");
		});
	</script>
</body>
</html>