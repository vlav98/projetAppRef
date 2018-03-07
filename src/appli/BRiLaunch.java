package appli;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

import bri.ServeurBRi;
import bri.Service;
import bri.ServiceRegistry;

public class BRiLaunch {
	private final static int PORT_SERVICE = 3000;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner clavier = new Scanner(System.in);
		
		// URLClassLoader sur ftp
		URLClassLoader urlcl = null;
		
		try {
			urlcl = new URLClassLoader(new URL[]{new URL("ftp://localhost:2121/classes/")});
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Bienvenue dans votre gestionnaire dynamique d'activité BRi");
		System.out.println("Pour ajouter une activité, celle-ci doit être présente sur votre serveur ftp");
		System.out.println("A tout instant, en tapant le nom de la classe, vous pouvez l'intégrer");
		System.out.println("Les clients se connectent au serveur 3000 pour lancer une activité");
		
		new Thread(new ServeurBRi(PORT_SERVICE)).start();
		
		while (true){
			try {
				System.out.println("Nom de service à charger");
				String classeName = clavier.next();	
				// charger la classe et la déclarer au ServiceRegistry
				Class<?> c = urlcl.loadClass(classeName);
				ServiceRegistry.addService(c);
			} catch (Exception e) {
				System.out.println(e);
			}
		}		
	}
}
