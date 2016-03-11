-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Tempo de geração: 11/03/2016 às 21:29
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `cliente`
--

INSERT INTO `cliente` (`cod_cliente`, `nome`, `cpf`, `email`, `contato1`, `contato2`, `cep`, `rua`, `bairro`, `uf`, `cidade`, `numero`, `ibge`, `exclusao_logica`) VALUES
(3, 'LEANDRO BRITO CORREIA DA SILVA', '119.174.064-10', 'leandro@gmail.com', '(88)88888-8888', '(88)88888-8888', '54320-003', '3ª Travessa Gonçalves Dias', 'Jardim Jordão', 'PE', 'Jaboatão dos Guararapes', '280', 2607901, 0);

-- --------------------------------------------------------

--
-- Estrutura para tabela `itens_produto`
--

CREATE TABLE IF NOT EXISTS `itens_produto` (
  `cod_recno` int(11) NOT NULL,
  `cod_pedidoProduto` int(11) NOT NULL,
  `cod_itemProduto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_unitario` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `itens_servico`
--

INSERT INTO `itens_servico` (`cod_recno`, `cod_pedidoServico`, `item_servico`, `valor_unitario`) VALUES
(3, 3, 1, 5.24),
(4, 3, 2, 300),
(5, 4, 1, 5.24),
(6, 4, 2, 300);

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
  `descricao` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `nivel_user`
--

INSERT INTO `nivel_user` (`cod_nivel`, `nome`, `descricao`) VALUES
(1, 'admin', 'permissão para todas as telas'),
(2, 'Ténico Administrativo', 'Tem permissão apenas para algumas telas');

-- --------------------------------------------------------

--
-- Estrutura para tabela `pedido`
--

CREATE TABLE IF NOT EXISTS `pedido` (
  `cod_pedido` int(11) NOT NULL,
  `cliente` int(11) NOT NULL,
  `situacao` varchar(1) NOT NULL,
  `data_pedido` date DEFAULT NULL,
  `valor_total` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `pedido`
--

INSERT INTO `pedido` (`cod_pedido`, `cliente`, `situacao`, `data_pedido`, `valor_total`) VALUES
(3, 3, 'A', '2016-03-10', 305.24),
(4, 3, 'A', '2016-03-10', 305.24),
(5, 3, 'A', '2016-03-11', 0);

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
  `user_cadastrante` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `user_cadastrante` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `servicos`
--

INSERT INTO `servicos` (`cod_servico`, `descricao`, `nome`, `garantia`, `preco`, `user_cadastrante`) VALUES
(1, 'manuntençã de Arcondiciona', 'Manuntenção', '2015-10-10', 5.24, NULL),
(2, 'manuntençã de Arcondiciona', 'Manuntenção', '2017-01-12', 300, 2);

-- --------------------------------------------------------

--
-- Estrutura para tabela `telas`
--

CREATE TABLE IF NOT EXISTS `telas` (
  `cod_tela` int(11) NOT NULL,
  `url` varchar(50) NOT NULL,
  `descricao` varchar(50) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `telas`
--

INSERT INTO `telas` (`cod_tela`, `url`, `descricao`) VALUES
(1, 'chamaCadastrarProduto', 'Tela de Cadastro de Produto'),
(2, 'ListarProduto', 'Listar Produto'),
(3, 'chamaServico', 'tela de Servico'),
(4, 'cadastrarNivelUsuario', 'Cadastro de nivel de Usuario'),
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
(20, 'listarPedidoServico', 'Pedidos de Servico'),
(21, 'listarPedidoProduto', 'Pedidos de Produto');

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
  `nivel_usuario` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Fazendo dump de dados para tabela `usuarios`
--

INSERT INTO `usuarios` (`cod_usuario`, `nome`, `login`, `senha`, `telefone`, `foto`, `email`, `nivel_usuario`) VALUES
(2, 'leandro', 'leandro', '81dc9bdb52d04dc20036dbd8313ed055', '8888', '888888', '888888', 1);

-- --------------------------------------------------------

--
-- Estrutura para tabela `valida_url`
--

CREATE TABLE IF NOT EXISTS `valida_url` (
  `cod_valida` int(11) NOT NULL,
  `cod_nivelUsuario` int(11) NOT NULL,
  `cod_telaUsuario` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

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
(29, 1, 21);

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
  ADD PRIMARY KEY (`cod_nivel`);

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
  MODIFY `cod_cliente` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de tabela `itens_produto`
--
ALTER TABLE `itens_produto`
  MODIFY `cod_recno` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `itens_servico`
--
ALTER TABLE `itens_servico`
  MODIFY `cod_recno` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de tabela `nivel_user`
--
ALTER TABLE `nivel_user`
  MODIFY `cod_nivel` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `pedido`
--
ALTER TABLE `pedido`
  MODIFY `cod_pedido` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `cod_produto` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de tabela `servicos`
--
ALTER TABLE `servicos`
  MODIFY `cod_servico` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `telas`
--
ALTER TABLE `telas`
  MODIFY `cod_tela` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `cod_usuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de tabela `valida_url`
--
ALTER TABLE `valida_url`
  MODIFY `cod_valida` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
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
