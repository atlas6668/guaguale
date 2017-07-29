/**
 * <p>Title: PrizeController.java</p>
 * <p>奖品管理</p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoma.guaguale.activity.model.Prize;
import com.xiaoma.guaguale.activity.service.PrizeService;
import com.xiaoma.guaguale.common.utils.Code;

/**
 * <p>Title: PrizeController</p>
 * <p>奖品管理</p> 
 * @author damon
 * @date 2015年1月29日
 */
@Controller
@RequestMapping("/prize")
public class PrizeController {
	private static Logger log = LoggerFactory.getLogger(PrizeController.class);
	
	@Autowired
	private PrizeService prizeService;
	
	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public String default_() {
		
		return "redirect:/prize/index";
	}
	
	@RequestMapping(value = "/index", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView index(@ModelAttribute("model")Prize model) {
		ModelAndView mav = new ModelAndView("/prize/index");
    	Map<String, Object> searchParams = new HashMap<String, Object>();
    	searchParams.put("model", model);
    	
    	List<Prize> prizes = prizeService.findPrizes(searchParams);
    	
    	mav.addObject("prizes", prizes);
    	mav.addObject("model", model);
    	return mav;
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("model") Prize model) {
		ModelAndView mav = new ModelAndView("/prize/form");
		
		return mav;
	}
	
	@RequestMapping(value = "/batchform", method = RequestMethod.GET)
	public ModelAndView batchform(@ModelAttribute("model") Prize model) {
		ModelAndView mav = new ModelAndView("/prize/batchform");
		
		return mav;
	}
	
	@RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("model") Prize model, 
			@PathVariable(value = "id") Integer id) {
		ModelAndView mav = new ModelAndView("/prize/form");
		model = prizeService.findById(id);
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("model") Prize model,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String result = "redirect:/prize/form/";
		try {
			// 上传图片
			String targetPath = prizeService.upload_img(file, request);
			// 存储图片路径
			model.setImage(targetPath);
			model.setXmk(Code.createXMK());
			model.setCreatetime(new Date());
			model.setStatus(0);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		
		model = prizeService.save(model);
		result += (model.getId() == null ? "" : model.getId());
		return result;
	}
	
	@RequestMapping(value = "/batchsave", method = RequestMethod.POST)
	public String batchsave(@ModelAttribute("model") Prize model,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		try {
			// 上传图片
			String targetPath = prizeService.upload_img(file, request);
			// 存储图片路径
			model.setImage(targetPath);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		
		// 批量生产
		// 产量
		int sum = model.getSum();
		List<Prize> prizes = new ArrayList<Prize>();
		Prize prize;
		for(int i = 0; i < sum; i++){
			prize = new Prize();
			prize.setCreatetime(new Date());
			prize.setImage(model.getImage());
			prize.setName(model.getName());
			prize.setPtype(model.getPtype());
			prize.setXmk(Code.createXMK());
			prize.setStatus(0);
			prizes.add(prize);
		}
		prizeService.batchsave(prizes);
		return "redirect:/prize/index";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String ids) {
//		log.info(ids);
		String[] ids_ = ids.split(",");
		prizeService.delete(ids_);
		return "redirect:/prize/index";
	}
	
}
