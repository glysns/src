package com.digytal.control.infra.sql;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class StringSQL {
    public static Integer DEFAULT_PAGE_SIZE=50;
    private enum Definition{
        UPPER,
        LOWER,
        INT,
        LONG,
        DOUBLE,
        LOCAL_DATE,
        LOCAL_DATE_TIME,
        SIMILAR,
        SIMILAR_LEFT,
        VETOR,
        TEXT,
        ENUM,
        SIMILAR_UPPERCASE,
    }
    private enum RelationalOperator{
        EQUAL("="),
        DIFFERENT("<>"),
        LIKE("LIKE"),
        LESS_THEN("<"),
        GREATER_THEN(">"),
        LESS_THEN_EQUAL("<="),
        GREATER_THEN_EQUAL(">="),
        IN("IN"),
        BETWEEN("NOT USE IN THE MOMENT"),

        ;
        private String symbol;
        private RelationalOperator(String symbol){
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }
    private int pindex=0;
    private String order = "";
    private String select;
    private Map<String, Object> filters =new LinkedHashMap<>();
    private StringBuilder where = new StringBuilder();
    private Map<String, Condition> conditions = new LinkedHashMap<>();
    private Condition currentCondition;
    private Integer pageIndex=0;
    private Integer pageSize;
    public StringSQL select(String select){
        this.select = select;
        return this;
    }
    public void orderBy(String orderBy) {
        this.order = " ORDER BY " +  orderBy;
    }
    public StringSQL setFilters(Map<String, Object> filters) {
        this.pageIndex= Integer.valueOf(Objects.toString(filters.remove("pageIndex"),"1"));
        this.pageSize= Integer.valueOf(Objects.toString(filters.remove("pageSize"),DEFAULT_PAGE_SIZE.toString()));
        this.filters = filters;
        return this;
    }
    //conditions
    public StringSQL where(String field){
        addCondition(field,"WHERE");
        return this;
    }
    public StringSQL and(String field){
        addCondition(field,"AND");
        return this;
    }
    public StringSQL or(String field){
        addCondition(field,"OR");
        return this;
    }
    //param like collum
    public StringSQL equal(){
        return equal(paramByCollum());
    }

    public StringSQL different(){
        return different(paramByCollum());
    }
    public StringSQL like(){
        return like(paramByCollum());
    }
    //relational operators
    public StringSQL greaterThan(String param){
        return relationalOperator(param, RelationalOperator.GREATER_THEN);
    }
    public StringSQL greaterThanEqual(){
        return  greaterThanEqual(paramByCollum());
    }
    public StringSQL greaterThanEqual(String param){
        return relationalOperator(param, RelationalOperator.GREATER_THEN_EQUAL);
    }
    public StringSQL lessThan(String param){
        return relationalOperator(param, RelationalOperator.LESS_THEN);
    }

    public StringSQL lessThanEquals(){
        return lessThanEquals(paramByCollum());
    }
    public StringSQL lessThanEquals(String param){
        return relationalOperator(param, RelationalOperator.LESS_THEN_EQUAL);
    }
    public StringSQL like(String param){
        return relationalOperator(param, RelationalOperator.LIKE);
    }
    public StringSQL in(String param){
        return relationalOperator(param, RelationalOperator.IN);
    }
    public StringSQL different (String param){
        return relationalOperator(param, RelationalOperator.DIFFERENT);
    }
    public StringSQL equal(String param){
        return relationalOperator(param, RelationalOperator.EQUAL);
    }
    //definitions
    public StringSQL uppercase(){
        define(Definition.UPPER);
        return this;
    }
    public StringSQL integer(){
        define(Definition.INT);
        return this;
    }
    public StringSQL decimal(){
        define(Definition.DOUBLE);
        return this;
    }
    public StringSQL text(){
        define(Definition.TEXT);
        return this;
    }
    public StringSQL enumeration(){
        define(Definition.ENUM);
        return this;
    }
    public StringSQL similar(){
        define(Definition.SIMILAR);
        return this;
    }
    public StringSQL similarUppercase(){
        define(Definition.SIMILAR_UPPERCASE);
        return this;
    }

    public StringSQL similarLeft(){
        define(Definition.SIMILAR_LEFT);
        return this;
    }
    public StringSQL vetor(){
        define(Definition.VETOR);
        return this;
    }
    public StringSQL localDate(){
        define(Definition.LOCAL_DATE);
        return this;
    }
    public StringSQL localDateTime(){
        define(Definition.LOCAL_DATE_TIME);
        return this;
    }
    //filters
    public StringSQL filter(String param, Object value){
        this.filters.put(param,value);
        return this;
    }
    public StringSQL integer(Integer value){
        filter(currentCondition.getParam(), value);
        return integer();
    }
    public StringSQL decimal(Double value){
        filter(currentCondition.getParam(), value);
        return decimal();
    }
    public StringSQL similar(String value){
        filter(currentCondition.getParam(), value);
        return similar();
    }
    public StringSQL similarUppercase(String value){
        filter(currentCondition.getParam(), value);
        return similarUppercase();
    }
    public StringSQL text(String value){
        filter(currentCondition.getParam(), value);
        return text();
    }
    public StringSQL similarLeft(String value){
        filter(currentCondition.getParam(), value);
        return similarLeft();
    }


    private void define(Definition definition){
        Object value = filters.get(currentCondition.getParam());
        if(value!=null){
            String str = value.toString();
            if(str.trim().length() >0 ){
                currentCondition.setApply(true);
                if(Definition.UPPER == definition)
                    value = str.toUpperCase();
                else if(Definition.LOWER == definition)
                    value = str.toLowerCase();
                else if(Definition.SIMILAR == definition)
                    value = "%".concat(str).concat("%");
                else if(Definition.SIMILAR_LEFT == definition)
                    value = str.concat("%");
                else if(Definition.LOCAL_DATE == definition)
                    value = LocalDate.parse(str);
                else if(Definition.LOCAL_DATE_TIME == definition)
                    value = LocalDateTime.parse(str);
                else if(Definition.INT == definition)
                    value = Integer.valueOf(str);
                else if(Definition.DOUBLE == definition)
                    value = Double.valueOf(str);
                else if(Definition.SIMILAR_UPPERCASE == definition){
                    value = "%".concat(str).concat("%");
                    value = value.toString().toUpperCase();
                }

            }
            filters.put(currentCondition.getParam(),value);
        }
    }
    private String paramByCollum(){
        String param = currentCondition.getField().replaceAll("\\w\\.","");
        return param;
    }
    private StringSQL relationalOperator(String param, RelationalOperator operator){
        currentCondition.setRelational(operator.symbol);
        currentCondition.setParam(param);
        return this;
    }
    public String generate(){
        StringBuilder sql = new StringBuilder();
        sql.append(select);
        boolean where = true;
        for(Map.Entry<String,Condition> map: conditions.entrySet()){
            Condition c = map.getValue();
            if(c.isApply()) {
                sql.append(String.format(" %s %s %s :%s", where ? "WHERE" : c.getLogic(), c.getField(), c.getRelational(), c.getParam()));
                where=false;
            }
        }
        sql.append(this.order);
        //System.out.println("Gerando SQL " + sql.toString());
        return sql.toString();
    }
    private void addCondition(String field, String logic){
        currentCondition= Condition.of(field,logic);
        conditions.put(field+(pindex++), currentCondition);
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Map<String, Object> getFilters() {
        return filters;
    }

}