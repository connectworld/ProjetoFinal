package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connectWorld.projeto.model.ValidaUrl;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class ValidarUrlDao {

	private Connection conexao;

	// CRIANDO O METODO DA CONEXAO
	public ValidarUrlDao() {
		try {
			conexao = new ConexaoComBanco().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<ValidaUrl> obterTelasUsuario(int nivelUsuario) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM valida_url where cod_nivelUsuario = ? ");
			stmt.setInt(1, nivelUsuario);
			ResultSet rs = stmt.executeQuery();

			List<ValidaUrl> listaValidaUrl = new ArrayList<ValidaUrl>();
			while (rs.next()) {
				listaValidaUrl.add(montarObjeto(rs));
			}

			rs.close();
			stmt.close();
			conexao.close();
			return listaValidaUrl;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private ValidaUrl montarObjeto(ResultSet rs) throws SQLException {

		ValidaUrl validaUrl = new ValidaUrl();
		validaUrl.setCod(rs.getInt("cod_valida"));
		
		NivelUsuarioDao dao = new NivelUsuarioDao();
		validaUrl.setNivelUsuario(dao.buscarPorCod(rs.getInt("cod_nivelUsuario")));
		dao.fecharBanco();
		TelasDao dao2 = new TelasDao();
		validaUrl.setTela(dao2.buscarPorCod(rs.getInt("cod_telaUsuario")));
		dao.fecharBanco();
		return validaUrl;
	}

	public boolean verificaUrl(String url, int nivelUsuario) {

		try {
			PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM telas "
					+ "inner join valida_url on(valida_url.cod_telaUsuario = telas.cod_tela) where telas.url "
					+ "like ? and valida_url.cod_nivelUsuario=? ");
			stmt.setString(1, url);
			stmt.setInt(2, nivelUsuario);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return true;
			}

			rs.close();
			stmt.close();
			conexao.close();
			return false;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void fecharBanco() throws SQLException {
		conexao.close();

	}

}
