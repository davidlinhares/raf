package dbutil;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbutil.ClassExtractor;
import dbutil.DBColumn;
import dbutil.DatabaseUtil;

public class Main {
	
	public static void main(String[] args) {
		
		List<String> modelList = null;
		
		String packageName = "models";
		
		try {
			modelList = ClassExtractor.getClassNamesFromPackage(packageName);	
			if(modelList != null) {
				for(String className : modelList) {
					Class<?> theClass = Class.forName(packageName+"."+className);
					if(!Modifier.isAbstract(theClass.getModifiers()) && theClass.isAnnotationPresent(Entity.class)) {
						Field[] fields = theClass.getDeclaredFields();
						List<DBColumn> columns = new ArrayList<>();
						for(Field f : fields) {
							columns.add(new DBColumn(f.getName().toLowerCase(), f.getType().getSimpleName()));
						}
						System.out.println("tbl_"+camelToUnderscore(className));
						DatabaseUtil.createTable("tbl_"+camelToUnderscore(className), columns);
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String camelToUnderscore(String s) {
		Matcher m = Pattern.compile("(?<=[a-z])[A-Z]").matcher(s);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
		    m.appendReplacement(sb, "_"+m.group().toLowerCase());
		}
		m.appendTail(sb);
		s = sb.toString();
		return Character.toLowerCase(s.charAt(0)) + s.substring(1);
	}
	
}
