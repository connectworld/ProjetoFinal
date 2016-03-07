package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connectWorld.projeto.model.ItensPedido;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.Produto;
import br.com.connectWorld.projeto.model.Servico;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class ItensPedidoDao {
	private Connection conexao;
	// CRIANDO O METODO DA CONEXAO
	public ItensPedidoDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void salvar(ItensPedido itensPedido) {
		// COMANDO SQL PARA SALVAR CONTATOS
		String insert = "INSERT INTO itens_pedido (pedido,iten_servico,iten_produto,quantidade,valor) VALUES (?,?,?,?,?)";
		// CRIANDO VAIRAVEL QUE VAI RESPONSALVEL PELO COMANDO ACIMA
		PreparedStatement stmt;
		try {
			// INSERINDO O COMANDO SQL DENTRO DA VARIAVEL CRIANDA ACIMA
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setInt(1, itensPedido.getPedido().getCod());
			stmt.setInt(2,itensPedido.getServico().getCod());
			stmt.setInt(3,itensPedido.getProduto().getCod());
			stmt.setInt(4,itensPedido.getQuantidade());
			stmt.setDouble(5, itensPedido.getValorTotal());
			// EXUCUTANDO O SQL
			stmt.execute();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
public void salvarItens(int cliente, Servico servico) {
		
		String insert = "INSERT INTO itens_pedido (cod_pedidoItens,cod_servicoItens,valor_unitario) VALUES (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setInt(1, cliente);
			stmt.setInt(2, servico.getCod());
			stmt.setDouble(3, servico.getPreco());
			// EXUCUTANDO O SQL
			stmt.execute();
			// FECHANDO CONEXAO
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
public void salvarItenProduto(int cliente, Produto produto) {
	
	String insert = "INSERT INTO itens_pedido (cod_pedidoItens,cod_produtoItens,valor_unitario) VALUES (?,?,?)";
	PreparedStatement stmt;
	try {
		stmt = conexao.prepareStatement(insert);
		// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
		stmt.setInt(1, cliente);
		stmt.setInt(2, produto.getCod());
		stmt.setDouble(3, produto.getPrecoVenda());
		// EXUCUTANDO O SQL
		stmt.execute();
		// FECHANDO CONEXAO
		conexao.close();
	} catch (SQLException e) {
		throw new RuntimeException(e);
	}
}
	public List<ItensPedido> listar() {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<ItensPedido> listaritensPedido = new ArrayList<ItensPedido>();
			PreparedStatement stmt = this.conexao.prepareStatement("select * from itens_pedido");
			ResultSet param = stmt.executeQuery();

			// PECORRENDO ////////////////O ARRAY E MONTADO O OBJETO
			ItensPedido itensPedido = null;
			while (param.next()) {
				itensPedido = montarObjeto(param);
				listaritensPedido.add(itensPedido);
			}
			param.close();
			stmt.close();
			conexao.close();
			return listaritensPedido;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ItensPedido buscarPorCod(int cod) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM pedido WHERE cod_itensPedido= ?");
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			ItensPedido itensPedido = null;
			if (rs.next()) {
				itensPedido = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return itensPedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void atualizarItensPedido(ItensPedido itensPedido) {
		String sql = "update itens_pedido set pedido = ?, iten_servico=?, iten_produto, quatidade=?, valor= ?"
				+ "where cod_itensPedido=?";

		try {
			PreparedStatement param = conexao.prepareStatement(sql);
			param.setInt(1, itensPedido.getPedido().getCod());
			param.setInt(2,itensPedido.getServico().getCod());
			param.setInt(3,itensPedido.getProduto().getCod());
			param.setInt(4,itensPedido.getQuantidade());
			param.setDouble(5, itensPedido.getValorTotal());
			
			param.execute();
			param.close();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public void deletar(ItensPedido itensPedido) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM itens_pedido WHERE cod_itensPedido= ?");
			stmt.setInt(1, itensPedido.getCod());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ItensPedido buscarPorNome(String nome) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM itens_pedido WHERE valor= ?");
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			ItensPedido itensPedido= null;
			if (rs.next()) {
				itensPedido = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return itensPedido;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ItensPedido buscarPorcod(ItensPedido itensPedido) {
		try {
			PreparedStatement stmt = this.conexao.prepareStatement("select * from itens_pedido where cod_itensPedido=?");
			stmt.setInt(1, itensPedido.getCod());
			ResultSet rs = stmt.executeQuery();
			ItensPedido itensPedidoConsu = null;
			if (rs.next()) {
				itensPedidoConsu = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return itensPedidoConsu;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private ItensPedido montarObjeto(ResultSet rs) throws SQLException {

		ItensPedido itenPedido = new ItensPedido();
		itenPedido.setQuantidade(rs.getInt("quantidade"));
		itenPedido.setValorTotal(rs.getDouble("valor"));
		
		// MONTANODO O NIVEL USUÃ�RIO
		int codProduto = rs.getInt("iten_produto");
		int codServico = rs.getInt("iten_servico");
		int codPedido = rs.getInt("pedido");
		
		ServicoDao dao = new ServicoDao();
		Servico servico= dao.buscarPorCod(codServico);
		itenPedido.setServico(servico);
		
		ProdutoDao dao2 = new ProdutoDao();
		Produto produto= dao2.buscarPorCod(codProduto);
		itenPedido.setProduto(produto);
		
		PedidoDao dao3 = new PedidoDao();
		Pedido pedido = dao3.buscarPorCod(codPedido);
		itenPedido.setPedido(pedido);
		return itenPedido;
	}
	public void fecharBanco() throws SQLException {
		conexao.close();

	}
}

