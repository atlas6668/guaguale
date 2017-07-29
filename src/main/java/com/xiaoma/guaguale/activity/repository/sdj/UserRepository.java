/**
 * <p>Title: UserRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月28日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.repository.sdj;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xiaoma.guaguale.activity.model.User;
import com.xiaoma.guaguale.base.repository.sdj.BaseRepository;

/**
 * <p>Title: UserRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月28日
 */
public interface UserRepository extends BaseRepository<User, Integer>, JpaSpecificationExecutor<User> {

	/**
	 * <p>findByMobile</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月2日
	 * @param u_mobile
	 * @return
	 */
	User findByMobile(String mobile);

	/**
	 * <p>findByMobileAndAkey</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月4日
	 * @param mobile
	 * @param akey
	 * @return
	 */
	User findByMobileAndAkey(String mobile, String akey);

}
