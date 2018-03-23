package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import bri.ServiceRegistry;

public class ServiceLancer implements Runnable{
	
	private Socket client;
	
	public ServiceLancer(Socket socket) {
		client = socket;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream ( )));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);
			out.println(ServiceRegistry.toStringue()+"##Tapez le numéro de service désiré :");
			int choix = Integer.parseInt(in.readLine());		
			// instancier le service numéro "choix" en lui passant la socket "client"
			// invoquer run() pour cette instance ou la lancer dans un thread à part 
			Constructor c = ServiceRegistry.getServiceClass(choix).getConstructor(Socket.class);
			Runnable r = (Runnable)c.newInstance(client);
			r.run();
//			Class<?> classe = choix;
		}catch (IOException | InstantiationException | 
				IllegalAccessException | IllegalArgumentException |
				InvocationTargetException | NoSuchMethodException |
				SecurityException e) {
			//Fin du service
			e.printStackTrace();
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
