package com.digytal.control.infra.commons.definition;
import java.math.BigDecimal;
import java.util.Objects;
public class Definition {
    public static <T> T seNulo(Object valor, Object valorPadrao){
        return (T) Objects.requireNonNullElse(valor, valorPadrao);
    }
    public static String seNulo(String valor, String valorPadrao){
        return Objects.toString(valor, valorPadrao);
    }
    public static String seNulo(String valor){
        return Objects.toString(valor, "");
    }
    public static String seNuloOuVazio(String valor){
        return seNuloOuVazio(valor, "");
    }
    public static String seNuloOuVazio(String valor, String valorPadrao){
        return seNuloOuVazio(valor, valorPadrao, valorPadrao.length());
    }
    public static String seNuloOuVazio(String valor, String valorPadrao, Integer numeroCaracteres){
        String texto = seNulo(valor,"");
        return texto.isBlank() ? Text.maxLength(valorPadrao, numeroCaracteres)  : texto ;
    }
}
