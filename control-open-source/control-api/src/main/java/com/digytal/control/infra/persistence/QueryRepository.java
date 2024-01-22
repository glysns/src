package com.digytal.control.infra.persistence;

import com.digytal.control.infra.sql.PageRecord;
import com.digytal.control.infra.sql.StringSQL;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Repository
@Primary
public class QueryRepository {
    @PersistenceContext
    protected EntityManager entityManager;
    public <T> List<T> search(StringSQL sql, Class dto) {
        return  searchPage(sql,dto,false).getRecords();
    }
    public PageRecord searchPage(StringSQL sql, Class dto, boolean perPage) {
        List records = null;
        try {
            String finalSql = sql.generate();
            Query query = entityManager.createQuery(finalSql, Tuple.class);
            filter(query, sql.getFilters());
            int total = 0;
            if (perPage) {
                //paginate(query, sql.getPageIndex(), sql.getPageSize());
                //total = count(finalSql,sql.getFilters());
            }
            List<Tuple> result = query.getResultList();
            records = convertCollection(result, dto);
            return PageRecord.of(records, sql.getPageIndex(), sql.getPageSize(), total);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao converter");
        }
    }
    private int count(String sql, Map<String, Object> filters) {
        String countSql = "SELECT count (*) as total FROM ";
        countSql = countSql + sql.split("FROM")[1];
        countSql = countSql.split("ORDER BY")[0];
        countSql = countSql.replace("ORDER BY", "");
        Query query = entityManager.createNativeQuery(countSql);
        filter(query, filters);
        BigInteger count = (BigInteger) query.getSingleResult();
        return count == null ? 0 : count.intValue();

    }
    private void filter(Query query, Map<String, Object> filters) {
        boolean filtrar = filters !=null &&  filters.size() >0;
        if (filtrar) {
            filters.forEach((k, v) -> {
                if (v!=null && v.toString().trim().length() >0) {
                    //System.out.println(String.format("adicionando parametro: { %-15s : %s }", k, v));
                    query.setParameter(k, v);
                }
            });
        }
    }
    private <T> List<T> convertCollection(List<Tuple> result, Class dto) {
        List list = new ArrayList();
        result.forEach(tuple -> {
            list.add(convertElement(tuple, dto));
        });
        return list;

    }

    private <T> T convertElement(Tuple record, Class dto) {
        Object item = null;
        String field = null;
        try {
            item = dto.newInstance();
            for (TupleElement te : record.getElements()) {
                field = te.getAlias();
                Object value = record.get(field);
                set(item, field, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) item;
    }
    private void set(Object item, String field, Object value) throws Exception {
        if (value != null) {
            Class typeValue = value.getClass();
            if (typeValue.equals(Integer.class))
                value = new BigDecimal(value.toString()).intValue();
            else if (typeValue.equals(Long.class))
                value = new BigDecimal(value.toString()).longValue();
            else if (typeValue.equals(java.sql.Timestamp.class))
                value = ((java.sql.Timestamp) value).toLocalDateTime();
            else if (typeValue.equals(java.sql.Date.class))
                value = ((java.sql.Date) value).toLocalDate();

            /**
             * Usando o spring, depois ver outras formas como BeansUtils
             * https://stackoverflow.com/questions/10009052/invoking-setter-method-using-java-reflection
             * https://www.logicbig.com/how-to/reflection/setting-nested-field-value.html
             */
            PropertyAccessor myAccessor = PropertyAccessorFactory.forDirectFieldAccess(item);
            String atttibute = field.replaceAll("\\_",".");
            recursive(atttibute,myAccessor);
            myAccessor.setPropertyValue(atttibute, value);
        }
    }
    private void recursive(String atttibute, PropertyAccessor myAccessor ) throws Exception{
        String[] path = atttibute.split("\\.");
        int length =path.length;
        while (length>1){
            String nested = path[path.length-2];
            Class type = myAccessor.getPropertyType (nested);
            if(myAccessor.getPropertyValue (nested)==null) {
                myAccessor.setPropertyValue(nested, type.newInstance());
            }
            length --;
        }

    }

}