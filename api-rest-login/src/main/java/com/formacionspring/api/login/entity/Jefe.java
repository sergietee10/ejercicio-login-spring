package com.formacionspring.api.login.entity;
import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "jefes")
public class Jefe implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable=false, unique=true)
	private String dni;
	@Column(nullable=false)
	private String nombre;
	@Column(nullable=false)
	private String salario;
	@Column(nullable=false, unique=true)
	private int telefono;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departamento_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Departamento departamento;
	
	// GETTERS Y SETTERS

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getSalario() {
		return salario;
	}


	public void setSalario(String salario) {
		this.salario = salario;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public Departamento getDepartamento() {
		return departamento;
	}


	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
