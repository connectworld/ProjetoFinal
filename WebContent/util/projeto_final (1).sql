-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Tempo de geração: 22/03/2016 às 21:50
-- Versão do servidor: 5.6.26
-- Versão do PHP: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `projeto_final`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `cod_cliente` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `contato1` varchar(15) NOT NULL,
  `contato2` varchar(15) DEFAULT NULL,
  `cep` varchar(10) NOT NULL,
  `rua` varchar(50) NOT NULL,
  `bairro` varchar(50) NOT NULL,
  `uf` varchar(3) NOT NULL,
  `cidade` varchar(50) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `ibge` int(11) NOT NULL,
  `exclusao_logica` int(11) DEFAULT '0' COMMENT '0 = ativo, 1 = inativo'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `nome`, `cpf`, `email`, `contato1`, `contato2`, `cep`, `rua`, `bairro`, `uf`, `cidade`, `numero`, `ibge`, `exclusao_logica`) VALUES
(3, 'LEANDRO BRITO CORREIA DA SILVA', '119.174.064-10', 'leandro@gmail.com', '(88)88888-8888', '(88)88888-8888', '54320-003', '3ª Travessa Gonçalves Dias', 'Jardim Jordão', 'PE', 'Jaboatão dos Guararapes', '280', 2607901, 0),
(4, 'jo', '013.916.024-80', 'jorge@gmail.com', '(33)33333-3333', '(33)33333-3333', '51345-810', 'Rua Padre Dias', 'COHAB', 'PE', 'Recife', '20', 2611606, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `itens_produto`
--

CREATE TABLE IF NOT EXISTS `itens_produto` (
  `cod_recno` int(11) NOT NULL,
  `cod_pedidoProduto` int(11) NOT NULL,
  `cod_itemProduto` int(11) NOT NULL,
  `nome_produto` varchar(50) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_unitario` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `itens_produto`
--

INSERT INTO `itens_produto` (`cod_recno`, `cod_pedidoProduto`, `cod_itemProduto`, `nome_produto`, `quantidade`, `valor_unitario`) VALUES
(1, 38, 1, 'teste', 1, 5),
(2, 41, 2, 'teste2', 1, 5),
(3, 42, 2, 'teste2', 1, 5),
(4, 43, 2, 'teste2', 1, 5),
(5, 44, 2, 'teste2', 1, 5),
(6, 44, 2, 'teste2', 1, 5),
(7, 45, 2, 'teste2', 1, 5),
(8, 45, 2, 'teste2', 1, 5),
(9, 46, 1, 'teste2', 1, 5),
(10, 47, 1, 'teste2', 1, 5),
(11, 47, 2, 'teste2', 1, 5),
(12, 47, 1, 'teste2', 1, 5),
(13, 48, 1, 'teste2', 1, 5),
(14, 49, 1, 'teste2', 1, 5),
(15, 50, 1, 'teste2', 1, 5),
(16, 51, 1, 'teste2', 1, 5),
(17, 51, 2, 'teste2', 1, 5),
(18, 51, 2, 'teste2', 1, 5),
(19, 52, 2, 'teste2', 1, 5),
(20, 52, 2, 'teste2', 1, 5),
(21, 52, 2, 'teste2', 1, 5),
(22, 52, 2, 'teste2', 1, 5),
(23, 53, 2, 'teste2', 1, 5),
(24, 53, 2, 'teste2', 1, 5),
(25, 53, 2, 'teste2', 1, 5),
(26, 53, 2, 'teste2', 1, 5),
(27, 54, 2, 'teste2', 1, 5),
(28, 54, 2, 'teste2', 1, 5),
(29, 54, 2, 'teste2', 1, 5),
(30, 54, 2, 'teste2', 1, 5),
(31, 55, 2, 'teste2', 1, 5),
(32, 56, 2, 'teste2', 1, 5),
(33, 57, 2, 'teste2', 1, 5),
(34, 58, 2, 'teste2', 1, 5),
(35, 59, 2, 'teste2', 1, 5),
(36, 60, 1, 'teste2', 1, 5),
(37, 60, 2, 'teste2', 1, 5),
(38, 61, 1, 'teste2', 1, 5),
(39, 61, 2, 'teste2', 1, 5),
(40, 62, 1, 'teste2', 1, 5),
(41, 62, 2, 'teste2', 1, 5),
(42, 63, 1, 'teste2', 1, 5),
(43, 63, 2, 'teste2', 1, 5),
(44, 66, 2, 'teste2', 1, 5);

--
-- Gatilhos `itens_produto`
--
DELIMITER $$
CREATE TRIGGER `atualiza_valorPedPro_Sub` AFTER DELETE ON `itens_produto`
 FOR EACH ROW BEGIN
	update pedido p set p.valor_total = p.valor_total - old.valor_unitario
	where p.cod_pedido = old.cod_pedidoProduto;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `atualiza_valorPedProdu_Add` AFTER INSERT ON `itens_produto`
 FOR EACH ROW BEGIN
	update pedido p set p.valor_total = p.valor_total + new.valor_unitario
	where p.cod_pedido = new.cod_pedidoProduto;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `itens_servico`
--

CREATE TABLE IF NOT EXISTS `itens_servico` (
  `cod_recno` int(11) NOT NULL,
  `cod_pedidoServico` int(11) NOT NULL,
  `item_servico` int(11) NOT NULL,
  `valor_unitario` double NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `itens_servico`
--

INSERT INTO `itens_servico` (`cod_recno`, `cod_pedidoServico`, `item_servico`, `valor_unitario`) VALUES
(3, 3, 1, 5.24),
(4, 3, 2, 300),
(5, 4, 1, 5.24),
(6, 4, 2, 300),
(7, 6, 1, 5.24),
(8, 7, 1, 5.24),
(9, 8, 1, 5.24),
(10, 9, 3, 50),
(11, 10, 2, 300),
(12, 11, 3, 50),
(13, 12, 1, 5.24),
(14, 13, 1, 5.24),
(15, 14, 1, 5.24),
(16, 15, 1, 5.24),
(17, 16, 1, 5.24),
(18, 17, 1, 5.24),
(19, 18, 1, 5.24),
(20, 19, 1, 5.24),
(21, 20, 1, 5.24),
(22, 21, 1, 5.24),
(23, 22, 1, 5.24),
(24, 23, 1, 5.24),
(25, 24, 1, 5.24),
(26, 25, 1, 5.24),
(27, 26, 1, 5.24),
(28, 27, 1, 5.24),
(29, 28, 1, 5.24),
(30, 29, 1, 5.24),
(31, 30, 1, 5.24),
(32, 31, 1, 5.24),
(33, 32, 1, 5.24),
(34, 33, 1, 5.24),
(35, 34, 1, 5.24),
(36, 35, 1, 5.24),
(37, 36, 1, 5.24),
(38, 37, 1, 5.24),
(39, 39, 1, 5.24),
(40, 40, 3, 50),
(41, 40, 3, 50),
(42, 64, 3, 50),
(43, 65, 3, 50),
(44, 67, 4, 0.01),
(45, 68, 4, 0.01),
(46, 69, 4, 0.01);

--
-- Gatilhos `itens_servico`
--
DELIMITER $$
CREATE TRIGGER `atualiza_valorPedSer_Add` AFTER INSERT ON `itens_servico`
 FOR EACH ROW BEGIN
	update pedido p set p.valor_total = p.valor_total + new.valor_unitario
	where p.cod_pedido = new.cod_pedidoServico;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `atualiza_valorPedSer_Sub` AFTER DELETE ON `itens_servico`
 FOR EACH ROW BEGIN
	update pedido p set p.valor_total = p.valor_total - old.valor_unitario
	where p.cod_pedido = old.cod_pedidoServico;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estrutura para tabela `nivel_user`
--

CREATE TABLE IF NOT EXISTS `nivel_user` (
  `cod_nivel` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `user_cadastrante` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `nivel_user`
--

INSERT INTO `nivel_user` (`cod_nivel`, `nome`, `descricao`, `user_cadastrante`) VALUES
(1, 'admin', 'permissão para todas as telas', NULL),
(2, 'Ténico Administrativo', 'Tem permissão apenas para algumas telas', NULL),
(3, 'test', 'teste', 3);

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `cod_pedido` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `situacao` varchar(1) NOT NULL,
  `data_pedido` date DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `flag_tipo` int(11) DEFAULT NULL,
  `user_autor` int(11) unsigned DEFAULT NULL,
  `exclusao_logica` int(11) DEFAULT NULL COMMENT '1 - ATIVO, 2 - INATIVO'
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `pedido`
--

INSERT INTO `pedido` (`cod_pedido`, `cliente`, `situacao`, `data_pedido`, `valor_total`, `flag_tipo`, `user_autor`, `exclusao_logica`) VALUES
(3, 3, 'A', '2016-03-10', 305.24, 0, NULL, 1),
(4, 3, 'A', '2016-03-10', 305.24, 0, NULL, 1),
(5, 3, 'A', '2016-03-11', 0, 0, NULL, 1),
(6, 3, 'A', '2016-03-16', 5.24, 0, NULL, 1),
(7, 3, 'A', '2016-03-16', 5.24, 0, NULL, 1),
(8, 3, 'A', '2016-03-16', 5.24, 0, NULL, 1),
(9, 3, 'A', '2016-03-16', 50, 0, NULL, 1),
(10, 3, 'A', '2016-03-16', 300, 0, NULL, 1),
(11, 3, 'A', '2016-03-16', 50, 0, NULL, 1),
(12, 3, 'A', '2016-03-17', 5.24, 0, NULL, 1),
(13, 3, 'A', '2016-03-17', 5.24, 0, NULL, 1),
(14, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(15, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(16, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(17, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(18, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(19, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(20, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(21, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(22, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(23, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(24, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(25, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(26, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(27, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(28, 3, 'A', '2016-03-17', 5.24, NULL, NULL, 1),
(29, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(30, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(31, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(32, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(33, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(34, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(35, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(36, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(37, 3, 'A', '2016-03-17', 5.24, NULL, NULL, NULL),
(38, 4, 'A', '2016-03-18', 5, NULL, NULL, NULL),
(39, 4, 'A', '2016-03-19', 5.24, NULL, NULL, NULL),
(40, 4, 'A', '2016-03-19', 100, NULL, NULL, NULL),
(41, 4, 'A', '2016-03-19', 5, NULL, NULL, NULL),
(42, 4, 'A', '2016-03-19', 5, NULL, NULL, NULL),
(43, 4, 'A', '2016-03-19', 5, NULL, NULL, NULL),
(44, 4, 'A', '2016-03-19', 10, NULL, NULL, NULL),
(45, 4, 'A', '2016-03-19', 10, NULL, NULL, NULL),
(46, 4, 'A', '2016-03-19', 5, NULL, NULL, NULL),
(47, 3, 'A', '2016-03-19', 15, NULL, NULL, NULL),
(48, 4, 'A', '2016-03-20', 5, 1, NULL, 1),
(49, 4, 'A', '2016-03-20', 5, 1, NULL, 1),
(50, 4, 'A', '2016-03-20', 5, 1, NULL, 1),
(51, 4, 'A', '2016-03-20', 15, 1, NULL, 1),
(52, 4, 'A', '2016-03-20', 20, 1, NULL, 1),
(53, 4, 'A', '2016-03-20', 20, 1, NULL, 1),
(54, 4, 'A', '2016-03-20', 20, 1, NULL, 1),
(55, 4, 'A', '2016-03-20', 5, 1, NULL, 1),
(56, 4, 'A', '2016-03-20', 5, 1, NULL, 1),
(57, 4, 'A', '2016-03-20', 5, 1, NULL, 1),
(58, 4, 'A', '2016-03-20', 5, 1, NULL, 1),
(59, 4, 'A', '2016-03-20', 5, 1, NULL, 1),
(60, 3, 'A', '2016-03-20', 10, 1, NULL, 1),
(61, 3, 'A', '2016-03-20', 10, 1, NULL, 1),
(62, 3, 'A', '2016-03-20', 10, 1, NULL, 1),
(63, 3, 'A', '2016-03-20', 10, 1, NULL, 1),
(64, 3, 'A', '2016-03-21', 50, 0, 0, 1),
(65, 3, 'A', '2016-03-21', 50, 0, 5, 1),
(66, 3, 'A', '2016-03-21', 5, 1, 5, 1),
(67, 3, 'A', '2016-03-21', 0.01, 0, 3, 1),
(68, 3, 'A', '2016-03-21', 0.01, 0, 3, 1),
(69, 3, 'A', '2016-03-21', 0.01, 0, 3, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `produtos`
--

CREATE TABLE IF NOT EXISTS `produtos` (
  `cod_produto` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `preco_venda` double NOT NULL,
  `quantidade` int(11) NOT NULL,
  `imagem` varchar(100) NOT NULL,
  `user_cadastrante` int(11) DEFAULT NULL,
  `exclusao_logica` int(11) DEFAULT NULL COMMENT '1 - ATIVO, 0 - INATIVO'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `produtos`
--

INSERT INTO `produtos` (`cod_produto`, `nome`, `descricao`, `preco_venda`, `quantidade`, `imagem`, `user_cadastrante`, `exclusao_logica`) VALUES
(1, 'teste2', 'teste', 5, 5, '1.png', 3, 1),
(2, 'teste2', 'teste2', 5, 10, '2.png', 3, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `servicos`
--

CREATE TABLE IF NOT EXISTS `servicos` (
  `cod_servico` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `garantia` date NOT NULL,
  `preco` double DEFAULT NULL,
  `user_cadastrante` int(11) DEFAULT NULL,
  `exclusao_logica` int(11) DEFAULT NULL COMMENT '1- ativo, 0 - inativo'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `servicos`
--

INSERT INTO `servicos` (`cod_servico`, `descricao`, `nome`, `garantia`, `preco`, `user_cadastrante`, `exclusao_logica`) VALUES
(1, 'manuntençã de Arcondiciona', 'Manuntenção', '2015-10-10', 5.24, NULL, 1),
(2, 'manuntençã de Arcondiciona', 'Manuntenção', '2017-01-12', 300, 2, 1),
(3, 'teste2', '2teste', '2016-06-06', 50, 2, 1),
(4, 'teste1', 'teste', '2015-05-05', 0.01, 3, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `telas`
--

CREATE TABLE IF NOT EXISTS `telas` (
  `cod_tela` int(11) NOT NULL,
  `url` varchar(50) NOT NULL,
  `descricao` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `telas`
--

INSERT INTO `telas` (`cod_tela`, `url`, `descricao`) VALUES
(1, 'cadastrarProduto', 'Tela de Cadastro de Produto'),
(2, 'listarProduto', 'Listar Produto'),
(3, 'cadastrarServico', 'Tela de Servico'),
(4, 'cadastrarNivelUsuario', 'Tela nivel de Usuario'),
(5, 'listarNivelUsuario', 'Listar Nivel de Usuário'),
(6, 'editarNivelUsuario', 'Editar nivel de Usuário'),
(7, 'deletarNivelUsuario', 'Deletar Nivel Usuário'),
(8, 'editarProduto', 'Editar Produto'),
(9, 'deletarProduto', 'Deletar Produto'),
(10, 'listarServico', 'Listar Servico'),
(11, 'editarServico', 'Editar Servico'),
(12, 'deletarServico', 'Deletar Servico'),
(13, 'cadastrarUsuario', 'Cadastrar Usuario'),
(14, 'listarUsuario', 'Listar Usuario'),
(15, 'editarUsuario', 'Editar Usuario'),
(16, 'deletarUsuario', 'Deletar Usuario'),
(17, 'listarCliente', 'Listar Cliente'),
(18, 'verDadosCliente', 'Ver Dados do Cliente'),
(19, 'deletarCliente', 'Deletar Cliente'),
(20, 'listarPedidoServico', 'Listar Pedido de Servico'),
(21, 'listarPedidoProduto', 'Listar Pedido de Produto'),
(22, 'atualizarNivelUsuario', 'Atualizar Nivel de Usuario'),
(23, 'atualizarUsuario', 'Atualizar Usuario'),
(24, 'atualizarProduto', 'Atualizar Produto'),
(25, 'atualizarServico', 'Atualizar Servico'),
(26, 'salvarProduto', 'Salvar Produto'),
(27, 'salvarServico', 'Salvar Servico'),
(28, 'salvarNivelUsuario', 'Salvar Nivel Usuario'),
(29, 'salvarUsuario', 'Salvar Usuario'),
(30, 'buscarCliente', 'Buscar Cliente Pedido'),
(31, 'clienteSelecionado', 'Selecionar Cliente Pedido'),
(32, 'pedidoServicoAdmin', 'Pedido de Servico'),
(33, 'pesquisarServico', 'pesquisar Servico'),
(34, 'retornapedidoServicoAdmin', 'retorn apedido Servico Admin'),
(35, 'addServicoPedido', 'Adicionar Servico ao Pedido'),
(36, 'buscarCpfAdmin', 'Buscar Cliente por Cpf'),
(37, 'removerServicoPedido', 'Remover Item Servico Pedido'),
(38, 'pedidoServicoEtapa2', 'Pedido Servico 2º Fase'),
(39, 'salvarPedidoServicoAdmin', 'Salvar Pedido Servico'),
(40, 'pedidoProdutoAdmin', 'realiza pedido do produto sendo admin'),
(41, 'pedidoProdutoAdd', 'realizar pedido sendo admin'),
(42, 'removerProdutoPedidoAdmin', 'remover Produto do Pedido Admin'),
(43, 'pedidoProdutoEtapa2Admin', 'pedido Produto Etapa2 Admin'),
(44, 'salvarPedidoProdutoAdmin', 'salvar Pedido Produto Admin'),
(45, 'retornarPedidoAdmin', 'retornar Pedido Admin'),
(46, 'buscarCpfAdminProduto', 'buscar Cpf Admin Produto'),
(47, 'buscarClienteAdmin', 'buscar Cliente Admin'),
(48, 'clienteSelecionadoAdmin', 'cliente Selecionado Admin'),
(49, 'exibirRelatorioServico', 'exibir relatorio servico'),
(50, 'selecionarTela', 'Selecionar Tela de Permissão'),
(51, 'removerTela', 'Remover Tela'),
(52, 'cadastrarNivelEtapa2', 'Cadastrar Nivel 2 etapa'),
(53, 'voltar', 'Alterar Pedido Servico'),
(54, 'listarPedidoServico', 'Gerar Relatorios Servico');

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `cod_usuario` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `telefone` varchar(20) DEFAULT NULL,
  `foto` varchar(100) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `nivel_usuario` int(11) NOT NULL,
  `user_cadastrante` int(11) DEFAULT NULL,
  `exclusao_logica` int(11) DEFAULT NULL COMMENT '0- INATIVO, 1 - ATIVO'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `usuarios`
--

INSERT INTO `usuarios` (`cod_usuario`, `nome`, `login`, `senha`, `telefone`, `foto`, `email`, `nivel_usuario`, `user_cadastrante`, `exclusao_logica`) VALUES
(3, 'Jorge Batista202', 'jorge', '81dc9bdb52d04dc20036dbd8313ed055', '(081)98645-2028', 'Mon Mar 14 21:10:47 BRT 2016 - CEFTP.jpeg', 'jorgebatista_7@hotmail.com', 1, 2, 1),
(4, 'leandro', 'leandroBrito', '81dc9bdb52d04dc20036dbd8313ed055', '(081)98617-4918', '888888', 'leandro@gmail.com', 1, 3, 1),
(5, 'cliente', 'padrao', 'padrao', NULL, NULL, 'padrao', 2, 1, 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `valida_url`
--

CREATE TABLE IF NOT EXISTS `valida_url` (
  `cod_valida` int(11) NOT NULL,
  `cod_nivelUsuario` int(11) NOT NULL,
  `cod_telaUsuario` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `valida_url`
--

INSERT INTO `valida_url` (`cod_valida`, `cod_nivelUsuario`, `cod_telaUsuario`) VALUES
(1, 1, 4),
(2, 1, 13),
(3, 1, 1),
(4, 1, 3),
(5, 1, 7),
(6, 1, 9),
(7, 1, 12),
(8, 1, 16),
(10, 1, 6),
(12, 1, 8),
(14, 1, 11),
(16, 1, 15),
(18, 1, 5),
(20, 1, 2),
(22, 1, 10),
(24, 1, 14),
(25, 1, 17),
(26, 1, 19),
(27, 1, 18),
(28, 1, 20),
(29, 1, 21),
(30, 1, 23),
(31, 1, 22),
(32, 1, 24),
(33, 1, 25),
(34, 1, 26),
(35, 1, 26),
(36, 1, 27),
(37, 1, 28),
(38, 1, 29),
(39, 1, 30),
(40, 1, 31),
(41, 1, 32),
(42, 1, 33),
(43, 1, 34),
(44, 1, 35),
(45, 1, 36),
(46, 1, 37),
(47, 1, 38),
(49, 1, 39),
(50, 1, 40),
(51, 1, 41),
(52, 1, 42),
(53, 1, 43),
(54, 1, 44),
(55, 1, 45),
(56, 1, 46),
(57, 1, 47),
(58, 1, 48),
(59, 1, 49),
(60, 1, 50),
(61, 1, 51),
(62, 1, 52),
(63, 3, 1),
(64, 1, 53),
(65, 1, 54);

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cod_cliente`),
  ADD UNIQUE KEY `cpf` (`cpf`);

--
-- Índices de tabela `itens_produto`
--
ALTER TABLE `itens_produto`
  ADD PRIMARY KEY (`cod_recno`),
  ADD KEY `cod_pedidoProduto` (`cod_pedidoProduto`),
  ADD KEY `cod_itemProduto` (`cod_itemProduto`);

--
-- Índices de tabela `itens_servico`
--
ALTER TABLE `itens_servico`
  ADD PRIMARY KEY (`cod_recno`),
  ADD KEY `cod_pedidoServico` (`cod_pedidoServico`),
  ADD KEY `item_servico` (`item_servico`);

--
-- Índices de tabela `nivel_user`
--
ALTER TABLE `nivel_user`
  ADD PRIMARY KEY (`cod_nivel`),
  ADD KEY `user_cadastrante` (`user_cadastrante`);

--
-- Índices de tabela `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`cod_pedido`),
  ADD KEY `cliente` (`cliente`);

--
-- Índices de tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`cod_produto`);

--
-- Índices de tabela `servicos`
--
ALTER TABLE `servicos`
  ADD PRIMARY KEY (`cod_servico`);

--
-- Índices de tabela `telas`
--
ALTER TABLE `telas`
  ADD PRIMARY KEY (`cod_tela`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`cod_usuario`),
  ADD KEY `nivel_usuario` (`nivel_usuario`);

--
-- Índices de tabela `valida_url`
--
ALTER TABLE `valida_url`
  ADD PRIMARY KEY (`cod_valida`),
  ADD KEY `cod_nivelUsuario` (`cod_nivelUsuario`),
  ADD KEY `cod_telaUsuario` (`cod_telaUsuario`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `cliente`
--
ALTER TABLE `cliente`
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de tabela `itens_produto`
--
ALTER TABLE `itens_produto`
  MODIFY `cod_recno` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=45;
--
-- AUTO_INCREMENT de tabela `itens_servico`
--
ALTER TABLE `itens_servico`
  MODIFY `cod_recno` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=47;
--
-- AUTO_INCREMENT de tabela `nivel_user`
--
ALTER TABLE `nivel_user`
  MODIFY `cod_nivel` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `pedido`
--
ALTER TABLE `pedido`
  MODIFY `cod_pedido` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=70;
--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `cod_produto` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `servicos`
--
ALTER TABLE `servicos`
  MODIFY `cod_servico` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de tabela `telas`
--
ALTER TABLE `telas`
  MODIFY `cod_tela` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=55;
--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de tabela `valida_url`
--
ALTER TABLE `valida_url`
  MODIFY `cod_valida` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=66;
--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `itens_produto`
--
ALTER TABLE `itens_produto`
  ADD CONSTRAINT `itens_produto_ibfk_1` FOREIGN KEY (`cod_pedidoProduto`) REFERENCES `pedido` (`cod_pedido`),
  ADD CONSTRAINT `itens_produto_ibfk_2` FOREIGN KEY (`cod_itemProduto`) REFERENCES `produtos` (`cod_produto`);

--
-- Restrições para tabelas `itens_servico`
--
ALTER TABLE `itens_servico`
  ADD CONSTRAINT `itens_servico_ibfk_1` FOREIGN KEY (`cod_pedidoServico`) REFERENCES `pedido` (`cod_pedido`),
  ADD CONSTRAINT `itens_servico_ibfk_2` FOREIGN KEY (`item_servico`) REFERENCES `servicos` (`cod_servico`);

--
-- Restrições para tabelas `nivel_user`
--
ALTER TABLE `nivel_user`
  ADD CONSTRAINT `nivel_user_ibfk_1` FOREIGN KEY (`user_cadastrante`) REFERENCES `usuarios` (`cod_usuario`);

--
-- Restrições para tabelas `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`cod_cliente`);

--
-- Restrições para tabelas `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`nivel_usuario`) REFERENCES `nivel_user` (`cod_nivel`);

--
-- Restrições para tabelas `valida_url`
--
ALTER TABLE `valida_url`
  ADD CONSTRAINT `valida_url_ibfk_1` FOREIGN KEY (`cod_nivelUsuario`) REFERENCES `nivel_user` (`cod_nivel`),
  ADD CONSTRAINT `valida_url_ibfk_2` FOREIGN KEY (`cod_telaUsuario`) REFERENCES `telas` (`cod_tela`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
