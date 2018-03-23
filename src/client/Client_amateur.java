package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import bri.PORT;

/*
 * Ce client se connecte � un serveur dont le protocole est 
 * menu-choix-question-r�ponse client-r�ponse service
 * il n'y a qu'un �change (pas de boucle)
 * la réponse est saisie au clavier en String
 * 
 * Le protocole d'échange est suffisant pour le tp4 avec ServiceInversion 
 * ainsi que tout service qui pose une question, traite la donnée du client et envoie sa r�ponse 
 * mais est bien sur susceptible de (nombreuses) améliorations
 */
class Client_amateur {
		private final static String HOST = "localhost";
	
	public static void main(String[] args) {
		Socket s = null;		
		try {
			s = new Socket(HOST, PORT.PORT_AMA);
			
			BufferedReader sin = new BufferedReader (new InputStreamReader(s.getInputStream ( )));
			PrintWriter sout = new PrintWriter (s.getOutputStream ( ), true);
			BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));			

			System.out.println("Connecté au serveur " + s.getInetAddress() + ":"+ s.getPort());
			
			String line="";
//			String sortie= "sortie";
//			System.out.println("Si vous voulez quitter l'application, tapez 'sortie'.");
//			
//			do{
//				line = sin.readLine();
//				System.out.println(line.replaceAll("##", "\n"));
//				sout.println(clavier.readLine());
//			}while(sortie!=line);
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
			
			
			// URLClassLoader sur ftp

		}
		catch (IOException e) { System.err.println("Fin de la connexion"); }
		// Refermer dans tous les cas la socket
		try { if (s != null) s.close(); } 
		catch (IOException e2) { ; }		
	}
}
