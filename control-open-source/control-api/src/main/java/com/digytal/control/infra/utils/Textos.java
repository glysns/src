package com.digytal.control.infra.utils;

public class Textos {
    public static String retiraSeparadores(String texto) {
        return texto==null ? "": texto.replaceAll("\\D", "");
    }
    public static String retirarAcentos(String str) {
        String temp = null;
        temp = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD);
        temp = temp.replaceAll("[^\\p{ASCII}]", "");
        return temp;
    }

    public static String formatarCep(String cep) {
        String texto = retiraSeparadores(cep);
        return texto.replaceAll("(\\d{2})(\\d{3})(\\d{3})", "$1.$2-$3");
    }
    public static String formatarCpfCnpj(String cep) {
        String texto = retiraSeparadores(cep);
        if(texto.length()==11)
            return texto.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        else if (texto.length()==14)
            return texto.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        else
            return texto;
    }

}
