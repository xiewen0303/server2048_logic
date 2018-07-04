package com.game.nio.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder.ErrorDataDecoderException;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.InterfaceHttpData.HttpDataType;

import java.io.IOException;
import java.nio.ByteBuffer;

import log.LogUtil;

import com.alibaba.fastjson.JSONObject;
import com.game.config.ProperitesConfig;
import com.game.config.ProperitesConst;
import com.game.core.IPlayer;
import com.game.core.PlayerForHttp;
import com.game.msg.IMessageReadable;
import com.game.nio.http.bak.MessageCode2;
import com.game.service.hero.util.HeroUtil;

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter {
	
//	private boolean readingChunks;
//	private static final HttpDataFactory factory = new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); //Disk
    
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		HttpPostRequestDecoder decoder = null;
		HttpRequest request =null;
		
		 String uri = ""; 
         
		if (msg instanceof HttpRequest) {
			
            request = (HttpRequest) msg;
            uri = request.getUri();
            LogUtil.debug(uri); 
            if (uri.equals("/favicon.ico")) {
                return;
            }
            
//            //test
//            if(uri.contains("test")){
//            	CSBagItems csMsg = new CSBagItems();
//            	Hero hero = new Hero();
//            	hero.setUid(77);
//            	PlayerForHttp p = new PlayerForHttp(ctx, "11111");
//            	p.setHero(hero);
//            	csMsg.setType(1);
//            	HeroCtl.getBagItems(p, csMsg);
//            	return;
//            }
            
            //游戏正常协议
            String servletName = ProperitesConfig.getString(ProperitesConst.SERVER_BASE, "OPEN_HTTP_SERVLET");
            if(!uri.contains(servletName)){
            	ctx.close();
            	return;
            }
            
            if (request.getMethod().equals(HttpMethod.GET)) {
            	LogUtil.error("===this is http get===");
            	releaseData(msg);
                return;
            }
            
            //判断request请求是否是post请求
            if (request.getMethod().equals(HttpMethod.POST)) {
            	LogUtil.debug("===this is http post===");
                try {
                    /**
                     * 通过HttpDataFactory和request构造解码器
                     */
//                  decoder = new HttpPostRequestDecoder(factory, request);
                    decoder = new HttpPostRequestDecoder(request);
                } catch (ErrorDataDecoderException e1) {
                	ctx.channel().close();
                    e1.printStackTrace();
                }
            }
        }
        
		if (decoder != null) {
		if (msg instanceof HttpContent) {
                if(decoder.hasNext()){
//                	//充值
//                	String payServletName = ProperitesConfig.getString(ProperitesConst.SERVER_BASE, "PAY_SERVLET");
//                	if(uri.contains(payServletName)){
//                		Map<String,String> paramData = new HashMap<String, String>();
//                		paramData.put("game_key", getParamData(decoder, "game_key"));
//                		paramData.put("game_orderno",getParamData(decoder, "game_orderno"));
//                		paramData.put("orderno",getParamData(decoder, "orderno"));
//                		paramData.put("subject", getParamData(decoder, "subject"));
//                		paramData.put("description", getParamData(decoder, "description"));
//                		paramData.put("total_fee", getParamData(decoder, "total_fee"));
//                		paramData.put("signature", getParamData(decoder, "signature"));
//                		
//                		DispatchHandler.dispatchInner(new RechargeCmd(ModuleName.ARENA,paramData,new PlayerForHttp(ctx,"")));
//                		releaseData(msg);
//                		return;
//                	}
                	
//                	//后台操作
//                	String gmServer = ProperitesConfig.getString(ProperitesConst.SERVER_BASE, "GM_SERVER");
//                	if(uri.contains(gmServer)) {
//                		String paramDataStr = getParamData(decoder, "data_text");
//                		DispatchHandler.dispatchInner(new GroovyCmd(ModuleName.SETTING,paramDataStr,new PlayerForHttp(ctx,"")));
//                		releaseData(msg);
//                		return;
//                	}
                	
                	
                	InterfaceHttpData data = decoder.getBodyHttpData("data");
                	
                	String msgContent = writeHttpData(data);
                	if(data == null || "".equals(msgContent)){
                		LogUtil.error("请确认客服端数据中是否给data赋值！");
                		releaseData(msg);
                		ctx.channel().close();
                		return;
                	}
                	
                	byte[] datas = Base64Util.decoder(msgContent);
                	ByteBuffer byteBuffer = ByteBuffer.wrap(datas);
                	byteBuffer.getInt(); //包长
                	int cmd = byteBuffer.getInt();
                	
                	IMessageReadable absMsg = MessageCode2.getGenMessage2(cmd, byteBuffer);
                	
//                	String moduleName = ProperitesConfig.getString(ProperitesConst.CMD_MODULENAME, String.valueOf(cmd));
                	String uidFlag = ""; //测试日志 
                	//如果是登录,可以走登录流程
                	if(cmd == MessageCode2.CS_Login){
                		absMsg.dispatch(new PlayerForHttp(ctx,""));
                	}else{
                		//非登录注册,必须需要有uid
                    	InterfaceHttpData loginUid = decoder.getBodyHttpData("sign");
                    	String sign =uidFlag= writeHttpData(loginUid);
                    	
                    	if(sign == null||"".equals(sign.trim())) {
                    		LogUtil.error("请确认客服端数据中是否给sign赋值");
                    		releaseData(msg);
                    		ctx.channel().close();
                    		return;
                    	}
                    	 
                    	IPlayer player = new PlayerForHttp(ctx,sign);
                    	if(HeroUtil.isOnline(player.getUid())){
                    		
//                    		//判定该模块是否开放
//                        	if(!HeroUtil.isOpenModule(player,cmd)){
//                        		BenNiuLog.error("改模块还没有开放");
//                        		releaseData(msg);
//                        		return;
//                        	}
                    		
                    		absMsg.dispatch(player);
                    	}
                	}
                	
                	//打印接受客服端信息
                	if(!absMsg.getClass().getSimpleName().equals("CSHeartBeat")){
                		LogUtil.debug("=============ReceiveClient协议名["+uidFlag+"]==========["+absMsg.getClass().getSimpleName()+"---接收("+cmd+")时间:"+System.currentTimeMillis()+"----------]====msgContent:"+JSONObject.toJSONString(absMsg));	
                	}
                }
                
                releaseData(msg);
            } 
        }
	}

	private void releaseData(Object msg) {
		try {
			//释放byteBuf内存
			HttpContent content = (HttpContent) msg;
			ByteBuf buf = content.content();
			buf.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getParamData(HttpPostRequestDecoder decoder,String keyName){
		InterfaceHttpData data = decoder.getBodyHttpData(keyName);
    	return writeHttpData(data);
	}
	
//	private void reset(HttpPostRequestDecoder decoder, HttpRequest request) {
//	        request = null;
//	        // destroy the decoder to release all resources
//	        decoder.destroy();
//	        decoder = null;
//	}
	
    private String writeHttpData(InterfaceHttpData data) {
    	 String value=null; 
    	 if(data==null){
    		 return value;
    	 }
        /**
         * HttpDataType有三种类型  Attribute, FileUpload, InternalAttribute
         */
    	 try {
    		 if (data.getHttpDataType() == HttpDataType.Attribute) {
    			 Attribute attribute = (Attribute) data; 
                 value = attribute.getValue();
    		 }
             } catch (IOException e) {
                e.printStackTrace();
            }finally{
            	data.release();
            }
        return value;
    }
	 
	
   @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	LogUtil.error(cause.getMessage());
        ctx.close();
    }

}
