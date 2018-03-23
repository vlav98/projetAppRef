package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;

import bri.PORT;
import bri.ServiceRegistry;

public class Client_programmeur {
	
	private final static String HOST = "localhost";
	private static String login;
	private static String mdp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Socket s = null;		
		try {
			s = new Socket(HOST, PORT.PORT_PROG);
			
			BufferedReader sin = new BufferedReader (new InputStreamReader(s.getInputStream ( )));
			PrintWriter sout = new PrintWriter (s.getOutputStream ( ), true);
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			
			
			System.out.println("Connecté au serveur " + s.getInetAddress() + ":"+ s.getPort());
			
			String line;
			
			// connexion
//			System.out.println("Veuillez saisir votre login : ");
//			//recuperer le login
//			sout.println(clavier.readLine());
//			//setLogin(clavier.readLine());
//			System.out.println("Veuillez saisir votre mot de passe :");
			// sout.println(clavier.readLine());
//			setMdp(clavier.readLine());
						
									
		// menu et choix du service
			line = sin.readLine();
			System.out.println(line.replaceAll("##", "\n"));
		// saisie/envoie du choix
			sout.println(clavier.readLine());
						
		// réception/affichage de la question
			System.out.println(sin.readLine());
		// saisie clavier/envoie au service de la réponse
			sout.println(clavier.readLine());
		// réception/affichage de la réponse
			System.out.println(sin.readLine());

			
		}
		catch (IOException e) { System.err.println("Fin de la connexion"); }
		// Refermer dans tous les cas la socket
		try { if (s != null) s.close(); } 
		catch (IOException e2) { ; }		
		
	}

	public String getLogin() {
		return login;
	}

	private static void setLogin(String login) {
		Client_programmeur.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	private static void setMdp(String mdp) {
		Client_programmeur.mdp = mdp;
	}

}
