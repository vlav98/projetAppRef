package bri;

import java.util.List;
import java.util.Vector;

public class ServiceRegistry {
	// cette classe est un registre de services
	// partag�e en concurrence par les clients et les "ajouteurs" de services,
	// un Vector pour cette gestion est pratique

	static {
		servicesClasses = new Vector<Class<?>>();
	}
	private static List<Class<?>> servicesClasses;

// ajoute une classe de service apr�s contr�le de la norme BLTi
	public static void addService(Class<?> service) throws Exception {
		// v�rifier la conformit� par introspection
		// si non conforme --> exception avec message clair
		// si conforme, ajout au vector

		// VERIFICATION
		TestBean.validationBean(service);
		
		servicesClasses.add(service);
	}
		
// renvoie la classe de service (numService -1)	
	public static Class<?> getServiceClass(int numService) {
		return servicesClasses.get(numService);
	}
	
// liste les activit�s pr�sentes
	public static String toStringue() {
		String result = "Activit�s pr�sentes :##";
//		 todo
		if(!servicesClasses.isEmpty()){
			for(int i = 0; i<servicesClasses.size(); i++)
				result = result + servicesClasses.get(i).getSimpleName();
		}
		return result;
	}

}
