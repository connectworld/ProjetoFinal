package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.connectWorld.projeto.dao.PedidoDao;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.RelatorioPedido;

@Controller
public class RelatorioController {
	
	@RequestMapping("/exibirRelatorioServico")
	public String exibirRelatorioServico() throws SQLException {
		return "relatorio/buscarPedidoServico";
	}
	@RequestMapping("/listarPedidoServico")
	public String listarPedidoServico(Model model, RelatorioPedido relatorio) throws SQLException {
		PedidoDao dao = new PedidoDao();
		//Pedido pedido = new Pedido();
		//pedido.setTipo(0);
		//pedido.setTipo(0);
				List<Pedido> listaPedidoServico = null;
		if (relatorio.getSituacao().equals("A") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null) {
			System.out.println(relatorio.getDataFinal()+""+relatorio.getDataInicial());
			listaPedidoServico = dao.buscarPorSituacaoA(relatorio);
			Date date = new Date();
			model.addAttribute("data", date);
			model.addAttribute("listaPedidoServico", listaPedidoServico);
			dao.fecharBanco();
			return "relatorio/listarRelatorioServico";
		}
		else if (relatorio.getSituacao().equals("B") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null ) {
			listaPedidoServico = dao.buscarPorSituacaoB(relatorio);
			Date date = new Date();
			model.addAttribute("data", date);
			model.addAttribute("listaPedidoServico", listaPedidoServico);
			dao.fecharBanco();
			return "relatorio/listarRelatorioServico";
		}
		else if (relatorio.getSituacao().equals("A") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null ){
			listaPedidoServico = dao.buscarPorSituacaoC(relatorio);
			Date date = new Date();
			model.addAttribute("data", date);
			model.addAttribute("listaPedidoServico", listaPedidoServico);
			dao.fecharBanco();
			return "relatorio/listarRelatorioServico";
		}
		else  {
			listaPedidoServico = dao.buscarPorData(relatorio);
			Date date = new Date();
			model.addAttribute("data", date);
			model.addAttribute("listaPedidoServico", listaPedidoServico);
			dao.fecharBanco();
			return "relatorio/listarRelatorioServico";
		}
	}
}
