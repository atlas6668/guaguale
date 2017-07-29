/**
 * <p>Title: ActivityService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年2月3日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.service;

import java.util.List;

import com.xiaoma.guaguale.activity.model.Activity;

/**
 * <p>Title: ActivityService</p>
 * <p></p> 
 * @author damon
 * @date 2015年2月3日
 */
public interface ActivityService {

	/**
	 * <p>findAll</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @return
	 */
	List<Activity> findAll();

	/**
	 * <p>step1save</p>
	 * <p></p>
	 * @author damon
	 * @param model 
	 * @date 2015年2月3日
	 * @return
	 */
	Activity step1save(Activity model);
	
	/**
	 * <p>step2save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param model
	 * @return
	 */
	Activity step2save(Activity model);
	
	/**
	 * <p>step3save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param model
	 * @return
	 */
	Activity step3save(Activity model);
	
	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param parseInt
	 */
	void delete(int id);

	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param _ids
	 */
	void delete(String[] _ids);

	/**
	 * <p>findById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param id
	 * @return
	 */
	Activity findById(Integer id);

	/**
	 * <p>save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月4日
	 * @param activity
	 * @return 
	 */
	Activity save(Activity activity);

	/**
	 * <p>findByAkey</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月4日
	 * @param akey
	 * @return
	 */
	Activity findByAkey(String akey);

}
