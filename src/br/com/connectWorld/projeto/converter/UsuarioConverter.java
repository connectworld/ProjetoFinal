package br.com.connectWorld.projeto.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.connectWorld.projeto.dao.UsuarioDao;
import br.com.connectWorld.projeto.model.Usuario;


public class UsuarioConverter implements Converter<String, Usuario> {

	public Usuario convert(String cod) {
		UsuarioDao dao = new UsuarioDao();
		return dao.buscarPorCod(Integer.valueOf(cod));
	}
	
}
