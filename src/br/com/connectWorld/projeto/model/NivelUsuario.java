package br.com.connectWorld.projeto.model;

import java.util.List;

public class NivelUsuario {
	
	private int cod;
	private String descricao,nome;
	private List <Telas> telas;
	
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Telas> getTelas() {
		return telas;
	}
	public void setTelas(List<Telas> telas) {
		this.telas = telas;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
