package model;

import java.awt.Image;
import java.io.Serializable;
import java.time.LocalDate;
/**
 * 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *clase donde tengo mi persona con su caracteristicas esta personas en un momento
 */
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;//8 Byte
	private String firstName;//20 caracteres => 40 Bytes
	private String lastName;//20 caracteres => 40 Bytes
	private Gender gender; //1 Byte
	private LocalDate birthDay; // 10 caracteres => 20 Bytes
	private double value; // 8 Byte 
	private String profile;//descripcion del perfil 2000 caracteres => 4.000 Bytes
	private Image photo;//
	private String passport;
	
	public Person() {
		
	}
	
	
	/**
	 * creo mi persona con las caracteristicas propuestas
	 * @param id identificacion puede ser la cedula 
	 * @param firstName primer nombre de mi persona
	 * @param lastName apellido (s) de mi persona
	 * @param gender Genero de mi persona
	 * @param birthDay cumpleaños de mi persona
	 * @param value salario de la persona
	 * @param profile perfil de la persona o descripcion de esta
	 * @param photo foto de la persona en formato url
	 * @param passport pasaporte de mi persona
	 */
	public Person(long id, String firstName, String lastName, Gender gender, LocalDate birthDay, double value,
			String profile, Image photo, String passport) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.birthDay = birthDay;
		this.value = value;
		this.profile = profile;
		this.photo = photo;
		this.passport = passport;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}


	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


	public Image getPhoto() {
		return photo;
	}


	public void setPhoto(Image photo) {
		this.photo = photo;
	}


	public String getPassport() {
		return passport;
	}


	public void setPassport(String passport) {
		this.passport = passport;
	}
	
	@Override
	public String toString() {
		return String.format("|Nombre %s|Apellido %s| id %d | Genero  %s | pasaporte  %s |localDate  %s | photo %s|value %f | perfil %s|",
				firstName, lastName, id, gender.toString(),passport,birthDay.toString(), photo,value,profile);
	}
//	public Person(long id, String firstName, String lastName, Gender gender, LocalDate birthDay, double value,
//			String profile, Image photo, String passport) {
}
