package com.bolsadeideas.springboot.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.app.models.dao.IClienteDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;


/*La anotación @Valid sirve para que las validaciones hechas en el modelo(Entity)
 * funcionen correctamente, el BindingResult es una clase para atrapar
 * los errores*/

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
	
	//Método para crear la vista del formulario 
	@GetMapping("/form")
	public String crear(Model model) {
		
		Cliente cliente = new Cliente();
		model.addAttribute("titulo", "Formulario de cliente");
		model.addAttribute("cliente", cliente);
		return "clientes/form";
	}
	
	//Método para guardar llamando desde la interface
	@PostMapping("/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model ) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "clientes/form";
		}
		
		clienteDao.save(cliente);
		return "redirect:/clientes/listar";
	}
}
