/**
 * <p>Title: UserServiceImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月28日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.xiaoma.guaguale.activity.model.User;
import com.xiaoma.guaguale.activity.repository.jdbc.JdbcUserRepository;
import com.xiaoma.guaguale.activity.repository.sdj.UserRepository;
import com.xiaoma.guaguale.common.utils.StringUtil;

/**
 * <p>Title: UserServiceImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月28日
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	@Qualifier("jdbcUserRepository")
	private JdbcUserRepository jdbcUserRepository;

	@Override
	public Page<User> search(Map<String, Object> searchParams) {
		int pageNum = (Integer) searchParams.get("pageNum");
		int pageSize = (Integer) searchParams.get("pageSize");
		//PageRequest page zero-based page index. size the size of the page to be returned.
		return userRepository.findAll(buildSpecification(searchParams), 
				new PageRequest(pageNum - 1, pageSize, new Sort(Direction.ASC, "id")));
	}
	
	/**
	 * <p>Title: buildSpecification</p>
	 * <p>Description: 构建查询条件</p>
	 * @param searchParams
	 * @return
	 */
	private Specification<User> buildSpecification(final Map<String, Object> searchParams) {
		return new Specification<User>(){  
	        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
	        	User model = (User) searchParams.get("model");
	        	// where 1=1 --> cb.conjunction()
	        	List<Predicate> pl = new ArrayList<Predicate>();
	        	pl.add(cb.conjunction());
				
	        	if(StringUtil.isValid(model.getNick())){
	        		pl.add(cb.like(root.get("nick").as(String.class), "%"+model.getNick()+"%"));
	        	}
	        	
	        	if(StringUtil.isValid(model.getMobile())){
	        		pl.add(cb.like(root.get("mobile").as(String.class), "%"+model.getMobile()+"%"));
	        	}
	        	
	        	if(StringUtil.isValid(model.getAkey())){
	        		pl.add(cb.like(root.get("akey").as(String.class), "%"+model.getAkey()+"%"));
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
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findByMobileAndAkey(String mobile, String akey) {
		return userRepository.findByMobileAndAkey(mobile, akey);
	}

	@Override
	public void delete(String[] _ids) {
		for(String id : _ids){
			userRepository.delete(Integer.parseInt(id));
		}
	}

	@Override
	public List<User> findByActivity(Map<String, Object> searchParams) {
		return userRepository.findAll(buildSpecification(searchParams));
	}

	@Override
	public void update(User user_) {
		userRepository.save(user_);
	}

	@Override
	public void updateAllTimes() {
		jdbcUserRepository.updateAllTimes();
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

}
