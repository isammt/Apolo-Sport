 package com.projeto.Projeto.application.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	ProdutoDAO cdao;
	
	public void inserirProduto(Produto p) {
		cdao.inserirProduto(p);
	}
	
	public Map<String,Object> getProduto(int idproduto) {
		return cdao.getProduto(idproduto);
	}
	
	public List<Map<String, Object>> getProdutos(){
		return cdao.getProdutos();
	}
	
	public void deleteProduto(int idproduto) {
		cdao.deleteProduto(idproduto);
	}
	public void atualizarProduto(int idproduto, Produto p) {
		cdao.atualizarProduto(idproduto, p);
	}

	public void atualizarImagem(int idproduto, Produto p, String filename) {
		cdao.atualizarImagem(idproduto, p, filename);
	}

	public List<Map<String, Object>> getProdutoImagensByIdProduto(int idproduto) {
		return cdao.getProdutoImagensByIdProduto(idproduto);
	}

	public void deleteImagem(int idimagens) {
		cdao.deleteImagem(idimagens);		
	}
	
	public List<Map<String, Object>> getImagens(){
		return cdao.getImagens();
	}
	
	public List<Map<String, Object>> getProdutosHome(){
		return cdao.getProdutosHome();
	}
	public List<Map<String, Object>> getProdutosMasculino(){
		return cdao.getProdutosMasculino();
	}
	public List<Map<String, Object>> getProdutosFeminino(){
		return cdao.getProdutosFeminino();
	}
	
}
