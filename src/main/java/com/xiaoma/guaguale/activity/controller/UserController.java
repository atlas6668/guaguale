/**
 * <p>Title: UserController.java</p>
 * <p>用户管控</p>
 * @author damon
 * @date 2015年1月28日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.controller;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.xiaoma.guaguale.activity.model.Activity;
import com.xiaoma.guaguale.activity.model.User;
import com.xiaoma.guaguale.activity.service.UserService;
import com.xiaoma.guaguale.common.taglib.Pager;
import com.xiaoma.guaguale.common.utils.Constants;
import com.xiaoma.guaguale.common.utils.StringUtil;

/**
 * <p>Title: UserController</p>
 * <p>用户管控</p> 
 * @author damon
 * @date 2015年1月28日
 */
@Controller
@RequestMapping("/user")
public class UserController {
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	private final static String CHARSET = ";charset=UTF-8";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public String default_() {
		
		return "redirect:/user/index";
	}
	
	@RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index(@ModelAttribute("model")User model) {
		ModelAndView mav = new ModelAndView("/user/index");
    	int pageNum = model.getPageNum(); 
    	int pageSize = model.getPageSize();
    	Map<String, Object> searchParams = new HashMap<String, Object>();
    	searchParams.put("pageNum", pageNum);
    	searchParams.put("pageSize", pageSize);
    	searchParams.put("model", model);
    	
    	Page<User> users = userService.search(searchParams);
    	Pager<User> pager = new Pager<User>(pageNum,(int)users.getTotalElements(), 
    			pageSize, (List<User>)users.getContent());
    	
    	mav.addObject("users", users.getContent());
    	mav.addObject("pager", pager);
    	mav.addObject("model", model);
    	return mav;
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("model") User model) {
		ModelAndView mav = new ModelAndView("/user/form");
		
		return mav;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("model") User model) {
		model.setCreatetime(new Date());
		userService.save(model);
		
		return "redirect:/user/index";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String ids){
		String[] _ids = ids.split(",");
		userService.delete(_ids);
		return "redirect:/user/index";
	}
	
	@ResponseBody
	@RequestMapping(value = "/binduser", method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE + CHARSET)
	public String binduser(@RequestParam(value = "callback") String callBack, 
			@RequestParam(value="userinfo") String userinfo) {
		// 结果
		Map<String, Object> ret = new HashMap<String, Object>();
		try {
			// 参数
			userinfo = URLDecoder.decode(userinfo, "utf-8");
//			log.info("userinfo : " + userinfo);
			if(StringUtil.isValid(userinfo)){
				ret = dobind(userinfo);
			}
			
			JSONObject result = new JSONObject(ret);
			if (callBack == null) {
	            return result.toString();
	        } else {
	            ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.writeValueAsString(new JSONPObject(callBack, result));
	        }
        } catch (Exception e) {
        	log.error(e.getMessage());
        	return "{'code':'"+Constants.ERROR+"','msg':'"+e.getMessage()+"'}";
        }
	}

	/**
	 * <p>dobind</p>
	 * <p>执行绑定</p>
	 * @author damon
	 * @date 2015年2月5日
	 * @param userinfo
	 * @return
	 */
	private Map<String, Object> dobind(String userinfo) {
		Map<String, Object> ret = new HashMap<String, Object>();
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
		// 绑定中
		} else {
			// 检查用户
			User user_ = userService.findByMobileAndAkey(user.getMobile(), user.getAkey());
			if(user_ == null){
				user.setCreatetime(new Date());
				user.setOnedaymaxtimes(activity.getOnedaymaxtimes());
				user.setOnewholetimes(activity.getOnewholetimes());
				userService.save(user);
				ret.put("code", Constants.USER_BIND_SUCCESS);
				ret.put("msg", "新用户绑定成功");
			} else {
				ret.put("code", Constants.USER_NONEED_BIND);
				ret.put("msg", "老用户不再绑定");
			}
		}
		return ret;
	}
	
}
