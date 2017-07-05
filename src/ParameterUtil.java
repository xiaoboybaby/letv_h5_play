import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class ParameterUtil {
	/**
	 * ������������ȡ����ֵ
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
	 * ��ȡ����������
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
	 * ��ȡ��������(type)��������(name)������ֵ(value)��map��ɵ�list
	 * */
	public static Map getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		Map map = new HashMap();
		Map infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			map.put(fields[i].getName(),infoMap);
		}
		return map;
	}

	/**
	 * ��ȡ�������������ֵ������һ����������
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
