package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Comunicacion extends Observable implements Runnable, Observer {

	private ServerSocket ss;
	private Socket s;
	private ArrayList<AtencionCliente> clientes;
	private boolean online;

	public Comunicacion() {
		// TODO Auto-generated constructor stub

		try {
			System.out.println("Inciando el servidor");
			ss = new ServerSocket(5100);
			System.out.println("Servidor corriendo");
			s = null;
			clientes = new ArrayList<AtencionCliente>();
			online = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		while (online) {
			try {
				System.out.println("Esperando nuevo Cliente");
				Socket nuevoTemp = ss.accept();
				AtencionCliente nuevo = new AtencionCliente(nuevoTemp, clientes.size());
				nuevo.addObserver(this);
				clientes.add(nuevo);
				new Thread(nuevo).start();
				System.out
						.println("se ha recibido un nuevo cliente, vamos en: "
								+ clientes.size());
				setChanged();
				notifyObservers(""+clientes.size());
				clearChanged();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
