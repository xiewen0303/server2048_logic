package com.game.service.login.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountUtil {

	 public static void main(String[] args) {
		/* String value="手机号";  
		  
		String regExp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$";  
		  
		Pattern p = Pattern.compile(regExp);  
		  
		Matcher m = p.matcher(value);  
		m.find();*/
		 
		 
//		 isMobileNO("1522684752");
//		 isEmail("X@yahoo.com.cn");
		 System.out.println(isMobileNO("18221610336"));
	}
	  
	  
	 public static boolean isMobileNO(String mobiles){  
		  
		Pattern p = Pattern.compile("1[0-9]{10}");  
		  
		Matcher m = p.matcher(mobiles);  
		  
		System.out.println(m.matches()+"---"); 
		  
		return m.matches();
		} 
	
	 public static boolean isEmail(String email){  
		  
		 Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
			  
			Matcher m = pattern.matcher(email);  
			  
			System.out.println(m.matches()+"---"); 
			  
			return m.matches();
			} 
	
	 
}
