/**
 * <p>Title: PondController.java</p>
 * <p>奖池管理</p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoma.guaguale.activity.model.Prize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoma.guaguale.activity.model.Pond;
import com.xiaoma.guaguale.activity.service.PondService;

/**
 * <p>Title: PondController</p>
 * <p>奖池管理</p> 
 * @author damon
 * @date 2015年1月29日
 */
@Controller
@RequestMapping("/pond")
public class PondController {
	private static Logger log = LoggerFactory.getLogger(PondController.class);
	
	@Autowired
	private PondService pondService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public String default_() {
		
		return "redirect:/pond/index";
	}
	
	@RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index(@ModelAttribute("model")Pond model) {
		ModelAndView mav = new ModelAndView("/pond/index");
    	Map<String, Object> searchParams = new HashMap<String, Object>();
    	searchParams.put("model", model);
    	
    	List<Pond> ponds = pondService.findPonds(searchParams);
    	
    	mav.addObject("ponds", ponds);
    	mav.addObject("model", model);
    	return mav;
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("model") Pond model) {
		ModelAndView mav = new ModelAndView("/pond/form");
		
		return mav;
	}
	
	@RequestMapping(value = "/batchform", method = RequestMethod.GET)
	public ModelAndView batchform(@ModelAttribute("model") Pond model) {
		ModelAndView mav = new ModelAndView("/pond/batchform");
		
		return mav;
	}
	
	@RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("model") Pond model, 
			@PathVariable(value = "id") Integer id) {
		ModelAndView mav = new ModelAndView("/pond/form");
		model = pondService.findById(id);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("model") Pond model) {
		model.setCreatetime(new Date());
		model = pondService.save(model);
		
		return "redirect:/pond/index";
	}
	
	@RequestMapping(value = "/batchsave", method = RequestMethod.POST)
	public String batchsave(@ModelAttribute("model") Pond model) {
		// 批量生产
		// 产量
		int sum = model.getSum();
		List<Pond> ponds = new ArrayList<Pond>();
		Pond pond;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(model.getUsetime());
		Long millis = calendar.getTimeInMillis();
		for(int i = 0; i < sum; i++){
			pond = new Pond();
			calendar.setTimeInMillis(millis + 1000*60*60*24*i);
			pond.setUsetime(calendar.getTime());
			pond.setCreatetime(new Date());
			pond.setName(model.getName());
			ponds.add(pond);
		}
		pondService.batchsave(ponds);
		return "redirect:/pond/index";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String ids) {
		log.info(ids);
		String[] ids_ = ids.split(",");
		pondService.delete(ids_);
		return "redirect:/pond/index";
	}
	
	@RequestMapping(value = "/chart/{id}", method = RequestMethod.GET)
	public ModelAndView chart(@ModelAttribute("model") Pond model, 
			@PathVariable(value = "id") Integer id) {
		ModelAndView mav = new ModelAndView("/pond/echart");
		
		model = pondService.findById(id);
		model.setPrizes(pondService.findPrizesByIdWithStatus(id, Prize.STATUS_0));
		String option = pondService.drawPie(model);
		mav.addObject("option", option);
		mav.addObject("model", model);
		return mav;
	}
	
}
