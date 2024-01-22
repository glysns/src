package com.digytal.control.infra.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;

public abstract class EnumConverter<T extends Enum<T> & EnumerateId, E> implements AttributeConverter<T, E> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final Class<T> clazz;
    public EnumConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E convertToDatabaseColumn(T attribute) {
        return attribute != null ? (E) attribute.getId() : null;
    }
    @Override
    public T convertToEntityAttribute(E dbData) {
        T[] enums = clazz.getEnumConstants();
        for (T e : enums) {
            if (dbData!=null && e.getId().equals(dbData))
                return e;
        }
        return null;
        //throw new UnsupportedOperationException(String.format("%s não pode ser convertido para um item de %s", dbData, clazz));
    }

}

