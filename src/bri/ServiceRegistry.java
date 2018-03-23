package bri;

import java.util.List;
import java.util.Vector;

public class ServiceRegistry {
	// cette classe est un registre de services
	// partag�e en concurrence par les clients et les "ajouteurs" de services,
	// un Vector pour cette gestion est pratique

	static {
		setServicesClasses(new Vector<Class<?>>());
	}
	private static List<Class<?>> servicesClasses;

	// ajoute une classe de service apr�s contr�le de la norme BLTi
	public static void addService(Class<?> service) throws Exception {
		// vérifier la conformité par introspection
		// si non conforme --> exception avec message clair
		// si conforme, ajout au vector

		// VERIFICATION
		TestBean.validationBean(service);

		getServicesClasses().add(service);
	}

	// renvoie la classe de service (numService -1)
	public static Class<?> getServiceClass(int numService) {
		return getServicesClasses().get(numService);
	}

	// liste les activités présentes
	public static String toStringue() {
		String result = "Activités présentes :##";
		// todo
		if (!getServicesClasses().isEmpty()) {
			for (int i = 0; i < getServicesClasses().size(); i++)
				result = result + getServicesClasses().get(i).getSimpleName();
		}
		return result;
	}

	public static List<Class<?>> getServicesClasses() {
		return servicesClasses;
	}

	public static void setServicesClasses(List<Class<?>> servicesClasses) {
		ServiceRegistry.servicesClasses = servicesClasses;
	}

}
