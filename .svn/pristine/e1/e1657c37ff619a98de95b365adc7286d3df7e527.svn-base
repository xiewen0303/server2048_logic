package com.game.mapping.manage;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import log.LogUtil;

import com.game.mapping.annotation.EasyMapping;
import com.game.mapping.annotation.EasyWorker;
import com.game.mapping.resolver.DefaultResolver;
import com.game.mapping.resolver.IResolver;
import com.game.util.ToolUtils;

public class GotoManager  {
	
	public static final String filterPackagePath = "com.game.service";
	
	private Map<Short,IResolver> resolvers = new HashMap<Short, IResolver>();
	
	private GotoManager() {
		super();
		init();
	}
	
	private static GotoManager gotoManager;
	
	/**
	 * 启动后加载注解配置  
	 * @date 2016-4-15 下午4:00:28
	 */
	public static void load(){
		gotoManager = new GotoManager();
	}

	public void init(){
		File root = new File(GotoManager.class.getClass().getResource("/").getFile());
		String prefix = root.getAbsolutePath();
		List<Class<?>> classes = new ArrayList<Class<?>>(); 
		ToolUtils.listCommandClass(filterPackagePath,root,prefix,classes);
		for(Class<?> cls : classes){
			analyzeClass(cls);
		}
	}

	private void analyzeClass(Class<?> cls) {
		
		EasyWorker easyWorker = cls.getAnnotation(EasyWorker.class);
		if (null == easyWorker) {
			return;
		}
			try {
				// FIXME aop先过滤掉
				Method[] methods = cls.getDeclaredMethods();

				for (Method m : methods) {
				
					EasyMapping commandMapping = m.getAnnotation(EasyMapping.class);
					if (null != commandMapping) {

//						List<IInterceptor> methodInterceptors = null;
//						EasyInterceptors easyInterceptors = m.getAnnotation(EasyInterceptors.class);
//						if(null != easyInterceptors){
//							EasyInterceptor[] interceptors = easyInterceptors.value();
//							if(null != interceptors && interceptors.length > 0){
//								methodInterceptors = new ArrayList<IInterceptor>();
//								for(EasyInterceptor interceptor : interceptors){
//									IInterceptor tmp = null;
//									if(interceptor.isSpringBean()){
//										tmp = (IInterceptor) workerContainer.getWorker(interceptor.value());
//									}else{
//										tmp = interceptor.value().newInstance();
//									}
//									methodInterceptors.add(tmp);
//								}
//							}
//						}
						
						Class<?>[] paramTypes = m.getParameterTypes();
						if("".equals(easyWorker.workerName())){
							resolvers.put(commandMapping.mapping(), new DefaultResolver(m, paramTypes,cls,MappingContainer.getWorker(cls)));
							CmdGroupInfo.registerCmds(commandMapping.mapping(), easyWorker.moduleName(), easyWorker.groupName(),commandMapping.kuafuType());
						}else{
							resolvers.put(commandMapping.mapping(), new DefaultResolver(m, paramTypes,cls,MappingContainer.getWorker(easyWorker.workerName())));
							CmdGroupInfo.registerCmds(commandMapping.mapping(), easyWorker.moduleName(), easyWorker.groupName(),commandMapping.kuafuType());
						}
					}
				}
				
			} catch (Exception e) {
				LogUtil.error("error in analyzeClass", e);
			}
	}
	
//	private void listCommandClass(File file,String prefix,List<Class<?>> list){
//
//		File[] files = file.listFiles();
//		if(null!=files){
//			try{
//				for(File f : files){
//					if(f.isDirectory()){
//						listCommandClass(f,prefix,list);
//					}else{
//						if(f.getName().endsWith(".class")){
//						
//							String parent = f.getParent();
//							String name = f.getName();
//							String packageName = parent.length() >prefix.length()? parent.substring(prefix.length()+1)+File.separator :"";
//							String classpath = (packageName+name.substring(0, name.length()-6)).replace("\\", ".");
//							
//							//对指定的包才做检查
//							if(classpath.contains(filterPackagePath)){
//								list.add(Class.forName(classpath.replace(File.separator, ".")));
//							}
////							if(easyexecutorMeta.isScanPackage(classpath)){
////								list.add(Class.forName(classpath.replace(File.separator, ".")));
////							}
//						}else if(f.getName().endsWith(".jar")){
//							JarFile jarFile = new JarFile(f);
//							Enumeration<JarEntry> jarEntries = jarFile.entries();
//							while(jarEntries.hasMoreElements()){
//								JarEntry jarEntry = jarEntries.nextElement();
//								if(jarEntry.getName().endsWith(".class")){
//
//									String jarEntryName = jarEntry.getName();
//									String classpath = jarEntryName.substring(0, jarEntryName.length()-6).replace("/", ".");
//									if(classpath.contains(filterPackagePath)){
//										list.add(Thread.currentThread().getContextClassLoader().loadClass(classpath));
//									}
//								}
//							}
//						}
//					}
//				}
//			}catch (Exception e) {
//				throw new RuntimeException("error in listCommandClass",e);
//			}
//		}
//	}
	
	public static IResolver getResolver(Short command) {
		IResolver resolver = gotoManager.resolvers.get(command);
		if(null == resolver){
			LogUtil.error("指令cmd="+command+"不存在！");
		}
		return resolver;
	}
}
