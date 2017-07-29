/**
 * <p>Title: HttpRequest.java</p>
 * <p></p>
 * @author damon
 * @date 2014年12月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: HttpRequest</p>
 * <p></p> 
 * @author damon
 * @date 2014年12月29日
 */
public class HttpRequest {

	/**
	 * <p>catchRequestData</p>
	 * <p>捕获请求附带数据</p>
	 * @author damon
	 * @date 2014年12月29日
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static String catchRequestData(HttpServletRequest request) throws IOException {
		StringBuffer buffer = new StringBuffer();
		// 将返回的输入流转换成字符串
		InputStream inputStream = request.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String str = null;
		while ((str = bufferedReader.readLine()) != null) {
			buffer.append(str);
		}
//		System.out.println(buffer.toString());
		// 释放资源
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		inputStream = null;
		
		return buffer.toString();
	}
	
}
