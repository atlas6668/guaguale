/**
 * <p>Title: PrizeRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.repository.sdj;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.xiaoma.guaguale.activity.model.Prize;
import com.xiaoma.guaguale.base.repository.sdj.BaseRepository;

/**
 * <p>Title: PrizeRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月29日
 */
public interface PrizeRepository extends BaseRepository<Prize, Integer>, JpaSpecificationExecutor<Prize> {

	/**
	 * <p>findByActivity</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param id
	 * @return
	 */
	List<Prize> findByActivity(Integer id);

	/**
	 * <p>findByXmk</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月5日
	 * @param key
	 * @return
	 */
	Prize findByXmk(String key);

	/**
	 * <p>findByActivityAndUmobile</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月6日
	 * @param activity
	 * @param mobile
	 * @return
	 */
	Prize findByActivityAndUmobile(Integer activity, String mobile);

}
