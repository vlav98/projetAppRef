package bri;


import java.io.*;
import java.net.*;

import client.Client_programmeur;
import service.ServiceAjout;
import service.ServiceLancer;


public class ServeurBRi implements Runnable {
	private ServerSocket listen_socket;
	private final static String HOST = "localhost";
	
	// Cree un serveur TCP - objet de la classe ServerSocket
	public ServeurBRi(int port) {
		try {
			listen_socket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Le serveur ecoute et accepte les connections.
	// pour chaque connection, il cree un ServiceInversion, 
	// qui va la traiter.
	public void run() {
		if(listen_socket.getLocalPort()==PORT.PORT_AMA){
			try{
				while(true){
					new ServiceLancer(listen_socket.accept()).start();
				}
			}catch(IOException e){
				try {this.listen_socket.close();} catch (IOException e1) {}
				System.err.println("Pb sur le port d'écoute :"+e);
			}
		}
		if(listen_socket.getLocalPort()==PORT.PORT_PROG){
			try{
				while(true){
					new ServiceAjout(listen_socket.accept()).start();
				}
			}catch(IOException e2){
				try {this.listen_socket.close();} catch (IOException e4) {}
				System.err.println("Pb sur le port d'écoute :"+e2);
			}
		}
	}

	 // restituer les ressources --> finalize
	protected void finalize() throws Throwable {
		try {this.listen_socket.close();} catch (IOException e1) {}
	}

	// lancement du serveur
	public void lancer() {
		(new Thread(this)).start();
	}
}
