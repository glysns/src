package com.digytal.control.infra.commons.definition;

import com.digytal.control.infra.commons.validation.Attributes;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Definitions {
    private enum Type{
        UPPER,
        LOWER,
        DIGITS,
        NORMALIZE,
        UPPER_NORMALIZE;
    }

    private Map<Type,List<String>> defintions = new LinkedHashMap<>();
    private static Definitions instance;

    private Definitions(){

    }
    private static String[] names(Attributes... fields){
        return Arrays.stream(fields).map(f -> f.getAttribute()).toArray(String[]::new);
    }
    public static Definitions upperCase(Attributes... fields){
        return upperCase(names(fields));
    }
    public static Definitions upperNormalize(Attributes... fields){
        return upperNormalize(names(fields));
    }
    public static Definitions upperNormalize(String ... attributes){
        return put(Type.UPPER_NORMALIZE,attributes);
    }
    public static Definitions upperCase(String ... attributes){
        return put(Type.UPPER,attributes);
    }
    public static Definitions lowerCase(Attributes... fields){
        return lowerCase(names(fields));
    }
    public static Definitions lowerCase(String ... attributes){
        return put(Type.LOWER,attributes);
    }
    public static Definitions onlyDigits(Attributes... fields){
        return onlyDigits(names(fields));
    }
    public static Definitions onlyDigits(String ... attributes){
        return put(Type.DIGITS,attributes);
    }
    public static Definitions normalize(Attributes... fields){
        return normalize(names(fields));
    }
    public static Definitions normalize(String ... attributes){
        return put(Type.NORMALIZE,attributes);
    }

    public static Definitions build(){
        instance = new Definitions();
        return instance;
    }
    public static Definitions put(Type type, String ... attributes){
        instance.defintions.put(type,Arrays.asList(attributes));
        return instance;
    }
    public void define(final Object object){
        for (Map.Entry<Type,List<String>> entry : instance.defintions.entrySet()) {
            List<String> fields = entry.getValue();
            fields.forEach(f->{
                define(object,entry.getKey(), f);
            });
        }
    }
    private void define(Object object,Type type, String field){
        PropertyAccessor accessor = PropertyAccessorFactory.forDirectFieldAccess(object);
        Object value = accessor.getPropertyValue(field);
        if(value!=null){
            if(type== Type.UPPER)
                value = Text.upperCase(value);
            else if(type== Type.LOWER)
                value = Text.lowerCase(value);
            else if(type== Type.DIGITS)
                value = Text.onlyDigits(value);
            else if(type== Type.NORMALIZE)
                value = Text.normalize(value);
            else if(type== Type.UPPER_NORMALIZE) {
                value = Text.upperCase(value);
                value = Text.normalize(value);
            }

            accessor.setPropertyValue(field,value);
        }
    }
}

