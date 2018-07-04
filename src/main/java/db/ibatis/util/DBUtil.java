package db.ibatis.util;

public class DBUtil {
	
	/**
	 * 
	 * @param columName   数据库的字段名字
	 * @param flag  首字母是否大写
	 * @return
	 */
	public static String coverToPropName(String columName, boolean flag) {
		int index = 0;
		while ((index = columName.indexOf("_")) != -1) {
			String targetChar = columName.substring(index + 1, index + 2);
			String bigChar = targetChar.toUpperCase();
			columName = columName.replace("_" + targetChar, bigChar);
		}

		String firstChar = columName.substring(0, 1);
		if (flag) {
			columName = columName.replaceFirst(firstChar,
					firstChar.toUpperCase());
		}
		return columName;
	}
}
