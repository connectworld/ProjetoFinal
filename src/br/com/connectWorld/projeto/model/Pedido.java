package br.com.connectWorld.projeto.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Pedido {

	private int cod;
	private Cliente cliente;
	private String situacao;
	private double valor;
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
