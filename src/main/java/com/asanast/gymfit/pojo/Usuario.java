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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@NotEmpty
	@Size(min=4)
	private String login;
	
	@NotEmpty
	@Size(min=4)
	private String clave;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	private int edad;
	
	@NotNull
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
}
