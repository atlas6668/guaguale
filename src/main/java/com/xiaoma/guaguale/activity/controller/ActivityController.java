/**
 * <p>Title: ActivityController.java</p>
 * <p></p>
 * @author damon
 * @date 2015年2月2日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xiaoma.guaguale.activity.model.Activity;
import com.xiaoma.guaguale.activity.model.Pond;
import com.xiaoma.guaguale.activity.model.Prize;
import com.xiaoma.guaguale.activity.model.User;
import com.xiaoma.guaguale.activity.service.ActivityService;
import com.xiaoma.guaguale.activity.service.PondService;
import com.xiaoma.guaguale.activity.service.PrizeService;
import com.xiaoma.guaguale.activity.service.UserService;
import com.xiaoma.guaguale.common.utils.Code;
import com.xiaoma.guaguale.common.utils.Constants;
import com.xiaoma.guaguale.common.utils.StringUtil;

/**
 * <p>Title: ActivityController</p>
 * <p>活动管理</p> 
 * @author damon
 * @date 2015年2月2日
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {
	private static Logger log = LoggerFactory.getLogger(ActivityController.class);
	private final static String CHARSET = ";charset=UTF-8";
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PondService pondService;
	
	@Autowired
	private PrizeService prizeService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public String default_() {
		
		return "redirect:/activity/index";
	}
	
	@RequestMapping(value = "index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/activity/index");
    	List<Activity> activities = activityService.findAll();
    	mav.addObject("activities", activities);
    	return mav;
	}
	
	// 第一步 基础设置
	@RequestMapping(value = "/step1", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("model") Activity model) {
		ModelAndView mav = new ModelAndView("/activity/step1");
		return mav;
	}
	
	@RequestMapping(value = "/step1/{id}", method = RequestMethod.GET)
	public ModelAndView step1(@ModelAttribute("model") Activity model, 
			@PathVariable(value = "id") Integer id) {
		ModelAndView mav = new ModelAndView("/activity/step1");
		model = activityService.findById(id);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/step1/save", method = RequestMethod.POST)
	public String step1save(@ModelAttribute("model") Activity model) {
		// 保存活动基础设置
		model = activityService.step1save(model);
		Constants.GGLMAP.put(model.getAkey(), model);
		
		// 活动创建时生产奖池
		Pond pond = pondService.findByActivity(model.getId());
		if(pond == null){
			pond = pondService.createPonds(model);
		}
		Constants.PONDMAP.put(model.getId(), pond.getId());
		return "redirect:/activity/step2/" + model.getId();
	}
	
	// 第二步 活动项设置
	@RequestMapping(value = "/step2/{id}", method = RequestMethod.GET)
	public ModelAndView step2(@ModelAttribute("model") Activity model, 
			@PathVariable(value = "id") Integer id) {
		ModelAndView mav = new ModelAndView("/activity/step2");
		model = activityService.findById(id);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/step2/save", method = RequestMethod.POST)
	public String step2save(@ModelAttribute("model") Activity model) {
		model = activityService.step2save(model);
		Constants.GGLMAP.put(model.getAkey(), model);
		return "redirect:/activity/step3/" + model.getId();
	}
	
	// 第三步 奖项设置
	@RequestMapping(value = "/step3/{id}", method = RequestMethod.GET)
	public ModelAndView step3(@PathVariable(value = "id") Integer id, 
			@ModelAttribute("model") Prize model) {
		ModelAndView mav = new ModelAndView("/activity/step3");
		model.setActivity(id);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/step3/save", method = RequestMethod.POST)
	public String step3save(@ModelAttribute("model") Prize model,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			// 上传图片
			String targetPath = prizeService.upload_img(file, request);
			// 存储图片路径
			model.setImage(targetPath);
			// 批量生产奖品
			prizeService.createPrizes(model);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		// 是否继续添加奖品
		if(model.isContinued()){
			return "redirect:/activity/step3/" + model.getActivity();
		}
		List<Prize> prizes = prizeService.findByActivity(model.getActivity());
    	Integer pondid;
    	if(Constants.PONDMAP.containsKey(model.getActivity())){
    		pondid = Constants.PONDMAP.get(model.getActivity());
    		// 装配奖池
    		Pond pond = pondService.findById(pondid);
    		pond.setPrizes(prizes);
    		pondService.save(pond);
    		// 奖品缓存
    		model.setStatus(Prize.STATUS_0);
    		Map<String, Object> searchParams = new HashMap<String, Object>();
    		searchParams.put("model", model);
    		List<Prize> prizess = prizeService.findPrizes(searchParams);
    		String[] pp = new String[prizess.size()];
        	int count = 0;
        	for(Prize prize : prizess){
        		pp[count] = prize.getXmk();
        		count++;
        	}
    		Constants.PRIZEMAP.put(pondid, pp);
    	}
		return "redirect:/activity/index";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String ids){
		String[] _ids = ids.split(",");
		for(String id : _ids){
			activityService.delete(Integer.parseInt(id));
			pondService.deleteByActivity(Integer.parseInt(id));
			prizeService.deleteByActivity(Integer.parseInt(id));
		}
		return "redirect:/activity/index";
	}

	@ResponseBody
	@RequestMapping(value = "/choujiang", method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE + CHARSET)
	public String choujiang(@RequestParam(value = "callback") String callBack, 
			@RequestParam(value="userinfo") String userinfo, HttpServletRequest request) {
		// 结果
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 参数
			userinfo = URLDecoder.decode(userinfo, "utf-8");
			log.info("userinfo : " + userinfo);
			if(StringUtil.isValid(userinfo)){
				ret = dochoujiang(userinfo, request);
			}
			
			JSONObject result = new JSONObject(ret);
			if (callBack == null) {
	            return result.toString();
	        } else {
	            ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(new JSONPObject(callBack, result));
	        }
        } catch (Exception e) {
        	return "{'code':'"+Constants.ERROR+"','msg':'"+e.getMessage()+"','key':''}";
        }
	}
	
	/**
	 * <p>dochoujiang</p>
	 * <p>执行抽奖</p>
	 * @author damon
	 * @date 2015年2月5日
	 * @param userinfo
	 * @param request 
	 * @return 
	 */
	private synchronized Map<String, Object> dochoujiang(String userinfo, HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		Map<String, Object> ret = new HashMap<String, Object>();
		String key = "";
		String name = "";
		String image = "";
		ret.put("key", key);
		ret.put("prize", name);
		ret.put("image", image);
		JSONObject jsonObject = JSONObject.parseObject(userinfo);
		@SuppressWarnings("static-access")
		User user = jsonObject.toJavaObject(jsonObject, User.class);
		Activity activity = Constants.GGLMAP.get(user.getAkey());
		// 检查活动
		if(activity == null || activity.getStatus().equals(Activity.STATUS_0)){
			ret.put("code", Constants.NOREADY);
			ret.put("msg", "活动尚未就绪");
		} else if(activity.getStatus().equals(Activity.STATUS_2)){
			ret.put("code", Constants.FINISHED);
			ret.put("msg", "活动已经结束");
		} else {
			User user_ = userService.findByMobileAndAkey(user.getMobile(), user.getAkey());
			// 检查用户 抽奖机会
			if(user_ == null){
				ret.put("code", Constants.USER_NOVALIABLE);
				ret.put("msg", "无效用户");
			} else if(user_.getOnedaymaxtimes() != null && user_.getOnedaymaxtimes() <= 0){
				ret.put("code", Constants.USER_NODAYTIMES);
				ret.put("msg", activity.getOnedayoutremind());
			} else if(user_.getOnewholetimes() != null && user_.getOnewholetimes() <= 0){
				ret.put("code", Constants.USER_NOTIMES);
				ret.put("msg", activity.getOnewholeoutremind());
			} else {
				// 是否中过奖
				Prize _prize = prizeService.findByActivityAndUmobile(activity.getId(), user_.getMobile());
				if(_prize != null){
					ret.put("code", Constants.USER_HAVE_ZHONGJIANG);
					ret.put("msg", "用户已经中过奖");
					ret.put("key", _prize.getXmk());
					ret.put("prize", _prize.getName());
					ret.put("image", basePath + _prize.getImage());
				} else {
					// 初始化奖池
					cachePrize(user.getAkey());
					int pondid = Constants.PONDMAP.get(activity.getId());
					String[] pp = Constants.PRIZEMAP.get(pondid);
					key = Code.random1(pp, 10);
					// 未中奖
					if(key.equals(Constants.NOZHONG)){
						ret.put("code", Constants.NOZHONGJIANG);
						ret.put("msg", activity.getNozhongremind());
						// 中奖
					} else {
						// 取得奖品
						Prize prize = prizeService.findByXmk(key);
						image = basePath + prize.getImage();
						name = prize.getName();
						ret.put("code", Constants.ZHONGJIANG);
						ret.put("msg", activity.getZhongremind());
						ret.put("key", key);
						ret.put("prize", name);
						ret.put("image", image);
						// 更新数据库奖品信息
						prize.setStatus(Prize.STATUS_1);
						prize.setUnick(user.getNick());
						prize.setUmobile(user.getMobile());
						prizeService.update(prize);
					}
					// 更新当前用户抽奖次数
					user_.setOnedaymaxtimes(user_.getOnedaymaxtimes()-1);
					user_.setOnewholetimes(user_.getOnewholetimes()-1);
					userService.update(user_);
					// 记录抽奖过程
					
				}
			}
		}
		return ret;
	}

	// 用户管理
	@RequestMapping(value = "/users/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView users(@ModelAttribute("model")User model, 
			@PathVariable(value = "id") Integer id) {
		// 活动
		Activity activity = activityService.findById(id);
		model.setAkey(activity.getAkey());
		// 检索
		ModelAndView mav = new ModelAndView("/activity/users");
    	Map<String, Object> searchParams = new HashMap<String, Object>();
    	searchParams.put("model", model);
    	
    	List<User> users = userService.findByActivity(searchParams);
    	mav.addObject("users", users);
    	mav.addObject("id", id);
    	mav.addObject("model", model);
    	return mav;
	}
	
	// 奖品管理
	@RequestMapping(value = "/prizes/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView prizes(@ModelAttribute("model")Prize model, 
			@PathVariable(value = "id") Integer id) {
		// 活动
		model.setActivity(id);
		// 当前活动全部奖品
		ModelAndView mav = new ModelAndView("/activity/prizes");
    	Map<String, Object> searchParams = new HashMap<String, Object>();
    	searchParams.put("model", model);
    	
    	List<Prize> prizes = prizeService.findPrizes(searchParams);
    	mav.addObject("prizes", prizes);
    	mav.addObject("id", id);
    	mav.addObject("model", model);
    	return mav;
	}
	
	// 启动
	@RequestMapping(value = "/start/{id}", method = RequestMethod.GET)
	public String start(@PathVariable(value = "id") Integer id){
		// 缓存活动
		Activity activity = activityService.findById(id);
		Constants.GGLMAP.put(activity.getAkey(), activity);
		
		// 缓存奖池
		Pond pond = pondService.findByActivity(activity.getId());
		if(pond != null){
			Constants.PONDMAP.put(activity.getId(), pond.getId());
			// 缓存未被抽中奖品
			cachePrize(activity.getAkey());
		}
		// 启动定时任务
		timerScheduleTask(activity);
		return "redirect:/activity/index";
	}
	
	/**
	 * <p>timerScheduleTask</p>
	 * <p>定时任务</p>
	 * @author damon
	 * @date 2015年2月6日
	 */
	private void timerScheduleTask(final Activity activity) {
		// 定点启动活动的计时器
		final java.util.Timer timer0 = new java.util.Timer(false);  
		// 零点刷新任务的计时器
		final java.util.Timer timer1 = new java.util.Timer(false);  
		// 定点结束任务的计时器
		final java.util.Timer timer2 = new java.util.Timer(false);
		// 启动活动
		java.util.TimerTask task0 = new java.util.TimerTask(){
            @Override
            public void run() {
            	activity.setStatus(Activity.STATUS_1);
        		activityService.save(activity);
            	log.info(System.currentTimeMillis()+" : 活动启动！");
            	// 取消定时任务
            	timer0.cancel();
            }
        };
		
		// 刷新活动
        java.util.TimerTask task1 = new java.util.TimerTask(){  
            @Override  
            public void run() {
            	// 刷新用户日抽奖机会
            	userService.updateAllTimes();
            	log.info(System.currentTimeMillis()+" : 用户日抽奖机会刷新！");
            	
            }             
        };
        
        java.util.TimerTask task2 = new java.util.TimerTask(){  
            @Override  
            public void run() {
            	activity.setStatus(Activity.STATUS_2);
        		Activity activitytemp = activityService.save(activity);
        		Constants.GGLMAP.put(activitytemp.getAkey(), activitytemp);
            	log.info(System.currentTimeMillis()+" : 活动关闭！");
            	// 取消定时任务
            	timer1.cancel();
            	timer2.cancel();
            }             
        };
        // 启动活动时间
        Date startuptime = new Date();
        Long millis = Code.whatMillis(activity.getStarttime());
        // 零点毫秒数 取得活动时间的凌晨
        millis = millis - (1000*60*60*8);
        startuptime.setTime(millis);
        // 凌晨启动活动
        timer0.schedule(task0, startuptime);
        // 执行周期 秒 --> 分钟 --> 小时 --> 天
        long period = 1000 * 60 * 60 * 24;
        // 任务 第一次执行任务时间 任务执行时间间隔
        timer1.schedule(task1, startuptime, period);
        // 关闭活动时间
        Date shutdowntime = new Date();
        millis = Code.whatMillis(activity.getStoptime());
        // 零点毫秒数 取得活动时间的凌晨
        millis = millis - (1000*60*60*8);
        shutdowntime.setTime(millis);
        // 凌晨关闭活动
        timer2.schedule(task2, shutdowntime);
	}
	
	// 重新启动
	@RequestMapping(value = "/restart/{id}", method = RequestMethod.GET)
	public String restart(@PathVariable(value = "id") Integer id){
		// 缓存活动
		Activity activity = activityService.findById(id);
		Constants.GGLMAP.put(activity.getAkey(), activity);
		
		// 缓存奖池
		Pond pond = pondService.findByActivity(activity.getId());
		if(pond != null){
			Constants.PONDMAP.put(activity.getId(), pond.getId());
			// 缓存未被抽中奖品
			cachePrize(activity.getAkey());
		}
		activity.setStatus(Activity.STATUS_1);
		activity = activityService.save(activity);
		return "redirect:/activity/index";
	}
	
	/**
	 * <p>cachePrize</p>
	 * <p>初始化奖池并缓存</p>
	 * @author damon
	 * @param akey 
	 * @date 2015年2月5日
	 */
	private void cachePrize(String akey) {
		Activity activity = Constants.GGLMAP.get(akey);
		int pondid = Constants.PONDMAP.get(activity.getId());
		// 缓存奖品
		Prize model = new Prize();
		model.setActivity(activity.getId());
		// 未被抽中
		model.setStatus(Prize.STATUS_0);
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("model", model);
		List<Prize> prizes = prizeService.findPrizes(searchParams);
		int zhongjiangrate = activity.getWinningrate();
		// 奖池尚有奖品
		if(prizes.size() > 0){
			int zongchouma = prizes.size() * 100 / zhongjiangrate;
			String[] pp = new String[zongchouma];
			int count = 0;
			for(Prize prize : prizes){
				pp[count] = prize.getXmk();
				count++;
			}
			for(int i = count; i < zongchouma; i++){
				pp[i] = Constants.NOZHONG;
			}
			Constants.PRIZEMAP.put(pondid, pp);
		// 奖池无奖品
		} else {
			String[] pp = new String[100];
			for(int i = 0; i < 100; i++){
				pp[i] = Constants.NOZHONG;
			}
			Constants.PRIZEMAP.put(pondid, pp);
		}
	}
	
	@RequestMapping(value = "/duijiang/{activityid}/{id}", method = RequestMethod.GET)
	public String duijiang(@PathVariable(value = "activityid") Integer activityid, 
			@PathVariable(value = "id") Integer id) {
		Prize prize = prizeService.findById(id);
		prize.setDuijiangtime(new Date());
		prize.setStatus(Prize.STATUS_2);
		prizeService.update(prize);
		return "redirect:/activity/prizes/"+activityid;
	}
	
	// 结束
	@RequestMapping(value = "/stop/{id}", method = RequestMethod.GET)
	public String stop(@PathVariable(value = "id") Integer id){
		Activity activity = activityService.findById(id);
		activity.setStatus(Activity.STATUS_2);
		activity = activityService.save(activity);
		Constants.GGLMAP.put(activity.getAkey(), activity);
		return "redirect:/activity/index";
	}
	
	// 模拟测试 批量添加用户
	@RequestMapping(value = "/testausers", method = RequestMethod.GET)
	public ModelAndView ausers() {
		ModelAndView mav = new ModelAndView("/activity/testausers");
		return mav;
	}
	
	@RequestMapping(value = "/dotestausers", method = RequestMethod.POST)
	public String dotestausers(String akey, Integer number) {
//		log.info(""+number);
		if(number != null && number > 0){
			Activity activity = Constants.GGLMAP.get(akey);
			String initmobile = "13051401723";
			Long intmobile;
			User user;
			for(int i = 0; i < number; i++){
				// 检查用户
				User user_ = userService.findByMobileAndAkey(initmobile, akey);
				if(user_ == null){
					user = new User();
					user.setAkey(akey);
					user.setMobile(initmobile);
					user.setNick("测试用户"+Code.createXMK());
					user.setCreatetime(new Date());
					user.setOnedaymaxtimes(activity.getOnedaymaxtimes());
					user.setOnewholetimes(activity.getOnewholetimes());
					userService.save(user);
				}
				intmobile = Long.parseLong(initmobile) + 1;
				initmobile = intmobile + "";
			}
		}
		
		return "redirect:/activity/testalluserchoujiang";
	}
	
	// 模拟测试 批量所以用户抽奖
	@RequestMapping(value = "/testalluserchoujiang", method = RequestMethod.GET)
	public ModelAndView testalluserchoujiang() {
		ModelAndView mav = new ModelAndView("/activity/testalluserchoujiang");
		return mav;
	}
	
	@RequestMapping(value = "/dotestalluserchoujiang", method = RequestMethod.POST)
	public String dotestalluserchoujiang() {
		log.info("coming...");
		List<User> alluser = userService.findAll();
		Activity activity;
		Prize _prize; 
		Prize prize;
		String key;
		for(User user : alluser){
			activity = Constants.GGLMAP.get(user.getAkey());
			// 检查用户 抽奖机会
			if(user.getOnedaymaxtimes() != null && user.getOnedaymaxtimes() <= 0){
			} else if(user.getOnewholetimes() != null && user.getOnewholetimes() <= 0){
			} else {
				// 是否中过奖
				_prize = prizeService.findByActivityAndUmobile(activity.getId(), user.getMobile());
				if(_prize != null){
				} else {
					// 初始化奖池
					cachePrize(user.getAkey());
					int pondid = Constants.PONDMAP.get(activity.getId());
					String[] pp = Constants.PRIZEMAP.get(pondid);
					key = Code.random1(pp, 10);
					// 未中奖
					if(key.equals(Constants.NOZHONG)){
						// 中奖
					} else {
						// 取得奖品
						prize = prizeService.findByXmk(key);
						// 更新数据库奖品信息
						prize.setStatus(Prize.STATUS_1);
						prize.setUnick(user.getNick());
						prize.setUmobile(user.getMobile());
						prizeService.update(prize);
					}
					// 更新当前用户抽奖次数
					user.setOnedaymaxtimes(user.getOnedaymaxtimes()-1);
					user.setOnewholetimes(user.getOnewholetimes()-1);
					userService.update(user);
				}
			}
		}
		return "redirect:/activity/index";
	}
	
}
