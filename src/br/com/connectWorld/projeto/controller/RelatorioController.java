package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String listarPedidoServico(Model model, RelatorioPedido relatorio) throws SQLException, ParseException {
		PedidoDao dao = new PedidoDao();
		relatorio.setDataInicial2(new SimpleDateFormat("dd/MM/yyyy").parse(relatorio.getDataInicial()));
		relatorio.setDataFinal2(new SimpleDateFormat("dd/MM/yyyy").parse(relatorio.getDataFinal()));
				List<Pedido> listaPedidoServico = null;
		if (relatorio.getSituacao().equals("A") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null) {
			//System.out.println(relatorio.getDataFinal()+""+relatorio.getDataInicial());
			listaPedidoServico = dao.buscarPorSituacaoA(relatorio);
			if (listaPedidoServico.size() != 0) {
				Date date = new Date();
				model.addAttribute("data", date);
				model.addAttribute("listaPedidoServico", listaPedidoServico);
				dao.fecharBanco();
				return "relatorio/listarRelatorioServico";
			}
			else{
				model.addAttribute("mensagem", "NÃO HÁ REGISTROS");
				return "relatorio/buscarPedidoServico";
			}
		}
		else if (relatorio.getSituacao().equals("B") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null ) {
			listaPedidoServico = dao.buscarPorSituacaoB(relatorio);
			
			if (listaPedidoServico.size() != 0) {
				Date date = new Date();
				model.addAttribute("data", date);
				model.addAttribute("listaPedidoServico", listaPedidoServico);
				dao.fecharBanco();
				return "relatorio/listarRelatorioServico";
			}
			else{
				model.addAttribute("mensagem", "NÃO HÁ REGISTROS");
				return "relatorio/buscarPedidoServico";
			}
		}
		else if (relatorio.getSituacao().equals("C") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null ){
			listaPedidoServico = dao.buscarPorSituacaoC(relatorio);
			
			if (listaPedidoServico.size() != 0) {
				Date date = new Date();
				model.addAttribute("data", date);
				model.addAttribute("listaPedidoServico", listaPedidoServico);
				dao.fecharBanco();
				return "relatorio/listarRelatorioServico";
			}
			else{
				model.addAttribute("mensagem", "NÃO HÁ REGISTROS");
				return "relatorio/buscarPedidoServico";
			}
		}
		else  {
			listaPedidoServico = dao.buscarPorData(relatorio);
			if (listaPedidoServico.size() != 0) {
				Date date = new Date();
				model.addAttribute("data", date);
				model.addAttribute("listaPedidoServico", listaPedidoServico);
				dao.fecharBanco();
				return "relatorio/listarRelatorioServico";
			}
			else{
				model.addAttribute("mensagem", "NÃO HÁ REGISTROS");
				return "relatorio/buscarPedidoServico";
			}
		}
	}
	@RequestMapping("/buscarPedidoPorCod")
	public String buscarPedidoPorCod(Pedido pedido, Model model) throws SQLException {
		PedidoDao dao = new PedidoDao();
		Pedido pedidoConsultado = dao.buscarPorcod(pedido);
		if (pedidoConsultado != null) {
			List<Pedido> listaPedidoServico = new ArrayList<Pedido>();
			listaPedidoServico.add(pedidoConsultado);
			model.addAttribute("listaPedidoServico", listaPedidoServico);
			dao.fecharBanco();
			return "relatorio/listarRelatorioServico";
		}
		else {
			model.addAttribute("mensagem", "NÃO HÁ NENHUM PEDIDO COM ESSE CODIGO");
			return "relatorio/buscarPedidoServico";
		}
	}
	@RequestMapping("/exibirRelatorioProduto")
	public String exibirRelatorioProduto() throws SQLException {
		return "relatorio/buscarPedidoProduto";
	}
	@RequestMapping("/listarPedidoProduto")
	public String listarPedidoProduto(Model model, RelatorioPedido relatorio) throws SQLException, ParseException {
		PedidoDao dao = new PedidoDao();
		relatorio.setDataInicial2(new SimpleDateFormat("dd/MM/yyyy").parse(relatorio.getDataInicial()));
		relatorio.setDataFinal2(new SimpleDateFormat("dd/MM/yyyy").parse(relatorio.getDataFinal()));
				List<Pedido> listaPedidoProduto = null;
		if (relatorio.getSituacao().equals("A") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null) {
			//System.out.println(relatorio.getDataFinal()+""+relatorio.getDataInicial());
			listaPedidoProduto = dao.buscarPedProPorSituacaoA(relatorio);
			if (listaPedidoProduto.size() != 0) {
				Date date = new Date();
				model.addAttribute("data", date);
				model.addAttribute("listaPedidoProduto", listaPedidoProduto);
				dao.fecharBanco();
				return "relatorio/listarRelatorioProduto";
			}
			else{
				model.addAttribute("mensagem", "NÃO HÁ REGISTROS");
				return "relatorio/buscarPedidoProduto";
			}
		}
		else if (relatorio.getSituacao().equals("B") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null ) {
			listaPedidoProduto = dao.buscarPedProPorSituacaoB(relatorio);
			if (listaPedidoProduto.size() != 0) {
				Date date = new Date();
				model.addAttribute("data", date);
				model.addAttribute("listaPedidoProduto", listaPedidoProduto);
				dao.fecharBanco();
				return "relatorio/listarRelatorioProduto";
			}
			else{
				model.addAttribute("mensagem", "NÃO HÁ REGISTROS");
				return "relatorio/buscarPedidoProduto";
			}
		}
		else if (relatorio.getSituacao().equals("C") && relatorio.getDataInicial() != null && relatorio.getDataFinal() != null ){
			listaPedidoProduto = dao.buscarPedProPorSituacaoC(relatorio);
			
			if (listaPedidoProduto.size() != 0) {
				Date date = new Date();
				model.addAttribute("data", date);
				model.addAttribute("listaPedidoProduto", listaPedidoProduto);
				dao.fecharBanco();
				return "relatorio/listarRelatorioProduto";
			}
			else{
				model.addAttribute("mensagem", "NÃO HÁ REGISTROS");
				return "relatorio/buscarPedidoProduto";
			}
		}
		else  {
			listaPedidoProduto = dao.buscarPorData(relatorio);
			if (listaPedidoProduto.size() != 0) {
				Date date = new Date();
				model.addAttribute("data", date);
				model.addAttribute("listaPedidoProduto", listaPedidoProduto);
				dao.fecharBanco();
				return "relatorio/listarRelatorioProduto";
			}
			else{
				model.addAttribute("mensagem", "NÃO HÁ REGISTROS");
				return "relatorio/buscarPedidoProduto";
			}
		}
	}
}
