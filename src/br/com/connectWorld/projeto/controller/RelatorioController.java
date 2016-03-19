package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.connectWorld.projeto.dao.PedidoDao;
import br.com.connectWorld.projeto.dao.ProdutoDao;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.Produto;
import br.com.connectWorld.projeto.model.RelatorioPedido;

@Controller
public class RelatorioController {
	
	@RequestMapping("/exibirRelatorioServico")
	public String exibirRelatorioServico() throws SQLException {
		return "relatorio/buscarPedidoServico";
	}
	@RequestMapping("/listarPedidoServico")
	public String listarPedidoServico(Model model, RelatorioPedido relatorio) {
		PedidoDao dao = new PedidoDao();
		Pedido pedido = new Pedido();
		pedido.setSituacao(relatorio.getSituacao()); 
				List<Pedido> listaPedidoServico = null;
		if (relatorio.getSituacao().equals("A")) {
			listaPedidoServico = dao.buscarPorSituacaoA(pedido);
		}
		List<Pedido> listaPedidoServico = dao.listar();
		model.addAttribute("listaPedidoServico",listaPedidoServico);
		return "relatorio/listarRelatorioServico";
	}
}
