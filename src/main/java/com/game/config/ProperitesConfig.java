package com.game.config;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List; 
import java.util.Map; 
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder; 

/**
 * 读取Properites配置文件
 * @author xiewen
 *
 */
@SuppressWarnings("unchecked")
public class ProperitesConfig {
	    private  String propertiesPath; 
	    private static Map<String,ResourceBundle> rbMap=new HashMap<String, ResourceBundle>();
	    
		public ProperitesConfig(String propertiesPath) {
			super();
			this.propertiesPath = propertiesPath;
			load();
		}
 
		public void load() {
			try {
				InputStream in = ProperitesConfig.class.getClassLoader().getResourceAsStream(propertiesPath);
				SAXBuilder builder = new SAXBuilder(false); 
				Document doc = builder.build(in);
				Element rootE = doc.getRootElement(); 
				List<Element> beansE = rootE.getChildren("bean");
				for (Element beanE : beansE) {
					String name = beanE.getAttributeValue("name");
					String path = beanE.getAttributeValue("path");
					//config.log 目录使用点  如  config.log
				    ResourceBundle rb = ResourceBundle.getBundle(path);
					rbMap.put(name, rb); 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 获得proterties的数据
		 * @param properitesName：在这个里面有对应的常量ProperitesConst.java
		 * @param key
		 * @return
		 */
		public static String getString(String properitesName,String key){
			String value = "";
			ResourceBundle  rb=null;
			try{
				rb=rbMap.get(properitesName);
				if(rb==null){
					System.err.println("请检查是否有配置文件config/protertiesfile/"+properitesName+".properties");
					return value;
				}
				value=rb.getString(key);
				value=new String(value.getBytes("ISO-8859-1"),"utf-8");
			}catch (MissingResourceException mre){
				System.err.println("请检查配置文件config/protertiesfile/"+properitesName+".properties，缺少key:" + key);
				mre.printStackTrace();
			}catch (Exception e){
				e.printStackTrace();
			}
			return value;
		}

		/**
		 * 获得proterties的数据
		 * @param properitesName：在这个里面有对应的常量ProperitesConst.java
		 * @param key
		 * @return
		 */
		public static int getInt(String properitesConst,String key){
			int value = 0;
			ResourceBundle  rb=null;
			try{
				rb=rbMap.get(properitesConst);
				if(rb==null){
					System.err.println("请检查是否有配置文件config/protertiesfile/"+properitesConst+".properties");
					return value;
				}
				value =Integer.parseInt(rb.getString(key.trim()));
			}catch (MissingResourceException mre){
				System.err.println("请检查配置文件config/protertiesfile/"+properitesConst+".properties，缺少key:" + key);
				mre.printStackTrace();
			}catch (Exception e){
				System.err.println("请检查配置文件config/protertiesfile/"+properitesConst+".properties, key=" + key);
				e.printStackTrace();
			}
			return value;
		}
}
