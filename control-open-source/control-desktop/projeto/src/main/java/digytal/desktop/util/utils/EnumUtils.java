package digytal.desktop.util.utils;

import java.util.Arrays;

public class EnumUtils {
    public static <T> T valueOf(Class<?> enumm,Object id) {
        if(id==null)
            return null;
        else
            return (T) Arrays.stream(enumm.getEnumConstants()).filter(
                    i->{
                        try {
                            Object value = i.getClass().getMethod("getId").invoke(i); // explicit cast
                            return id.equals(value);
                        }catch (Exception ex){
                            return false;
                        }
                    }
                    ).findFirst().orElse(null);

    }

}
