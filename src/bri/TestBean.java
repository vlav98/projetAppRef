package bri;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.Socket;
import java.util.Arrays;

public class TestBean {
// teste si une classe est un bean : public, un constructeur vide,Serializable, 
//		pas final et get/set standard pour chaque attribut

	public static void validationBean(Class<?> classe) throws Exception {
		// � rajouter : �liminer ce qui n'est pas une classe
		int mod = classe.getModifiers();
		if (!Modifier.isPublic(mod)) throw new Exception ("la classe doit �tre publique");
		if (Modifier.isFinal(mod)) throw new Exception ("la classe ne doit pas �tre final");
		if (!Arrays.asList(classe.getInterfaces()).contains(java.io.Serializable.class))
			// controler aussi par h�ritage, boucle jusqu'� Object...
			throw new Exception ("la classe doit impl�menter java.io.Serializable");
		try {
			classe.getConstructor(Socket.class);
		} catch (NoSuchMethodException e) {
			throw new Exception ("la classe doit poss�der un constructeur Socket");
		}
		Field[] fields = classe.getDeclaredFields();
		String getter, setter;
		Method get,set;
		for (Field field : fields){
			int modifiers = field.getModifiers();
			if (Modifier.isStatic(modifiers)) continue; // attribut static, non control�
			if (!Modifier.isPrivate(modifiers)) 
				throw new Exception("l'attribut "+field.getName()+ " doit �tre private");
			String name = field.getName();
			name = (name.substring(0, 1)).toUpperCase() + name.substring(1);
			getter = "get"+name; setter = "set"+name;
			try {
				get = classe.getDeclaredMethod(getter);
				if(!Modifier.isFinal(modifiers))
				{
				set = classe.getDeclaredMethod(setter, field.getType());
					if (!Modifier.isPublic(set.getModifiers())){
						throw new Exception ("le setter de l'attribut "+field.getName()+" doit �tre public");
					}
				}
			} catch (NoSuchMethodException e) {
				throw new Exception ("il manque le getter ou setter de l'attribut "+field.getName());
			}
			if (!Modifier.isPublic(get.getModifiers())){
				throw new Exception ("le getter de l'attribut "+field.getName()+" doit �tre public");
			}
			// etc le getter doit renvoyer le type du field, le setter doit renvoyer void,...
		}
	}
}
