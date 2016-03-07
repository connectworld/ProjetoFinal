package br.com.connectWorld.projeto.tdd;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import br.com.connectWorld.projeto.dao.ProdutoDao;
import br.com.connectWorld.projeto.model.Produto;

public class TddProduto {
	
	@Test
	public void cadastrarProdutoTest() throws SQLException{
		Random gerador = new Random();
		String descricao = "Amazonia123" + gerador.nextInt();
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setNome("teste");
		produto.setPrecoVenda(10);
		produto.setQuantidade(10);
		produto.setImagem("fulano");
		
		ProdutoDao dao = new ProdutoDao();
		dao.salvar(produto);
		
		Produto feedBack = new Produto();
		feedBack = dao.buscarPorDesc(descricao);
		dao.fecharBanco();
		assertEquals(feedBack.getDescricao(),descricao);
	}
	@Test
	public void deletarProdutoTest() throws SQLException{
		
		Random gerador = new Random();
		String descricao = "Amazonia123" + gerador.nextInt();
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setNome("teste");
		produto.setPrecoVenda(10);
		produto.setQuantidade(10);
		produto.setImagem("fulano");
		
		ProdutoDao dao = new ProdutoDao();
		dao.salvar(produto);
		
		ProdutoDao recebe = new ProdutoDao();
		List<Produto> feedBack = new ArrayList<>();
		feedBack = recebe.listar();
		int valorA = feedBack.size();
		recebe.deletarTdd(produto);
		List<Produto> feedBack2 = new ArrayList<>();
		feedBack2 = recebe.listar();
		int valorB = feedBack2.size();
		assertEquals(valorA,valorB + 1);
	
	}
	@Test
	public void editarProdutoTest() throws SQLException{
		
		Random gerador = new Random();
		String descricao = "Amazonia123" + gerador.nextInt();
		Produto produto = new Produto();
		produto.setDescricao(descricao);
		produto.setNome("teste");
		produto.setPrecoVenda(10);
		produto.setQuantidade(10);
		produto.setImagem("fulano");
		
		ProdutoDao dao = new ProdutoDao();
		dao.salvar(produto);
		
		Produto feedBack = new Produto();
		feedBack = dao.buscarPorDesc(descricao);
		String valorA = feedBack.getDescricao();
		String descricao2 = "teste" + gerador.nextInt();
		feedBack.setDescricao(descricao2);
		
		dao.atualizarProduto(feedBack);
		
		Produto produto2 = new Produto();
		produto2 = dao.buscarPorDesc(descricao2);
		String valorB = produto2.getDescricao();
		
		
		assertNotEquals(valorA,valorB);
	
	}
}
