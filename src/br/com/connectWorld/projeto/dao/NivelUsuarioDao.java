package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connectWorld.projeto.model.NivelUsuario;
import br.com.connectWorld.projeto.model.Telas;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class NivelUsuarioDao {
	// CRIANDO A VAIRAVEL QUE IRAR RECEBER A CONEXAO
	private Connection conexao;

	// CRIANDO O METODO DA CONEXAO
	public NivelUsuarioDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void salvar(NivelUsuario nivelUsuario) {
		// COMANDO SQL PARA SALVAR CONTATOS
		String insert = "INSERT INTO nivel_user (nome,descricao) VALUES (?,?)";
		// CRIANDO VAIRAVEL QUE VAI RESPONSALVEL PELO COMANDO ACIMA
		PreparedStatement stmt;
		try {
			// INSERINDO O COMANDO SQL DENTRO DA VARIAVEL CRIANDA ACIMA
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setString(1, nivelUsuario.getNome());
			stmt.setString(2, nivelUsuario.getDescricao());

			// EXUCUTANDO O SQL
			stmt.execute();
			int codNivel = obterUltimoPerfil();
			for (Telas tela : nivelUsuario.getTelas()) {
				salvarValidaUrl(codNivel, tela);
			}
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void salvarValidaUrl(int codNivel, Telas tela) {
		
		String insert = "INSERT INTO valida_url (cod_nivelUsuario,cod_telaUsuario) VALUES (?,?)";
		PreparedStatement stmt;
		try {
			stmt = conexao.prepareStatement(insert);
			// INSERINDO OS DADOS DENTR DA VARIAVEL STMT
			stmt.setInt(1, codNivel);
			stmt.setInt(2, tela.getCod());

			// EXUCUTANDO O SQL
			stmt.execute();
			// FECHANDO CONEXAO
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public int obterUltimoPerfil() {

		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT cod_nivel from nivel_user order by cod_nivel desc");
			ResultSet rs = stmt.executeQuery();

			int codNivel = 0;
			if (rs.next()) {
				codNivel = rs.getInt("cod_nivel");
			}
			rs.close();
			stmt.close();
			conexao.close();
			return codNivel;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<NivelUsuario> listar() {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<NivelUsuario> listarNivelUsuario = new ArrayList<NivelUsuario>();
			PreparedStatement stmt = this.conexao.prepareStatement("select * from nivel_user");
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			NivelUsuario nivelUsuario = null;
			while (param.next()) {
				nivelUsuario = montarObjeto(param);
				listarNivelUsuario.add(nivelUsuario);
			}
			param.close();
			stmt.close();
			conexao.close();
			return listarNivelUsuario;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// METODO QUE ATUALIZA CONTATOS DO BANCO DE DADOS
	public void atualizarNivelUsuario(NivelUsuario nivelUsuario) {
		String sql = "update nivel_user set nome = ?, descricao=? where cod_nivel = ?";
		try {
			PreparedStatement param = conexao.prepareStatement(sql);
			param.setString(1, nivelUsuario.getNome());
			param.setString(2, nivelUsuario.getDescricao());
			param.setInt(3, nivelUsuario.getCod());

			param.execute();
			param.close();
			conexao.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	// METODO QUE DELETA CONTATOS
	public void deletar(NivelUsuario nivelUsuario) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("DELETE FROM nivel_user WHERE cod_nivel = ?");
			stmt.setInt(1, nivelUsuario.getCod());
			stmt.execute();
			stmt.close();
			conexao.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public NivelUsuario buscarPorCod(int cod) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM nivel_user WHERE cod_nivel = ?");
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			NivelUsuario nivelUsuario = null;
			if (rs.next()) {
				nivelUsuario = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return nivelUsuario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// METODO DE TESTE DO JUNIT
	public NivelUsuario buscarPorDesc(String descricao) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM nivel_user WHERE descricao = ?");
			stmt.setString(1, descricao);
			ResultSet rs = stmt.executeQuery();

			NivelUsuario nivelUsuario = null;
			if (rs.next()) {
				nivelUsuario = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return nivelUsuario;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Montar Objeto
	private NivelUsuario montarObjeto(ResultSet rs) throws SQLException {

		NivelUsuario nivelUsuario = new NivelUsuario();
		nivelUsuario.setCod(rs.getInt("cod_nivel"));
		nivelUsuario.setNome(rs.getString("nome"));
		nivelUsuario.setDescricao(rs.getString("descricao"));
		return nivelUsuario;
	}

	public void fecharBanco() throws SQLException {
		conexao.close();
	}
}
