package com.projeto.Projeto.application.principal;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.projeto.Projeto.application.model.Imagens;
import com.projeto.Projeto.application.model.Produto;
import com.projeto.Projeto.application.model.ProdutoService;



@Controller
@ComponentScan("com.projeto.Projeto.applicantion.model")
public class ProdutoController {
	
	@Autowired
	private ApplicationContext context;
	
	
	@GetMapping("/produto")
	public String formProduto(Model model) {
		model.addAttribute("produto", new Produto());
		return "cadastroproduto";
	}
	
	@PostMapping("/produto")
	public String postProduto(@ModelAttribute Produto pro,
								Model model) {
		ProdutoService ps = context.getBean(ProdutoService.class);
		ps.inserirProduto(pro);
		return "sucesso";
	}

	
	@GetMapping("/prod/{idproduto}")
	public String getProd(@PathVariable("idproduto") int idproduto, Model model) {
		ProdutoService ps = context.getBean(ProdutoService.class);
		Map<String, Object> mapa = ps.getProduto(idproduto);
		List<Map<String, Object>> imagens = ps.getProdutoImagensByIdProduto(idproduto);
		model.addAttribute("idproduto",mapa.get("idproduto"));
		model.addAttribute("nome",mapa.get("nome"));
		model.addAttribute("preco",mapa.get("preco"));
		model.addAttribute("categoria",mapa.get("categoria"));
		model.addAttribute("imagens",imagens);
		return "prod";
	}
	
	@GetMapping("/produtos")
	public String listar(Model model) {
		ProdutoService cdao = context.getBean(ProdutoService.class);
		List<Map<String, Object>> produtos = cdao.getProdutos();
		model.addAttribute("produtos",produtos);
		return "listaprodutos";
	}
	@GetMapping("/")
	public String listarHome(Model model) {
		ProdutoService cdao = context.getBean(ProdutoService.class);
		List<Map<String, Object>> produtos = cdao.getProdutosHome();
		model.addAttribute("produtos",produtos);
		return "index";
	}
	@GetMapping("/masculino")
	public String listarMasculino(Model model) {
		ProdutoService cdao = context.getBean(ProdutoService.class);
		List<Map<String, Object>> produtos = cdao.getProdutosMasculino();
		model.addAttribute("produtos",produtos);
		return "masculino";
	}
	@GetMapping("/feminino")
	public String listarFeminino(Model model) {
		ProdutoService cdao = context.getBean(ProdutoService.class);
		List<Map<String, Object>> produtos = cdao.getProdutosFeminino();
		model.addAttribute("produtos",produtos);
		return "feminino";
	}
	
	@PostMapping("/apagar/produto/{idproduto}")
	public String apagarProduto(@PathVariable("idproduto")int idproduto) {
		ProdutoService cdao = context.getBean(ProdutoService.class);
		cdao.deleteProduto(idproduto);
		return "redirect:/produtos";
	}
	
	@GetMapping("/updp/{idproduto}")
	public String formAtualizar(@PathVariable("idproduto") int idproduto,
								Model model) {
		ProdutoService cdao = context.getBean(ProdutoService.class);
		Map<String, Object> regs = cdao.getProduto(idproduto);
		Produto pro = new Produto(idproduto, regs.get("nome").toString(),(double) regs.get("preco"), regs.get("categoria").toString());
		model.addAttribute("produto", pro);
		model.addAttribute("idproduto", idproduto);
		return "formupdproduto";
	}
	
	@PostMapping("/updp/{idproduto}")
	public String atualizarProduto(@PathVariable("idproduto") int idproduto,
									Model model,
									@ModelAttribute Produto pro) {		
		ProdutoService cdao = context.getBean(ProdutoService.class);
		model.addAttribute("produto", pro);
		cdao.atualizarProduto(idproduto, pro);
		return "redirect:/produtos";
	}
	
	
	@GetMapping("/updi/{idproduto}")
	public String imgAtualizar(@PathVariable("idproduto") int idproduto,
								Model model) {
		ProdutoService cdao = context.getBean(ProdutoService.class);
		Map<String, Object> regs = cdao.getProduto(idproduto);
		Produto pro = new Produto(idproduto, regs.get("nome").toString(),(double) regs.get("preco"), regs.get("categoria").toString());
		model.addAttribute("produto", pro);
		model.addAttribute("idproduto", idproduto);
		

		List<Map<String, Object>> imagens = cdao.getProdutoImagensByIdProduto(idproduto);
		model.addAttribute("imagens",imagens);
		return "formupdimagem";
	}
	
	@PostMapping("/updi/{idproduto}")
	public ResponseEntity<?> atualizarImagem(@PathVariable("idproduto") int idproduto,
									Model model,
									@ModelAttribute Produto pro, @RequestParam("file") MultipartFile file) {		
		String filename = file.getOriginalFilename();
		ProdutoService cdao = context.getBean(ProdutoService.class);
		model.addAttribute("produto", pro);
		cdao.atualizarImagem(idproduto, pro, filename);
		try {
			file.transferTo(new File("\\images\\"+ filename));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", ""+idproduto);    
		return new ResponseEntity<String>(headers,HttpStatus.FOUND);
	}
	
	@PostMapping("/apagar/{idimagens}")
	public String deleteImagem(@PathVariable("idimagens") int idimagens) {
		ProdutoService cdao = context.getBean(ProdutoService.class);
		cdao.deleteImagem(idimagens);
		return "redirect:/produtos";
	}

	
}
