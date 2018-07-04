package com.game.nio.http;
 
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	private static  BASE64Encoder encoder = new BASE64Encoder(); 
	private static 	BASE64Decoder decoder = new BASE64Decoder();
	 
	 
	public static String encoder(byte[] bytes){
		 String data = encoder.encode(bytes);
		 return data; 
	}
	
	public static byte[] decoder(String data) {
		 try { 
			byte[] targetData = decoder.decodeBuffer(decoderUrlData(data));
			 return targetData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return null;
	}
	
	
	public static String decoderUrlData(String data){
		return data.replace(' ', '+');
	}
}
