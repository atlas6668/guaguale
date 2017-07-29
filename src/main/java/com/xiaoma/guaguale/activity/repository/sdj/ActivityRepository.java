/**
 * <p>Title: ActivityRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年2月3日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.repository.sdj;

import com.xiaoma.guaguale.activity.model.Activity;
import com.xiaoma.guaguale.base.repository.sdj.BaseRepository;

/**
 * <p>Title: ActivityRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年2月3日
 */
public interface ActivityRepository extends BaseRepository<Activity, Integer> {

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
