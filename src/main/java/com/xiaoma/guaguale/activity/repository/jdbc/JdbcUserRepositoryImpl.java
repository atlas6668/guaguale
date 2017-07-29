/**
 * <p>Title: JdbcUserRepositoryImpl.java</p>
 * <p></p>
 * @author damon
 * @date 2015年2月9日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: JdbcUserRepositoryImpl</p>
 * <p></p> 
 * @author damon
 * @date 2015年2月9日
 */
@Repository("jdbcUserRepository")
public class JdbcUserRepositoryImpl implements JdbcUserRepository {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcUserRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void updateAllTimes() {
		String sql = "update user u set u.onedaymaxtimes=3 ";
		jdbcTemplate.update(sql);
	}

}
