package com.asanast.gymfit.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@NotEmpty
	private String login;
	
	private String nombre;
	
	private String apellido1;
	
	private String apellido2;
	
	@NotEmpty
	private String clave;
	
	@NotEmpty
	private String email;
	

	private int edad;
	

	private int altura;
	
	private String rutaFoto;
	
	@ManyToOne
	@JoinColumn(name = "idRol")
	private Rol rol;
	
	@OneToMany(mappedBy="usuario")
	private List<Peso> pesos;
	
	@OneToMany(mappedBy="usuario")
	private List<Entrenamiento> entrenamientos;
	
	public Usuario() {
		
	}

	public Usuario(String login, String clave, String email, int edad, int altura, String rutaFoto) {
		this.login = login;
		this.clave = clave;
		this.email = email;
		this.edad = edad;
		this.altura = altura;
		this.rutaFoto = rutaFoto;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public String getRutaFoto() {
		return rutaFoto;
	}

	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}	

	
}
