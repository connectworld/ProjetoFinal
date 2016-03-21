package br.com.connectWorld.projeto.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RelatorioPedido {
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicial;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFinal;
	private String situacao;
	private int codigoPedido;
	
	public int getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	

}
