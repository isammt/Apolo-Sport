package com.projeto.Projeto.application.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoDAO {

	@Autowired
	DataSource dataSource;

	JdbcTemplate jdbc;

	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}

	public void inserirProduto(Produto produto) {
		String sql = "INSERT INTO produto(nome,categoria,preco)" + " VALUES (?,?,?)";
		Object[] obj = new Object[3];
		obj[0] = produto.getNome();
		obj[1] = produto.getCategoria();
		obj[2] = produto.getPreco();
		jdbc.update(sql, obj);
	}

	public Map<String, Object> getProduto(int idproduto) {
		String sql = "SELECT * FROM produto WHERE produto.idproduto = ?";
		Object[] obj = new Object[1];
		obj[0] = idproduto;
		return jdbc.queryForMap(sql, obj);
	}

	public List<Map<String, Object>> getProdutos() {
		String sql = "SELECT * FROM produto";
		List<Map<String, Object>> produtos = (List<Map<String, Object>>) jdbc.queryForList(sql);
		return produtos;
	}

	public void deleteProduto(int idproduto) {
		String sql = "DELETE FROM produto WHERE idproduto = ?";
		Object[] obj = new Object[1];
		obj[0] = idproduto;
		jdbc.update(sql, obj);
	}

	public void atualizarProduto(int idproduto, Produto produto) {
		String sql = "UPDATE produto SET nome = ?, categoria = ?, preco = ? WHERE idproduto = ?";
		Object[] obj = new Object[4];
		obj[0] = produto.getNome();
		obj[1] = produto.getCategoria();
		obj[2] = produto.getPreco();
		obj[3] = produto.getIdproduto();
		jdbc.update(sql, obj);
	}
	
	public void atualizarImagem(int idproduto, Produto produto, String filename) {
		String sql = "INSERT INTO imagens (imagem_nome,produto_idproduto) VALUES (?,?)";
		Object[] obj = new Object[2];
		obj[0] = filename;
		obj[1] = idproduto;
		jdbc.update(sql, obj);
	}	
	
	public List<Map<String, Object>> getProdutoImagensByIdProduto(int idproduto) {
		String sql = "SELECT * FROM imagens WHERE produto_idproduto = ?";
		Object[] obj = new Object[1];
		obj[0] = idproduto;
		List<Map<String, Object>> imagens = (List<Map<String, Object>>) jdbc.queryForList(sql,obj);
		return imagens;
	}
	
	public void deleteImagem(int idimagens) {
		String sql = "DELETE FROM imagens WHERE idimagens = ?";
		Object[] obj = new Object[1];
		obj[0] = idimagens;
		jdbc.update(sql, obj);
	}
	
	public List<Map<String, Object>> getImagens() {
		String sql = "SELECT * FROM imagens";
		List<Map<String, Object>> imagens = (List<Map<String, Object>>) jdbc.queryForList(sql);
		return imagens;
	}

	public List<Map<String, Object>> getProdutosHome() {
		String sql = "SELECT * FROM produto LEFT JOIN imagens C ON C.idimagens = (SELECT MIN(idimagens) FROM imagens WHERE produto_idproduto = idproduto);";
		List<Map<String, Object>> produtosimagens = (List<Map<String, Object>>) jdbc.queryForList(sql);
		return produtosimagens;

	}
	
	public List<Map<String, Object>> getProdutosMasculino() {
		String sql = "SELECT * FROM produto LEFT JOIN imagens C ON C.idimagens = (SELECT MIN(idimagens) FROM imagens WHERE produto_idproduto = idproduto) WHERE categoria = 'masculino';";
		List<Map<String, Object>> produtosmasculino = (List<Map<String, Object>>) jdbc.queryForList(sql);
		return produtosmasculino;

	}
	public List<Map<String, Object>> getProdutosFeminino() {
		String sql = "SELECT * FROM produto LEFT JOIN imagens C ON C.idimagens = (SELECT MIN(idimagens) FROM imagens WHERE produto_idproduto = idproduto) WHERE categoria = 'feminino';";
		List<Map<String, Object>> produtosfeminino = (List<Map<String, Object>>) jdbc.queryForList(sql);
		return produtosfeminino;

	}
}