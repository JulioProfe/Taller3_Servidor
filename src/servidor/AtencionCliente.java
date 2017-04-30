package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;

public class AtencionCliente extends Observable implements Runnable {

	private Socket s;
	private boolean online;
	private int cliente;

	public AtencionCliente(Socket ref, int cliente) {
		// TODO Auto-generated constructor stub
		s = ref;
		online = true;
		this.cliente = cliente;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (online) {
			recibir();

		}
	}

	private void recibir() {
		DataInputStream entrada = null;
		try {
			entrada = new DataInputStream(s.getInputStream());
			int val = entrada.readInt();
			String valor = entrada.readUTF();
			System.out.println(valor);
			System.out.println("se recibió: " + val);
		} catch (SocketException e) {
			System.out.println(" se perdio la conexion con el cliente ");
			online = false;
			try {
				entrada.close();
				s.close();
				s = null;
				setChanged();
				notifyObservers();
				clearChanged();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
			online = false;
		}
	}
	
	public void enviar(String mensaje) {
		try {
			DataOutputStream salida = new DataOutputStream(s.getOutputStream());
			salida.writeUTF(mensaje);
			System.out.println("Se envio el HH al cliente");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
