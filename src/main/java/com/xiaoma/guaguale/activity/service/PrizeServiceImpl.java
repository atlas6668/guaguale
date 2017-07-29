/**
 * <p>Title: PrizeServiceImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xiaoma.guaguale.activity.model.Prize;
import com.xiaoma.guaguale.activity.repository.sdj.PrizeRepository;
import com.xiaoma.guaguale.common.utils.Code;
import com.xiaoma.guaguale.common.utils.DateUtil;
import com.xiaoma.guaguale.common.utils.FileUtils;

/**
 * <p>Title: PrizeServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月29日
 */
@Service
@Transactional
public class PrizeServiceImpl implements PrizeService {

	@Autowired
	private PrizeRepository prizeRepository;

	@Override
	public List<Prize> findPrizes(Map<String, Object> searchParams) {
		return prizeRepository.findAll(buildSpecification(searchParams));
	}
	
	/**
	 * <p>Title: buildSpecification</p>
	 * <p>Description: 构建查询条件</p>
	 * @param searchParams
	 * @return
	 */
	private Specification<Prize> buildSpecification(final Map<String, Object> searchParams) {
		return new Specification<Prize>(){  
	        public Predicate toPredicate(Root<Prize> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        	Prize model = (Prize) searchParams.get("model");
	        	// where 1=1 --> cb.conjunction()
	        	List<Predicate> pl = new ArrayList<Predicate>();
	        	pl.add(cb.conjunction());
	        	
	        	// 兑奖码
	        	if(model.getXmk() != null && !model.getXmk().equals("")){
	        		pl.add(cb.like(root.get("xmk").as(String.class), "%"+model.getXmk()+"%"));
	        	}
	        	
	        	// 奖品名称
	        	if(model.getName() != null && !model.getName().equals("")){
	        		pl.add(cb.like(root.get("name").as(String.class), "%"+model.getName()+"%"));
	        	}
	        	
	        	// 兑奖状态 跟页面约定 "9"表示未选择状态条件
				if(model.getStatus() != null && model.getStatus() != 9){
					pl.add(cb.equal(root.get("status").as(Integer.class), model.getStatus()));
				}
				
				// 活动
	        	if(model.getActivity() != null){
	        		pl.add(cb.equal(root.get("activity").as(Integer.class), model.getActivity()));
	        	}
				
				// 拼接where 条件
	        	Object[] oa = pl.toArray();
	        	Predicate[] pp = new Predicate[oa.length];
	        	for(int i = 0; i < oa.length; i++){
	        		pp[i] = (Predicate) oa[i];
	        	}
	        	query.where(pp);
	            return query.getRestriction();  
	        }  
    	};
	}

	@Override
	public Prize save(Prize model) {
		Prize prize = model;
		if(model.getId() != null){
			prize = prizeRepository.findById(model.getId());
			prize.setName(model.getName());
			prize.setPtype(model.getPtype());
			prize.setImage(model.getImage());
		}
		return prizeRepository.save(prize);
	}

	@Override
	public Prize findById(Integer id) {
		return prizeRepository.findById(id);
	}

	@Override
	public String upload_img(MultipartFile file, HttpServletRequest request) throws IOException {
		//目标目录构成方案 upload + 模块代码 + 文件类型 + 日期 
		return FileUtils.transferTo(file, request, "/resources/upload/prize/img/" + DateUtil.getDate("yyyyMMdd"));
	}

	@Override
	public void batchsave(List<Prize> prizes) {
		for(Prize prize : prizes){
			prizeRepository.save(prize);
		}
	}

	@Override
	public void delete(String[] ids_) {
		for(String id : ids_){
			prizeRepository.delete(Integer.parseInt(id));
		}
	}

	@Override
	public void deleteByActivity(Integer id) {
		List<Prize> prizes = prizeRepository.findByActivity(id);
		for(Prize prize : prizes){
			prizeRepository.delete(prize);
		}
	}

	@Override
	public void createPrizes(Prize model) {
		int sum = model.getSum();
		Prize prize;
		for(int i = 0; i < sum; i++){
			prize = new Prize();
			prize.setCreatetime(new Date());
			prize.setImage(model.getImage());
			prize.setName(model.getName());
			prize.setPtype(model.getPtype());
			prize.setXmk(Code.createXMK());
			prize.setActivity(model.getActivity());
			prizeRepository.save(prize);
		}
	}

	@Override
	public List<Prize> findByActivity(Integer activity) {
		return prizeRepository.findByActivity(activity);
	}

	@Override
	public Prize findByXmk(String key) {
		return prizeRepository.findByXmk(key);
	}

	@Override
	public void update(Prize prize) {
		prizeRepository.save(prize);
	}

	@Override
	public Prize findByActivityAndUmobile(Integer activity, String mobile) {
		return prizeRepository.findByActivityAndUmobile(activity, mobile);
	}
	
}
