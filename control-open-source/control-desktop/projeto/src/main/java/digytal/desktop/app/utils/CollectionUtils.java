package digytal.desktop.app.utils;

import java.util.List;

public class CollectionUtils {
	public static <T> T valueOf(List list,Object id) {
        if(id==null)
            return null;
        else
            return (T) list.stream().filter(
                    i->{
                        try {
                            Object value = i.getClass().getDeclaredField("id").get(i); // explicit cast
                            return id.equals(value);
                        }catch (Exception ex){
                            return false;
                        }
                    }
                    ).findFirst().orElse(null);

    }
}
