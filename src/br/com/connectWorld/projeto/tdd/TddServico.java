package br.com.connectWorld.projeto.tdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import br.com.connectWorld.projeto.dao.ServicoDao;
import br.com.connectWorld.projeto.model.Servico;

public class TddServico {
	@Test
	public void cadastrarServicoTeste() throws SQLException{
		
		Random gerador = new Random();
		
		String nome = "testeServico" + gerador.nextInt();
		Servico servico = new Servico();
		servico.setNome(nome);
		servico.setDescricao("teste");
		servico.setPreco(3);
		ServicoDao dao = new ServicoDao();
		dao.salvar(servico);
		
		Servico feedback = new Servico();
		feedback = dao.buscarPorNome(nome);
		dao.fecharBanco();
		assertEquals(feedback.getNome(),nome);
	}
	
	@Test
	public void deletarServicoTest() throws SQLException{
		
		Random gerador = new Random();
		String nome = "deletarServico" + gerador.nextInt();
		Servico servico = new Servico();
		servico.setNome(nome);
		servico.setDescricao("testeDeletar");
	    servico.setPreco(10);
		
		ServicoDao dao = new ServicoDao();
		dao.salvar(servico);
		
		ServicoDao recebe = new ServicoDao();
		List<Servico> feedBack = new ArrayList<>();
		feedBack = recebe.listar();
		int valorA = feedBack.size();
		recebe.deletar(servico);
		
		List<Servico> feedBack2 = new ArrayList<>();
		feedBack2 = recebe.listar();
	    int valorB = feedBack2.size();
		recebe.fecharBanco();
		assertEquals(valorA,valorB+1);
}
	@Test
	public void editarServicoTest() throws SQLException{
		
		Random gerador = new Random();
		String nome = "EditarServico" + gerador.nextInt();
		Servico servico = new Servico();
		servico.setNome(nome);
		servico.setDescricao("testeDeletar");
	    servico.setPreco(10);
		
		ServicoDao dao = new ServicoDao();
		dao.salvar(servico);
		
		Servico feedBack = new Servico();
		feedBack = dao.buscarPorNome(nome);
		String valorA = feedBack.getNome();
		String nome1 = "teste" + gerador.nextInt();
		feedBack.setNome(nome1);
		dao.atualizarServico(feedBack);
		
		Servico nome2 = new Servico();
		nome2 = dao.buscarPorNome(nome1);
		String valorB = nome2.getNome();
		assertNotEquals(valorA,valorB);
	}
}
