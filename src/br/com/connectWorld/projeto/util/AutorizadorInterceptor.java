package br.com.connectWorld.projeto.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.connectWorld.projeto.dao.ValidarUrlDao;
import br.com.connectWorld.projeto.model.Usuario;
import br.com.connectWorld.projeto.model.ValidaUrl;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
			throws Exception {
		String uri = request.getRequestURI();
		if (uri.contains("/view/bootstrap") || uri.contains("/view/css") || uri.contains("/view/img")
				|| uri.contains("/view/js") || uri.endsWith("/Projeto_Final/") || uri.endsWith("efetuarLogin") || uri.endsWith("logout")
				|| uri.endsWith("chamaHome") || uri.endsWith("chamaPedidoWeb") || uri.endsWith("salvarClientePedido") || uri.endsWith("buscarCpf")
				|| uri.endsWith("servicos") || uri.endsWith("chamaServico")|| uri.endsWith("realizarPedidoWeb")
				|| uri.endsWith("retornaPedidoServicoWeb")|| uri.endsWith("removerServicoPedidoWeb")|| uri.endsWith("pedidoServicoEtapa2Web")
				|| uri.endsWith("voltarPedidoWeb")|| uri.endsWith("buscarCpfWeb")|| uri.endsWith("salvarServicoPedidoWeb") || uri.endsWith("produtos") || uri.endsWith("realizarPedidoProdutoWeb")
				|| uri.endsWith("retornaPedidoProdutoWeb") || uri.endsWith("removerProdutoPedidoWeb")  || uri.endsWith("pedidoProdutoEtapa2Web") || uri.endsWith("voltarPedidoProdutoWeb")
				|| uri.endsWith("buscarCpfProdutoWeb") ) {
			return true;
		}
		boolean acesso = false;
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			ValidarUrlDao dao = new ValidarUrlDao();
			List<ValidaUrl> lista = dao.obterTelasUsuario(usuario.getNivelUsuario().getCod());
			dao.fecharBanco();
			for (ValidaUrl validaUrl : lista) {
				if (uri.endsWith(validaUrl.getTela().getUrl())) {
					acesso = true;
				}
			}
			if (!acesso && request.getSession().getAttribute("usuarioLogado") != null && uri.endsWith("chamaHomeAdmin") ) {
				acesso = true;
			}
		}
		if (acesso) {
			return acesso;
		} else {

			if (request.getSession().getAttribute("usuarioLogado") != null) {
				request.getSession().setAttribute("mensagem", "VOCE NAO TEM PERMISSAO");
				response.sendRedirect("/Projeto_Final/homeAdmin");
			} else {
				response.sendRedirect("/Projeto_Final/");
			}	
			return acesso;
		}
	}
}
