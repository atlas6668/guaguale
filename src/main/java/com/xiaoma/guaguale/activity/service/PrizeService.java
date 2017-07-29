/**
 * <p>Title: PrizeService.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.xiaoma.guaguale.activity.model.Prize;

/**
 * <p>Title: PrizeService</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月29日
 */
public interface PrizeService {

	/**
	 * <p>findPrizes</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月29日
	 * @param searchParams
	 * @return
	 */
	List<Prize> findPrizes(Map<String, Object> searchParams);

	/**
	 * <p>save</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月29日
	 * @param model
	 * @return
	 */
	Prize save(Prize model);

	/**
	 * <p>findById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月29日
	 * @param id
	 * @return
	 */
	Prize findById(Integer id);

	/**
	 * <p>upload_img</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月29日
	 * @param file
	 * @param request
	 * @return
	 */
	String upload_img(MultipartFile file, HttpServletRequest request) throws IOException;

	/**
	 * <p>batchsave</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月29日
	 * @param prizes
	 */
	void batchsave(List<Prize> prizes);

	/**
	 * <p>delete</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年1月30日
	 * @param ids_
	 */
	void delete(String[] ids_);

	/**
	 * <p>deleteByActivity</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param id
	 */
	void deleteByActivity(Integer id);

	/**
	 * <p>createPrizes</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月4日
	 * @param model
	 */
	void createPrizes(Prize model);

	/**
	 * <p>findByActivity</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月4日
	 * @param activity
	 * @return
	 */
	List<Prize> findByActivity(Integer activity);

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
	 * <p>update</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月5日
	 * @param prize
	 */
	void update(Prize prize);

	/**
	 * <p>findByActivityAndUmobile</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月5日
	 * @param activity
	 * @param mobile
	 * @return
	 */
	Prize findByActivityAndUmobile(Integer activity, String mobile);

}
