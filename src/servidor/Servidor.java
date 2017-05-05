package servidor;

import processing.core.PApplet;

public class Servidor extends PApplet{
	Logica log; 
	String archivo = "../../data/usuarios.xml";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("servidor.Servidor");
	}
	
	@Override
	public void settings() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void setup() {
		// TODO Auto-generated method stub
		log = new Logica(this);
		
		log.start();
	}	
	
	
}
