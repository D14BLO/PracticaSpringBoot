package com.bolsadeideas.springboot.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteDao clienteDao;
	
	@GetMapping("/listar")
	public String listarClientes(Model model) {
		model.addAttribute("titulo", "Clientes");
		model.addAttribute("clientes", clienteDao.findAll());
		return "clientes/listar";
	}
}
