package com.digytal.control.infra.commons.definition;

import java.text.Normalizer;

public class Text {
    private enum Operation{
        UPPER_CASE,
        LOWER_CASE,
        REMOVE_ESPECIAL_CHAR,
        ONLY_DIGITS,
        NORMALIZER,
        UPPER_NORMALIZE;
    }
    public static String upperCase(Object value){
        return replace(Operation.UPPER_CASE,value);
    }
    public static String upperNormalize(Object value){
        return replace(Operation.UPPER_NORMALIZE,value);
    }
    public static String lowerCase(Object value){
        return replace(Operation.LOWER_CASE,value);
    }
    public static String removeSpecialChar(Object value){
        return replace(Operation.REMOVE_ESPECIAL_CHAR,value);
    }
    public static String onlyDigits(Object value){
        return replace(Operation.ONLY_DIGITS,value);
    }
    public static String normalize(Object value){
        return replace(Operation.NORMALIZER,value);
    }
    private static String replace(Operation operation, Object value ){
        try {
            String sValue=null;
            if(value!=null ){
                sValue= value.toString();
                if(operation== Operation.UPPER_CASE)
                    sValue=value.toString().toUpperCase();
                if(operation== Operation.LOWER_CASE)
                    sValue=value.toString().toLowerCase();
                else if(operation== Operation.REMOVE_ESPECIAL_CHAR)
                    sValue=value.toString().toUpperCase();
                else if(operation== Operation.ONLY_DIGITS)
                    sValue=value.toString().replaceAll("\\D","");
                else if(operation== Operation.NORMALIZER)
                   sValue = Normalizer.normalize(value.toString(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                else if(operation== Operation.UPPER_NORMALIZE) {
                    sValue = normalize(value).toUpperCase();
                }
                sValue = sValue.toString().trim();
            }
            return sValue;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static String adjust(String text, int maxLength){
        String format = "%-".concat(String.format("%d.%<ds", maxLength ));
        return text==null ? null : String.format(format, text);
    }
    public static String maxLength(String text, int maxLength){
        return text==null ? null : adjust(text, maxLength).trim();
    }

}