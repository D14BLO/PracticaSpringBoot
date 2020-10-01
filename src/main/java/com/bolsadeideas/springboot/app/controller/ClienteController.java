package com.bolsadeideas.springboot.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;


/*La anotación @Valid sirve para que las validaciones hechas en el modelo(Entity)
 * funcionen correctamente, el BindingResult es una clase para atrapar
 * los errores
 * 
 * La anotación @SessionAttributes("nombre del objeto") sirve para guardar en sesion
 * el crud en este caso se termina cuando se guarda con el metodo setComplete() que viene de la clase
 * SessionStatus 
 * 
 * 
 * La clase RedirectAttributes llama al metodo AddFlashAttributes el cual permite mandar un mensaje 
 * de sesion, es decir este se crea y se elimina en un solo request
 * 
 * 
 * Los metodos y las variables que tengan que ver con page, pageRequest, pageRender, etc.
 * son parte del paginador. El paginador funciona por medio de dos clases: PageItem
 * la cual enlista o mejor dicho pagina todo los registros de la BBDD y el PageRender
 * la cual se encarga de la logica para el render de la paginacion, esto se logra gracias
 * a las colecciones
 * 
 * En el metodo guardar se implementa un @RequestParam con el nombre de "file" el cual es el
 * campo para subir una imagen de igual manera se utiliza el objeto MultipartFile para acceder
 * a las rutas de la imagen asi como almacenarla en el servidor
 * 
 *  Se hace una validacion para saber si la variable foto esta no esta vacia, en caso de que la
 *  variable foto sea diferente de vacia se llama a las rutas y se convierte en bytes para
 *  poser almacenarla en la BBDD */

@Controller
@SessionAttributes("cliente")
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		
		if(id > 0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!!");
				return "redirect:/clientes/listar";
			}
		}else {
			flash.addFlashAttribute("error", "El ID no puede ser cero!!");
			return "redirect:/clientes/listar";
		}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Detalle de " + cliente.getNombre());
		
		return "clientes/ver";
	}
	
	@GetMapping("/listar")
	public String listarClientes(@RequestParam(name = "page", defaultValue = "0") int page,Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		PageRender<Cliente> pageRender = new PageRender<>("listar", clientes);
		model.addAttribute("titulo", "Clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "/clientes/listar";
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
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model,@RequestParam("file") MultipartFile foto, RedirectAttributes flash,SessionStatus status ) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de cliente");
			return "clientes/form";
		}
		
		if(!foto.isEmpty()) {
			String rootPath = "/opt/uploads";
			try {
				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "La imagen '" + foto.getOriginalFilename() + " se subio correctamente'");
				
				cliente.setFoto(foto.getOriginalFilename());
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		
		String mensajeFlash = (cliente.getId() != null)? "Cliente editado con exito!!": "Cliente creado con exito";
		
		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:/clientes/listar";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El iD del cliente no existe en la BBDD!!");
				return "redirect:/clientes/listar";
			}
		}else {
			flash.addFlashAttribute("error", "El ID no puede ser cero!!");
			return "redirect:/clientes/listar";
		}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Editar cliente");
		
		return "clientes/form";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente eliminado con exito!!");
		}
		return "redirect:/clientes/listar";
	}
	
	
}
