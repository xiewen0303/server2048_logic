package com.game.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.game.constant.GlobalConst;
public class CodeUtil {
	
 
	 
	/**  
	 * MD5加密类  
	 * @param str 要加密的字符串  
	 * @return    加密后的字符串  
	 */  
	public static String toMD5(String str){   
	    try {   
	        MessageDigest md = MessageDigest.getInstance("MD5");   
	        md.update(str.getBytes());   
	        byte[]byteDigest = md.digest();   
	        StringBuffer buf = new StringBuffer("");  
	        int i = 0;
	        for (int offset = 0; offset < byteDigest.length; offset++) {   
	            i = byteDigest[offset];   
	            if (i < 0)   
	               i += 256;
	           if (i < 16)   
	               buf.append("0");   
	           buf.append(Integer.toHexString(i));   
	        }
	        //32位加密   
	       // return buf.toString();   
	        // 16位的加密   
	       return buf.toString().substring(8, 24);    
	    } catch (NoSuchAlgorithmException e) {   
	        e.printStackTrace();   
	        return null;
	    }   
	}
	
	public static void main(String[] args) {
		long uid = coverUid(3, 1);
		System.out.println(uid);
		System.out.println((long)(uid>>GlobalConst.MOVE_BIT));
	}
	
	/**
	 * 转换成UId
	 * @param uid
	 * @return
	 */
	public static long coverUid(long uid,int serverId){
		return ((long)serverId<<GlobalConst.MOVE_BIT)+uid;
	}
}
