package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.connectWorld.projeto.dao.ServicoDao;
import br.com.connectWorld.projeto.model.Servico;

@Controller
public class HomeController {
	
	@RequestMapping("/chamaHome")
	public String chamaHome() {
		return "principal/home";
	}
	@RequestMapping("/servicos")
	public String servicos(Model model) throws SQLException {
		ServicoDao dao = new ServicoDao();
		List<Servico> listaServico = dao.listar();
		dao.fecharBanco();
		model.addAttribute("listaServico", listaServico);
		return "principal/servicos";
	}
	@RequestMapping("/chamaServico")
	public String chamaServico(Model model, int cod) throws SQLException {
		ServicoDao dao = new ServicoDao();
		Servico servico = dao.buscarPorCod(cod);
		List<Servico> listaServico = dao.listar();
		dao.fecharBanco();
		model.addAttribute("listaServico", listaServico);
		model.addAttribute("servico", servico);
		return "principal/pedidoServicoWeb";
	}
}
