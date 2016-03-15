package br.com.connectWorld.projeto.controller;




import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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
}
