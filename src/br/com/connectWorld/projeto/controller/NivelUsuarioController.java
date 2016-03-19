package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.connectWorld.projeto.dao.NivelUsuarioDao;
import br.com.connectWorld.projeto.dao.TelasDao;
import br.com.connectWorld.projeto.dao.ValidarUrlDao;
import br.com.connectWorld.projeto.model.NivelUsuario;
import br.com.connectWorld.projeto.model.Telas;
import br.com.connectWorld.projeto.model.ValidaUrl;


@Controller
public class NivelUsuarioController {
	
	/*
	}*/
	List<Telas> listaTelaArray;

	@RequestMapping("/cadastrarNivelUsuario")
	public String cadastrarUsuario(Model model) throws SQLException {
		TelasDao dao = new TelasDao();
		List<Telas> listaTelas = dao.listar();
		this.listaTelaArray = new ArrayList<>();
		model.addAttribute("listaTelas", listaTelas);
		dao.fecharBanco();
		return "nivelUsuario/listarTela";
	}
	
	@RequestMapping("/selecionarTela")
	public String selecionarTela(Model model, @RequestParam("cod") int cod) throws SQLException {
		Telas param = null;
		Telas param2=null; 
		TelasDao dao = new TelasDao();
		List<Telas> listaTelas= dao.listar();
		Telas telaSelecionada = dao.buscarPorCod(cod);
		this.listaTelaArray.add(telaSelecionada);
		for(int i = 0; i < listaTelaArray.size(); i++) {
	        param = listaTelaArray.get(i);
	        for (int j= 0;j < listaTelas.size(); j++) {
	        	param2 = listaTelas.get(j);
	        	if(param.getCod() == param2.getCod())
	        	listaTelas.remove(param2);
	            break;
	        }
	    }
		model.addAttribute("listaTelaAdd", listaTelaArray);
		model.addAttribute("listaTelas", listaTelas);
		//model.addAttribute("cliente", this.clienteTela);
		dao.fecharBanco();
		return "nivelUsuario/listarTela";
	}
	@RequestMapping("/removerTela")
	public String removerTela(Model model, @RequestParam("cod") int cod) throws SQLException {
		Telas param = null;
		Telas param2=null; 
		Telas param3 = null;
		//Telas param4=null;
		TelasDao dao = new TelasDao();
		//Telas tela = dao.buscarPorCod(cod);
		List<Telas> listaTelas= dao.listar();
		Telas telaSelecionada = dao.buscarPorCod(cod);
		//this.listaTelaArray.add(telaSelecionada);
		for(int i = 0; i < listaTelaArray.size(); i++) {
	        param = listaTelaArray.get(i);
	        for (int j= 0;j < listaTelas.size(); j++) {
	        	param2 = listaTelas.get(j);
	        	if(param.getCod() == param2.getCod())
	        	listaTelas.remove(param2);
	            break;
	        }
	    }
		for(int i = 0; i < listaTelaArray.size(); i++) {
	        param3 = listaTelaArray.get(i);
	        	if(param3.getCod() == telaSelecionada.getCod())
	        	listaTelaArray.remove(param3);
	        	listaTelas.add(param3);
	            break;
	    }
		
		model.addAttribute("listaTelaAdd", listaTelaArray);
		model.addAttribute("listaTelas", listaTelas);
		//model.addAttribute("cliente", this.clienteTela);
		dao.fecharBanco();
		return "nivelUsuario/listarTela";
	}
	
	@RequestMapping("/cadastrarNivelEtapa2")
	public String cadastrarNivelEtapa2(Model model) throws SQLException {
		TelasDao dao = new TelasDao();
		model.addAttribute("listaTelaAdd", listaTelaArray);
		dao.fecharBanco();
		return "nivelUsuario/formulario";
	}

	@RequestMapping("/alterarTelas")
	public String alterarTelas(Model model) throws SQLException {
		TelasDao dao = new TelasDao();
		model.addAttribute("listaTelaAdd", listaTelaArray);
		dao.fecharBanco();
		return "nivelUsuario/formulario";
	}

	@RequestMapping("salvarNivelUsuario")
	public String salvarNivelUsuario(NivelUsuario nivelUsuario, Model model) throws SQLException {
		//nivelUsuario.setTelas(listaTelaArray);
		NivelUsuarioDao dao = new NivelUsuarioDao();
		dao.salvar(nivelUsuario, listaTelaArray);
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
	public String editarNivelUsuario(int cod, Model model) throws SQLException {

		NivelUsuarioDao dao = new NivelUsuarioDao();
		NivelUsuario nivelUsuario = dao.buscarPorCod(cod);
		ValidarUrlDao validaUrlDao = new ValidarUrlDao();
		List <ValidaUrl> listaTelasUsuario = validaUrlDao.obterTelasUsuario(nivelUsuario.getCod());
		TelasDao telasDao = new TelasDao();
		List <Telas> listaTelas = telasDao.listar();
		model.addAttribute("nivelUsuario", nivelUsuario);
		model.addAttribute("listaTelasUsuario", listaTelasUsuario);
		model.addAttribute("listaTelas", listaTelas);

		
		dao.fecharBanco();
		validaUrlDao.fecharBanco();
		return "nivelUsuario/editarNivelUsuario";
	}
	@RequestMapping("/atualizarNivelUsuario")
	public String atualizarUsuario(NivelUsuario nivelUsuario, Model model) throws SQLException {
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
