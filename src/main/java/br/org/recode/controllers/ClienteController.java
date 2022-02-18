package br.org.recode.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import br.org.recode.models.Cliente;
import br.org.recode.models.Endereco;
import br.org.recode.persistence.ClienteDao;
import br.org.recode.persistence.EnderecoDao;

@Controller
public class ClienteController {
	
	@Autowired
	private ClienteDao clienteDao;
	
	@Autowired
	private EnderecoDao enderecoDao;
	
	@GetMapping("/Clientes")
	public String listar(ModelMap modelMap) {
		modelMap.addAttribute("clientes", clienteDao.findAll());
		return "/cliente/lista";
	}
	
	@GetMapping("/Clientes/Cadastro")
	public String cadastrar(Cliente cliente) {
		return "/cliente/cadastro";
	}


	@PostMapping("/Clientes/Salvar")


	public String salvar(Cliente cliente, ModelMap modelMap) {
		try {
			clienteDao.create(cliente);
			return "redirect:/Clientes";

		} catch (DataIntegrityViolationException e) {
			System.out.println("-- EXCEPTION: " + e.getMessage());
			modelMap.addAttribute("msg", "Dados inválidos");
			return "/cliente/cadastro";
		}
	}
}
