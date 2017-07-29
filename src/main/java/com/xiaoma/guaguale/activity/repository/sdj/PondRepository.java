/**
 * <p>Title: PondRepository.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.repository.sdj;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xiaoma.guaguale.activity.model.Pond;
import com.xiaoma.guaguale.activity.model.Prize;
import com.xiaoma.guaguale.base.repository.sdj.BaseRepository;

/**
 * <p>Title: PondRepository</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月29日
 */
public interface PondRepository extends BaseRepository<Pond, Integer>, JpaSpecificationExecutor<Pond> {

	/**
	 * <p>findPrizesById</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月2日
	 * @param id
	 * @return
	 */
	@Query("SELECT DISTINCT prize FROM Prize prize LEFT JOIN prize.ponds pond WHERE pond.id = :id ")
	List<Prize> findPrizesById(@Param("id")Integer id);

	@Query("SELECT DISTINCT prize FROM Prize prize LEFT JOIN prize.ponds pond WHERE pond.id = :id AND prize.status = :status ")
	List<Prize> findPrizesByIdWithStatus(@Param("id") Integer id, @Param("status") Integer status);

	/**
	 * <p>findByActivity</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月3日
	 * @param id
	 * @return
	 */
	Pond findByActivity(Integer id);

}
