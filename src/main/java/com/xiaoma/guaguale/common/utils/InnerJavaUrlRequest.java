/**
 * <p>Title: InnerJavaUrlRequest.java</p>
 * <p>Java 内置URL请求</p>
 * @author damon
 * @date 2014年12月11日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <p>Title: InnerJavaUrlRequest</p>
 * <p>Java 内置URL请求</p> 
 * @author damon
 * @date 2014年12月11日
 */
public class InnerJavaUrlRequest {
	/* valid HTTP methods */
    private static final String[] methods = {
        "GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE"
    };
	
	/**
	 * <p>doGetHttpURLRequest</p>
	 * <p>GET 请求</p>
	 * @author damon
	 * @date 2014年12月11日
	 * @param urlStr
	 * @return
	 * @throws IOException
	 */
	public static String doGetHttpURLRequest(String urlStr) throws IOException {
		return doHttpURLRequest(urlStr, methods[0]);
	}

	/**
	 * <p>doPostHttpURLRequest</p>
	 * <p>POST 请求</p>
	 * @author damon
	 * @date 2014年12月11日
	 * @param urlStr
	 * @return
	 * @throws IOException
	 */
	public static String doPostHttpURLRequest(String urlStr) throws IOException {
		return doHttpURLRequest(urlStr, methods[1]);
	}
	
	/**
	 * <p>doHttpURLRequest</p>
	 * <p>执行请求</p>
	 * @author damon
	 * @date 2014年12月11日
	 * @param urlStr 请求链接
	 * @param method 请求方式
	 * @return 请求结果
	 * @throws IOException
	 */
	private static String doHttpURLRequest(String urlStr, String method) throws IOException {
		// 创建url对象
		URL url = new URL(urlStr);

		// 打开url连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 设置url请求方式 ‘post’
		connection.setRequestMethod(method);

		// 发送
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		
		// 返回发送结果
		String inputline = in.readLine();
		return inputline;
	}
	
//	public static void main(String[] args) {
//	
//	}
	
}
