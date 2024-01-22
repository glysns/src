package com.digytal.control.infra.sql;

public class Condition {
    private String logic = "AND";
    private String field;
    private String relational = "=";
    private String param;
    private boolean apply;

    public static Condition of(String field,String logic){
        Condition instance = new Condition();
        instance.field = field;
        instance.logic = logic;
        return instance;
    }

    public void setApply(boolean apply) {
        this.apply = apply;
    }

    public boolean isApply() {
        return apply;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }
    public String getLogic() {
        return logic;
    }

    public void setLogic(String logic) {
        this.logic = logic;
    }

    public String getRelational() {
        return relational;
    }

    public void setRelational(String relational) {
        this.relational = relational;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "logic='" + logic + '\'' +
                ", field='" + field + '\'' +
                ", relational='" + relational + '\'' +
                ", param='" + param + '\'' +
                ", apply=" + apply +
                '}';
    }
}