package com.game.util;


import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ToolUtils {

	public static long getNowTime(){
		return System.currentTimeMillis();
	}

	/**
	 * 检查是否为空，包括null ""  0
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Map obj){
		if (obj == null){
			return true;
		}
		if (obj.size() == 0){
			return true;
		}
		return false;
	}

	/**
	 * 检查是否为空，包括null ""  0
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(List obj){
		if (obj == null){
			return true;
		}
		if (obj.size() == 0){
			return true;
		}
		return false;
	}

	/**
	 * 检查是否为空，包括null ""  0
	 * @param obj
	 * @return
     */
	public static boolean isEmpty(Object obj){
		if (obj == null){
			return true;
		}
		if (obj.getClass().getClass() == String.class.getClass() && "".equals(obj)){
			return true;
		}
		if (obj.getClass().getClass() == Integer.class.getClass() && obj.equals(0)){
			return true;
		}
		if (obj.getClass().getClass() == Long.class.getClass() && obj.equals(0l)){
			return true;
		}
		if (obj.getClass().getClass() == Float.class.getClass() && obj.equals(0f)){
			return true;
		}
		if (obj.getClass().getClass() == Double.class.getClass() && obj.equals(0d)){
			return true;
		}
		return false;
	}
	/**
	 * 转换int
	 * @param obj
	 * @return
	 */
	public static int cover2int(String obj) {
		return isEmpty(obj)?0:Integer.parseInt(obj);
	}
	
	/**
	 * 转换成long
	 * @param obj
	 * @return
	 */
	public static long cover2Long(String obj) {
		return isEmpty(obj)?0:Long.parseLong(obj);
	}

	/**
	 *
	 * @param obj
	 * @return
     */
	public static String cover2String(String obj) {
		return obj == null? "":obj+"";
	}

	public static Map<Integer, Integer> parseMap(String target,String split1,String split2) {
		if(isEmpty(target)){
			return null;
		}
		Map<Integer,Integer> result = new HashMap<>();
		String[] data1  = target.split(split1);
		for(String data : data1){
			String[] data2  =  data.split(split2);
			result.put(cover2int(data2[0]),cover2int(data2[1]));
		}
		return result;
	}
	
	
	public static int[] parseIntArray(String dataStr,String splitFlag) {
		if (dataStr == null || "".equals(dataStr.trim()))
			return null;
		String[] datas = dataStr.split(splitFlag);
		int[] result = new int[datas.length];
		for (int i = 0; i < datas.length; i++) {
			result[i] = cover2int(datas[i]);
		}
		return result;
	}
	
	/**
	 * 相加两个相同类型的Map
	 * @param map1
	 * @param map2
	 * @return
	 */
	public static Map<Integer,Integer> addMap(Map<Integer,Integer> map1,Map<Integer, Integer> map2) {
		if(map1 == null && map2 == null){
			return null;
		}else if(map1 == null){
			return new HashMap<>(map2); 
		}else if(map2 == null){
			return new HashMap<>(map1);
		}
		Map<Integer,Integer> result = new HashMap<>(map1);
		for (Entry<Integer, Integer> element : map2.entrySet()) {
			int key = element.getKey();
			int value = element.getValue();
			Integer oldValue = result.get(key);
			result.put(key, oldValue == null ? value : value+oldValue);
		}
		
		return result;
	}
	
	
	/**
	 * 相减两个相同类型的Map map1 - map2
	 * @param map1 
	 * @param map2
	 * @return
	 */
	public static Map<Integer,Integer> deleteMap(Map<Integer,Integer> map1,Map<Integer, Integer> map2) {
		if(map1 == null){
			return null;
		}else if(map2 == null){
			return new HashMap<>(map1);
		}
		Map<Integer,Integer> result = new HashMap<>(map1);
		for (Entry<Integer, Integer> element : map2.entrySet()) {
			int key = element.getKey();
			int value = element.getValue();
			
			Integer value2 = result.get(key);
			if(value2 !=null ){
				result.put(key, filterMinus(value2-value));
			}
		} 
		return result;
	}
	
	/**
	 * 过滤掉负数 如果为负数 返回结果：0
	 * @param i
	 * @return
	 */
	public static int filterMinus(int i) {
		return i<0?0:i;
	}
	
	/**
	 * 过滤掉负数 如果为负数 返回结果：0
	 * @param i
	 * @return
	 */
	public static long filterMinus(long i) {
		return i<0?0:i;
	}
	
	/**
	 * 是否包含指定数据
	 * @param datas
	 * @param data
	 * @return
	 */
	public static boolean contains(int[] datas, int data) {
		if(datas == null){
			return false;
		}
		for (int i : datas) {
			if(i == data) {
				return true;	
			}
		}
		return false;
	}
	
	public static void listCommandClass(String filterPackagePath ,File file,String prefix,List<Class<?>> list){
		File[] files = file.listFiles();
		if(null!=files){
			try{
				for(File f : files){
					if(f.isDirectory()){
						listCommandClass(filterPackagePath,f,prefix,list);
					}else{
						if(f.getName().endsWith(".class")){
						
							String parent = f.getParent();
							String name = f.getName();
							String packageName = parent.length() >prefix.length()? parent.substring(prefix.length()+1)+File.separator :"";
							String classpath = (packageName+name.substring(0, name.length()-6)).replace("\\", ".");
							
							//对指定的包才做检查
							if(classpath.contains(filterPackagePath)){
								list.add(Class.forName(classpath.replace(File.separator, ".")));
							}
//							if(easyexecutorMeta.isScanPackage(classpath)){
//								list.add(Class.forName(classpath.replace(File.separator, ".")));
//							}
						}else if(f.getName().endsWith(".jar")){
							JarFile jarFile = new JarFile(f);
							Enumeration<JarEntry> jarEntries = jarFile.entries();
							while(jarEntries.hasMoreElements()){
								JarEntry jarEntry = jarEntries.nextElement();
								if(jarEntry.getName().endsWith(".class")){

									String jarEntryName = jarEntry.getName();
									String classpath = jarEntryName.substring(0, jarEntryName.length()-6).replace("/", ".");
									if(classpath.contains(filterPackagePath)){
										list.add(Thread.currentThread().getContextClassLoader().loadClass(classpath));
									}
								}
							}
						}
					}
				}
			}catch (Exception e) {
				throw new RuntimeException("error in listCommandClass",e);
			}
		}
	}
}
