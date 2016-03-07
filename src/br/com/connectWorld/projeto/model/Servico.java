package br.com.connectWorld.projeto.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Servico {
	private int cod;
	private String nome;
	private String descricao;
	private double preco;
	private Usuario usuario;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date garantia;
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Date getGarantia() {
		return garantia;
	}
	public void setGarantia(Date garantia) {
		this.garantia = garantia;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}
