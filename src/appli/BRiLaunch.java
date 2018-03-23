package appli;

import bri.PORT;
import bri.ServeurBRi;

public class BRiLaunch {
	
	
	public static void main(String[] args) {		
		
		System.out.println("Bienvenue dans votre gestionnaire dynamique d'activité BRi");
		System.out.println("Pour ajouter une activité, celle-ci doit être présente sur votre serveur ftp");
		System.out.println("A tout instant, en tapant le nom de la classe, vous pouvez l'intégrer");
		System.out.println("Les clients se connectent au serveur 3000 pour lancer une activité");
		
		new Thread(new ServeurBRi(PORT.PORT_AMA)).start();	
		new Thread(new ServeurBRi(PORT.PORT_PROG)).start();	
	}
}
