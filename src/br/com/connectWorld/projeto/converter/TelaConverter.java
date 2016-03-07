package br.com.connectWorld.projeto.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.connectWorld.projeto.dao.TelasDao;
import br.com.connectWorld.projeto.model.Telas;

public class TelaConverter implements Converter<String, Telas> {

	public Telas convert(String cod) {
		TelasDao dao = new TelasDao();
		return dao.buscarPorCod(Integer.valueOf(cod));
	}
	
}