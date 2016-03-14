package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.connectWorld.projeto.dao.NivelUsuarioDao;
import br.com.connectWorld.projeto.dao.UsuarioDao;
import br.com.connectWorld.projeto.model.NivelUsuario;
import br.com.connectWorld.projeto.model.Usuario;
import br.com.connectWorld.projeto.util.Util;

@Controller
public class UsuarioController {
	@RequestMapping("salvarUsuario")
	public String salvarUsuario(Usuario usuario,
			@RequestParam("img") MultipartFile imagem,
			@RequestParam("compara") String compara, Model model)
			throws SQLException {
		if (compara.equals("salvar")) {
			if (Util.fazerUploadImagem(imagem)) {
				usuario.setFoto(Calendar.getInstance().getTime() + " - "
						+ imagem.getOriginalFilename());
			}
			UsuarioDao dao = new UsuarioDao();
			dao.salvar(usuario);
			model.addAttribute("mensagem", "Usuario Incluido com Sucesso");
			dao.fecharBanco();
			return "forward:listarUsuario";
		} else {
			NivelUsuarioDao dao = new NivelUsuarioDao();
			List<NivelUsuario> listaNivelUsuario = dao.listar();
			model.addAttribute("listaNivelUsuario", listaNivelUsuario);
			dao.fecharBanco();
			return "usuario/cadastrarUsuario";
		}
	}

	@RequestMapping("/listarUsuario")
	public String listarUsuario(Model model) throws SQLException {
		UsuarioDao dao = new UsuarioDao();
		List<Usuario> listaUsuario = dao.listar();
		model.addAttribute("listaUsuario", listaUsuario);
		dao.fecharBanco();
		return "usuario/listarUsuario";
	}

	@RequestMapping("/editarUsuario")
	public String editarUsuario(int cod,@RequestParam("compara") String compara,Usuario usuario, Model model) throws SQLException {
		if (compara.equals("salvar")) {
			UsuarioDao dao = new UsuarioDao();
			NivelUsuarioDao dao2 = new NivelUsuarioDao();
			List<NivelUsuario> listaNivelUsuario = dao2.listar();
			model.addAttribute("listaNivelUsuario", listaNivelUsuario);
			model.addAttribute("usuario", dao.buscarPorCod(cod));
			dao.fecharBanco();
		return "usuario/editarUsuario";
		}
		else{
			UsuarioDao dao = new UsuarioDao();
			dao.atualizarUsuario(usuario);
			model.addAttribute("mensagem", "Usu�rio atualizado com Sucesso");
			dao.fecharBanco();
			return "forward:listarUsuario";
		}
	}

	@RequestMapping("/deletarUsuario")
	public String deletarUsuario(Usuario usuario, Model model)
			throws SQLException {
		UsuarioDao dao = new UsuarioDao();
		dao.deletar(usuario);
		model.addAttribute("mensagem", "Usu�rio Removido com Sucesso");
		dao.fecharBanco();
		return "forward:listarUsuario";
	}

	@RequestMapping("efetuarLogin")
	public String efetuarLogin(Usuario usuario, HttpSession session, Model model)
			throws SQLException {
		UsuarioDao dao = new UsuarioDao();
		Usuario usuarioLogado = dao.buscarUsuario(usuario);
		dao.fecharBanco();
		if (usuarioLogado != null) {
			session.setAttribute("usuarioLogado", usuarioLogado);
			model.addAttribute("mensagem", "Bem vindo");
			return "homeAdmin";
		} else if (usuario.getSenha() == null && usuario.getLogin() == null) {
			return "admin";
		}
		model.addAttribute("msg", "LOGIN OU SENHA INVALIDOS.");
		dao.fecharBanco();
		return "admin";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "admin";
	}

	@RequestMapping("login")
	public String login() {
		return "admin";
	}

	@RequestMapping("homeAdmin")
	public String homeAdmin() {
		return "homeAdmin";
	}
}
