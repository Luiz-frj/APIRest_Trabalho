
/*
CREATE DATABASE Transacao;

USE Transacao;

CREATE TABLE Transacao_info(
	id INTEGER AUTO_INCREMENT UNIQUE,
    descricao VARCHAR(200) NOT NULL,
    valor DOUBLE NOT NULL,
    tipo BOOLEAN NOT NULL,
    categoria VARCHAR(200) NOT NULL,
    data_transacao DATE NOT NULL
);
*/


package br.edu.ifsp.RestAPI.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transacao {

	private int id;
	private String descricaoTransacao;
	private double valor;
	private boolean tipoTransacao;
	private String categoriaTransacao;
	private LocalDate dataTransacao;
	
	public Transacao() {}
	
	public Transacao(int id, String descricao, double valor, boolean tipoTransacao, String categoriaTransacao, LocalDate data) {
		this.id = id;
		this.descricaoTransacao = descricao;
		this.valor = valor;
		this.tipoTransacao = tipoTransacao;
		this.categoriaTransacao = categoriaTransacao;
		this.dataTransacao = data;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescricaoTransacao() {
		return descricaoTransacao;
	}
	
	public void setDescricaoTransacao(String descricaoTransacao) {
		this.descricaoTransacao = descricaoTransacao;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	public boolean isTipoTransacao() {
		return tipoTransacao;
	}
	
	public void setTipoTransacao(boolean tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
	
	public String getCategoriaTransacao() {
		return categoriaTransacao;
	}
	
	public void setCategoriaTransacao(String categoriaTransacao) {
		this.categoriaTransacao = categoriaTransacao;
	}
	
	public LocalDate getDataTransacao() {
		return dataTransacao;
	}
	
	public static LocalDate getDataString(String data) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy");
		return LocalDate.parse(data, format);
	}
	
	//inserir no banco de dados
	public String getDate() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy");
		return dataTransacao.format(format);
	}
	
	public void setDataTransacao(LocalDate dataTransacao) {
		this.dataTransacao = dataTransacao;
	}
	
	@Override
	public String toString() {
		return "Transacao[ID =" + id + ", Descrição = " + descricaoTransacao + ", Valor = " + valor + 
				", Tipo = " + tipoTransacao + ", Categoria = " + categoriaTransacao + ", Data = " + dataTransacao;
	}
}
