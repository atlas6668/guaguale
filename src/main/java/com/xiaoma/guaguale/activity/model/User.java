/**
 * <p>Title: User.java</p>
 * <p>彩民</p>
 * @author damon
 * @date 2015年1月28日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.xiaoma.guaguale.base.model.BaseSearchEntity;

/**
 * <p>Title: User</p>
 * <p>彩民</p> 
 * @author damon
 * @date 2015年1月28日
 */
@Entity
@Table(name = "user")
public class User extends BaseSearchEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 5843918042058072750L;
	
	/**
	 * nick 昵称
	 */
	@Column(name = "nick")
	private String nick;
	
	/**
	 * u_mobile 手机
	 */
	@Column(name = "mobile")
	private String mobile;
	
	/**
	 * u_createtime 记录时间
	 */
	@Column(name = "createtime")
	private Date createtime;
	
	/**
	 * akey 活动关键词
	 */
	@Column(name = "akey")
	private String akey;
	
	/**
	 * onedaymaxtimes 日剩余抽奖次数
	 */
	@Column(name = "onedaymaxtimes")
	private Integer onedaymaxtimes = 0;
	
	/**
	 * onewholetimes 总剩余抽奖次数
	 */
	@Column(name = "onewholetimes")
	private Integer onewholetimes = 0;
	
//***********************************	getter and setter	*************************************//
	
	/**
	 * @return the akey
	 */
	public String getAkey() {
		return akey;
	}

	/**
	 * @param akey the akey to set
	 */
	public void setAkey(String akey) {
		this.akey = akey;
	}

	/**
	 * @return the nick
	 */
	public String getNick() {
		return nick;
	}

	/**
	 * @param nick the nick to set
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * @return the onedaymaxtimes
	 */
	public Integer getOnedaymaxtimes() {
		return onedaymaxtimes;
	}

	/**
	 * @param onedaymaxtimes the onedaymaxtimes to set
	 */
	public void setOnedaymaxtimes(Integer onedaymaxtimes) {
		this.onedaymaxtimes = onedaymaxtimes;
	}

	/**
	 * @return the onewholetimes
	 */
	public Integer getOnewholetimes() {
		return onewholetimes;
	}

	/**
	 * @param onewholetimes the onewholetimes to set
	 */
	public void setOnewholetimes(Integer onewholetimes) {
		this.onewholetimes = onewholetimes;
	}

}
