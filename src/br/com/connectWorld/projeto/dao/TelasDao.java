package br.com.connectWorld.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.connectWorld.projeto.model.Telas;
import br.com.connectWorld.projeto.util.ConexaoComBanco;

public class TelasDao {
	// CRIANDO A VAIRAVEL QUE IRAR RECEBER A CONEXAO
	private Connection conexao;
	// CRIANDO O METODO DA CONEXAO
	public TelasDao() {
		try {
			this.conexao = new ConexaoComBanco().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Telas buscarPorCod(int cod) {
		try {
			PreparedStatement stmt = conexao
					.prepareStatement("SELECT * FROM telas WHERE cod_tela = ?");
			stmt.setInt(1, cod);
			ResultSet rs = stmt.executeQuery();

			Telas tela = null;
			if (rs.next()) {
				tela = montarObjeto(rs);
			}
			rs.close();
			stmt.close();
			conexao.close();
			return tela;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Telas> listar() {
		try {
			// CRIANDO UM ARRAY LISTA PARA GUARDAR OS DADOS PARA PODEREM
			// SER APRESENTADOS
			List<Telas> listarTelas = new ArrayList<Telas>();
			PreparedStatement stmt = this.conexao
					.prepareStatement("select * from telas");
			ResultSet param = stmt.executeQuery();

			// PECORRENDO O ARRAY E MONTADO O OBJETO
			Telas tela = null;
			while (param.next()) {
				tela = montarObjeto(param);
				listarTelas.add(tela);
			}
			param.close();
			stmt.close();
			conexao.close();
			return listarTelas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Telas montarObjeto(ResultSet rs) throws SQLException {
		Telas tela = new Telas();
		tela.setCod(rs.getInt("cod_tela"));
		tela.setUrl(rs.getString("url"));
		tela.setDescricao(rs.getString("descricao"));
		
		return tela;
	}
	public void fecharBanco() throws SQLException {
		conexao.close();
	}
}
