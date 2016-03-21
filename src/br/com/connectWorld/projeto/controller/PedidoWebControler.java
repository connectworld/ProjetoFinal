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
import br.com.connectWorld.projeto.model.Servico;
import br.com.connectWorld.projeto.model.Usuario;



@Controller
public class PedidoWebControler {
	
	
	
	/*@RequestMapping("/chamaPedidoWeb")
	public String chamaPedido(Model model) throws SQLException {
		ServicoDao dao = new ServicoDao();
		List <Servico> listaServico = dao.listar();
		model.addAttribute("listaServico",listaServico);
		dao.fecharBanco();
		return "principal/pedidoServicoWeb";
	}*/
	
	@RequestMapping("/salvarPedidoWeb")
	public String salvarPedidoWeb (Model model ){
		return "principal/pedidoServicoWeb";
	}
	
	List <Servico> listaServicoArray;
	Cliente clienteTela;
	
	@RequestMapping("/realizarPedidoWeb")
	public String realizarPedidoWeb(Model model) throws SQLException{
		ServicoDao dao = new ServicoDao();
		//this.clienteTela = cliente;
		this.listaServicoArray = new ArrayList<>();
		clienteTela = new Cliente();
		List<Servico> listaServico = dao.listar();
		model.addAttribute("listaServico", listaServico);
		dao.fecharBanco();
		return "principal/listaServico";
	}
	@RequestMapping("/retornaPedidoServicoWeb")
	public String retornaPedidoServicoWeb(Model model, @RequestParam("cod") int cod) throws SQLException{
		ServicoDao dao = new ServicoDao();
		List<Servico> listaServico = dao.listar();
		Servico servico = dao.buscarPorCod(cod);
		this.listaServicoArray.add(servico);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		model.addAttribute("listaServico", listaServico);
		//model.addAttribute("cliente", this.clienteTela);
		dao.fecharBanco();
		return "principal/listaServico";
	}
	
	@RequestMapping("/removerServicoPedidoWeb")
	public String removerServicoPedidoWeb(Servico servico, Model model) throws SQLException{
		ServicoDao dao = new ServicoDao();
		//Servico servicoConsultado = dao.buscarPorCod(servico.getCod());
		Servico param = null;
		List<Servico> listaServico = dao.listar();
		    for(int i = 0; i < listaServicoArray.size(); i++)
		    {
		        param = listaServicoArray.get(i);
		        if(param.getCod() == servico.getCod())
		        {
		        	listaServicoArray.remove(param);
		            break;
		        }
		    }
		model.addAttribute("listaServico", listaServico);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		dao.fecharBanco();
		return "principal/listaServico";
	}
	
