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
import br.com.connectWorld.projeto.dao.UsuarioDao;
import br.com.connectWorld.projeto.model.Cliente;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.Produto;
import br.com.connectWorld.projeto.model.Usuario;

@Controller
public class PedidoProdutoAdminController {
	
	List <Produto> listaProdutoArray;
	
	@RequestMapping("/pedidoProdutoAdmin")
	public String pedidoProdutoAdmin(Model model) {
		this.listaProdutoArray = new ArrayList<>();
		ProdutoDao dao = new ProdutoDao();
		List<Produto> listaProduto = dao.listar();
		model.addAttribute("listaProduto",listaProduto);
		return "pedido/pedidoProdutoAdmin";
	}
	
	@RequestMapping("/pedidoProdutoAdd")
	public String pedidoProdutoAdd(Model model, @RequestParam("cod") int cod) throws SQLException{
		
		ProdutoDao produtoDao = new ProdutoDao();
		List<Produto> listaProduto = produtoDao.listar();
		Produto produto = produtoDao.buscarPorCod(cod);
		this.listaProdutoArray.add(produto);
		model.addAttribute("listaProdutoAdd", listaProdutoArray);
		model.addAttribute("listaProduto", listaProduto);
		//model.addAttribute("cliente", this.clienteTela);
		produtoDao.fecharBanco();
		return "pedido/pedidoProdutoAdmin";
	}
	
	@RequestMapping("/removerProdutoPedidoAdmin")
	public String removerServicoPedido(Produto produto, Model model) throws SQLException{
		ProdutoDao dao = new ProdutoDao();
	//	Produto produtoConsultado = dao.buscarPorCod(produto.getCod());
		Produto param = null;
		List<Produto> listaProduto = dao.listar();
		    for(int i = 0; i < listaProdutoArray.size(); i++)
		    {
		        param = listaProdutoArray.get(i);
		        if(param.getCod() == produto.getCod())
		        {
		        	listaProdutoArray.remove(param);
		            break;
		        }
		    }
		model.addAttribute("listaProduto", listaProduto);
		model.addAttribute("listaProdutoAdd", listaProdutoArray);
		dao.fecharBanco();
		return "pedido/pedidoProdutoAdmin";
	}
	
	@RequestMapping("/pedidoProdutoEtapa2Admin")
	public String pedidoServicoEtapa2(Model model) throws SQLException{
		if (listaProdutoArray.size() == 0) {
			model.addAttribute("mensagem", "Voc� n�o Selecionou nenhum produto");
			return "forward: pedidoProdutoAdmin";
		}
		else{
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			return "pedido/pedidoProdutoFinalizarAdmin";
		}
	}
	
	@RequestMapping("/salvarPedidoProdutoAdmin")
	public String salvarPedidoProdutoAdmin(Model model, Cliente cliente, @RequestParam("codigo") int codigo) throws SQLException{
		PedidoDao pedidoDao = new PedidoDao();
		Pedido pedido = new Pedido();
		ClienteDao clienteDao = new ClienteDao();
		Cliente clientePesquisado = clienteDao.buscarPorCpf(cliente);
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = usuarioDao.buscarPorCod(codigo);
		
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
			model.addAttribute("usuario", usuario);
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			model.addAttribute("cliente", clientePesquisado);
			//pedidoDao.fecharBanco();
			itens.fecharBanco();
			usuarioDao.fecharBanco();
			pedidoDao.fecharBanco();
			clienteDao.fecharBanco();
			return "pedido/impressaoProduto";
		}
		else {
			clienteDao.salvar(cliente);
			Cliente ultimoCliente = clienteDao.obterUltimoCliente();
			pedido.setCliente(ultimoCliente);
			Date date = new Date();
			pedido.setData(date);
			pedido.setSituacao("A");
			pedido.setTipo(1);
			pedido.setValor(0);
			pedidoDao.salvar(pedido);
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoProdutoDao itens = new ItensPedidoProdutoDao();
			for (Produto produto: listaProdutoArray) {
				itens.salvarIten(ultimoPedidoSalvo.getCod(), produto,1);
			}
			Pedido exibirPedido = pedidoDao.buscarPorcod(ultimoPedidoSalvo);
			model.addAttribute("pedido", exibirPedido);
			model.addAttribute("usuario", usuario);
			model.addAttribute("listaServicoAdd", listaProdutoArray);
			model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
			pedidoDao.fecharBanco();
			clienteDao.fecharBanco();
			usuarioDao.fecharBanco();
			itens.fecharBanco();
			return "pedido/impressaoProduto";
		}
	}
	
	@RequestMapping("/buscarCpfAdminProduto")
	public String buscarCpfAdminProduto(Model model,Cliente cliente) throws SQLException {
		ClienteDao clienteDao = new ClienteDao();
		Cliente clienteConsultado = clienteDao.buscarPorCpf(cliente);
		clienteDao.fecharBanco();
		if (clienteConsultado != null) {
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			model.addAttribute("clienteConsultado", clienteConsultado);
			return "pedido/pedidoProdutoAdminPreenchido";
		}
		else {
			model.addAttribute("listaProdutoAdd", listaProdutoArray);
			model.addAttribute("mensagem", "Cliente n�o encontrado");
			return "pedido/pedidoProdutoFinalizarAdmin";
		}	
	}
	@RequestMapping("/retornarPedidoAdmin")
	public String retornarPedidoAdmin(Model model) throws SQLException{
		ProdutoDao dao = new ProdutoDao();
		List<Produto> listaProduto = dao.listar();
		model.addAttribute("listaProduto", listaProduto);
		model.addAttribute("listaProdutoAdd", listaProdutoArray);
		dao.fecharBanco();
		return "pedido/pedidoProdutoAdmin";
	}
	
	@RequestMapping("/buscarClienteAdmin")
	public String buscarCliente (Model model, Usuario usuario/*, @RequestParam("codigoUser") int codigUser*/)  throws SQLException{
		ClienteDao dao = new ClienteDao();
		List<Cliente> listaCliente = dao.listar();
		model.addAttribute("listaCliente", listaCliente);
		model.addAttribute("usuario", usuario);
		dao.fecharBanco();
		return "pedido/listarClienteAdminProduto";
	}
	@RequestMapping("/clienteSelecionadoAdmin")
	public String clienteSelecionado(Model model, int cod ) throws SQLException{
		ClienteDao dao = new ClienteDao();
		Cliente clienteConsultado = dao.buscarPorCod(cod);
		model.addAttribute("listaProdutoAdd", listaProdutoArray);
		model.addAttribute("clienteConsultado", clienteConsultado);
		dao.fecharBanco();
		return "pedido/pedidoProdutoAdminPreenchido";
	}
}
