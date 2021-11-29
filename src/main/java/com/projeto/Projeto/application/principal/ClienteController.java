package com.projeto.Projeto.application.principal;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.projeto.Projeto.application.model.Cliente;
import com.projeto.Projeto.application.model.ClienteService;



@Controller
@ComponentScan("com.projeto.Projeto.applicantion.model")
public class ClienteController {
	
	@Autowired
	private ApplicationContext context;
	
	
	@GetMapping("/cliente")
	public String formCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "cadastro";
	}
	
	@PostMapping("/cliente")
	public String postCliente(@ModelAttribute Cliente cli,
								Model model) {
		ClienteService cs = context.getBean(ClienteService.class);
		cs.inserirCliente(cli);
		return "sucesso";
	}
	
	@GetMapping("/perfil/{id}")
	public String getPerfil(@PathVariable("id") int id, Model model) {
		ClienteService cs = context.getBean(ClienteService.class);
		Map<String, Object> mapa = cs.getCliente(id);
		model.addAttribute("nome",mapa.get("nome"));
		model.addAttribute("cpf",mapa.get("cpf"));
		model.addAttribute("id",mapa.get("id"));
		return "perfil";
	}
	
	@GetMapping("/clientes")
	public String listar(Model model) {
		ClienteService cdao = context.getBean(ClienteService.class);
		List<Map<String, Object>> clientes = cdao.getClientes();
		model.addAttribute("clientes",clientes);
		return "listacliente";
	}
	
	@PostMapping("/apagar/cliente/{id}")
	public String apagarCliente(@PathVariable("id")int id) {
		ClienteService cdao = context.getBean(ClienteService.class);
		cdao.deleteCliente(id);
		return "redirect:/clientes";
	}
	
	@GetMapping("/upd/{id}")
	public String formAtualizar(@PathVariable("id") int id,
								Model model) {
		ClienteService cdao = context.getBean(ClienteService.class);
		Map<String, Object> regs = cdao.getCliente(id);
		Cliente cli = new Cliente(id, regs.get("nome").toString(), regs.get("cpf").toString());
		model.addAttribute("cliente", cli);
		model.addAttribute("id", id);
		return "formupdcliente";
	}
	
	@PostMapping("/upd/{id}")
	public String atualizarCliente(@PathVariable("id") int id,
									Model model,
									@ModelAttribute Cliente cli) {		
		ClienteService cdao = context.getBean(ClienteService.class);
		model.addAttribute("cliente", cli);
		cdao.atualizarCliente(id, cli);
		return "redirect:/clientes";
	}
	

	
}

