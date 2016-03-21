package br.com.connectWorld.projeto.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.connectWorld.projeto.dao.ClienteDao;
import br.com.connectWorld.projeto.dao.ItensPedidoProdutoDao;
import br.com.connectWorld.projeto.dao.PedidoDao;
import br.com.connectWorld.projeto.dao.ProdutoDao;
import br.com.connectWorld.projeto.model.Cliente;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.Produto;
import br.com.connectWorld.projeto.model.Usuario;



@Controller
public class PedidoProdutoWebController {
	
	List <Produto> listaProdutoArray;
	
	@RequestMapping("/realizarPedidoProdutoWeb")
	public String realizarPedidoProdutoWeb(Model model) throws SQLException{
		ProdutoDao dao = new ProdutoDao();
		//this.clienteTela = cliente;
		this.listaProdutoArray = new ArrayList<>();
		//clienteTela = new Cliente();
		List<Produto> listaProduto= dao.listar();
		model.addAttribute("listaProduto", listaProduto);
		dao.fecharBanco();
		return "principal/produtos";
	}
	@RequestMapping("/retornaPedidoProdutoWeb")
	public String retornaPedidoProdutoWeb(Model model, @RequestParam("cod") int cod) throws SQLException{
		ProdutoDao dao = new ProdutoDao();
		List<Produto> listaProduto = dao.listar();
		Produto produto = dao.buscarPorCod(cod);
		this.listaProdutoArray.add(produto);
		model.addAttribute("listaProdutoAdd", listaProdutoArray);
		model.addAttribute("listaProduto", listaProduto);
		//model.addAttribute("cliente", this.clienteTela);
		dao.fecharBanco();
		return "principal/produtos";
	}
	@RequestMapping("/removerProdutoPedidoWeb")
	public String removerProdutoPedidoWeb(Produto produto, Model model) throws SQLException{
		ProdutoDao dao = new ProdutoDao();
		Produto produtoConsultado = dao.buscarPorCod(produto.getCod());
		Produto param = null;
		List<Produto> listaProduto = dao.listar();
		    for(int i = 0; i < listaProdutoArray.size(); i++)
		    {
		        param = listaProdutoArray.get(i);
		        if(param.getCod() == produtoConsultado.getCod())
		        {
		        	listaProdutoArray.remove(param);
		            break;
		        }
		    }
		model.addAttribute("listaProduto", listaProduto);
		model.addAttribute("listaProdutoAdd", listaProdutoArray);
		dao.fecharBanco();
		return "principal/produtos";
	}
	@RequestMapping("/pedidoProdutoEtapa2Web")
	public String pedidoProdutoEtapa2Web(Model model) throws SQLException{
		if (listaProdutoArray.size() == 0) {
			model.addAttribute("mensagem", "Voce nao Selecionou nenhum produto");
			return "forward: realizarPedidoProdutoWeb";
		}
		else{
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			return "principal/pedidoProdutoWeb";
		}
	}
	@RequestMapping("/voltarPedidoProdutoWeb")
	public String voltarPedidoProdutoWeb(Model model) throws SQLException{
		ProdutoDao dao = new ProdutoDao();
		List<Produto> listaProduto= dao.listar();
		model.addAttribute("listaProduto", listaProduto);
		model.addAttribute("listaProdutoAdd", listaProdutoArray);
		dao.fecharBanco();
		return "principal/produtos";
	}
	@RequestMapping("/buscarCpfProdutoWeb")
	public String buscarCpfProdutoWeb(Model model,Cliente cliente) throws SQLException {
		ClienteDao clienteDao = new ClienteDao();
		Cliente clienteConsultado = clienteDao.buscarPorCpf(cliente);
		clienteDao.fecharBanco();
		if (clienteConsultado != null) {
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			model.addAttribute("clienteConsultado", clienteConsultado);
			return "principal/pedidoProdutoWebPreenchido";
		}
		else {
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			model.addAttribute("mensagem", "Cliente nao encontrado");
			return "principal/pedidoProdutoWeb";
		}	
	}
	@RequestMapping("/salvarProdutoPedidoWeb")
	public String salvarProdutoPedidoWeb(Model model, Cliente cliente) throws SQLException{
		PedidoDao pedidoDao = new PedidoDao();
		Pedido pedido = new Pedido();
		ClienteDao clienteDao = new ClienteDao();
		Cliente clientePesquisado = clienteDao.buscarPorCpf(cliente);
		Usuario usuario = new Usuario();
		usuario.setCod(5);
		if (clientePesquisado != null) {
			pedido.setCliente(clientePesquisado);
			Date date = new Date();
			pedido.setData(date);
			pedido.setSituacao("A");
			pedido.setValor(0);
			pedido.setTipo(1);
			pedido.setCodigo(usuario);
			pedidoDao.salvar(pedido);
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoProdutoDao itens = new ItensPedidoProdutoDao();
			for (Produto produto: listaProdutoArray) {
				itens.salvarIten(ultimoPedidoSalvo.getCod(), produto, 1);
			}
			//model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
			Pedido exibirPedido = pedidoDao.buscarPorcod(ultimoPedidoSalvo);
			model.addAttribute("pedido", exibirPedido);
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			//model.addAttribute("cliente", clientePesquisado);
			//pedidoDao.fecharBanco();
			itens.fecharBanco();
			pedidoDao.fecharBanco();
			clienteDao.fecharBanco();
			return "principal/impressaoProduto";
		}
		else {
			clienteDao.salvar(cliente);
			Cliente ultimoCliente = clienteDao.obterUltimoCliente();
			pedido.setCliente(ultimoCliente);
			Date date = new Date();
			pedido.setData(date);
			pedido.setSituacao("A");
			pedido.setValor(0);
			pedido.setTipo(1);
			pedido.setCodigo(usuario);
			pedidoDao.salvar(pedido);
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoProdutoDao itens = new ItensPedidoProdutoDao();
			for (Produto produto: listaProdutoArray) {
				itens.salvarIten(ultimoPedidoSalvo.getCod(), produto, 1);
			}
			Pedido exibirPedido = pedidoDao.buscarPorcod(ultimoPedidoSalvo);
			model.addAttribute("pedido", exibirPedido);
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			//model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
			pedidoDao.fecharBanco();
			clienteDao.fecharBanco();
			itens.fecharBanco();
			return "principal/impressaoProduto";
		}
	}
	
}
