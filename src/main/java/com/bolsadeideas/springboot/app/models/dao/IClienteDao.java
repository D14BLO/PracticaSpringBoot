package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

/*Esta interface ahora extiende de PagingAndSortingRepository en ves de 
 * CrudRepository ya que se utiliza para el paginador y a su vez esta
 * extiende de CrudRepository por lo cual los metodos utilizados con CrudRepository
 * se siguen manteniendo*/
public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

}
