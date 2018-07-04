package db.batching;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * 
 * 读配置文件并保存<br>
 * 实际业务里不可以调用此方法 
 * 
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public final class BatchConfig {
	
	public final static String CREATION = "insert"; 
	public final static String REGEX = "#[a-zA-Z0-9_-]*#";
	public final static String JDBC_PH = "?";
	private final static Pattern PATTERN = Pattern.compile(REGEX); 
	private final static Map<Class, BatchBean> beanMap = new HashMap<Class, BatchBean>();

	private static String configLocation;
	/**
	 * 默认1分钟，可在SPRING配置文件里修改
	 */
	public static int DELAYED_TIME = 0; 
	public static BatchBean getBatchBean(Class clz) {
		return beanMap.get(clz);
	}

	public static void load(String path, InputStream in, int delayedTime){
		configLocation = path;
		DELAYED_TIME = delayedTime;
		try {

			SAXBuilder builder = new SAXBuilder(false);

			Document doc = builder.build(in);
			Element rootE = doc.getRootElement();
			
			/*
			 * 解析insert标签
			 */
			
			List<Element> beansE = rootE.getChildren();
			for (Element beanE : beansE) {
				/*
				 * 解析parameterCass参数
				 */
				String clzName = beanE.getAttributeValue("parameterClass");
				String sql = beanE.getValue().trim();
				BatchBean batchBean = new BatchBean();
				Class clz = Class.forName(clzName);
				batchBean.setClz(clz);
				batchBean.setSql(sql);
				batchBean.setOperation(CREATION);
				/*
				 * 初始化每个实体Bean的配置
				 */
				init(batchBean);
				addConfig(clz, batchBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public static void load(String path,int delayedTime)  {
		configLocation = path;
		DELAYED_TIME = delayedTime;
		try {
			InputStream in = BatchConfig.class.getClassLoader().getResourceAsStream(path);

			SAXBuilder builder = new SAXBuilder(false);

			Document doc = builder.build(in);
			Element rootE = doc.getRootElement();
			
			/*
			 * 解析insert标签
			 */
			List<Element> beansE = rootE.getChildren();
			for (Element beanE : beansE) {
				/*
				 * 解析parameterCass参数
				 */
				String clzName = beanE.getAttributeValue("parameterClass");
				String sql = beanE.getValue().trim();
				BatchBean batchBean = new BatchBean();
				Class clz = Class.forName(clzName);
				batchBean.setClz(clz);
				batchBean.setSql(sql);
				batchBean.setOperation(CREATION);
				/*
				 * 初始化每个实体Bean的配置
				 */
				init(batchBean);
				addConfig(clz, batchBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void addConfig(Class clz, BatchBean config) {
		beanMap.put(clz, config);
	}
 
	private static void init(BatchBean batchBean) {
		String sql = batchBean.getSql();

		Matcher matcher = PATTERN.matcher(sql);

		/*
		 * 零时存放含＃＃的站位字符串
		 */
		ArrayList<String> tempList = new ArrayList<String>();
		while (matcher.find()) {
			tempList.add(matcher.group());
		}

		if (tempList.size() == 0)
			return;

		/*
		 * 去掉＃占位符,并初始化config里的属性LIST
		 */
		for (String temp : tempList) {
			String property = temp.substring(1, temp.length() - 1);
			String getter = toGetter(property);
			try {
				/*
				 * 启动加载时校验配置文件是否与BEAN一致
				 */
				batchBean.getClz().getDeclaredMethod(getter);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				System.err.println("\n请检查配置文件"+configLocation);
				System.err.println("\n" + batchBean.getClz().getName() + " 中配置了方法 " + getter + "()");
				System.err.println("\n" + batchBean.getSql() );
				System.exit(0);
			}
			batchBean.add(getter);
		}

		/*
		 * 初始化SQL, 用?替换#[]#占位符
		 */
		sql = sql.replaceAll(REGEX, JDBC_PH);
		batchBean.setSql(sql);

	}
	
	/**
	 * 属性转成GETTER
	 * @param property
	 * @return
	 */
	private static String toGetter(String property){
		if (property.startsWith("is"))//符合eclipse自动生成boolean型的setter或getter规范
			return property;
		String first = property.substring(0, 1);
		first = first.toUpperCase();
		return "get".concat(first).concat(property.substring(1, property.length()));
	}

}
