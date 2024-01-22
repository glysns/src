package com.digytal.control.infra.commons.validation;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Pattern.CASE_INSENSITIVE);

    public static boolean isEmpty(Object value){
        return value==null || value.toString().trim().isEmpty() || value.toString().length()==0;
    }
    public static boolean isNotEmpty(Object value) {
        return !isEmpty(value);
    }
    public static boolean isZero(Number value){
        //Double.compare(0.0,value)==0
        return value==null || BigDecimal.ZERO.compareTo(new BigDecimal(value.toString()))==0;
    }

    private static final int[] weightCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] weightCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int cpfCnpjCalculationDigits(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean cpf(String cpf) {
        cpf = cpf.replaceAll("\\D","");
        if ((cpf==null) || (cpf.length()!=11)) return false;

        if (isSameDigit(cpf)) return false;

        Integer digito1 = cpfCnpjCalculationDigits(cpf.substring(0,9), weightCPF);
        Integer digito2 = cpfCnpjCalculationDigits(cpf.substring(0,9) + digito1, weightCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    public static boolean cnpj(String cnpj) {
        cnpj = cnpj.replaceAll("\\D","");
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        if (isSameDigit(cnpj)) return false;

        Integer digito1 = cpfCnpjCalculationDigits(cnpj.substring(0,12), weightCNPJ);
        Integer digito2 = cpfCnpjCalculationDigits(cnpj.substring(0,12) + digito1, weightCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }
    public static boolean cpfCnpj(String cpfCnpj) {
        cpfCnpj = cpfCnpj.replaceAll("\\D","");
        if ((cpfCnpj==null) || (cpfCnpj.length()<11)) return false;
        else{
            if(cpfCnpj.length()==11)
                return cpf(cpfCnpj);
            else
                return cnpj(cpfCnpj);
        }
    }
    private static boolean isSameDigit(String cpfCnpj){
        for(int x=0;x<=9;x++){
            Pattern p = Pattern.compile(String.format("^[%d]{11}$",x));
            Matcher m = p.matcher(cpfCnpj);
            if(m.matches())
                return true;
        }
        return false;
    }
    public static boolean email(String email){
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
    public static boolean minLength(Object value, int min){
        return value==null? true: value.toString().length()>=min;
    }
    public static boolean maxLength(Object value, int max){
        return value==null? true: value.toString().length()<=max;
    }
    public static boolean rangeLength(Object value, int min, int max){
        return minLength(value,min) && maxLength(value, max);
    }
}
