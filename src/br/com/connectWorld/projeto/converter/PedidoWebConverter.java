package br.com.connectWorld.projeto.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.connectWorld.projeto.dao.ClienteDao;
import br.com.connectWorld.projeto.model.Cliente;

public class PedidoWebConverter implements Converter<String, Cliente> {

	public Cliente convert(String cod) {
		ClienteDao dao = new ClienteDao();
		return dao.buscarPorCod(Integer.valueOf(cod));
	}
	
}
