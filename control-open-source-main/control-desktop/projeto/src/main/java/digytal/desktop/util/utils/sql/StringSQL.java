package digytal.desktop.util.utils.sql;

import static digytal.desktop.util.utils.sql.Condition.LIKE;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringSQL {
	private StringBuilder sql = new StringBuilder();
	private Map<String, Condition> conditions = new LinkedHashMap<String, Condition>();
	private String order = "";
	private boolean customCondition =false;
	public void select(String select) {
		sql.append(select);
	}

	public void and(String field, String param) {
		and(false,field, "=", param);
	}
	public void and(boolean newGroup,String field, String param) {
		and(newGroup, field, "=", param);
	}
	public void and( String field, String operator, String param) {
		and(false, field, operator, param);
	}
	public void and(boolean newGroup, String field, String operator, String param) {
		condition(newGroup,"AND", field, operator, param);
		customCondition=true;
	}
	public void expression(String expression){

	}
	public void or(String field, String param) {
		or(field, "=", param);
	}
	public void or(String field, String operator, String param) {
		condition("OR", field, operator, param);
		customCondition=true;
	}
	
	public void where(String field, String param) {
		where(field, "=", param);
	}
	public void where(String field, String operator, String param) {
		condition("WHERE", field, operator, param);
	}

	public void condition(String logic, String field, String operator, String param) {
		condition(false,logic, field, operator, param);
	}
	public void condition(boolean newGroup, String logic, String field, String operator, String param) {
		Condition condition = Condition.of( logic, field, operator,newGroup);
		conditions.put(param,condition);
	}
	public void orderBy(String orderBy) {
		this.order = " ORDER BY " +  orderBy;
	}
	private StringBuilder fixConditions = new StringBuilder();
	public void fixCondition(String condition){
		fixConditions.append(" " + condition);
	}
	public String generate(Filter filter) {
		boolean where = true;
		boolean newGroup =false;
		if( filter.hasFilters()) {
			for (Map.Entry<String, Condition> e : conditions.entrySet()) {
				if(filter.aplly(e.getKey())) {
					Condition c =  e.getValue();
					sql.append(String.format(" %s %s %s %s :%s",  where?"WHERE": (customCondition? c.logic:"AND"),c.newGroup?"(":"", c.field, c.operator,e.getKey()));
					where=false;
					if(c.newGroup && !newGroup)
						newGroup = true;
				}
			}
		}
		sql.append(fixConditions.toString());
		sql.append(this.order);
		System.out.println("Gerando SQL " + sql.toString());
		return sql.toString();
	}
}
