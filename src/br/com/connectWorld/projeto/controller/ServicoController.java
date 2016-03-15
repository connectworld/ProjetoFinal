package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.connectWorld.projeto.model.Servico;
import br.com.connectWorld.projeto.dao.ServicoDao;


@Controller
public class ServicoController {
	
		@RequestMapping("/cadastrarServico")
		public String cadastrarServico() throws SQLException {
			return "servico/cadastrarServico";
		}
	@RequestMapping("salvarServico")
	public String salvarServico(Servico servico, Model model)
			throws SQLException {
			ServicoDao dao = new ServicoDao();
			dao.salvar(servico);
			model.addAttribute("mensagem", "Serviço Incluido com Sucesso");
			dao.fecharBanco();
			return "forward:listarServico";
	}

	@RequestMapping("/listarServico")
	public String listarServico(Model model) throws SQLException {
		ServicoDao dao = new ServicoDao();
		List<Servico> listaServico = dao.listar();
		model.addAttribute("listaServico", listaServico);
		dao.fecharBanco();
		return "servico/listarServico";
	}

	@RequestMapping("/editarServico")
	public String editarServico(int cod, Model model) throws SQLException {
		
			ServicoDao dao = new ServicoDao();
			model.addAttribute("servico", dao.buscarPorCod(cod));
			dao.fecharBanco();
			return "servico/editarServico";
	}
	@RequestMapping("/atualizarServico")
	public String atualizarServico(Servico servico, Model model) throws SQLException {
		ServicoDao dao = new ServicoDao();
		dao.atualizarServico(servico);
		model.addAttribute("mensagem", "Serviço atualizado com Sucesso");
		dao.fecharBanco();
		return "forward:listarServico";

	}
	@RequestMapping("/deletarServico")
	public String deletarServico(Servico servico, Model model)
			throws SQLException {
		ServicoDao dao = new ServicoDao();
		dao.deletar(servico);
		model.addAttribute("mensagem", "Servico Removido com Sucesso");
		dao.fecharBanco();
		return "forward:listarServico";
	}
}

