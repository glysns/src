package digytal.desktop.util.utils.sql;

public class Condition {
	public static final String LIKE = "LIKE";
	public String logic;
	public String field;
	public String operator;

	public boolean newGroup;
	public static Condition of(String logic, String field, String operator,boolean newGroup) {
		Condition condition = new Condition();
		condition.field = field;
		condition.operator = operator;
		condition.logic = logic;
		condition.newGroup = newGroup;
		return condition;
	}
	public Condition() {
		super();
	}
	@Override
	public String toString() {
		return "Condition [logic=" + logic + ", field=" + field + ", operator=" + operator + "]";
	}
	
}
