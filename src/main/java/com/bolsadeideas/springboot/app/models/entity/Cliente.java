package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/*La anotación @Entity nos indica que esta clase se refiere a una 
 * entidad, es decir, un mapeo con atributos a una base de datos
 * y bien la posterior creación de tablas en la BD mediante este
 * ORM (JPA)
 * 
 * 
 * La anotación @Table nos apoya para colocarle el nombre a nuestra tabla
 * sin embargo en caso de no utilizarla se colocara como nombre de tabla 
 * por defecto el nombre de la clase 
 * 
 * 
 * En una entidad se debe implementar la clase Serializable, esto debido
 * a que la clase es un mapeo y al implementar Serializable esto los convierte
 * a bytes, pudiendo asi convertirlas en tablas
 * 
 * 
 * Al implementar la clase Serializable pedira un identificador, esto con
 * el fin de saber a que clase se refiere o esta mapeada
 * 
 * 
 * La anotación @Id se coloca en el atributo que sera nuestra llave primaria
 * 
 * 
 * La anotación @GeneratedValue utiliza los parametros de GenerationType.IDENTITY
 * para que este sea autoincrementable, hay mas parametros que se puede utilizar con
 * Generationtype
 * 
 * 
 * La anotacion @Column nos funciona para customizar la tabla, como cambiarle
 * de nombre al atributo a como aparecera en la tabla, por convencion en base 
 * de datos los nombres de los atributos van en minusculas y separados por guion
 * bajo en caso de que sean dos palabras, en un lenguaje de programacion, la convencion
 * es si un atributo tiene dos palabras la primera comienza en minuscula y la segunda en 
 * mayuscula sin espacios o caracterés especiales, para ese tipo de orden se 
 * utiliza la anotacióń @Column
 * 
 * 
 * La anotación @Temporal indica el formato de fecha que se guardara en la base de dato
 * se puede guardar solo la fecha, fecha con hora, etc.
 * */
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;
	private String apellido;
	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

}
