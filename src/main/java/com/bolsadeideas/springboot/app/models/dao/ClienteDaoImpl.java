package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

/*La anotación @Repository funciona para marcar la clase como persistencia y              
 * de componente de acceso a datos, traduce las excepciones y errores de 
 * acceso a datos
 * 
 * 
 * El EntityManager se encarga de manejar las clases de identidades, su ciclo de vida
 * las persiste, crea consultas, etc. Hace todas las operaciones a la base de datos
 * pero con objetos
 * 
 * 
 * 
 * La anotación @SuppressWarnings quita los warning que pueden aparecer que sean indiferentes
 * 
 * 
 * La anotación @Transactional marcamos el metodo como transaccional y le pasamo como
 * parametro que solo sera de lectura, toma el contenido del metodo y la envuelve en transaccion
 * 
 * 
 * La anotación @PersistenceContext injecta al EntityManager, sino existe una configuracion
 * de base de datos en el application.properties por defecto se tomara el motor H2 que 
 * se tomo como dependencia al crear el proyecto
 * */


@Repository
public class ClienteDaoImpl implements IClienteDao {
	
	@PersistenceContext
	private EntityManager em;
   
	//Método para listar clientes, se llama en la interface
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		return em.createQuery("from Cliente").getResultList();
	}

    
    //Método para gurdar/registrar cliente, se llama en la interface
	@Override
	@Transactional
	public void save(Cliente cliente) {
		em.persist(cliente);		
	}

}
