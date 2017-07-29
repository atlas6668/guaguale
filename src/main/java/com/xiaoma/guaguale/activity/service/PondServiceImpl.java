/**
 * <p>Title: PondServiceImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xiaoma.guaguale.activity.model.Activity;
import com.xiaoma.guaguale.activity.model.Pond;
import com.xiaoma.guaguale.activity.model.Prize;
import com.xiaoma.guaguale.activity.repository.sdj.PondRepository;
import com.xiaoma.guaguale.common.utils.DateUtil;
import com.xiaoma.guaguale.common.utils.echart.Legend;
import com.xiaoma.guaguale.common.utils.echart.Option;
import com.xiaoma.guaguale.common.utils.echart.Options;
import com.xiaoma.guaguale.common.utils.echart.Serie;
import com.xiaoma.guaguale.common.utils.echart.Timeline;

/**
 * <p>Title: PondServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月29日
 */
@Service
@Transactional
public class PondServiceImpl implements PondService {
	
	@Autowired
	private PondRepository pondRepository;
	
	@Override
	public List<Pond> findPonds(Map<String, Object> searchParams) {
		return pondRepository.findAll(buildSpecification(searchParams));
	}
	
	/**
	 * <p>Title: buildSpecification</p>
	 * <p>Description: 构建查询条件</p>
	 * @param searchParams
	 * @return
	 */
	private Specification<Pond> buildSpecification(final Map<String, Object> searchParams) {
		return new Specification<Pond>(){  
	        public Predicate toPredicate(Root<Pond> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//	        	Pond model = (Pond) searchParams.get("model");
	        	// where 1=1 --> cb.conjunction()
	        	List<Predicate> pl = new ArrayList<Predicate>();
	        	pl.add(cb.conjunction());
				
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
	public Pond save(Pond model) {
		return pondRepository.save(model);
	}

	@Override
	public void delete(String[] ids_) {
		for(String id : ids_){
			pondRepository.delete(Integer.parseInt(id));
		}
	}

	@Override
	public Pond findById(Integer id) {
		return pondRepository.findById(id);
	}

	@Override
	public void batchsave(List<Pond> ponds) {
		for(Pond pond : ponds){
			pondRepository.save(pond);
		}
	}

	@Override
	public String drawPie(Pond pond) {
		// 时间轴和图例数据
		Map<String, Map<String, List<Prize>>> datas = packageDatas(pond);
		
		int kg = 0;
		// 组织时间轴
		int kg1 = kg + 1;
		// 时间轴数据
		SortedMap<String, List<Prize>> mtimelinedatas = (SortedMap<String, List<Prize>>) datas.get("timeline");
		Object[] oa = mtimelinedatas.keySet().toArray();
		String[] atimelinedatas = new String[oa.length];
		for(int i = 0; i < oa.length; i++){
			atimelinedatas[i] =  (String) oa[i];
		}
		Timeline timeline = new Timeline(kg1, atimelinedatas);
		
		// 组织图例
		int kg2 = kg + 1;
		Map<String, List<Prize>> mlegenddatas = datas.get("legend");
		Object[] ob = mlegenddatas.keySet().toArray();
		String[] alegenddatas = new String[ob.length];
		for(int i = 0; i < ob.length; i++){
			alegenddatas[i] = (String) ob[i];
		}
		Legend legend = new Legend(kg2 + 2, alegenddatas);
		
		// 组织数据
		// 装填数据
		List<Serie> series = setDatas(kg2, mtimelinedatas, atimelinedatas, mlegenddatas, alegenddatas);
		Options options = new Options(kg2, pond.getName(), "奖品数量占比", legend, series);
		// 实例化报表
		Option option = new Option(kg, timeline, options);
		return option.toString();
	}

	/**
	 * <p>setDatas</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月2日
	 * @param kg2
	 * @param mtimelinedatas
	 * @param atimelinedatas
	 * @param mlegenddatas
	 * @param alegenddatas
	 * @return
	 */
	private List<Serie> setDatas(int kg2,
			SortedMap<String, List<Prize>> mtimelinedatas,
			String[] atimelinedatas, Map<String, List<Prize>> mlegenddatas,
			String[] alegenddatas) {
		List<Serie> series = new ArrayList<Serie>();
		// keys 固定数据顺序
		String[] keys = alegenddatas;
		
		Serie serie;
		// 每日数据集
		List<Prize> dayprizes;
		Prize prize;
		Map<String,Integer> map;
		for(int j = 0; j < atimelinedatas.length; j++){
			// 取得每日的数据集
			dayprizes = mtimelinedatas.get(atimelinedatas[j]);
			map = new HashMap<String,Integer>();
			
			// 根据keys分类到map中
			for(int k = 0; k < dayprizes.size(); k++){
				prize = dayprizes.get(k);
				for(int i = 0; i < keys.length; i++){
					if(!map.containsKey(keys[i])){
						map.put(keys[i], 0);
					}
					if(keys[i].equals(prize.getName())){
						int value = map.get(keys[i]);
						map.put(keys[i], value + 1);
					}
				}
			}
			serie = new Serie(kg2 + 2, "奖品", map, keys);
			series.add(serie);
		}
		return series;
	}

	/**
	 * <p>packageDatas</p>
	 * <p></p>
	 * @author damon
	 * @date 2015年2月2日
	 * @param prizes
	 * @return
	 */
	private Map<String, Map<String, List<Prize>>> packageDatas(Pond pond) {
		Map<String, Map<String, List<Prize>>> datas = new HashMap<String, Map<String, List<Prize>>>();
		// 时间轴数据 有序
		SortedMap<String, List<Prize>> mtimelinedatas = new TreeMap<String, List<Prize>>();
		// 图例
		Map<String, List<Prize>> mlegenddatas = new HashMap<String, List<Prize>>();
		
		// 每日数据集
		List<Prize> dayprizes;
		// 每个legend数据集
		List<Prize> legendprizes;
		
		// 细节 去重
		for(Prize prize : pond.getPrizes()){
			// 时间轴数据
			String timeline = DateUtil.formatDate(pond.getUsetime(), "yyyy-MM-dd");
			if(!mtimelinedatas.containsKey(timeline)){
				dayprizes = new ArrayList<Prize>();
				dayprizes.add(prize);
				mtimelinedatas.put(timeline, dayprizes);
			} else {
				List<Prize> dayprizes_ = mtimelinedatas.get(timeline);
				dayprizes_.add(prize);
				mtimelinedatas.put(timeline, dayprizes_);
			}
			
			// 图例，图表数据
			// 图例
			String legend = prize.getName();
			if(!mlegenddatas.containsKey(legend)){
				legendprizes = new ArrayList<Prize>();
				legendprizes.add(prize);
				mlegenddatas.put(legend, legendprizes);
			} else {
				List<Prize> legendprizes_ = mlegenddatas.get(legend);
				legendprizes_.add(prize);
				mlegenddatas.put(legend, legendprizes_);
			}
			
		}
		// 约定timeline存放时间轴数据
		datas.put("timeline", mtimelinedatas);
		// 约定legend存放图例数据
		datas.put("legend", mlegenddatas);
		return datas;
	}

	@Override
	public List<Prize> findPrizesById(Integer id) {
		return pondRepository.findPrizesById(id);
	}

	@Override
	public List<Prize> findPrizesByIdWithStatus(Integer id, Integer status) {
		return pondRepository.findPrizesByIdWithStatus(id, status);
	}

	@Override
	public void deleteByActivity(Integer id) {
		Pond pond = pondRepository.findByActivity(id);
		if(pond != null){
			pondRepository.delete(pond);
		}
	}

	@Override
	public Pond findByActivity(Integer id) {
		return pondRepository.findByActivity(id);
	}

	@Override
	public Pond createPonds(Activity model) {
		Pond pond = new Pond();
		pond.setUsetime(model.getStarttime());
		pond.setCreatetime(new Date());
		pond.setName(model.getName());
		pond.setActivity(model.getId());
		return pondRepository.save(pond);
	}

}
