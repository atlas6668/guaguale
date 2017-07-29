/**
 * <p>Title: Constants.java</p>
 * <p></p>
 * @author damon
 * @date 2015年2月4日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.xiaoma.guaguale.activity.model.Activity;

/**
 * <p>Title: Constants</p>
 * <p></p> 
 * @author damon
 * @date 2015年2月4日
 */
public class Constants {
	
	// 
	public static final String AKEY = "新年抽奖";

	// 缓存 
	public static final Map<String, Activity> GGLMAP = new HashMap<String, Activity>();
	public static final Map<Integer, Integer> PONDMAP = new HashMap<Integer, Integer>();
	public static final Map<Integer, String[]> PRIZEMAP = new HashMap<Integer, String[]>();
	
	// 初始未中奖筹码
	public static final String NOZHONG = "188888888";
	
	// 返回值代码
	
	// 异常
	public static final String ERROR = "5000";
	
	// 活动尚未创建
	public static final String NOCREATE = "6000";
	
	// 活动尚未就绪
	public static final String NOREADY = "6001";
	
	// 活动已经结束
	public static final String FINISHED = "6003";
	
	// 新用户绑定成功
	public static final String USER_BIND_SUCCESS = "6666";
	
	// 老用户不再绑定
	public static final String USER_NONEED_BIND = "6660";
	
	// 无效用户
	public static final String USER_NOVALIABLE = "6663";
	
	// 日抽奖机会用完
	public static final String USER_NODAYTIMES = "5555";
	
	// 抽奖机会用完
	public static final String USER_NOTIMES = "55555";
	
	// 用户已经中过奖
	public static final String USER_HAVE_ZHONGJIANG = "1111";
	
	// 中奖
	public static final String ZHONGJIANG = "8888";
	
	// 未中奖
	public static final String NOZHONGJIANG = "8880";
	
}
