package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connectWorld.projeto.model.Cliente;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class PedidoDao {
	private Connection conexao;
	// CRIANDO O METODO DA CONEXAO
	public PedidoDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
			System.out.println(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void salvar(Pedido pedido) {
		// COMANDO SQL PARA SALVAR CONTATOS
		String insert = "INSERT INTO pedido (cliente,data_pedido,valor_total,situacao) VALUES (?,?,?,?)";
		// CRIANDO VAIRAVEL QUE VAI RESPONSALVEL PELO COMANDO ACIMA
		PreparedStatement stmt;
		try {
			// INSERINDO O COMANDO SQL DENTRO DA VARIAVEL CRIANDA ACIMA
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setInt(1, pedido.getCliente().getCod());
			stmt.setDate(2, new java.sql.Date(pedido.getData().getTime()));
			stmt.setDouble(3, pedido.getValor());
			stmt.setString(4, pedido.getSituacao());
			// EXUCUTANDO O SQL
			stmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Pedido obterUltimoPedido() {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * from pedido order by cod_pedido desc");
			ResultSet rs = stmt.executeQuery();

			Pedido pedido= null;
			if (rs.next()) {
				pedido = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return pedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Pedido> listar() {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<Pedido> listarPedido = new ArrayList<Pedido>();
			PreparedStatement stmt = this.conexao.prepareStatement("select * from pedido");
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Pedido pedido = null;
			while (param.next()) {
				pedido = montarObjeto(param);
				listarPedido.add(pedido);
			}
			param.close();
			stmt.close();
			
			return listarPedido;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Pedido buscarPorCod(int cod) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM pedido WHERE cod_pedido= ?");
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			Pedido pedido = null;
			if (rs.next()) {
				pedido = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return pedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void atualizarPedidoWeb(Pedido pedido) {
		String sql = "update pedido set cliente = ?, data_pedido =?, valor_total=?"
				+ "where cod_pedido=?";

		try {
			PreparedStatement param = conexao.prepareStatement(sql);
			param.setInt(1, pedido.getCliente().getCod());
			param.setDate(2, new java.sql.Date(pedido.getData().getTime()));
			param.setDouble(3, pedido.getValor());
			
			param.execute();
			param.close();
			

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public void deletar(Pedido pedido) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM pedido WHERE cod_pedido= ?");
			stmt.setInt(1, pedido.getCod());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Pedido buscarPorNome(String nome) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM pedido WHERE valor= ?");
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			Pedido pedido= null;
			if (rs.next()) {
				pedido = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return pedido;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Pedido buscarPorcod(Pedido pedido) {
		try {
			PreparedStatement stmt = this.conexao.prepareStatement("select * from pedido where cod_pedido=?");
			stmt.setInt(1, pedido.getCod());
			ResultSet rs = stmt.executeQuery();
			Pedido pedidoConsu = null;
			if (rs.next()) {
				pedidoConsu = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return pedidoConsu;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private Pedido montarObjeto(ResultSet rs) throws SQLException {

		Pedido pedido = new Pedido();
		pedido.setCod(rs.getInt("cod_pedido"));
		pedido.setValor(rs.getDouble("valor_total"));
		
		// MONTANODO O NIVEL USUÃ�RIO
		int cod = rs.getInt("cliente");
		ClienteDao dao = new ClienteDao();
		Cliente cliente= dao.buscarPorCod(cod);
		pedido.setCliente(cliente);	
		dao.fecharBanco();
		
		return pedido;
	}
	public void fecharBanco() throws SQLException {
		conexao.close();
		System.out.println(2);

	}
}


