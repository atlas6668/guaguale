/**
 * <p>Title: Code.java</p>
 * <p>自定义编码</p>
 * @author damon
 * @date 2014年12月3日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>Title: Code</p>
 * <p>自定义编码</p> 
 * @author damon
 * @date 2014年12月3日
 */
public class Code {

	/**
	 * <p>validate</p>
	 * <p>短信 6位验证码</p>
	 * @author damon
	 * @date 2014年12月3日
	 * @return
	 */
	public static String validate() {
		int[] nums = new int[6];
		// 生成随机数
		Random random = new Random();
		// 验证码
		String validate_code = "";
		for(int i = 0; i < nums.length; i++){
			nums[i] = random.nextInt(9);
			validate_code = validate_code + nums[i];
		}
		return validate_code;
	}
	
	/**
	 * <p>cutDigit</p>
	 * <p>保留三位小数</p>
	 * @author damon
	 * @date 2015年1月13日
	 * @param param
	 * @return
	 */
	public static double cutDigit(double param) {
		DecimalFormat df = new DecimalFormat("######0.000"); 
		return Double.parseDouble(df.format(param));
	}
	
	/**
	 * <p>createXMK</p>
	 * <p>生产XMK</p>
	 * @author damon
	 * @date 2015年1月29日
	 * @return
	 */
	public static String createXMK() {
		String code = StringUtil.subStrRemoveLine(UUID.randomUUID().toString(), "-");
		code = code.substring(0, 8);
		return code;
	}
	
	/**
	 * <p>random</p>
	 * <p>从已知数列中随机取数</p>
	 * @author damon
	 * @date 2015年1月30日
	 * @param nos 
	 * @return
	 */
	public static String random0(String[] nos) {
		String no;
		// 生成随机数 0~9
		Random random = new Random();
		int index = random.nextInt(nos.length);
		no = nos[index];
//		System.out.println(index);
		return no;
	}

	public static String random1(String[] nos, int k) {
		String no;
		// 生成随机数 0~9
		Random random = new Random();
		// 行列式
		// 列 初始化 K 列
		int x = nos.length % k;
		int y = nos.length / k;
		// 随机取 第 j+1 行 i+1 列的数据
		int j = random.nextInt(x == 0 ? y : y + 1);
		int i = random.nextInt(x == 0 ? k - 1 : x);
		no = nos[j * k + i];
//		System.out.println(j * k + i);
		return no;
	}

	public static String random2(String[][] nos) {
		String no;
		// 生成随机数 0~9
		Random random = new Random();
		int y = nos.length;
		// 随机取 第 j+1 行
		int j = random.nextInt(y);
		int x = nos[j].length;
		// 随机取 第 i+1 列
		int i = random.nextInt(x);
		// 随机取 第 j+1 行 i+1 列的数据
		no = nos[j][i];
//		System.out.println((j+1) + " " + (i+1));
		return no;
	}
	
	/**
	 * <p>whatMillis</p>
	 * <p>取某日零点某时区的毫秒数 e.g.目前在东八区，获得8:00的毫秒数</p>
	 * @author damon
	 * @date 2015年2月6日
	 * @param date
	 * @return
	 */
	public static Long whatMillis(Date date) {
        Long millis = date.getTime();
        int day = 1000 * 60 * 60 * 24;
		date.setTime(millis / day * day);
		return date.getTime();
	}
	
	public static void main(String[] args) {
//		String str = "";
//		for(int i = 0; i < 100; i++) {
//			if(i % 10 == 0){
//				str = "";
//				str += "\"" + Code.validate() + "\"";
//			}
//			if(i < 99){
//				str += ",";
//			}
//			if(i % 10 == 9){
//				System.out.println(str);
//			}
//			str += "\"" + Code.validate() + "\"";
//		}

//		// N * X
//		String[][] nos = {
//				{"831700","830250","021854","367064","385447","757032","362821","882255","288252","035340"},
//				{"248865","327325","046561","534214","553684","360747","663668","855623","662861"},
//				{"270278","730525","602483","052648","824684","401247","286551","234565"},
//				{"836831","367647","487122","250815","565565","825288","876786"},
//				{"144823","508068","676710","646726","064274","723813"},
//				{"071307","238354","288586","176835","308264"},
//				{"403333","246636","466234","474736","548368","511522"},
//				{"515775","860531","804670","201024","486525","210167","827527"},
//				{"581573","485550","831384","685713","654285","765125","210864","644888"},
//				{"626577","418203","280658","035226","846223","742352","605810","621261","480547"}
//		};

//		String[] nos = {
//				"831700","830250","021854","367064","385447","757032","362821","882255","288252","035340",
//				"248865","327325","046561","534214","553684","360747","663668","855623","662861","265316",
//				"270278","730525","602483","052648","824684","401247","286551","234565","604840","841002",
//				"836831","367647","487122","250815","565565","825288","876786","740655","486287","651533",
//				"144823","508068","676710","646726","064274","723813","305062","808842","280700","445380",
//				"071307","238354","288586","176835","308264","344261","330580","785421","012462","804786",
//				"403333","246636","466234","474736","548368","511522","524764","811561","106682","238247",
//				"515775","860531","804670","201024","486525","210167","827527","204188","013436","437218",
//				"581573","485550","831384","685713","654285","765125","210864","644888","614610","423037",
//				"626577","418203","280658","035226","846223","742352","605810","621261","480547","358507"
//		};

		String[] nos = {
				"831700","830250","021854","367064","385447","757032","362821","882255","288252","035340",
				"248865","327325","046561","534214","553684","360747","663668","855623","662861","265316",
				"270278","730525","602483","052648","824684","401247","286551","234565","604840","841002",
				"836831","367647","487122","250815","565565","825288","876786","740655","486287","651533",
				"144823","508068","676710","646726","064274","723813","305062","808842","280700","445380",
				"071307","238354","288586","176835","308264","344261","330580","785421","012462","804786",
				"403333","246636","466234","474736"
		};
		Long begin = System.currentTimeMillis();
		for(int i = 0; i < 100000; i++) {
			Code.random1(nos, 10);
		}
		Long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - begin));
//		for(int i = 0; i < 5; i++){
//			Long millis = Code.whatMillis(new Date());
//			millis = millis - (1000*60*60*8);
//			System.out.println(millis);
//			Date date = new Date();
//			date.setTime(millis);
//			System.out.println(date);
//		}
	}
}
