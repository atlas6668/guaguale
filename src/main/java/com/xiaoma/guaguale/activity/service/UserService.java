/**
 * <p>Title: UserService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月28日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.xiaoma.guaguale.activity.model.User;

/**
 * <p>Title: UserService</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月28日
 */
public interface UserService {

	/**
	 * <p>search</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月29日
	 * @param searchParams
	 * @return
	 */
	Page<User> search(Map<String, Object> searchParams);

	/**
	 * <p>save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月29日
	 * @param model
	 * @return
	 */
	User save(User model);

	/**
	 * <p>findByMobileAndAkey</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月2日
	 * @param mobile
	 * @param akey 
	 * @return
	 */
	User findByMobileAndAkey(String mobile, String akey);

	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param _ids
	 */
	void delete(String[] _ids);

	/**
	 * <p>findByActivity</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月4日
	 * @param searchParams
	 * @return
	 */
	List<User> findByActivity(Map<String, Object> searchParams);

	/**
	 * <p>update</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月5日
	 * @param user_
	 */
	void update(User user_);

	/**
	 * <p>updateAllTimes</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月9日
	 */
	void updateAllTimes();

	/**
	 * <p>findAll</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月9日
	 * @return
	 */
	List<User> findAll();

}
