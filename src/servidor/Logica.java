package servidor;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import serializadas.Usuario;

public class Logica extends Thread implements Observer{
	private PApplet app;
	private Data d;
	private ArrayList<Usuario> usuarios;
	private Comunicacion com;

	public Logica(PApplet app) {
		// TODO Auto-generated constructor stub
		this.app = app;
		d = new Data("../data/usuarios.xml");
		usuarios = d.getUsuarios();
		
		com = new Comunicacion();
		com.addObserver(com);
		new Thread(com).start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			d.guardar();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Murio el hilo");
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if (arg instanceof Usuario) {
			String yep = (String) arg;
			if (yep.contains(getName()) && yep.contains(((Usuario) arg).getPass())) {
				Usuario nuevo = new Usuario(getName(), ((Usuario) arg).getPass());
				usuarios.add(nuevo);
				d.agregarUsuario(nuevo);
			}
		}
	}

}
