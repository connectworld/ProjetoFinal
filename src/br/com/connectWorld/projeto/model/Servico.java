package br.com.connectWorld.projeto.model;

import java.util.Date;

//import org.springframework.format.annotation.DateTimeFormat;//

public class Servico {
	private int cod;
	private String nome;
	private String descricao;
	private double preco;
	private Usuario usuario;
	
	//@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date garantia2;
	
	public Date getGarantia2() {
		return garantia2;
	}
	public void setGarantia2(Date garantia2) {
		this.garantia2 = garantia2;
	}
	private String garantia;
	
	public String getGarantia() {
		return garantia;
	}
	public void setGarantia(String garantia) {
		this.garantia = garantia;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
