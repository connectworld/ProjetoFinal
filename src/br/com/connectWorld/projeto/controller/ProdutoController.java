package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.connectWorld.projeto.model.Produto;
import br.com.connectWorld.projeto.dao.ProdutoDao;
import br.com.connectWorld.projeto.util.Util;

@Controller
public class ProdutoController {

	@RequestMapping("salvarProduto")
	public String salvarProduto(Produto produto,
			@RequestParam("img") MultipartFile imagem,
			@RequestParam("compara") String compara, Model model)
			throws SQLException {

		if (compara.equals("salvar")) {
			if (Util.fazerUploadImagem(imagem)) {
				produto.setImagem(Calendar.getInstance().getTime() + " - "
						+ imagem.getOriginalFilename());
			}
			ProdutoDao dao = new ProdutoDao();
			dao.salvar(produto);
			model.addAttribute("mensagem", "Produto Incluido com Sucesso");
			dao.fecharBanco();
			return "forward:listarProduto";
		} else {
			return "produto/cadastrarProduto";
		}
	}

	@RequestMapping("/listarProduto")
	public String listarProduto(Model model) throws SQLException {
		ProdutoDao dao = new ProdutoDao();
		List<Produto> listaProduto = dao.listar();
		model.addAttribute("listaProduto", listaProduto);
		dao.fecharBanco();
		return "produto/listarProduto";
	}

	@RequestMapping("/editarProduto")
	public String editarProduto(int cod,@RequestParam("compara") String compara, Model model,Produto produto) throws SQLException {
		if (compara.equals("salvar")) {
			ProdutoDao dao = new ProdutoDao();
			dao.atualizarProduto(produto);
			model.addAttribute("mensagem", "Produto atualizado com Sucesso");
			dao.fecharBanco();
			return "forward:listarProduto";
		}
		else{
			ProdutoDao dao = new ProdutoDao();
			model.addAttribute("produto", dao.buscarPorCod(cod));
			dao.fecharBanco();
		return "produto/editarProduto";
		}
	}

	@RequestMapping("/deletarProduto")
	public String deletarProduto(Produto produto, Model model)
			throws SQLException {
		ProdutoDao dao = new ProdutoDao();
		dao.deletar(produto);
		model.addAttribute("mensagem", "Produto Removido com Sucesso");
		dao.fecharBanco();
		return "forward:listarProduto";
	}
}
