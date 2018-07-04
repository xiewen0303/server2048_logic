package com.game.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import log.LogUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.game.mapping.manage.GotoManager;
import com.game.util.ToolUtils;

/**
 * 读取游戏数值配置
 */
public class TemplatesManager {
 

	private final static Map<Class<IBeanConfigurable>, Map<Object, ? extends IBeanConfigurable>> beanMap = new HashMap<Class<IBeanConfigurable>, Map<Object, ? extends IBeanConfigurable>>();
	private Map<String, Map<String, Class<IBeanConfigurable>>> fileSheetClassMap = new HashMap<String, Map<String, Class<IBeanConfigurable>>>();

	private List<String> fileNames = new ArrayList<String>();
	
	public TemplatesManager() {
		try {
			init();
			load();
			initSelf();
			clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关联实体类的名称与excel表格
	 * @throws Exception 
	 * @throws JDOMException 
	 */
	@SuppressWarnings("unchecked")
	public void init() throws Exception {
		//获取excel.xml配置路径
		InputStream in = TemplatesManager.class.getClassLoader().getResourceAsStream("config/excel/excel.xml");
		//或者这个xml文件对象
//		File file = new File(path);
		//使用JDOM进行读取
		SAXBuilder builder=new SAXBuilder(false);
		//创建dom对象
		Document doc=builder.build(in);
		Element configRoot=doc.getRootElement();
		//获取dom内的XML元素
		List<?> configList=configRoot.getChildren("config");
		fileNames.clear();
		//对元素进行遍历
		for (Object configObject : configList) {
			Element configItem = (Element)configObject;
			//获取某个元素的name，这对应excel文件名
			String fileName = configItem.getAttributeValue("name");
			fileNames.add(fileName);
			//创建按标签名进行查询键名的map表
			Map<String, Class<IBeanConfigurable>> sheetClassMap = new HashMap<String, Class<IBeanConfigurable>> ();
			//将这个map表放入以文件名为查询键名的map表
			fileSheetClassMap.put(fileName, sheetClassMap);
			//获得XML元素中的sheet子元素
			List<Element> sheetElementList = configItem.getChildren("sheet");
			//对子元素进行遍历
			for (Element sheetE : sheetElementList){
				//获取子元素中某个标签名name
				String sheetName = sheetE.getAttributeValue("name");
				//获取该标签名对应的类名type
				try{
					Class<IBeanConfigurable> clz = (Class<IBeanConfigurable>)Class.forName(sheetE.getAttributeValue("type"));
					//将name-type对照关系存入map表
					sheetClassMap.put(sheetName, clz);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	

//   /** 
//    *  Description:用poi解析excel，支持office2007 xls，xlsx 
//    *  @author liuwei  DateTime 2013-8-26 上午10:58:16 
//    *  @param fileName 
//    *  @param sheetNumber 
//    *  @return 
//    */  
//   public static List<String[]> readByPoi() {
//   	
//   	String fileName ="E:/workspace/TestExcel/src/excel/c_arenaRankReward(竞技场排名奖励).xlsx";
//   	
//   	 
//       Workbook workbook = null;
//       List<String[]> list = new ArrayList<String[]>();
//       try {  
//           String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
//               if (fileType.equals("xls")) {    
//                   workbook = new HSSFWorkbook(new FileInputStream(fileName));    
//               }    
//               else if(fileType.equals("xlsx"))    
//               {    
//                   workbook = new XSSFWorkbook(new FileInputStream(fileName));    
//               }    
//               else    
//               {
////                   log.info("您的文档格式不正确！");    
//               }
//               
//               //创建sheet对象
//               Sheet sheet = workbook.getSheet("ArenaRankRewardStruct-竞技场排名奖励");
//               
//               int firstRowIndex = 1;					//sheet.getFirstRowNum();
//               int lastRowIndex = sheet.getLastRowNum();
//               int coloumNum = lastRowIndex - firstRowIndex;// sheet.getRow(2).getRowNum();//获得总列数,包括空这着的列
//               
//               Row keyRow = sheet.getRow(1);
//               int rowNum = keyRow.getLastCellNum();
//               
//               for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){    
//                   Row row = sheet.getRow(rIndex);    
//                   if(row != null){
//                	   
//                       String[] s = new String[coloumNum];  
//                       for(int cIndex = 0; cIndex < rowNum; cIndex ++){    
//                           Cell cell = row.getCell(cIndex);    
//                           String value = "";
//                           
//                           if(cell != null) {
//                           	cell.setCellType(Cell.CELL_TYPE_STRING);	
//                           	value = cell.getStringCellValue();
//                           }
//                           
//                           s[cIndex]=value;
//                           System.out.println("data:"+value);
//                       }    
//                       list.add(s);
//                       
//                       System.out.println("\n");
//                   }
//               }
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
//       return list;
//   }
   
   
   /**
    * 
    * @return
    */
   public  List<Map<String,String>> getSheetValue(Sheet sheet) {
	   List<Map<String,String>> result = new ArrayList<>();
	   
	 
	   
	   int firstRowIndex = 0;				//sheet.getFirstRowNum();
       int lastRowIndex = sheet.getLastRowNum();
//       int coloumNum = sheet.getRow(2).getRowNum();//获得总列数,包括空这着的列
       
       Row keyRow = sheet.getRow(0);
       int rowNum = keyRow.getLastCellNum();
       String[] keys =  new String[rowNum];
       
       for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex ++){    
           Row row = sheet.getRow(rIndex);
           if(row != null) {
        	   Map<String,String> rowMap = new HashMap<>();
        	   for(int cIndex = 0; cIndex < rowNum; cIndex ++){   
                   Cell cell = row.getCell(cIndex);    
                   String value = "";
                   
                   if(cell != null) {
                	   cell.setCellType(Cell.CELL_TYPE_STRING);	
                	   value = cell.getStringCellValue();
                   }
                   
                   if(rIndex == 0){
                	   keys[cIndex] = value;
                   }else if(rIndex >= 2){
                	   rowMap.put(keys[cIndex], value);
                   }
               }
        	   
        	   if(rIndex > 2){
        		   result.add(rowMap);
        	   }
           }
       }
       return result;
   }
	 
	
	public void load() throws Exception {
		//获取当前运行路径
//		String path = "config/excel/";//打包是处理
		String path = TemplatesManager.class.getClassLoader().getResource("config/excel/").getPath();
		//获取当前路径对应的文件夹
		File folder = new File(path);
		//对该文件夹内的所有文件进行遍历
		if(folder.listFiles()==null) {
			LogUtil.error("发生严重错误，未能读取EXCEL文件路径"+path);
			return;
		}
		HashMap<String,File> files = new HashMap<String,File>();
		for (File file : folder.listFiles()) {
			//获取文件夹内的文件名称和文件对象
			files.put( file.getName(), file);
		}
		for (String fileName : fileNames) {
			//获取文件夹内的文件
			File file = files.get(fileName);
			if(file==null){
				LogUtil.error("EXCEL文件 (" + fileName + ") 找不到" );
				continue;
			}
			//当文件扩展名为xls或者xlsx时
//			if (fileName.contains(".xls") || fileName.contains("xlsx")) {
				//从某个配置表获取这个文件所对应的模板类名Map<标签页名, Class<模板类>>
				Map<String, Class<IBeanConfigurable>> sheetClassMap = fileSheetClassMap.get(fileName);
				//读取整个excel文件
				
				if (sheetClassMap == null){
					try{
						LogUtil.error("配置文件 (config/excel.xml) 没找到 (" + fileName +") 的配置信息");
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				
				Workbook workbook = null;
				String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
	            if (fileType.equals("xls")) {    
	                   workbook = new HSSFWorkbook(new FileInputStream(file));    
	            }    
	            else if(fileType.equals("xlsx"))    
	            {    
	                   workbook = new XSSFWorkbook(new FileInputStream(file));    
	            }    
	            else    
	            {
//			                   log.info("您的文档格式不正确！");    
	            }
			               
	            Iterator<Sheet> iterator = workbook.sheetIterator();
	          //对所有标签进行遍历
	            while(iterator.hasNext()){
	            	Sheet sheet = iterator.next();
	            	
	            	//获取标签名
					String sheetName = sheet.getSheetName();
					//获取这个标签对应的模板类(配置EXCEL.XML中已经事先指定)
					long beginT = System.currentTimeMillis();
					if (sheetClassMap == null){
						continue;
					}
					
					Class<IBeanConfigurable> clz = sheetClassMap.get(sheetName);
					
					if (clz == null){
						try{
							LogUtil.error("配置文件 (config/excel.xml) 没找到 (" + fileName +") 里工作表  (" + sheetName +")的配置信息");
						}catch(Exception e){
							e.printStackTrace();
						}
						continue;
					}
					
					//该模板中按照每个id作为唯一的排序条件进行的数据配置集合
					Map<Object, IBeanConfigurable> beans = new HashMap<Object, IBeanConfigurable>();
					//存入配置表
					beanMap.put(clz, beans);
					
					List<Map<String, String>>  datas = getSheetValue(sheet);
					
					for (Map<String, String> data : datas) {
						//每行都对应一个配置数据对象
						IBeanConfigurable bean = clz.newInstance();
						//各个配置类都实现了parse方法，自行按照各自逻辑读取行数据
						bean.parse(data);
						beans.put(bean.getTemplateId(), bean);
					}

					long endT = System.currentTimeMillis();
					LogUtil.debug(clz.getSimpleName() + "加载结束,加载时间:"+(endT - beginT));
			}
		}
	}
	
	public void clear(){
		fileSheetClassMap.clear();
		fileSheetClassMap = null;
	}

	@SuppressWarnings("unchecked")
	public static <T extends  IBeanConfigurable> T getConfigBean(Class<? extends  IBeanConfigurable> clz, Object id) {
		Map<Object, ? extends IBeanConfigurable> result = beanMap.get(clz);
		if(result == null){
			LogUtil.error("配置表未解析，class={}",clz.getSimpleName());
			return null;
		}
		return (T)result.get(id);
	}
	

	public static Map<Object, ? extends IBeanConfigurable> getConfigBeans(Class<?> clz) {
		return beanMap.get(clz);
	}
	
	public void initSelf() {
		String filterPackagePath = "com.game.config.tpl";
		
		File root = new File(GotoManager.class.getClass().getResource("/").getFile());
		String prefix = root.getAbsolutePath();
		List<Class<?>> classes = new ArrayList<Class<?>>(); 
		ToolUtils.listCommandClass(filterPackagePath,root,prefix,classes);
		
		for (Class<?> clazz : classes) {
			for (Class<?> cls : clazz.getInterfaces()) {
				if(cls.getName() == IConfigInit.class.getName()){
					 try {
						((IConfigInit)clazz.newInstance()).init();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
