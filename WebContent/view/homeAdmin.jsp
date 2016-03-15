<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Home Admin</title>
    <link href="view/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen" />
    <script type="text/javascript" src="view/js/jquery-2.1.4.js"></script>
	<script src="view/bootstrap/js/bootstrap.min.js"></script>
	<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<c:import url="menu.jsp"></c:import>
<br><br><br>
<div class="spacing" align="center">
<div class="container">
      <div class="row">
        <div class="col-lg-12">
          <p>
            <a href="cadastrarUsuario" class="btn btn-sq-lg btn-primary">
                <i class="fa fa-user fa-5x"></i><br/>
                Usuario<br><br>Tela de Cadastro de Usuario
            </a>
            <a href="cadastrarNivelUsuario" class="btn btn-sq-lg btn-success" style="margin-left: 2%;">
              <i class="fa fa-user fa-5x"></i><br/>
              	Nivel Usuario <br> <br> Cadastrar o Nivel de Usuario
            </a>
            <a href="cadastrarServico" class="btn btn-sq-lg btn-info" style="margin-left: 2%;">
              <i class="fa fa-user fa-5x"></i><br/>
            	Servico <br><br> Tela de Cadastro de Servico
            </a>
            <a href="cadastrarProduto" class="btn btn-sq-lg btn-warning" style="margin-left: 2%;">
              <i class="fa fa-user fa-5x"></i><br/>
              Produto <br> <br> Tela de Cadastro de Produto
            </a>
            <a href="listarCliente" class="btn btn-sq-lg btn-danger" style="margin-left: 2%;">
              <i class="fa fa-user fa-5x"></i><br/>
              Clientes <br> <br>  Listagem de Clientes
            </a>
          </p>
        </div>
	</div>
	<br><br>  
	<div class="row">
        <div class="col-lg-12">
          <p>
            <a href="listarUsuario" class="btn btn-sq btn-primary">
                <i class="fa fa-user fa-5x"></i><br/>
                Usuarios<br><br> Lstar Usuarios
            </a>
            <a href="listarServico" class="btn btn-sq btn-success" style="margin-left: 2%;">
              <i class="fa fa-user fa-5x"></i><br/>
              Servico <br> <br> Listar Servicos
            </a>
            <a href="listarNivelUsuario" class="btn btn-sq btn-info" style="margin-left: 2%;">
              <i class="fa fa-user fa-5x"></i><br/>
              Niveis <br> <br> Listar Niveis
            </a>
            <a href="listarProduto" class="btn btn-sq btn-warning" style="margin-left: 2%;">
              <i class="fa fa-user fa-5x"></i><br/>
              Produtos <br> <br> Tela de Listagem de Produtos
            </a>
            <a href="listarPedidos" class="btn btn-sq btn-danger" style="margin-left: 2%;">
              <i class="fa fa-user fa-5x"></i><br/>
              Pedido Servicos <br> <br> Tela de Listagem de Pedidos
            </a>
          </p>
        </div>
	</div>
  </div>
</div>
</body>
</html>