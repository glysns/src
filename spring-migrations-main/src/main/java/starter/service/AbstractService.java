package starter.service;

import java.lang.reflect.Field;
import java.util.Arrays;

public class AbstractService {
    protected void convertSpringToUppercase(Object entity, String ... desconsiderar){
        try{
            for (Field field : entity.getClass().getDeclaredFields()) {
                if (!Arrays.asList(desconsiderar).contains(field.getName())){
                    if (field.getType().equals(String.class)) {
                        if (!field.isAccessible())
                            field.setAccessible(true);
                        if (field.get(entity) != null && ((String) field.get(entity)).trim() != "") {
                            field.set(entity, ((String) field.get(entity)).toUpperCase());
                        }
                    }
                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
