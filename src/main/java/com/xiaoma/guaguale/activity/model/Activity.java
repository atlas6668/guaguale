/**
 * <p>Title: Activity.java</p>
 * <p></p>
 * @author damon
 * @date 2015年2月3日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.xiaoma.guaguale.base.model.BaseEntity;

/**
 * <p>Title: Activity</p>
 * <p>活动</p> 
 * @author damon
 * @date 2015年2月3日
 */
@Entity
@Table(name = "activity")
public class Activity extends BaseEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 804750858090776113L;
	
	/**
	 * STATUS_0 编辑中
	 */
	public static final int STATUS_0 = 0;
	
	/**
	 * STATUS_1 活动中
	 */
	public static final int STATUS_1 = 1;
	
	/**
	 * STATUS_2 已结束
	 */
	public static final int STATUS_2 = 2;
	
	/**
	 * akey key
	 */
	@Column(name = "akey")
	private String akey;
	
	/**
	 * name 活动名称
	 */
	@Column(name = "name")
	private String name;
	
	/**
	 * intro 活动介绍
	 */
	@Column(name = "intro")
	private String intro;
	
	/**
	 * starttime 活动开始时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "starttime")
	private Date starttime;
	
	/**
	 * stoptime 活动结束时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "stoptime")
	private Date stoptime;
	
	/**
	 * status 状态
	 */
	@Column(name = "status")
	private Integer status = STATUS_0;
	
	/**
	 * onedaymaxtimes 每人每天抽奖次数
	 */
	@Column(name = "onedaymaxtimes")
	private Integer onedaymaxtimes = 0;
	
	/**
	 * onedayoutremind 每人每天超出抽奖次数提示
	 */
	@Column(name = "onedayoutremind")
	private String onedayoutremind;
	
	/**
	 * onewholetimes 每人全部抽奖次数
	 */
	@Column(name = "onewholetimes")
	private Integer onewholetimes = 0;
	
	/**
	 * onewholeoutremind 每人超出全部抽奖次数提示
	 */
	@Column(name = "onewholeoutremind")
	private String onewholeoutremind;
	
	/**
	 * zhongremind 中奖提示
	 */
	@Column(name = "zhongremind")
	private String zhongremind;
	
	/**
	 * nozhongremind 未中奖提示
	 */
	@Column(name = "nozhongremind")
	private String nozhongremind;
	
	/**
	 * winningrate 中奖概率
	 */
	@Column(name = "winningrate")
	private Integer winningrate = 0;
	
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
	 * @return the intro
	 */
	public String getIntro() {
		return intro;
	}

	/**
	 * @param intro the intro to set
	 */
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	/**
	 * @return the starttime
	 */
	public Date getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the stoptime
	 */
	public Date getStoptime() {
		return stoptime;
	}

	/**
	 * @param stoptime the stoptime to set
	 */
	public void setStoptime(Date stoptime) {
		this.stoptime = stoptime;
	}
	
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * @return the onedayoutremind
	 */
	public String getOnedayoutremind() {
		return onedayoutremind;
	}

	/**
	 * @param onedayoutremind the onedayoutremind to set
	 */
	public void setOnedayoutremind(String onedayoutremind) {
		this.onedayoutremind = onedayoutremind;
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

	/**
	 * @return the onewholeoutremind
	 */
	public String getOnewholeoutremind() {
		return onewholeoutremind;
	}

	/**
	 * @param onewholeoutremind the onewholeoutremind to set
	 */
	public void setOnewholeoutremind(String onewholeoutremind) {
		this.onewholeoutremind = onewholeoutremind;
	}

	/**
	 * @return the zhongremind
	 */
	public String getZhongremind() {
		return zhongremind;
	}

	/**
	 * @param zhongremind the zhongremind to set
	 */
	public void setZhongremind(String zhongremind) {
		this.zhongremind = zhongremind;
	}

	/**
	 * @return the nozhongremind
	 */
	public String getNozhongremind() {
		return nozhongremind;
	}

	/**
	 * @param nozhongremind the nozhongremind to set
	 */
	public void setNozhongremind(String nozhongremind) {
		this.nozhongremind = nozhongremind;
	}

	/**
	 * @return the winningrate
	 */
	public Integer getWinningrate() {
		return winningrate;
	}

	/**
	 * @param winningrate the winningrate to set
	 */
	public void setWinningrate(Integer winningrate) {
		this.winningrate = winningrate;
	}

}
