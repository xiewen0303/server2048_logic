package com.game.mapping.resolver;

import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;
import com.game.msg.IMessageReadable;
import com.game.util.TimeUtil;

import log.LogUtil;
 
public class DefaultResolver implements IResolver {

	private Method m;
	private Class<?> targetCls;
	private Class<?>[] paramTypes;
	private Object target;
	
//	private List<IInterceptor> globalInterceptors;
//	private List<IInterceptor> classInterceptors;
//	private List<IInterceptor> methodInterceptors;
	
	//,List<IInterceptor> globalInterceptors,List<IInterceptor> classInterceptors,List<IInterceptor> methodInterceptors
	public DefaultResolver(Method m,Class<?>[] paramTypes,Class<?> targetCls,Object target){
		this.m = m;
		this.paramTypes = paramTypes;
		this.targetCls = targetCls;
		this.target = target;
//		this.globalInterceptors = globalInterceptors;
//		this.classInterceptors = classInterceptors;
//		this.methodInterceptors = methodInterceptors;
	}
	

	@Override
	public void execute(Object message) {
		try {
			
//			// global interceptor
//			if(null !=globalInterceptors){
//				for(IInterceptor interceptor : globalInterceptors){
//					if(!interceptor.before(message)) return;
//				}
//			}
//			// class interceptor
//			if(null !=classInterceptors){
//				for(IInterceptor interceptor : classInterceptors){
//					if(!interceptor.before(message)) return;
//				}
//			}
//			// method interceptor
//			if(null !=methodInterceptors){
//				for(IInterceptor interceptor : methodInterceptors){
//					if(!interceptor.before(message)) return;
//				}
//			}
			
			// invoke
			//LogUtil.error("================================"+message);
			IMessageReadable msg = (IMessageReadable)message;
			long t1 = TimeUtil.getNowTime();
			LogUtil.debug("====begin message	:{},content={}",msg.getMessageCode(),msg.getMessage().toString());
			
			m.invoke(target,message);
			
			long t2 = TimeUtil.getNowTime();
			LogUtil.debug("====end   message :{},interval:{}ms",msg.getMessageCode(),t2-t1);
			
//			if(null !=methodInterceptors){
//				for(IInterceptor interceptor : methodInterceptors){
//					if(!interceptor.after(message)) return;
//				}
//			}
//			if(null !=classInterceptors){
//				for(IInterceptor interceptor : classInterceptors){
//					if(!interceptor.after(message)) return;
//				}
//			}
//			if(null !=globalInterceptors){
//				for(IInterceptor interceptor : globalInterceptors){
//					if(!interceptor.after(message)) return;
//				}
//			}
		
		} catch (Exception e) {
			LogUtil.error("执行指令错误："+ ((IMessageReadable)message).getMessageCode(), e);
		}
	}
}
