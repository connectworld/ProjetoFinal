package br.com.connectWorld.projeto.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.connectWorld.projeto.dao.ClienteDao;
import br.com.connectWorld.projeto.dao.ItensPedidoDao;
import br.com.connectWorld.projeto.dao.PedidoDao;
import br.com.connectWorld.projeto.model.Cliente;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.PedidoWebDTO;
import br.com.connectWorld.projeto.model.Servico;

@Controller
public class ClienteController {
	
	@RequestMapping("/salvarClientePedido")
	public String salvarClientePedido(Model model, PedidoWebDTO pedidoWeb){
		PedidoDao pedidoDao = new PedidoDao();
		Pedido pedido = new Pedido();
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = new Cliente();
		Cliente clientePesquisado = new Cliente();
		cliente.setCpf(pedidoWeb.getCpf());
		clientePesquisado = clienteDao.buscarPorCpf(cliente);
		
		if (clientePesquisado != null) {
			pedido.setCliente(clientePesquisado);
			Date date = new Date();
			pedido.setData(date);
			pedidoDao.salvar(pedido);
			pedido.setSituacao("A");
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoDao itens = new ItensPedidoDao();
			for (Servico servico: pedidoWeb.getServico()) {
				itens.salvarItens(ultimoPedidoSalvo.getCod(), servico);
			}
			model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
			return "principal/pedidoWeb";
		}
		else {
			cliente.setNome(pedidoWeb.getNome());
			cliente.setCpf(pedidoWeb.getCpf());
			cliente.setEmail(pedidoWeb.getEmail());
			cliente.setContato1(pedidoWeb.getContato1());
			cliente.setContato2(pedidoWeb.getContato2());
			cliente.setCep(pedidoWeb.getCep());
			cliente.setBairro(pedidoWeb.getBairro());
			cliente.setCidade(pedidoWeb.getCidade());
			cliente.setRua(pedidoWeb.getRua());
			cliente.setNumero(pedidoWeb.getNumero());
			cliente.setIbge(pedidoWeb.getIbge());
			cliente.setUf(pedidoWeb.getUf());
			clienteDao.salvar(cliente);
			pedido.setCliente(cliente);
			Date date = new Date();
			pedido.setData(date);
			pedido.setSituacao("A");
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoDao itens = new ItensPedidoDao();
			for (Servico servico: pedidoWeb.getServico()) {
				itens.salvarItens(ultimoPedidoSalvo.getCod(), servico);
			}
			return "principal/pedidoWeb";
		}
		
	}
}
