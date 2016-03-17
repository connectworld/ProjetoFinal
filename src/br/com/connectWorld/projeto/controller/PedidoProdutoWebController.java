package br.com.connectWorld.projeto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.connectWorld.projeto.dao.ProdutoDao;
import br.com.connectWorld.projeto.model.Produto;

@Controller
public class PedidoProdutoWebController {
	
	@RequestMapping("/produtos")
	public String produtos(Model model) {
		ProdutoDao dao = new ProdutoDao();
		List<Produto> listaProduto = dao.listar();
		model.addAttribute("listaProduto",listaProduto);
		return "principal/produtos";
	}
}
