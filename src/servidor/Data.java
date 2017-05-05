package servidor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import processing.data.XML;
import serializadas.Usuario;

public class Data {

	XML raiz;
	File archivo;

	public Data(String ruta) {
		// TODO Auto-generated constructor stub
		archivo = new File(ruta);
		if (archivo.exists() && archivo.isFile()) {
			try {
				raiz = new XML(archivo);
			} catch (IOException | ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			raiz = new XML("usuarios");
			raiz.addChild("user");
		}
	}

	public void guardar() {
		raiz.save(archivo);
	}

	public void agregarUsuario(Usuario u) {
		XML usuarios = raiz.getChild("user");
		XML newUser = usuarios.addChild("usuario");
		newUser.setString("name", u.getName());
		newUser.setString("pass", u.getPass());
	}

	public ArrayList<Usuario> getUsuarios() {
		XML usuarios = raiz.getChild("user");
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		if (usuarios.getChildCount() > 0) {
			XML[] usuarioXML = usuarios.getChildren("usuario");
			for (int i = 0; i < usuarioXML.length; i++) {
				String name = usuarioXML.toString();
				String pass = usuarioXML.toString();

				users.add(new Usuario(name, pass));
			}
		}
		return users;
	}
}
