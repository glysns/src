package digytal.desktop.util.utils.http;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Mapper {
	public static ObjectMapper instance;
	static {
		instance = new ObjectMapper();
		instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		instance.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		instance.registerModule(new JavaTimeModule());
		instance.registerModule(new Jdk8Module());

	}
	/**
	 * Function converts list of objects to another list of objects
	 * which' type is specified by the clazz attribute. Clazz attribute
	 * must not be null, or it will throw a NullPointerException.
	 * 
	 * @param list List of target objects
	 * @param clazz Class to map list objects to
	 * @param <T> Target class
	 * @param <F> From class
	 * @return F.class List converted to T.class List
	 */
	public static <T, F> List<T> convertList(List<F> list, Class<T> clazz) {
	    Objects.requireNonNull(clazz);
	    
	    // Important: this must be declared so mapper doesn't throw 
	    // an exception for all properties which it can't map.
	    instance.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	    return Optional.ofNullable(list)
	            .orElse(Collections.emptyList())
	            .stream()
	            .map(obj -> instance.convertValue(obj, clazz))
	            .collect(Collectors.toList());
	}
	//https://www.baeldung.com/jackson-serialize-enums
	//https://stackoverflow.com/questions/6349421/how-to-use-jackson-to-deserialise-an-array-of-objects
	public static <T> List<T> mapList(Object objectMap, Class<T> cls) {
		try {

			JavaType type = instance.getTypeFactory().constructCollectionType(List.class, cls);
			List<LinkedHashMap<String, String>> map = (List<LinkedHashMap<String, String>>) objectMap;
			List list = instance.convertValue(map, type);
			return (List<T>) list;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T mapObjet(Object objectMap, Class cls) {
		try {
			Object object=null;
			if(objectMap instanceof Integer)
				object= Integer.valueOf(objectMap.toString());
			else {
				LinkedHashMap<String, String> map = (LinkedHashMap<String, String>)objectMap;
				object = ((ObjectMapper) instance).convertValue(map, cls);
			}
			return (T) object;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
