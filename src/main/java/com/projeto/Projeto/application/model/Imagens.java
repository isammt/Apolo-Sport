package com.projeto.Projeto.application.model;

import org.springframework.data.relational.core.mapping.Column;

public class Imagens {
	
	private int idimagens;
	private String imagem_nome;
	
	public Imagens() {
		
	}
	
	public Imagens(int idimagens, String imagem_nome) {
		this.idimagens = idimagens;
		this.imagem_nome = imagem_nome;
	}
	
	public int getIdimagem() {
		return idimagens;
	}
	public void setIdimagem(int idimagens) {
		this.idimagens= idimagens;
	}
	
	public String getNomeImagem() {
		return imagem_nome;
	}
	public void setNomeImagem(String imagem_nome) {
		this.imagem_nome = imagem_nome;
	}
	
}
