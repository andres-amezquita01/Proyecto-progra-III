package model;

import java.io.Serializable;
/**
 * clase donde estara el usuario y contraseña de mi cliente 
 * @author Grupo 2 -- Darwin Vargas --Andres Amezquita Gordillo-- Andres Felipe Moreno
 *
 */
public class Password implements Serializable {
	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	public Password() {
		
	}
	
	/**
	 * Contructor donde creo que mi clase Password
	 * @param user usuario de mi sistemas
	 * @param password contraseña del usuario de mi sistema
	 */
	public Password(String user, String password) {
		this.user = user;
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return String.format("user: %s password: %s", user,password);
	}
}
