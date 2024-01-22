package com.digytal.control.infra.commons.validation;

import com.digytal.control.infra.business.*;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Validations {
    private enum Type{
        RANGE_LEN,
        MIN_LEN,
        MAX_LEN,
        NOT_EMPTY,
        CPF_CNPJ,
        EMAIL,
        ZERO,
        ;
    }

    private Map<Type, Object[]> defintions = new LinkedHashMap<>();
    private static Validations instance;

    private Attributes[] fields;

    private Validations(){

    }
    public static Validations build(Attributes... fields){
        instance = new Validations();
        instance.fields = fields;
        return instance;
    }
    public Validations minLen(int min){
        return put(Type.MIN_LEN,min);
    }
    public Validations cpfCnpj(){
        return put(Type.CPF_CNPJ);
    }
    public Validations email(){
        return put(Type.EMAIL);
    }
    public Validations maxLen(int max){
        return put(Type.MAX_LEN,max);
    }
    public Validations rangeLen(int min,int max){
        return put(Type.RANGE_LEN,min,max);
    }
    public Validations notEmpty(){
        return put(Type.NOT_EMPTY,0);
    }
    public Validations notZero() {return put(Type.ZERO);
    }
    private Validations put(Type type, Object ... conditions){
        instance.defintions.put(type,conditions);
        return  instance;
    }
    public void check(Object object) throws BusinessException {
        for (Map.Entry<Type, Object[]> entry : instance.defintions.entrySet()) {
            check(object, entry.getKey(), entry.getValue());
        }
    }
    private void check(Object object, Type type, Object[] conditions){
       for(Attributes field:instance.fields){
           String label = field.getLabel();
           PropertyAccessor accessor = PropertyAccessorFactory.forDirectFieldAccess(object);
           Object value = accessor.getPropertyValue(field.getAttribute());
           String condition = conditions!=null && conditions.length >0 ? conditions[0].toString():null;
           if(value!=null) {
               if (type == Type.NOT_EMPTY) {
                   if (Validation.isEmpty(value))
                       throw new CampoObrigatorioException(label);
               } else if (type == Type.RANGE_LEN) {
                   int min = Integer.parseInt(condition);
                   int max = Integer.parseInt(conditions[1].toString());
                   if (!Validation.rangeLength(value, min, max))
                       throw new TamanhoMinimoMaximoException(label, min, max);
               } else if (type == Type.MIN_LEN) {
                   int min = Integer.parseInt(condition);
                   if (!Validation.minLength(value, min))
                       throw new TamanhoMinimoException(label, min);
               } else if (type == Type.MAX_LEN) {
                   int max = Integer.parseInt(condition);
                   if (!Validation.maxLength(value, max))
                       throw new TamanhoMaximoException(label, max);
               }  else if (type == Type.CPF_CNPJ) {
                   if (!Validation.cpfCnpj(value.toString()))
                       throw new CpfCnpjInvalidoException();
               } else if (type == Type.EMAIL) {
                   if (!Validation.email(value.toString()))
                       throw new EmailInvalidoException();
               } else if (type == Type.ZERO) {
                   if(new BigDecimal(value.toString()).equals(BigDecimal.ZERO))
                       throw new NumeriZeroException(label);
               }
           }else{
               if (type == Type.NOT_EMPTY) {
                   throw new CampoObrigatorioException(label);
               }
           }
       }
    }
}
