package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.connectWorld.projeto.model.Produto;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class ItensPedidoProdutoDao {
	private Connection conexao;
	// CRIANDO O METODO DA CONEXAO
	public ItensPedidoProdutoDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
			System.out.println(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void salvarIten(int pedido, Produto produto, int quantidade) {
		
		String insert = "INSERT INTO itens_produto (cod_pedidoProduto,cod_itemProduto,nome_produto,quantidade,valor_unitario) VALUES (?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setInt(1, pedido);
			stmt.setInt(2, produto.getCod());
			stmt.setString(3, produto.getNome());
			stmt.setInt(4,quantidade);
			stmt.setDouble(5, produto.getPrecoVenda());
			// EXUCUTANDO O SQL
			stmt.execute();
			// FECHANDO CONEXAO
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void fecharBanco() throws SQLException {
		conexao.close();
		System.out.println(2);

	}
}
