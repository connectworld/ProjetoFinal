package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import br.com.connectWorld.projeto.model.Cliente;
import br.com.connectWorld.projeto.model.Pedido;
import br.com.connectWorld.projeto.model.RelatorioPedido;
import br.com.connectWorld.projeto.model.Usuario;
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
		String insert = "INSERT INTO pedido (cliente,data_pedido,valor_total,situacao,flag_tipo,user_autor,exclusao_logica) VALUES (?,?,?,?,?,?,?)";
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
			stmt.setInt(5, pedido.getTipo());
			stmt.setInt(6, pedido.getCodigo().getCod());
			stmt.setInt(7, 1);
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
			PreparedStatement stmt = this.conexao.prepareStatement("select * from pedido where exclusao = ?");
			stmt.setInt(1, 1);
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
	
	public List<Pedido> buscarPorSituacaoA(RelatorioPedido relatorio) {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			
			System.out.println(new java.sql.Date(relatorio.getDataInicial2().getTime()));
			System.out.println(new java.sql.Date(relatorio.getDataFinal2().getTime()));
			
			List<Pedido> listarPedido = new ArrayList<Pedido>();
			PreparedStatement stmt = this.conexao.prepareStatement("select * from pedido where situacao = ? and flag_tipo = ? and exclusao_logica = ? and data_pedido between (?) and (?)");
			stmt.setString(1, relatorio.getSituacao());
			stmt.setInt(2, 0);
			stmt.setInt(3, 1);
			stmt.setDate(4, new java.sql.Date(relatorio.getDataInicial2().getTime()));
			stmt.setDate(5, new java.sql.Date(relatorio.getDataFinal2().getTime()));
			System.out.println(stmt.toString());
			
			ResultSet param = stmt.executeQuery();
			
			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Pedido pedidoConsultado = null;
			while (param.next()) {
				pedidoConsultado = montarObjeto(param);
				listarPedido.add(pedidoConsultado);
			}
			param.close();
			stmt.close();
			
			return listarPedido;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Pedido> buscarPorSituacaoB(RelatorioPedido relatorio) {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<Pedido> listarPedido = new ArrayList<Pedido>();
			PreparedStatement stmt = this.conexao.prepareStatement("select * from pedido where situacao = ? and flag_tipo = ? and exclusao_logica = ? and data_pedido between (?) and (?)");
			stmt.setString(1, relatorio.getSituacao());
			stmt.setInt(2, 0);
			stmt.setInt(3, 1);
			stmt.setDate(4, new java.sql.Date(relatorio.getDataInicial2().getTime()));
			stmt.setDate(5, new java.sql.Date(relatorio.getDataFinal2().getTime()));
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Pedido pedidoConsultado = null;
			while (param.next()) {
				pedidoConsultado = montarObjeto(param);
				listarPedido.add(pedidoConsultado);
			}
			param.close();
			stmt.close();
			
			return listarPedido;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Pedido> buscarPorSituacaoC(RelatorioPedido relatorio) {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<Pedido> listarPedido = new ArrayList<Pedido>();
			PreparedStatement stmt = this.conexao.prepareStatement("select * from pedido where situacao = ? and flag_tipo = ? and exclusao_logica = ? and data_pedido between (?) and (?)");
			stmt.setString(1, relatorio.getSituacao());
			stmt.setInt(2, 0);
			stmt.setInt(3, 1);
			stmt.setDate(4, new java.sql.Date(relatorio.getDataInicial2().getTime()));
			stmt.setDate(5, new java.sql.Date(relatorio.getDataFinal2().getTime()));
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Pedido pedidoConsultado = null;
			while (param.next()) {
				pedidoConsultado = montarObjeto(param);
				listarPedido.add(pedidoConsultado);
			}
			param.close();
			stmt.close();
			
			return listarPedido;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Pedido> buscarPorData(RelatorioPedido relatorio) {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<Pedido> listarPedido = new ArrayList<Pedido>();
			PreparedStatement stmt = this.conexao.prepareStatement("select * from pedido where situacao = ? flag_tipo = ? and exclusao_logica = ? and data_pedido between ? and ?");
			stmt.setString(1, relatorio.getSituacao());
			stmt.setInt(2, 0);
			stmt.setInt(3, 1);
			stmt.setDate(4, new java.sql.Date(relatorio.getDataInicial2().getTime()));
			stmt.setDate(5, new java.sql.Date(relatorio.getDataFinal2().getTime()));
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Pedido pedidoConsultado = null;
			while (param.next()) {
				pedidoConsultado = montarObjeto(param);
				listarPedido.add(pedidoConsultado);
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
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM pedido WHERE cod_pedido= ? e exclusao_logica = ?");
			stmt.setInt(1, cod);
			stmt.setInt(1, 1);
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
			PreparedStatement stmt = conexao.prepareStatement("update pedido set exclusao_logica = ? where cod_pedido = ?");
			stmt.setInt(1, 0);
			stmt.setInt(2, pedido.getCod());
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
			PreparedStatement stmt = this.conexao.prepareStatement("select * from pedido where cod_pedido=? and exclusao_logica = ?");
			stmt.setInt(1, pedido.getCod());
			stmt.setInt(1, 1);
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
	
	public List <Pedido> buscarClientePedido(Cliente cliente){
		try {
			PreparedStatement stmt = this.conexao
					.prepareStatement("select * from pedido where cliente =? and situacao = ?");
			stmt.setInt(1, cliente.getCod());
			stmt.setString(2, "A");
			ResultSet rs = stmt.executeQuery();
			Pedido pedido= null;
			List <Pedido> listaPedido = new ArrayList<Pedido>();
			if (rs.next()) {
				pedido = montarObjeto(rs);
				listaPedido.add(pedido);
			}
			rs.close();
			stmt.close();
			
			return listaPedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	private Pedido montarObjeto(ResultSet rs) throws SQLException {

		Pedido pedido = new Pedido();
		pedido.setCod(rs.getInt("cod_pedido"));
		pedido.setValor(rs.getDouble("valor_total"));
		pedido.setData(rs.getDate("data_pedido"));
		pedido.setSituacao(rs.getString("situacao"));
		
		// MONTANODO O NIVEL USUÃ�RIO
		int cod = rs.getInt("cliente");
		ClienteDao dao = new ClienteDao();
		Cliente cliente= dao.buscarPorCod(cod);
		pedido.setCliente(cliente);	
		dao.fecharBanco();
		
		int codi = rs.getInt("user_autor");
		UsuarioDao dao2 = new UsuarioDao();
		Usuario usuario= dao2.buscarPorCod(codi);
		pedido.setCodigo(usuario);	
		dao2.fecharBanco();
		
		return pedido;
	}
	public void fecharBanco() throws SQLException {
		conexao.close();
		System.out.println(2);

	}
	
}


