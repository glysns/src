package digytal.desktop.app.utils;

import java.util.Optional;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

public class Tipos {
	public static Object get(Object modelo, String campo) {
		if (Optional.ofNullable(modelo).isPresent()){
			PropertyAccessor accessor = PropertyAccessorFactory.forDirectFieldAccess(modelo);
			return accessor.getPropertyValue(campo);
		}else
			return null;
		 
	}

	public static Integer inteiro(Object modelo, String campo) {
		return Optional.ofNullable(get(modelo, campo)).isPresent() ? Integer.valueOf(get(modelo,campo).toString()) : null;
	}
	public static Integer id(Object modelo) {
		return Optional.ofNullable(get(modelo, "id")).isPresent() ? Integer.valueOf(get(modelo,"id").toString()) : null;
	}

}
