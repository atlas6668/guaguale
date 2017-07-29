/**
 * <p>Title: ActivityServiceImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年2月3日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaoma.guaguale.activity.model.Activity;
import com.xiaoma.guaguale.activity.repository.sdj.ActivityRepository;

/**
 * <p>Title: ActivityServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年2月3日
 */
@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public List<Activity> findAll() {
		return (List<Activity>) activityRepository.findAll();
	}

	@Override
	public Activity step1save(Activity model) {
		Activity activity = model;
		if(model.getId() != null){
			activity = activityRepository.findById(model.getId());
			activity.setAkey(model.getAkey());
			activity.setName(model.getName());
			activity.setIntro(model.getIntro());
			activity.setStarttime(model.getStarttime());
			activity.setStoptime(model.getStoptime());
		}
		return activityRepository.save(activity);
	}

	@Override
	public Activity step2save(Activity model) {
		Activity activity = activityRepository.findById(model.getId());
		activity.setOnedaymaxtimes(model.getOnedaymaxtimes());
		activity.setOnedayoutremind(model.getOnedayoutremind());
		activity.setOnewholetimes(model.getOnewholetimes());
		activity.setOnewholeoutremind(model.getOnewholeoutremind());
		activity.setZhongremind(model.getZhongremind());
		activity.setNozhongremind(model.getNozhongremind());
		activity.setWinningrate(model.getWinningrate());
		return activityRepository.save(activity);
	}

	@Override
	public Activity step3save(Activity model) {
		
		
		
		
		return null;
	}
	
	@Override
	public void delete(String[] _ids) {
		for(String id : _ids){
			activityRepository.delete(Integer.parseInt(id));
		}
	}
	
	@Override
	public Activity findById(Integer id) {
		return activityRepository.findById(id);
	}

	@Override
	public void delete(int id) {
		activityRepository.delete(id);
	}

	@Override
	public Activity save(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity findByAkey(String akey) {
		return activityRepository.findByAkey(akey);
	}
	
}
