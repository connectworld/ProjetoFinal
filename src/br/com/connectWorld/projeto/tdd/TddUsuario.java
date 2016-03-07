package br.com.connectWorld.projeto.tdd;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import br.com.connectWorld.projeto.dao.NivelUsuarioDao;
import br.com.connectWorld.projeto.dao.ProdutoDao;
import br.com.connectWorld.projeto.dao.UsuarioDao;
import br.com.connectWorld.projeto.model.NivelUsuario;
import br.com.connectWorld.projeto.model.Produto;
import br.com.connectWorld.projeto.model.Usuario;

public class TddUsuario {
	Random gerador = new Random();
	String login = "login" + gerador.nextInt();
	
	@Test
	public void cadastrarUsuarioTeste() throws SQLException{
		
		String login = "login" + gerador.nextInt();
		Usuario usuario = new Usuario();
		NivelUsuario nivel;
		NivelUsuarioDao exemplo = new NivelUsuarioDao();
		
		UsuarioDao dao = new UsuarioDao();
		usuario.setNome("teste");
		usuario.setLogin(login);
		usuario.setEmail("jorge@gmail.com");
		usuario.setFoto("foto");
		nivel = exemplo.buscarPorCod(14);
		usuario.setNivelUsuario(nivel);
		usuario.setTelefone("081986452028");
		usuario.setSenha("081986452028");
	
		dao.salvar(usuario);
		
		Usuario feedBack = new Usuario();
		feedBack = dao.buscarPorLogin(login);
		dao.fecharBanco();
		assertEquals(feedBack.getLogin(),login);
		System.out.println(feedBack.getLogin() + "- " + login);
	}
	@Test
	public void deletarUsuarioTest() throws SQLException{
		NivelUsuarioDao exemplo = new NivelUsuarioDao();
		NivelUsuario teste;
		Usuario usuario = new Usuario();
		usuario.setNome("teste");
		usuario.setLogin(login);
		usuario.setEmail("jorge@gmail.com");
		usuario.setFoto("foto");
		teste = exemplo.buscarPorCod(14);
		usuario.setNivelUsuario(teste);
		usuario.setTelefone("081986452028");
		usuario.setSenha("081986452028");
		UsuarioDao dao = new UsuarioDao();
		dao.salvar(usuario);
		
		UsuarioDao recebe = new UsuarioDao();
		List<Usuario> feedBack = new ArrayList<>();
		feedBack = recebe.listar();
		int valorA = feedBack.size();
		recebe.deletarTdd(usuario);
		List<Usuario> feedBack2 = new ArrayList<>();
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
		
		
		assertNotEquals(valorA,valorB+1);
	
	}
}
