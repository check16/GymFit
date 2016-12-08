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

/**
 * Clase entidad para el mapeo de la BD de la tabla usuario
 * @author antonio
 */
@Entity
@Table(name = "usuario")
@DynamicUpdate(value=true)
public class Usuario {

	/**
	 * Propiedad que encapsula el id del usuario
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;

	/**
	 * Propiedad que encapsula el login de usuario
	 */
	@Size(min = 4)
	private String login;
	
	/**
	 * Propiedad que encapsula el nombre y apellido del usuario
	 */
	private String nombreApellido;

	/**
	 * Propiedad que encapsula la clave del usuario
	 */
	@NotEmpty
	@Size(min = 4)
	private String clave;
	
	/**
	 * Propiedad que encapsula la clave repetida del usuario
	 */
	@Transient
	@Size(min = 4)
	private String claveRep;

	/**
	 * Propiedad que encapsula el email de usuario
	 */
	@NotEmpty
	@Email
	private String email;

	/**
	 * Propiedad que encapsula la fecha de registro del usuario
	 */
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaRegistro;

	/**
	 * Propiedad que encapsula la edad del usuario
	 */
	private int edad;

	/**
	 * Propiedad que encapsula la altura del usuario
	 */
	private int altura;

	/**
	 * Propiedad  que encapsula la ruta de la foto del usuario
	 */
	private String rutaFoto;

	/**
	 * Propiedad que encapsula el rol del usuario
	 */
	@ManyToOne
	@JoinColumn(name = "idRol")
	private Rol rol;

	/**
	 * Propiedad que encapsula los pesos del usuario
	 */
	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Peso> pesos;

	/**
	 * Propiedad que encapsula los entrenamientos del usuario
	 */
	@OneToMany(mappedBy = "usuario")
	@LazyCollection(LazyCollectionOption.TRUE)
	private List<Entrenamiento> entrenamientos;

	/**
	 * Constructor por defecto
	 */
	public Usuario() {
	}

	/**
	 * Metodo getter del id del usuario
	 * @return el id del usuario
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Metodo setter del id del usuario
	 * @param idUsuario el id del usuario
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Metodo getter del login de usuario
	 * @return el login de usuario
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metodo setter del login de usuario
	 * @param login el login de usuario
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Metodo getter de la clave de usuario
	 * @return la clave de usuario
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Metodo setter de la clave de usuario
	 * @param clave la clave de usuario
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Metodo getter del email de usuario
	 * @return el email de usuario
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo setter del email de usuario
	 * @param email el email de usuario
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo getter de la edad de usuario
	 * @return la edad del usuario
	 */
	public int getEdad() {
		return edad;
	}

	/**
	 * Metodo setter de la edad del usuario
	 * @param edad la edad del del usuario
	 */
	public void setEdad(int edad) {
		this.edad = edad;
	}

	/**
	 * Metodo getter de la altura del usuario
	 * @return la altura del usuario
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Metodo setter de la altura del usuario
	 * @param altura la altura del usuario
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}

	/**
	 * Metodo getter de la ruta de la foto del usuario
	 * @return la ruta de la foto del usuario
	 */
	public String getRutaFoto() {
		return rutaFoto;
	}

	/**
	 * Metodo setter de la ruta de la foto del usuario
	 * @param rutaFoto la ruta de la foto del usuario
	 */
	public void setRutaFoto(String rutaFoto) {
		this.rutaFoto = rutaFoto;
	}

	/**
	 * Metodo getter del rol del usuario
	 * @return el rol del usuario
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * Metodo setter del rol de usuario
	 * @param rol el rol del usuario
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}

	/**
	 * Metodo getter de la fecha de registro del usuario
	 * @return la fecha de registro del usuario
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Metodo setter de la fecha de registro del usuario
	 * @param la fecha de registro del usuario
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Metodo getter de los pesos del usuario
	 * @return los pesos del usuario
	 */
	public List<Peso> getPesos() {
		return pesos;
	}

	/**
	 * Metodo getter de los pesos del usuario
	 * @param los pesos del usuario
	 */
	public void setPesos(List<Peso> pesos) {
		this.pesos = pesos;
	}

	/**
	 * Metodo getter de los entrenamientos del usuario
	 * @return los entrenamientos del usuario
	 */
	public List<Entrenamiento> getEntrenamientos() {
		return entrenamientos;
	}

	/**
	 * Metodo setter de los entrenamientos del usuario
	 * @param los entrenamientos del usuario
	 */
	public void setEntrenamientos(List<Entrenamiento> entrenamientos) {
		this.entrenamientos = entrenamientos;
	}

	/**
	 * Metodo getter del nombre y apellido del usuario
	 * @return el nombre y apellido del usuario
	 */
	public String getNombreApellido() {
		return nombreApellido;
	}

	/**
	 * Metodo setter del nombre y apellido del usuario
	 * @param el nombre y apellido del usuario
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	/**
	 * Metodo getter de la clave repetida del usuario
	 * @return la clave repetida del usuario
	 */
	public String getClaveRep() {
		return claveRep;
	}

	/**
	 * Metodo setter de la clave repetida del usuario
	 * @param la clave repetida del usuario
	 */
	public void setClaveRep(String claveRep) {
		this.claveRep = claveRep;
	}

	/**
	 * Metodo toString del usuario
	 */
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", login=" + login + ", nombreApellido=" + nombreApellido
				+ ", clave=" + clave + ", email=" + email + ", fechaRegistro=" + fechaRegistro + ", edad=" + edad
				+ ", altura=" + altura + ", rutaFoto=" + rutaFoto + ", rol=" + rol + ", pesos=" + pesos
				+ ", entrenamientos=" + entrenamientos + "]";
	}
}
