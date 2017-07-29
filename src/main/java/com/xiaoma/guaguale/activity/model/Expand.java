/**
 * <p>Title: Expand.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月29日
 * @version 1.0
 */
package com.xiaoma.guaguale.activity.model;

import com.xiaoma.guaguale.base.model.BaseSearchEntity;

/**
 * <p>Title: Expand</p>
 * <p>拓展</p> 
 * @author damon
 * @date 2015年1月29日
 */
public class Expand extends BaseSearchEntity {

	/**
	 * serialVersionUID 
	 */
	private static final long serialVersionUID = 1171203102225875396L;
	
	private int sum;
	
	private boolean continued;
	
//***********************************	getter and setter	*************************************//
	
	/**
	 * @return the continued
	 */
	public boolean isContinued() {
		return continued;
	}

	/**
	 * @param continued the continued to set
	 */
	public void setContinued(boolean continued) {
		this.continued = continued;
	}

	/**
	 * @return the sum
	 */
	public int getSum() {
		return sum;
	}
	
	/**
	 * @param sum the sum to set
	 */
	public void setSum(int sum) {
		this.sum = sum;
	}
	
}
