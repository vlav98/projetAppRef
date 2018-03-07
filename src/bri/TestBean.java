package bri;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.Socket;

public class TestBean {
	
	public static void checkBRi(Class<?> service) throws Exception{
		
			if(!Modifier.isPublic(String.class.getModifiers()))
				throw new Exception("Classe non public.");
			else if(Modifier.isAbstract(service.getModifiers()))
				throw new Exception("Classe abstract.");
			
//			for(Constructor<?> constructeur:service.getConstructors()){
//				//Constructeur non vide
//				if(constructeur.getParameterCount() == 0)
//					throw new Exception("Constructeur sans paramètres");
//				else if(constructeur.getParameters() != null) {
//					for(int i=0; i < constructeur.getParameterCount() ; i++){
//						if(constructeur.getParameterTypes().toString().compareTo("Socket")==0)
//							throw new Exception("Constructeur qui ne prend pas en paramètre un Socket");
//					}
//				}
//			}
			
			try{
				service.getConstructor(Socket.class);	
			}catch(NoSuchMethodException e){
				throw new Exception("Le constructeur ne prend pas en paramètre Socket");
			}
						
			for(Field attribut:service.getDeclaredFields()){
				String attSock = attribut.getType().toString();
							
				try{
					if(attSock == "Socket"){
						if(!Modifier.isPrivate(attribut.getModifiers())){
							throw new Exception("Attribut non private");
						}
						else if(!Modifier.isFinal(attribut.getModifiers())){
							throw new Exception("Attribut non final");
						}
					}
				}
				catch(Exception e){
					throw new Exception("La classe de service " + service.getSimpleName() 
					+ " n'a pas d'attribut Socket private final");
				}
			}
			Method methode = service.getDeclaredMethod(ServiceRegistry.toStringue(), String.class);
			if(Modifier.isPublic(methode.getModifiers())){
				if(Modifier.isStatic(methode.getModifiers())){
					if(methode.getExceptionTypes() != null){
						throw new Exception("La méthode " + methode.getName() 
						+ " n'est pas sans Exception");
					}
				}else{
					throw new Exception("La méthode " + methode.getName() 
					+ " n'est pas static");
				}
			}else{
				throw new Exception("La méthode " + methode.getName() 
				+ " n'est pas public");
			}
					
//		catch(Exception e){
//			throw new Exception("L'ajout du service " + service.getSimpleName() + " a échoué.");
//		}
	}
	
}
