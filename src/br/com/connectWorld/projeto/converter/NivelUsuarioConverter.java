package br.com.connectWorld.projeto.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.connectWorld.projeto.dao.NivelUsuarioDao;
import br.com.connectWorld.projeto.model.NivelUsuario;

public class NivelUsuarioConverter implements Converter<String, NivelUsuario> {

	public NivelUsuario convert(String cod) {
		NivelUsuarioDao dao = new NivelUsuarioDao();
		return dao.buscarPorCod(Integer.valueOf(cod));
	}
	
}
