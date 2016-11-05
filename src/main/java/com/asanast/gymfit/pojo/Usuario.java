package com.asanast.gymfit.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "usuario")
@DynamicUpdate(value=true)
public class Usuario {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	@Size(min = 4)
	private String login;
	
	private String nombreApellido;

	@NotEmpty
	@Size(min = 4)
	private String clave;
	
	@Transient
	@Size(min = 4)
	private String claveRep;

	@NotEmpty
	@Email
	private String email;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaRegistro;


	private int edad;

	private int altura;

	private String rutaFoto;

	@ManyToOne
	@JoinColumn(name = "idRol")
	private Rol rol;

	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Peso> pesos;

	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Entrenamiento> entrenamientos;

	public Usuario() {
		

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

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Peso> getPesos() {
		return pesos;
	}

	public void setPesos(List<Peso> pesos) {
		this.pesos = pesos;
	}

	public List<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}

	public void setEntrenamientos(List<Entrenamiento> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	public String getClaveRep() {
		return claveRep;
	}

	public void setClaveRep(String claveRep) {
		this.claveRep = claveRep;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", login=" + login + ", nombreApellido=" + nombreApellido
				+ ", clave=" + clave + ", email=" + email + ", fechaRegistro=" + fechaRegistro + ", edad=" + edad
				+ ", altura=" + altura + ", rutaFoto=" + rutaFoto + ", rol=" + rol + ", pesos=" + pesos
				+ ", entrenamientos=" + entrenamientos + "]";
	}
}
