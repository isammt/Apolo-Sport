package com.projeto.Projeto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProjetoController {
	
	@GetMapping("/")
	public String pagPrincipal() {
		return "index";
	}
	
	@GetMapping("/masculino")
	public String pagMasculino() {
		return "masculino";
	}
	
	@GetMapping("/feminino")
	public String pagFeminino() {
		return "feminino";
	}
	
	@GetMapping("/infantil")
	public String pagInfantil() {
		return "infantil";
	}
	
	@GetMapping("/sobre")
	public String pagSobre() {
		return "sobre";
	}
	
	@GetMapping("/login")
	public String pagLogin() {
		return "login";
	}
	
	@GetMapping("/cadastro")
	public String pagCadastro() {
		return "cadastro";
	}
	
	
}

