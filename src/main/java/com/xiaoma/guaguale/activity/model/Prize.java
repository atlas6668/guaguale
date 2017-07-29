/**
 * <p>Title: Prize.java</p>
 * <p>奖品</p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <p>Title: Prize</p>
 * <p>奖品</p> 
 * @author damon
 * @date 2015年1月29日
 */
@Entity
@Table(name = "prize")
public class Prize extends Expand {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 1950771211977688289L;
	
	/**
	 * STATUS_0 未发放
	 */
	public static final int STATUS_0 = 0;
	
	/**
	 * STATUS_1 已发放
	 */
	public static final int STATUS_1 = 1;
	
	/**
	 * STATUS_2 已兑奖
	 */
	public static final int STATUS_2 = 2;
	
	/**
	 * xmk 小马抽奖活动兑奖码
	 */
	@Column(name = "xmk")
	@NotEmpty
	private String xmk;
	
	/**
	 * name 奖品名称
	 */
	@Column(name = "name")
	@NotEmpty
	private String name;

	/**
	 * ptype 奖品类型
	 */
	@Column(name = "ptype")
	private String ptype;
	
	/**
	 * image 奖品图片
	 */
	@Column(name = "image")
	private String image;
	
	/**
	 * unick 获奖者昵称
	 */
	@Column(name = "unick")
	private String unick;
	
	/**
	 * umobile 获奖者手机号码
	 */
	@Column(name = "umobile")
	private String umobile;
	
	/**
	 * createtime 奖品生产日期
	 */
	@Column(name = "createtime")
	private Date createtime; 
	
	/**
	 * status 奖品状态
	 */
	@Column(name = "status")
	private Integer status = STATUS_0;
	
	/**
	 * duijiangtime 兑奖日期
	 */
	@Column(name = "duijiangtime")
	private Date duijiangtime;
	
	/**
	 * 双向关联 奖池
	 */
	@ManyToMany(mappedBy = "prizes") 
	public List<Pond> ponds;
	
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
	 * @return the ponds
	 */
	public List<Pond> getPonds() {
		return ponds;
	}

	/**
	 * @param ponds the ponds to set
	 */
	public void setPonds(List<Pond> ponds) {
		this.ponds = ponds;
	}

	/**
	 * @return the xmk
	 */
	public String getXmk() {
		return xmk;
	}

	/**
	 * @param xmk the xmk to set
	 */
	public void setXmk(String xmk) {
		this.xmk = xmk;
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
	 * @return the ptype
	 */
	public String getPtype() {
		return ptype;
	}

	/**
	 * @param ptype the ptype to set
	 */
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the unick
	 */
	public String getUnick() {
		return unick;
	}

	/**
	 * @param unick the unick to set
	 */
	public void setUnick(String unick) {
		this.unick = unick;
	}

	/**
	 * @return the umobile
	 */
	public String getUmobile() {
		return umobile;
	}

	/**
	 * @param umobile the umobile to set
	 */
	public void setUmobile(String umobile) {
		this.umobile = umobile;
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
	 * @return the duijiangtime
	 */
	public Date getDuijiangtime() {
		return duijiangtime;
	}

	/**
	 * @param duijiangtime the duijiangtime to set
	 */
	public void setDuijiangtime(Date duijiangtime) {
		this.duijiangtime = duijiangtime;
	}
	
}
