package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connectWorld.projeto.model.NivelUsuario;
import br.com.connectWorld.projeto.model.Usuario;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class UsuarioDao {

	// CRIANDO A VAIRAVEL QUE IRAR RECEBER A CONEXAO
	private Connection conexao;

	// CRIANDO O METODO DA CONEXAO
	public UsuarioDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
			System.out.println(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvar(Usuario usuario) {
		// COMANDO SQL PARA SALVAR CONTATOS
		String insert = "INSERT INTO usuarios (nome,login,email,telefone,senha,nivel_usuario,foto,user_cadastrante,exclusao_logica) VALUES (?,?,?,?,md5(?),?,?,?,?)";
		// CRIANDO VAIRAVEL QUE VAI RESPONSALVEL PELO COMANDO ACIMA
		PreparedStatement stmt;
		try {
			// INSERINDO O COMANDO SQL DENTRO DA VARIAVEL CRIANDA ACIMA
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getLogin());
			stmt.setString(3, usuario.getEmail());
			stmt.setString(4, usuario.getTelefone());
			stmt.setString(5, usuario.getSenha());
			stmt.setInt(6, usuario.getNivelUsuario().getCod());
			stmt.setString(7, usuario.getFoto());
			stmt.setInt(8, usuario.getUsuario().getCod());
			stmt.setInt(9, 1);

			// EXUCUTANDO O SQL
			stmt.execute();
			// FECHANDO CONEXAO
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> listar() {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<Usuario> listarUsuario = new ArrayList<Usuario>();
			PreparedStatement stmt = this.conexao.prepareStatement("select * from usuarios where lower(nome) <> 'cliente' and login <> 'padrao' and exclusao_logica = ?");
			stmt.setInt(1, 1);
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Usuario usuario = null;
			while (param.next()) {
				usuario = montarObjeto(param);
				listarUsuario.add(usuario);
			}
			param.close();
			stmt.close();
			

			return listarUsuario;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Usuario buscarPorCod(int cod) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM usuarios WHERE cod_usuario= ?");
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			Usuario usuario = null;
			if (rs.next()) {
				usuario = montarObjeto(rs);
			}

			rs.close();
			stmt.close();
			
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizarUsuario(Usuario usuario) {
		//String sql = "update usuarios set nome=?, login = ?, email = ? where cod_usuario = ?";
		String sql = "update usuarios set nome=?, login=?, email=?, telefone=?, senha = ?, nivel_usuario=?, foto=?, user_cadastrante = ?"
				+ " where cod_usuario = ?";
		try {
			PreparedStatement param = conexao.prepareStatement(sql);
			param.setString(1, usuario.getNome());
			param.setString(2, usuario.getLogin());
			param.setString(3, usuario.getEmail());
			param.setString(4, usuario.getTelefone());
			param.setString(5, usuario.getSenha());
			param.setInt(6, usuario.getNivelUsuario().getCod());
			param.setString(7, usuario.getFoto());
			param.setInt(8, usuario.getUsuario().getCod());
			param.setInt(9, usuario.getCod());
			param.execute();
			param.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public void deletar(Usuario usuario) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM usuarios WHERE cod_usuario = ?");
			stmt.setInt(1, usuario.getCod());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletarTdd(Usuario usuario) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM usuarios WHERE login = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.execute();
			stmt.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Usuario buscarPorLogin(String login) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM usuarios WHERE login = ?");
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			System.out.println(login);
			Usuario usuario = null;
			if (rs.next()) {
				usuario = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return usuario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Usuario buscarUsuario(Usuario usuario) {
		try {
			Usuario usuarioConsultado = null;
			PreparedStatement stmt = this.conexao.prepareStatement("select * from usuarios where login = ? and senha = md5(?)");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				usuarioConsultado = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			
			return usuarioConsultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private Usuario montarObjeto(ResultSet rs) throws SQLException {

		Usuario usuario = new Usuario();
		usuario.setCod(rs.getInt("cod_usuario"));
		usuario.setNome(rs.getString("nome"));
		usuario.setLogin(rs.getString("login"));
		usuario.setEmail(rs.getString("email"));
		usuario.setTelefone(rs.getString("telefone"));
		usuario.setSenha(rs.getString("senha"));
		usuario.setFoto(rs.getString("foto"));

		// MONTANODO O NIVEL USUÃ�RIO
		int cod = rs.getInt("nivel_usuario");
		NivelUsuarioDao dao = new NivelUsuarioDao();
		NivelUsuario nivelUsuario = dao.buscarPorCod(cod);
		usuario.setNivelUsuario(nivelUsuario);
		dao.fecharBanco();
		
		int userCadastrante = rs.getInt("user_cadastrante");
		UsuarioDao dao2 = new UsuarioDao();
		Usuario usuarioCadas = dao2.buscarPorCod(userCadastrante);
		usuario.setUsuario(usuarioCadas);
		dao2.fecharBanco();

		return usuario;
	}

	public void fecharBanco() throws SQLException {
		conexao.close();
		System.out.println(2);

	}

}
