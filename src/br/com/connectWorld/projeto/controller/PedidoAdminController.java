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
import br.com.connectWorld.projeto.dao.ItensPedidoServicoDao;
import br.com.connectWorld.projeto.dao.PedidoDao;
import br.com.connectWorld.projeto.dao.ServicoDao;
import br.com.connectWorld.projeto.model.Cliente;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.PedidoWebDTO;
import br.com.connectWorld.projeto.model.Servico;

@Controller
public class PedidoAdminController {

	List <Servico> listaServicoArray;
	Cliente clienteTela;
	

	@RequestMapping("/pedidoServicoAdmin")
	public String pedidoServicoAdmin(Model model) throws SQLException{
		//ServicoDao dao = new ServicoDao();
		this.listaServicoArray = new ArrayList<>();
		clienteTela = new Cliente();
		//List<Servico> listaServico = dao.listar();
		model.addAttribute("cliente", this.clienteTela);
		//dao.fecharBanco();
		return "pedido/pedidoServicoAdmin";
	}

	@RequestMapping("/retornapedidoServicoAdmin")
	public String retornaPedidoServico(Model model, @RequestParam("id") int id) throws SQLException{
		ServicoDao dao = new ServicoDao();
		Servico servico = dao.buscarPorCod(id);
		this.listaServicoArray.add(servico);
		model.addAttribute("listaServico", listaServicoArray);
		model.addAttribute("cliente", this.clienteTela);
		dao.fecharBanco();
		return "pedido/pedidoServicoAdmin";
	}

	@RequestMapping("/pesquisarServico")
	public String pesquisarServico(Model model, Cliente cliente) throws SQLException{
		ServicoDao dao = new ServicoDao();
		this.clienteTela = cliente;
		List<Servico> listaServico = dao.listar();
		model.addAttribute("listaServico", listaServico);
		dao.fecharBanco();
		return "servico/PesquisarServico";
	}




	@RequestMapping("/buscarCliente")
	public String buscarCliente (Model model ) throws SQLException{
		ClienteDao dao = new ClienteDao();
		List<Cliente> listaCliente = dao.listar();
		model.addAttribute("listaCliente", listaCliente);
		dao.fecharBanco();
		return "pedido/listarClientePedido";
	}
	@RequestMapping("/clienteSelecionado")
	public String clienteSelecionado(Model model, int cod ) throws SQLException{
		ClienteDao dao = new ClienteDao();
		Cliente clienteConsultado = dao.buscarPorCod(cod);
		model.addAttribute("clienteConsultado", clienteConsultado);
		dao.fecharBanco();
		return "pedido/pedidoServicoAdminPreenchido";
	}
	@RequestMapping("/salvarPedidoServicoAdmin")
	public String salvarPedidoServicoAdmin(Model model, PedidoWebDTO pedidoWebDTO) throws SQLException{
		ClienteDao dao = new ClienteDao();
		Cliente clienteConsultado = dao.buscarPorCod(pedidoWebDTO.getCod());
		PedidoDao pedidoDao = new PedidoDao();
		Pedido pedido = new Pedido();
		pedido.setCliente(clienteConsultado);
		Date date = new Date();
		pedido.setData(date);
		pedido.setData(date);
		pedido.setSituacao("A");
		pedido.setValor(0);
		pedidoDao.salvar(pedido);
		Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
		ItensPedidoServicoDao itens = new ItensPedidoServicoDao();
		for (Servico servico: listaServicoArray) {
			itens.salvarItens(ultimoPedidoSalvo.getCod(), servico);
		}
		model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
		pedidoDao.fecharBanco();
		itens.fecharBanco();
		dao.fecharBanco();
		return "pedido/pedidoServicoAdminPreenchido";
	}
	@RequestMapping("/buscarCpfAdmin")
	public String buscarCpf(Model model,Cliente cliente) throws SQLException {
		ClienteDao clienteDao = new ClienteDao();
		Cliente clienteConsultado = clienteDao.buscarPorCpf(cliente);
		clienteDao.fecharBanco();
		if (clienteConsultado != null) {
			ServicoDao dao = new ServicoDao();
			List <Servico> listaServico = dao.listar();
			dao.fecharBanco();
			model.addAttribute("listaServico",listaServico);
			model.addAttribute("clienteConsultado", clienteConsultado);
			return "principal/pedidoServicoWebPreechido";
		}
		else {
			model.addAttribute("mensagem", "Desculpa, voce ainda nao realizou nenhum tipo de compra conosco");
			return "principal/pedidoServicoWeb";
		}	
	}
}
