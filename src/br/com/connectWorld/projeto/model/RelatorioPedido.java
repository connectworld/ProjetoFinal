package br.com.connectWorld.projeto.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class RelatorioPedido {
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicial2;
	
	private String dataInicial;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFinal2;
	
	private String dataFinal;
	
	private String situacao;
	//private int codigoPedido;
	
	/*public int getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(int codigoPedido) {
		this.codigoPedido = codigoPedido;
	}*/
	public String getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	public String getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Date getDataInicial2() {
		return dataInicial2;
	}
	public void setDataInicial2(Date dataInicial2) {
		this.dataInicial2 = dataInicial2;
	}
	public Date getDataFinal2() {
		return dataFinal2;
	}
	public void setDataFinal2(Date dataFinal2) {
		this.dataFinal2 = dataFinal2;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
	

}
