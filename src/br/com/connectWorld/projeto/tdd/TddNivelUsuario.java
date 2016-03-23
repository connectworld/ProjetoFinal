/*package br.com.connectWorld.projeto.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import br.com.connectWorld.projeto.dao.NivelUsuarioDao;
import br.com.connectWorld.projeto.model.NivelUsuario;


public class TddNivelUsuario {
	Random gerador = new Random();
	
	@Test
	public void cadastrarNivelUsuarioTeste() throws SQLException{
		
		String descricao = "teste" + gerador.nextInt();
		NivelUsuario nivelUsuario = new NivelUsuario();
		NivelUsuarioDao dao = new NivelUsuarioDao();

		nivelUsuario.setNome("teste");
		nivelUsuario.setDescricao(descricao);
		
		dao.salvar(nivelUsuario);
		
		NivelUsuario retorno = dao.buscarPorDesc(descricao);
		dao.fecharBanco();
		assertEquals(retorno.getDescricao(),descricao);
	}
	@Test
	public void deletarNivelUsuarioTest() throws SQLException{
		
		String nome = "teste" + gerador.nextInt();
		NivelUsuario nivelUsuario = new NivelUsuario();
		NivelUsuarioDao dao = new NivelUsuarioDao();

		nivelUsuario.setNome(nome);
		nivelUsuario.setDescricao("teste");
		
		dao.salvar(nivelUsuario);
		
		List<NivelUsuario> feedBack = dao.listar();
		int valorA = feedBack.size();
		dao.deletar(nivelUsuario);
		List<NivelUsuario> feedBack2 = dao.listar();
		int valorB = feedBack2.size();
		assertEquals(valorA,valorB + 1);
	}
	@Test
	public void editarNivelUsuarioTest() throws SQLException{
		
		String descricao = "teste" + gerador.nextInt();
		NivelUsuario nivelUsuario = new NivelUsuario();
		NivelUsuarioDao dao = new NivelUsuarioDao();

		nivelUsuario.setNome("teste");
		nivelUsuario.setDescricao(descricao);
		
		dao.salvar(nivelUsuario);
		
		NivelUsuario feedBack = dao.buscarPorDesc(descricao);
		String valorA = feedBack.getDescricao();
		String descricao2 = "teste" + gerador.nextInt();
		feedBack.setDescricao(descricao2);
		
		dao.atualizarNivelUsuario(feedBack);
		
		NivelUsuario feedBack2 = dao.buscarPorDesc(descricao2);
		String valorB = feedBack2.getDescricao();
		
		assertNotEquals(valorA,valorB);
	
	}
}

*/