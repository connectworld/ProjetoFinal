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

public class ClienteDao {
	private Connection conexao;

	// CRIANDO O METODO DA CONEXAO
	public ClienteDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvar(Cliente cliente) {
		// COMANDO SQL PARA SALVAR CONTATOS
		String insert = "INSERT INTO cliente (nome,cpf,email,contato1,contato2,cep,rua,bairro,cidade,uf,numero,ibge) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		// CRIANDO VAIRAVEL QUE VAI RESPONSALVEL PELO COMANDO ACIMA
		PreparedStatement stmt;
		try {
			// INSERINDO O COMANDO SQL DENTRO DA VARIAVEL CRIANDA ACIMA
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(3, cliente.getEmail());
			stmt.setString(4, cliente.getContato1());
			stmt.setString(5, cliente.getContato2());
			stmt.setString(6, cliente.getCep());
			stmt.setString(7, cliente.getRua());
			stmt.setString(8, cliente.getBairro());
			stmt.setString(9, cliente.getCidade());
			stmt.setString(10, cliente.getUf());
			stmt.setString(11, cliente.getNumero());
			stmt.setInt(12, cliente.getIbge());
			// EXUCUTANDO O SQL
			stmt.execute();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente obterUltimoCliente() {

		try {
			PreparedStatement stmt = conexao
					.prepareStatement("SELECT * from cliente order by cod_cliente desc");
			ResultSet rs = stmt.executeQuery();

			Cliente cliente = null;
			if (rs.next()) {
				cliente = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void salvarPedido(Pedido pedido) {
		// COMANDO SQL PARA SALVAR CONTATOS
		String insert = "INSERT INTO pedido (cliente,data_pedido,valor_total,) VALUES (?,?,?)";
		// CRIANDO VAIRAVEL QUE VAI RESPONSALVEL PELO COMANDO ACIMA
		PreparedStatement stmt;
		try {
			// INSERINDO O COMANDO SQL DENTRO DA VARIAVEL CRIANDA ACIMA
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setInt(1, pedido.getCliente().getCod());
			stmt.setDate(2, new java.sql.Date(pedido.getData().getTime()));
			stmt.setDouble(3, pedido.getValor());
			// EXUCUTANDO O SQL
			stmt.execute();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> listar() {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<Cliente> listarCliente = new ArrayList<Cliente>();
			PreparedStatement stmt = this.conexao
					.prepareStatement("select * from cliente");
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Cliente cliente = null;
			while (param.next()) {
				cliente = montarObjeto(param);
				listarCliente.add(cliente);
			}
			param.close();
			stmt.close();
			conexao.close();
			return listarCliente;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente buscarPorCod(int cod) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("SELECT * FROM cliente WHERE cod_cliente= ?");
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			Cliente cliente = null;
			if (rs.next()) {
				cliente = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente buscarPorCpf(Cliente cliente) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("SELECT * FROM cliente WHERE cpf = ?");
			stmt.setString(1, cliente.getCpf());
			ResultSet rs = stmt.executeQuery();

			Cliente clienteResul = null;
			if (rs.next()) {
				clienteResul = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return clienteResul;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizarPedidoWeb(Cliente cliente) {
		String sql = "update cliente set nome=?, cpf=?, email=?, contato1=?, contato2=?, cep=?, rua=?, bairro=?, cidade=?, uf=?, numero=?, ibge=?"
				+ "where cod_cliente=?";

		try {
			PreparedStatement param = conexao.prepareStatement(sql);
			param.setString(1, cliente.getNome());
			param.setString(2, cliente.getCpf());
			param.setString(3, cliente.getEmail());
			param.setString(4, cliente.getContato1());
			param.setString(5, cliente.getContato2());
			param.setString(6, cliente.getCep());
			param.setString(7, cliente.getRua());
			param.setString(8, cliente.getBairro());
			param.setString(9, cliente.getCidade());
			param.setString(10, cliente.getUf());
			param.setString(11, cliente.getNumero());
			param.setInt(12, cliente.getIbge());
			param.setInt(13, cliente.getCod());

			param.execute();
			param.close();
			conexao.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void deletar(Cliente cliente) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("DELETE FROM cliente WHERE cod_cliente= ?");
			stmt.setInt(1, cliente.getCod());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente buscarPorNome(String nome) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("SELECT * FROM cliente WHERE nome = ?");
			stmt.setString(1, nome);
			ResultSet rs = stmt.executeQuery();
			Cliente cliente = null;
			if (rs.next()) {
				cliente = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return cliente;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente buscarPorcod(Cliente cliente) {
		try {
			PreparedStatement stmt = this.conexao
					.prepareStatement("select * from cliente where cod_cliente=?");
			stmt.setInt(1, cliente.getCod());
			ResultSet rs = stmt.executeQuery();
			Cliente clienteConsu = null;
			if (rs.next()) {
				clienteConsu = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return clienteConsu;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Cliente montarObjeto(ResultSet rs) throws SQLException {

		Cliente cliente = new Cliente();
		cliente.setCod(rs.getInt("cod_cliente"));
		cliente.setNome(rs.getString("nome"));
		cliente.setCpf(rs.getString("cpf"));
		cliente.setEmail(rs.getString("email"));
		cliente.setContato1(rs.getString("contato1"));
		cliente.setContato2(rs.getString("contato2"));
		cliente.setCep(rs.getString("cep"));
		cliente.setRua(rs.getString("rua"));
		cliente.setBairro(rs.getString("bairro"));
		cliente.setCidade(rs.getString("cidade"));
		cliente.setUf(rs.getString("uf"));
		cliente.setNumero(rs.getString("numero"));
		cliente.setIbge(rs.getInt("ibge"));

		return cliente;
	}

	public void fecharBanco() throws SQLException {
		conexao.close();

	}
}
