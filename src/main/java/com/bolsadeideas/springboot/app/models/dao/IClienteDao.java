package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDao {
	
	//metodo para listar clientes
	public List<Cliente> findAll();
	
	//metodo para registrar clientes
	public void save(Cliente cliente);
}
