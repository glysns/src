package digytal.desktop.util.utils.sql;

import java.time.LocalDateTime;
import java.util.*;

import digytal.desktop.util.utils.DateTimeConvert;

public class Filter {
	public static final int DEFAULT_PAGE_INDEX = 0;
	public static final int DEFAULT_PAGE_SIZE = 200;
	public Integer pageIndex; // offset
	public Integer pageSize; // limit
	public Map<String, Object> filters = new LinkedHashMap<>();
	public static Filter of(Map<String, Object> filters) {
		Filter filter = new Filter();
		filter.filters = filters;
		filter.pageIndex = filter.toInt("pageIndex");
		filter.pageSize = filter.toInt("pageSize");
		filters.remove("pageIndex");
		filters.remove("pageSize");
		filter.definePagination();
		return filter;
	}
	public static Filter of() {
		Filter filter = new Filter();
		filter.definePagination();
		return filter;
	}

	public static Filter of(String key, Object value) {
		Filter filter = new Filter();
		filter.definePagination();
		filter.add(key, value);
		return filter;
	}
	public void definePagination() {
		if (pageSize == null || pageIndex ==null || pageSize == 0) {
			this.pageIndex = DEFAULT_PAGE_INDEX;
			this.pageSize = DEFAULT_PAGE_SIZE; // depois mudar para contador de paginas
		}
	}

	public void add(String key, Object value) {
		if(value!=null) {
			boolean put = true;
			if(value instanceof Collection && ((Collection)value).size() ==0)
				put=false;
			if(value instanceof String && ((String)value).trim().length() ==0)
				put =false;
			if(put)
				filters.put(key, value);
		}
	}
	
	public boolean containsKeys(String ... keys) {
		for(String key: keys ) {
			if(filters.containsKey(key))
				return true;
		}
		return false;
	}
	public LocalDateTime dateTimeMin(String key) {
		return dateTime(key, false);
	}

	public LocalDateTime dateTimeMax(String key) {
		return dateTime(key, true);
	}

	public LocalDateTime dateTime(String key, boolean max) {
		Object value = get(key); 
		if(Objects.nonNull(value)) {
			if (max)
				return change(key, DateTimeConvert.localDateTimeMax(value.toString()));
			else
				return change(key, DateTimeConvert.localDateTimeMin(value.toString()));
		}
		return null;
		
	}
	public boolean getBoolean(String key) {
		Object value = get(key);
		if(Objects.nonNull(value)) {
			return value.toString().toLowerCase().equals("true");
		}
		return false;

	}
	public Integer toInt(String key) {
		Object value = get(key);
		if(aplly(key)) {
			String number= value.toString().replaceAll("\\D","");
			if(number.isEmpty())
				return null;

			return change(key, Integer.valueOf(number));
		}
		return null;
	}
	public Long toLong(String key) {
		Object value = get(key);
		if(aplly(key)) {
			String number= value.toString().replaceAll("\\D","");
			if(number.isEmpty())
				return null;
			return change(key, Long.valueOf(number));
		}
		return null;
	}
	public String like(String key) {
		Object value = get(key); 
		if(aplly(key)) {
			return change(key, "%" + value.toString() + "%");
		}
		return null;
	}
	public Object get(String key) {
		return filters.get(key);
	}
	public boolean aplly(String key) {
		Object value = get(key); 
		return value!=null && !value.toString().isEmpty() && value.toString().trim().length() >0;
	}
	public <T> T change(String key, Object value) {
		filters.put(key, value);
		return (T) value;
	}
	public boolean hasFilters() {
		return filters != null && filters.size() > 0;
	}
}
