package br.com.connectWorld.projeto.converter;

import org.springframework.core.convert.converter.Converter;

import br.com.connectWorld.projeto.dao.ProdutoDao;
import br.com.connectWorld.projeto.model.Produto;

public class ProdutoConverter implements Converter<String, Produto> {

	public Produto convert(String cod) {
		ProdutoDao dao = new ProdutoDao();
		return dao.buscarPorCod(Integer.valueOf(cod));
	}
	
}
