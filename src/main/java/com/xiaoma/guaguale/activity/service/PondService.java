/**
 * <p>Title: PondService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.service;

import java.util.List;
import java.util.Map;

import com.xiaoma.guaguale.activity.model.Activity;
import com.xiaoma.guaguale.activity.model.Pond;
import com.xiaoma.guaguale.activity.model.Prize;

/**
 * <p>Title: PondService</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月29日
 */
public interface PondService {

	/**
	 * <p>findPonds</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月29日
	 * @param searchParams
	 * @return
	 */
	List<Pond> findPonds(Map<String, Object> searchParams);

	/**
	 * <p>save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月30日
	 * @param model
	 * @return
	 */
	Pond save(Pond model);

	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月30日
	 * @param ids_
	 */
	void delete(String[] ids_);

	/**
	 * <p>findById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月30日
	 * @param id
	 * @return
	 */
	Pond findById(Integer id);

	/**
	 * <p>batchsave</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月30日
	 * @param ponds
	 */
	void batchsave(List<Pond> ponds);

	/**
	 * <p>drawPie</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月2日
	 * @param Pond
	 * @return
	 */
	String drawPie(Pond pond);

	/**
	 * <p>findPrizesById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月2日
	 * @param id
	 * @return
	 */
	List<Prize> findPrizesById(Integer id);

	List<Prize> findPrizesByIdWithStatus(Integer id, Integer status);

	/**
	 * <p>deleteByActivity</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param id
	 */
	void deleteByActivity(Integer id);

	/**
	 * <p>findByActivity</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月4日
	 * @param activity
	 * @return 
	 */
	Pond findByActivity(Integer activity);

	/**
	 * <p>createPonds</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月4日
	 * @param model
	 * @return
	 */
	Pond createPonds(Activity model);

	
	
}
