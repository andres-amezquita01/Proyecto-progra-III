package model;

import java.io.Serializable;

public class Password implements Serializable {
	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	public Password() {
		
	}
	public Password(String user, String password) {
		super();
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
