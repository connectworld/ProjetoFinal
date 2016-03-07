package br.com.connectWorld.projeto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.connectWorld.projeto.dao.ServicoDao;
import br.com.connectWorld.projeto.model.Servico;

@Controller
public class PedidoWebControler {
	
	@RequestMapping("/chamaPedidoWeb")
	public String chamaPedido(Model model) {
		ServicoDao dao = new ServicoDao();
		List <Servico> listaServico = dao.listar();
		model.addAttribute("listaServico",listaServico);
		return "principal/pedidoServicoWeb";
	}
	
	@RequestMapping("/salvarPedidoWeb")
	public String salvarPedidoWeb (Model model ){
		
		return "principal/pedidoServicoWeb";
	}
}