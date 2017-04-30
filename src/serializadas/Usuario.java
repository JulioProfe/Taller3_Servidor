package serializadas;

import java.io.Serializable;

public class Usuario implements Serializable {
	private String name;
	private String pass;
	private boolean registrado;
	
	public Usuario(String name, String pass) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.pass = pass;
		this.registrado = registrado;
	}

	public String getName() {
		return name;
	}

	public String getPass() {
		return pass;
	}
	
	

}
