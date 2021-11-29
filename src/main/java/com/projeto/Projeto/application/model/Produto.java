package com.projeto.Projeto.application.model;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Produto {
	
	private int idproduto;
	private String nome, categoria;
	double preco;
	public Produto() {
		
	}
	
	public Produto(int idproduto, String nome, double preco,String categoria) {
		this.idproduto = idproduto;
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
	}
	
	public int getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

}
