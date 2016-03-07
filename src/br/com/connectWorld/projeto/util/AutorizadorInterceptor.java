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
				|| uri.endsWith("chamaHome") || uri.endsWith("chamaPedidoWeb")|| uri.endsWith("salvarClientePedido"))  {
			return true;
		}
		boolean acesso = false;
		if (request.getSession().getAttribute("usuarioLogado") != null) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
			ValidarUrlDao dao = new ValidarUrlDao();
			List<ValidaUrl> lista = dao.obterTelasUsuario(usuario.getNivelUsuario().getCod());
			
			for (ValidaUrl validaUrl : lista) {
				if (uri.endsWith(validaUrl.getTela().getUrl())) {
					acesso = true;
				}
			}
		}
		if (acesso) {
			return acesso;
		} else {
			
			if (request.getSession().getAttribute("usuarioLogado") != null) {
				request.getSession().setAttribute("mensagem", "Voc� n�o tem permiss�o");
				response.sendRedirect("/Projeto_Final/listarProduto");
			} else {
				response.sendRedirect("/Projeto_Final/");
			}	
			return acesso;
		}
	}
}
