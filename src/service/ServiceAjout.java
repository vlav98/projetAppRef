package service;


import java.io.*;
import java.net.*;

import bri.ServiceRegistry;


public class ServiceAjout implements Runnable {
	
	private Socket client;
	
	public ServiceAjout(Socket socket) {
		client = socket;
	}

	public void run() {
		URLClassLoader urlcl = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			out.println("veuillez saisir le nom d'une classe à charger");
			String classeDemandee = in.readLine();
			urlcl = new URLClassLoader(new URL[]{new URL("ftp://localhost:2121/classes/")});
			while (true){
				try {
					// charger la classe et la déclarer au ServiceRegistry
					Class<?> c = urlcl.loadClass(classeDemandee);
					ServiceRegistry.addService(c);
					out.println("Service "+classeDemandee+ " chargée" );
				} catch (Exception e) {
					out.println(e);
				}
			}	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {client.close();} catch (IOException e2) {}
	}
	
	protected void finalize() throws Throwable {
		 client.close(); 
	}

	// lancement du service
	public void start() {
		(new Thread(this)).start();		
	}

}
