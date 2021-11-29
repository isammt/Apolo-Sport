package com.projeto.Projeto.application.principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProjetoController {
	
	

	
	
	@GetMapping("/sobre")
	public String pagSobre() {
		return "sobre";
	}
	
	@GetMapping("/cadastro")
	public String pagCadastro() {
		return "cadastro";
	}
	@GetMapping("/cadastroproduto")
	public String pagCadastroproduto() {
		return "cadastroproduto";
	}
	@GetMapping("/error")
	public String error() {
		return "error";
	}
	
	
}

