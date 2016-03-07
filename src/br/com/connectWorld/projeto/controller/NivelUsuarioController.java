package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.connectWorld.projeto.dao.NivelUsuarioDao;
import br.com.connectWorld.projeto.dao.TelasDao;
import br.com.connectWorld.projeto.model.NivelUsuario;
import br.com.connectWorld.projeto.model.Telas;

@Controller
public class NivelUsuarioController {
	
	@RequestMapping("/cadastrarNivelUsuario")
	public String cadastrarNivelUsuario(Model model) {
		TelasDao dao = new TelasDao();
		List<Telas> listaTelas = dao.listar();
		model.addAttribute("listaTelas", listaTelas);
		return "nivelUsuario/cadastrarNivelUsuario";
		}
	@RequestMapping("salvarNivelUsuario")
	public String salvarNivelUsuario(NivelUsuario nivelUsuario, Model model) throws SQLException{
	 	
		NivelUsuarioDao dao = new NivelUsuarioDao();
		dao.salvar(nivelUsuario);
		model.addAttribute("mensagem", "Nivel de usuario Incluido com Sucesso");
		dao.fecharBanco();
		return "forward:listarNivelUsuario";
	}
	@RequestMapping("/listarNivelUsuario")
	public String listarNivelUsuario(Model model) throws SQLException {
		
		NivelUsuarioDao dao = new NivelUsuarioDao();
		List<NivelUsuario> listaNivelUsuario = dao.listar();
		model.addAttribute("listaNivelUsuario", listaNivelUsuario);
		dao.fecharBanco();
		return "nivelUsuario/listarNivelUsuario";
		
	}
	@RequestMapping("/editarNivelUsuario")
	public String editarNivelUsuario( int cod, Model model) throws SQLException {
		
		NivelUsuarioDao dao = new NivelUsuarioDao();
		model.addAttribute("nivelUsuario",dao.buscarPorCod(cod));
		dao.fecharBanco();
		return "nivelUsuario/editarNivelUsuario";
	}
	@RequestMapping("atualizarNivelUsuario")
	public String atualizarNivelUsuario(NivelUsuario nivelUsuario, Model model) throws SQLException{
		
		NivelUsuarioDao dao = new NivelUsuarioDao();
		dao.atualizarNivelUsuario(nivelUsuario);
		model.addAttribute("mensagem", "Nivel atualizado com Sucesso");
		dao.fecharBanco();
		return "forward:listarNivelUsuario";
	}
	@RequestMapping("/deletarNivelUsuario")
	public String deletarNivelUsuario(NivelUsuario nivelUsuario, Model model) throws SQLException {
		
		NivelUsuarioDao dao = new NivelUsuarioDao();
		dao.deletar(nivelUsuario);
		model.addAttribute("mensagem", "Nivel removido com sucesso!");
		dao.fecharBanco();
		return "forward:listarNivelUsuario";
	}
}
