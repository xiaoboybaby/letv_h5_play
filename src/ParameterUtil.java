import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class ParameterUtil {
	/**
	 * 根据属性名获取属性值
	 * */
	private static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Object value = o.getClass().getField(fieldName).get(o);
//			Method method = o.getClass().getMethod(getter, new Class[] {});
//			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			//log.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 获取属性名数组
	 * */
	private static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			//System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
	 * */
	public static Map getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		Field[] superFields = o.getClass().getSuperclass().getDeclaredFields();
		String[] fieldNames = new String[fields.length + superFields.length];
		Map map = new HashMap();
		Map infoMap = null;
		Map infoMap2 = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			map.put(fields[i].getName(),infoMap);
			if("gp".equals(infoMap.get("name"))){
				infoMap2 = getFiledsInfo(getFieldValueByName(fields[i].getName(), o));
				map.putAll(infoMap2);
			}
		}
		
		
		
//		for (int i = 0; i < superFields.length; i++) {
//			infoMap = new HashMap();
//			infoMap.put("type", superFields[i].getType().toString());
//			infoMap.put("name", superFields[i].getName());
//			infoMap.put("value", getFieldValueByName(superFields[i].getName(), o));
//			map.put(superFields[i].getName(),infoMap);
//		}
		return map;
	}

	/**
	 * 获取对象的所有属性值，返回一个对象数组
	 * */
	public static Object[] getFiledValues(Object o) {
		String[] fieldNames = getFiledName(o);
		Object[] value = new Object[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			value[i] = getFieldValueByName(fieldNames[i], o);
		}
		return value;
	}
}
