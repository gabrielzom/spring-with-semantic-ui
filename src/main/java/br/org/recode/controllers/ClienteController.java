package br.org.recode.controllers;

import br.org.recode.services.ClienteImageWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import br.org.recode.models.Cliente;
import br.org.recode.persistence.ClienteDao;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

//@EnableWebSecurity
//@EnableAuthorizationServer
//@EnableResourceServer
@Controller @RequestMapping("/Clientes")
public class ClienteController {
	@Autowired
	private ClienteDao clienteDao;
	
	@Value("${api.imgur.client.id}")
	private String clientId;
	
	
	@GetMapping("/Cadastro")
	public String cadastrar(Cliente cliente) {
		return "cliente/cadastro";
	}

	@PostMapping("/Cadastro/Salvar")
	public String salvarCadastro(Cliente cliente, ModelMap modelMap, @RequestParam("imagem") MultipartFile image) {
		try {
			if (!image.isEmpty()) {
				String [] imageData = ClienteImageWebService.uploadImageAndGetData(image, this.clientId);
				cliente.setImageUrl(imageData[0]);
				cliente.setImageDeleteHash(imageData[1]);
			}

		} catch (IOException e) {
			System.out.println("-- ERRO AO INSERIR FOTO. " + e.getMessage());
		}

		try {
			clienteDao.create(cliente);
			return "redirect:/Clientes/Lista";

		} catch (DataIntegrityViolationException e) {
			System.out.println("-- EXCEPTION: " + e.getMessage());
			modelMap.addAttribute("msg", "Dados de inserção faltando e/ou muito longos.");
			return "cliente/cadastro";
		}
	}

	@GetMapping("/Lista")
	public String listar(ModelMap modelMap) {
		modelMap.addAttribute("clientes", clienteDao.findAll());
		return "cliente/lista";
	}

	@GetMapping("/Editar/{id}")
	public String atualizar(@PathVariable("id") Long id, ModelMap modelMap) {
		modelMap.addAttribute("cliente", clienteDao.findByPk(id));
		return "cliente/atualizar";
	}

	@PostMapping("/Editar/Confirmar")
	public String confirmarAtualizacao(Cliente cliente) {
		clienteDao.update(cliente);
		return "redirect:/Clientes/Lista";
	}

	@GetMapping("/Excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap modelMap, String msg) {
		modelMap.addAttribute("msg", msg);
		modelMap.addAttribute("cliente",clienteDao.findByPk(id));
		return "cliente/excluir";
	}

	@PostMapping("/Excluir/Confirmar/{id}")
	public String confirmarExclusao(@PathVariable("id") Long id, ModelMap modelMap) {

		Cliente cliente = clienteDao.findByPk(id);

		if (ClienteImageWebService.deleteImage(cliente.getImageDeleteHash(), clientId).equals("Error")) {
			return excluir(id, modelMap, "Não foi possível excluir o cliente. Contate o suporte técnico.");

		} else {
			clienteDao.delete(id);
			return "redirect:/Clientes/Lista";
		}

	}
}
