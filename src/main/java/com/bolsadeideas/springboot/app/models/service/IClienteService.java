package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteService {
	   //metodo para listar clientes
		public List<Cliente> findAll();
		
		//metodo para paginar los registros
		public Page<Cliente> findAll(Pageable pageable);
		
		//metodo para registrar clientes
		public void save(Cliente cliente);
		
		//metodo para actualizar cliente
		public Cliente findOne(Long id);
		
		//método para borrar cliente
		public void delete(Long id);
}
