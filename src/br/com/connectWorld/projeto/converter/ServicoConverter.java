package br.com.connectWorld.projeto.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.connectWorld.projeto.dao.ServicoDao;
import br.com.connectWorld.projeto.model.Servico;

public class ServicoConverter implements Converter<String, Servico> {

	public Servico convert(String cod) {
		ServicoDao dao = new ServicoDao();
		return dao.buscarPorCod(Integer.valueOf(cod));
	}
}
