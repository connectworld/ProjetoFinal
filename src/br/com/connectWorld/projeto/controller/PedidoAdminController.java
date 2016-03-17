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
import br.com.connectWorld.projeto.dao.UsuarioDao;
import br.com.connectWorld.projeto.model.Cliente;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.Servico;
import br.com.connectWorld.projeto.model.Usuario;

@Controller
public class PedidoAdminController {

	List <Servico> listaServicoArray;
	Cliente clienteTela;
	
	@RequestMapping("/pesquisarServico")
	public String pesquisarServico(Model model) throws SQLException{
		ServicoDao dao = new ServicoDao();
		//this.clienteTela = cliente;
		this.listaServicoArray = new ArrayList<>();
		clienteTela = new Cliente();
		List<Servico> listaServico = dao.listar();
		model.addAttribute("listaServico", listaServico);
		dao.fecharBanco();
		return "servico/PesquisarServico";
	}

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
	public String retornaPedidoServico(Model model, @RequestParam("cod") int cod) throws SQLException{
		ServicoDao dao = new ServicoDao();
		List<Servico> listaServico = dao.listar();
		Servico servico = dao.buscarPorCod(cod);
		this.listaServicoArray.add(servico);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		model.addAttribute("listaServico", listaServico);
		//model.addAttribute("cliente", this.clienteTela);
		dao.fecharBanco();
		return "servico/PesquisarServico";
	}
	@RequestMapping("/addServicoPedido")
	public String pedidoServicoAdmin(Servico servico, Model model) throws SQLException{
		ServicoDao dao = new ServicoDao();
		Servico servicoConsultado = dao.buscarPorCod(servico.getCod());
		List<Servico> listaServico = dao.listar();
		this.listaServicoArray.add(servicoConsultado);
		model.addAttribute("listaServico", listaServico);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		dao.fecharBanco();
		return "servico/PesquisarServico";
	}
	@RequestMapping("/removerServicoPedido")
	public String removerServicoPedido(Servico servico, Model model) throws SQLException{
		ServicoDao dao = new ServicoDao();
		Servico servicoConsultado = dao.buscarPorCod(servico.getCod());
		Servico param = null;
		List<Servico> listaServico = dao.listar();
		    for(int i = 0; i < listaServicoArray.size(); i++)
		    {
		        param = listaServicoArray.get(i);
		        if(param.getCod() == servicoConsultado.getCod())
		        {
		        	listaServicoArray.remove(param);
		            break;
		        }
		    }
		model.addAttribute("listaServico", listaServico);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		dao.fecharBanco();
		return "servico/PesquisarServico";
	}
	@RequestMapping("/pedidoServicoEtapa2")
	public String pedidoServicoEtapa2(Model model) throws SQLException{
		if (listaServicoArray.size() == 0) {
			model.addAttribute("mensagem", "Você não Selecionou nenhum servico");
			return "forward: pesquisarServico";
		}
		else{
			model.addAttribute("listaServicoAdd", listaServicoArray);
			return "pedido/pedidoServicoAdmin";
		}
	}
	@RequestMapping("/voltar")
	public String voltar(Model model) throws SQLException{
		ServicoDao dao = new ServicoDao();
		List<Servico> listaServico = dao.listar();
		model.addAttribute("listaServico", listaServico);
		model.addAttribute("listaServicoAdd", listaServicoArray);
		dao.fecharBanco();
		return "servico/PesquisarServico";
	}
	@RequestMapping("/buscarCliente")
	public String buscarCliente (Model model) throws SQLException{
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
		model.addAttribute("listaServicoAdd", listaServicoArray);
		model.addAttribute("clienteConsultado", clienteConsultado);
		dao.fecharBanco();
		return "pedido/pedidoServicoAdminPreenchido";
	}
	@RequestMapping("/salvarPedidoServicoAdmin")
	public String salvarPedidoServicoAdmin(Model model, Cliente cliente, @RequestParam("codigo") int codigo) throws SQLException{
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
			pedidoDao.salvar(pedido);
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoServicoDao itens = new ItensPedidoServicoDao();
			for (Servico servico: listaServicoArray) {
				itens.salvarItens(ultimoPedidoSalvo.getCod(), servico);
			}
			//model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
			Pedido exibirPedido = pedidoDao.buscarPorcod(ultimoPedidoSalvo);
			model.addAttribute("pedido", exibirPedido);
			model.addAttribute("usuario", usuario);
			model.addAttribute("listaServicoAdd", listaServicoArray);
			//model.addAttribute("cliente", clientePesquisado);
			//pedidoDao.fecharBanco();
			itens.fecharBanco();
			usuarioDao.fecharBanco();
			pedidoDao.fecharBanco();
			clienteDao.fecharBanco();
			return "pedido/impressao";
		}
		else {
			clienteDao.salvar(cliente);
			Cliente ultimoCliente = clienteDao.obterUltimoCliente();
			pedido.setCliente(ultimoCliente);
			Date date = new Date();
			pedido.setData(date);
			pedido.setSituacao("A");
			pedido.setValor(0);
			pedidoDao.salvar(pedido);
			Pedido ultimoPedidoSalvo = pedidoDao.obterUltimoPedido();
			ItensPedidoServicoDao itens = new ItensPedidoServicoDao();
			for (Servico servico: listaServicoArray) {
				itens.salvarItens(ultimoPedidoSalvo.getCod(), servico);
			}
			Pedido exibirPedido = pedidoDao.buscarPorcod(ultimoPedidoSalvo);
			model.addAttribute("pedido", exibirPedido);
			model.addAttribute("usuario", usuario);
			model.addAttribute("listaServicoAdd", listaServicoArray);
			model.addAttribute("mensagem", "Pedido Realizado Com sucesso");
			pedidoDao.fecharBanco();
			clienteDao.fecharBanco();
			usuarioDao.fecharBanco();
			itens.fecharBanco();
			return "pedido/impressao";
		}
		
		
	}
	@RequestMapping("/buscarCpfAdmin")
	public String buscarCpf(Model model,Cliente cliente) throws SQLException {
		ClienteDao clienteDao = new ClienteDao();
		Cliente clienteConsultado = clienteDao.buscarPorCpf(cliente);
		clienteDao.fecharBanco();
		if (clienteConsultado != null) {
			model.addAttribute("listaServicoAdd", listaServicoArray);
			model.addAttribute("clienteConsultado", clienteConsultado);
			return "pedido/pedidoServicoAdminPreenchido";
		}
		else {
			model.addAttribute("listaServicoAdd", listaServicoArray);
			model.addAttribute("mensagem", "Cliente não encontrado");
			return "principal/pedidoServicoAdmin";
		}	
	}
}
