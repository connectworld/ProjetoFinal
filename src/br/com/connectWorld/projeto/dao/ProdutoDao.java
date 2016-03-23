package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connectWorld.projeto.model.Produto;
import br.com.connectWorld.projeto.model.Usuario;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class ProdutoDao {
	// CRIANDO A VAIRAVEL QUE IRAR RECEBER A CONEXAO
	private Connection conexao;
	// CRIANDO O METODO DA CONEXAO
	public ProdutoDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
			System.out.println(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void salvar(Produto produto) {
		// COMANDO SQL PARA SALVAR CONTATOS
		String insert = "INSERT INTO produtos (nome,descricao,preco_venda,quantidade,imagem,user_cadastrante,exclusao_logica) VALUES (?,?,?,?,?,?,?)";
		// CRIANDO VAIRAVEL QUE VAI RESPONSALVEL PELO COMANDO ACIMA
		PreparedStatement stmt;
		try {
			// INSERINDO O COMANDO SQL DENTRO DA VARIAVEL CRIANDA ACIMA
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setString(1, produto.getNome());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPrecoVenda());
			stmt.setInt(4, produto.getQuantidade());
			stmt.setString(5, produto.getImagem());
			stmt.setInt(6, produto.getUsuario().getCod());
			stmt.setInt(7, 1);
			// EXUCUTANDO O SQL
			stmt.execute();
			// FECHANDO CONEXAO
			
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
	}

	public List<Produto> listar() {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<Produto> listarProduto = new ArrayList<Produto>();
			PreparedStatement stmt = this.conexao
					.prepareStatement("select * from produtos where exclusao_logica = ?");
			stmt.setInt(1,1);
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Produto produto = null;
			while (param.next()) {
				produto = montarObjeto(param);
				listarProduto.add(produto);
			}
			param.close();
			stmt.close();
			
			return listarProduto;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// METODO QUE ATUALIZA CONTATOS DO BANCO DE DADOS
	public void atualizarProduto(Produto produto) {
		String sql = "update produtos set descricao=?, nome=?, preco_venda=?, quantidade=?, imagem=?,user_cadastrante = ?"
				+ " where cod_produto=?";

		try {
			PreparedStatement param = conexao.prepareStatement(sql);
			param.setString(1, produto.getDescricao());
			param.setString(2, produto.getNome());
			param.setDouble(3, produto.getPrecoVenda());
			param.setInt(4, produto.getQuantidade());
			param.setString(5, produto.getImagem());
			param.setInt(6, produto.getUsuario().getCod());
			param.setInt(7, produto.getCod());

			param.execute();
			param.close();
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// METODO QUE DELETA CONTATOS
	public void deletar(Produto produto) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("update produtos set exclusao_logica = ? where cod_produto = ?");
			stmt.setInt(1, 0);
			stmt.setInt(2, produto.getCod());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void deletarTdd(Produto produto) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("DELETE FROM produtos WHERE descricao = ?");
			stmt.setString(1, produto.getDescricao());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Produto buscarPorCod(int cod) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("SELECT * FROM produtos WHERE cod_produto = ? and exclusao_logica = ?");
			stmt.setInt(1, cod);
			stmt.setInt(2, 1);
			ResultSet rs = stmt.executeQuery();

			Produto produto = null;
			if (rs.next()) {
				produto = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return produto;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// METODO DE TESTE DO JUNIT
	public Produto buscarPorDesc(String descricao) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("SELECT * FROM produtos WHERE descricao = ?");
			stmt.setString(1, descricao);
			ResultSet rs = stmt.executeQuery();

			Produto produto = null;
			if (rs.next()) {
				produto = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return produto;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	// Montar Objeto
	private Produto montarObjeto(ResultSet rs) throws SQLException {

		Produto produto = new Produto();
		produto.setCod(rs.getInt("cod_produto"));
		produto.setNome(rs.getString("nome"));
		produto.setDescricao(rs.getString("descricao"));
		produto.setPrecoVenda(rs.getDouble("preco_venda"));
		produto.setQuantidade(rs.getInt("quantidade"));
		produto.setImagem(rs.getString("imagem"));

		// MONTANODO USU�RIO
		int cod = rs.getInt("user_cadastrante");
		UsuarioDao dao = new UsuarioDao();
		Usuario usuario = dao.buscarPorCod(cod);
		produto.setUsuario(usuario);
		dao.fecharBanco();
		
		return produto;
	}
	public void fecharBanco() throws SQLException {
		conexao.close();
		System.out.println(2);
	}

}