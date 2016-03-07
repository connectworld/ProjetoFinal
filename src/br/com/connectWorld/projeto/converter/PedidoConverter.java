package br.com.connectWorld.projeto.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.connectWorld.projeto.dao.PedidoDao;
import br.com.connectWorld.projeto.model.Pedido;

public class PedidoConverter implements Converter<String, Pedido> {

	public Pedido convert(String cod) {
		PedidoDao dao = new PedidoDao();
		return dao.buscarPorCod(Integer.valueOf(cod));
	}
}
