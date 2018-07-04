package com.game.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpClient {

	public static void main(String[] args) {
		//http://192.168.1.82:6666/auth?api=xiaomi&uid=xm0001&session=ffxooa21l&pt_type=2&sign=478f3f91de802bc6800f7071e8425542
//		String path = "http://192.168.1.99:6666/auth?api=xiaomi&uid=xm0001&session=ffxooa21l&pt_type=2&sign=478f3f91de802bc6800f7071e8425542";
		String path = "http://www.baidu.com";
		String temp  = uploadGetMethod(path);
		System.out.println(temp);
	}

	public static String uploadPostMethod(String path,Map<String, String> params) {
		StringBuilder sb = new StringBuilder();
		StringBuffer resultStr = new StringBuffer();
		InputStream inputStream = null;
		BufferedReader br = null;
		try {

			for (Map.Entry<String, String> entry : params.entrySet()) {
				sb.append(entry.getKey()).append('=').append(URLEncoder.encode(entry.getValue(), "UTF-8")).append('&');
			}
			sb.deleteCharAt(sb.length() - 1);
			byte[] entitydata = sb.toString().getBytes();
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length",String.valueOf(entitydata.length));
			OutputStream os = conn.getOutputStream();
			os.write(entitydata);

			os.flush();
			os.close();
			if (conn.getResponseCode() == 200) {
				inputStream = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

				String rl = "";
				while ((rl = br.readLine()) != null) {
					resultStr.append(rl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultStr.toString();
	}

	public static String uploadGetMethod(String path) {
		StringBuffer resultStr = new StringBuffer();
		InputStream inputStream = null;
		BufferedReader br = null;
		try {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
//			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
			if (conn.getResponseCode() == 200) {
				inputStream = conn.getInputStream();
				br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

				String rl = "";
				while ((rl = br.readLine()) != null) {
					resultStr.append(rl);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultStr.toString();
	}
}