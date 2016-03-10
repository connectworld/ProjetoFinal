package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connectWorld.projeto.model.ItensPedidoServico;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.Servico;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class ItensPedidoServicoDao {
	private Connection conexao;
	// CRIANDO O METODO DA CONEXAO
	public ItensPedidoServicoDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
			System.out.println(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
public void salvarItens(int pedido, Servico servico) {
		
		String insert = "INSERT INTO itens_servico (cod_pedidoServico,item_servico,valor_unitario) VALUES (?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setInt(1, pedido);
			stmt.setInt(2, servico.getCod());
			stmt.setDouble(3, servico.getPreco());
			// EXUCUTANDO O SQL
			stmt.execute();
			// FECHANDO CONEXAO
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public ItensPedidoServico buscarItemPorCod(int codServico, int codPedido) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM itens_ WHERE item_servico = ? and cod_pedidoServico = ?");
			stmt.setInt(1, codServico);
			stmt.setInt(1, codPedido);
			ResultSet rs = stmt.executeQuery();

			ItensPedidoServico itensPedido = null;
			if (rs.next()) {
				itensPedido = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return itensPedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void deletar(ItensPedidoServico itensPedido) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM itens_servico WHERE cod_pedidoServico= ?");
			stmt.setInt(1, itensPedido.getPedido().getCod());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List <ItensPedidoServico> buscarItemPorPedido(ItensPedidoServico itensPedido) {
		try {
			PreparedStatement stmt = this.conexao.prepareStatement("select * from itens_servico where cod_pedidoServico = ? ");
			stmt.setInt(1, itensPedido.getPedido().getCod());
			ResultSet rs = stmt.executeQuery();
			List<ItensPedidoServico> listaritensPedido = new ArrayList<ItensPedidoServico>();
			ItensPedidoServico itensPedidoConsul = null;
			if (rs.next()) {
				itensPedidoConsul = montarObjeto(rs);
				 listaritensPedido.add(itensPedidoConsul);
			}
			rs.close();
			stmt.close();
			
			return  listaritensPedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private ItensPedidoServico montarObjeto(ResultSet rs) throws SQLException {

		ItensPedidoServico itenPedido = new ItensPedidoServico();
		
		int codServico = rs.getInt("item_servico");
		int codPedido = rs.getInt("cod_pedidoServico");
		
		ServicoDao dao = new ServicoDao();
		Servico servico= dao.buscarPorCod(codServico);
		itenPedido.setServico(servico);
		dao.fecharBanco();
		
		PedidoDao dao3 = new PedidoDao();
		Pedido pedido = dao3.buscarPorCod(codPedido);
		itenPedido.setPedido(pedido);
		dao3.fecharBanco();
		return itenPedido;
	}
	public void fecharBanco() throws SQLException {
		conexao.close();
		System.out.println(2);

	}
}

