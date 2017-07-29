/**
 * <p>Title: Pond.java</p>
 * <p>奖池</p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>Title: Pond</p>
 * <p>奖池</p> 
 * @author damon
 * @date 2015年1月29日
 */
@Entity
@Table(name = "pond")
public class Pond extends Expand {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = -1300247638898883432L;

	/**
	 * p_name 奖池名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * p_createtime 奖池生产日期
	 */
	@Column(name = "createtime")
	private Date createtime;
	
	/**
	 * p_usetime 开奖日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "usetime")
	private Date usetime;
	
	/**
	 * 双向关联 奖品
	 */
	@ManyToMany(cascade = CascadeType.REFRESH) 
	@JoinTable(name = "pond_prize", 
		joinColumns = @JoinColumn(name = "pond_id", referencedColumnName="id"), 
		inverseJoinColumns = @JoinColumn(name = "prize_id", referencedColumnName="id")) 
	public List<Prize> prizes;
	
	@Column(name = "activity")
	private Integer activity;
	
//***********************************	getter and setter	*************************************//
	
	/**
	 * @return the activity
	 */
	public Integer getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	/**
	 * @return the prizes
	 */
	public List<Prize> getPrizes() {
		return prizes;
	}

	/**
	 * @param prizes the prizes to set
	 */
	public void setPrizes(List<Prize> prizes) {
		this.prizes = prizes;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the usetime
	 */
	public Date getUsetime() {
		return usetime;
	}

	/**
	 * @param usetime the usetime to set
	 */
	public void setUsetime(Date usetime) {
		this.usetime = usetime;
	}
	
}