	@RequestMapping("/pedidoServicoEtapa2Web")
	public String pedidoServicoEtapa2a(Model model) throws SQLException{
		if (listaServicoArray.size() == 0) {
			model.addAttribute("mensagem", "Você não Selecionou nenhum servico");
			return "forward: realizarPedidoWeb";
		}
		else{
			model.addAttribute("listaServicoAdd", listaServicoArray);
			return "principal/pedidoServicoWeb";
		}
	}
	@RequestMapping("/voltarPedidoWeb")
	public String voltarPedidoWeb(Model model) throws SQLException{
		ServicoDao dao = new ServicoDao();
		List<Servico> listaServico = dao.listar();
		model.addAttribute("listaServico", listaServico);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		dao.fecharBanco();
		return "principal/listaServico";
	}
	@RequestMapping("/buscarCpfWeb")
	public String buscarCpfWeb(Model model,Cliente cliente) throws SQLException {
		ClienteDao clienteDao = new ClienteDao();
		Cliente clienteConsultado = clienteDao.buscarPorCpf(cliente);
		clienteDao.fecharBanco();
		if (clienteConsultado != null) {
			model.addAttribute("listaServicoAdd", listaServicoArray);
			model.addAttribute("clienteConsultado", clienteConsultado);
			return "principal/pedidoServicoWebPreenchido";
		}
		else {
			model.addAttribute("listaServicoAdd", listaServicoArray);
			model.addAttribute("mensagem", "Cliente não encontrado");
			return "principal/pedidoServicoWeb";
		}	
	}
	@RequestMapping("/salvarServicoPedidoWeb")
	public String salvarServicoPedidoWeb(Model model, Cliente cliente) throws SQLException{
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
			pedido.setTipo(0);
			pedido.setCodigo(usuario);
			pedidoDao.salvar(pedido);
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoServicoDao itens = new ItensPedidoServicoDao();
			for (Servico servico: listaServicoArray) {
				itens.salvarItens(ultimoPedidoSalvo.getCod(), servico);
			}
			//model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
			Pedido exibirPedido = pedidoDao.buscarPorcod(ultimoPedidoSalvo);
			model.addAttribute("pedido", exibirPedido);
			model.addAttribute("listaServicoAdd", listaServicoArray);
			//model.addAttribute("cliente", clientePesquisado);
			//pedidoDao.fecharBanco();
			itens.fecharBanco();
			pedidoDao.fecharBanco();
			clienteDao.fecharBanco();
			return "principal/impressaoServico";
		}
		else {
			clienteDao.salvar(cliente);
			Cliente ultimoCliente = clienteDao.obterUltimoCliente();
			pedido.setCliente(ultimoCliente);
			Date date = new Date();
			pedido.setData(date);
			pedido.setSituacao("A");
			pedido.setValor(0);
			pedido.setTipo(0);
			pedido.setCodigo(usuario);
			pedidoDao.salvar(pedido);
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoServicoDao itens = new ItensPedidoServicoDao();
			for (Servico servico: listaServicoArray) {
				itens.salvarItens(ultimoPedidoSalvo.getCod(), servico);
			}
			Pedido exibirPedido = pedidoDao.buscarPorcod(ultimoPedidoSalvo);
			model.addAttribute("pedido", exibirPedido);
			model.addAttribute("listaServicoAdd", listaServicoArray);
			//model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
			pedidoDao.fecharBanco();
			clienteDao.fecharBanco();
			itens.fecharBanco();
			return "principal/impressaoServico";
		}
	}
	@RequestMapping("/pedidoServicoAdmina")
	public String pedidoServicoAdmina(Model model) throws SQLException{
		//ServicoDao dao = new ServicoDao();
		this.listaServicoArray = new ArrayList<>();
		clienteTela = new Cliente();
		//List<Servico> listaServico = dao.listar();
		model.addAttribute("cliente", this.clienteTela);
		//dao.fecharBanco();
		return "pedido/pedidoServicoAdmin";
	}
	@RequestMapping("/addServicoPedidoa")
	public String pedidoServicoAdmina(Servico servico, Model model) throws SQLException{
		ServicoDao dao = new ServicoDao();
		Servico servicoConsultado = dao.buscarPorCod(servico.getCod());
		List<Servico> listaServico = dao.listar();
		this.listaServicoArray.add(servicoConsultado);
		model.addAttribute("listaServico", listaServico);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		dao.fecharBanco();
		return "servico/PesquisarServico";
	}
	@RequestMapping("/buscarClientea")
	public String buscarClientea (Model model) throws SQLException{
		ClienteDao dao = new ClienteDao();
		List<Cliente> listaCliente = dao.listar();
		model.addAttribute("listaCliente", listaCliente);
		dao.fecharBanco();
		return "pedido/listarClientePedido";
	}
	@RequestMapping("/clienteSelecionadoa")
	public String clienteSelecionadoa(Model model, int cod ) throws SQLException{
		ClienteDao dao = new ClienteDao();
		Cliente clienteConsultado = dao.buscarPorCod(cod);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		model.addAttribute("clienteConsultado", clienteConsultado);
		dao.fecharBanco();
		return "pedido/pedidoServicoAdminPreenchido";
	}	
}
