/**
 * <p>Title: Legend.java</p>
 * <p>渲染图例</p>
 * @author damon
 * @date 2015年1月8日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils.echart;

/**
 * <p>Title: Legend</p>
 * <p>渲染图例</p> 
 * @author damon
 * @date 2015年1月8日
 */
public class Legend {

//	public static void main(String[] args) {
//		String[] datas = {"Chrome","Firefox"};
//		Legend legend = new Legend(0, datas);
//		System.out.println(legend.data);
//		System.out.println(legend.toString());
//	}
	
	//-------- -------- -------- -------- -------- --------//
	/*	结构如下：
	 *	legend : {
            data : [
            	'Chrome',
            	'Firefox'
            ]
     *  }
     */
	//-------- -------- -------- -------- -------- --------//
	
	private int kg;
	
	private String data;
	
	/**
	 * <p></p>
	 * @param kg
	 * @param datas
	 */
	public Legend(int kg, String[] datas) {
		this.kg = kg;
		setData(datas);
	}

	/**
	 * <p>setData</p>
	 * <p>填充数据</p>
	 * @author damon
	 * @date 2015年1月8日
	 * @param datas
	 */
	private void setData(String[] datas) {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg + 1)).append("data : [").append(Option.HH);
		for(int i = 0; i < datas.length; i++){
			sb.append(Option.setKG(this.kg + 2)).append("'").append(datas[i]).append("'");
			// 最后元素
			if(i + 1 == datas.length){
				sb.append(Option.HH);
				// 其他
			} else {
				sb.append(",").append(Option.HH);
			}
		}
		sb.append(Option.setKG(this.kg + 1)).append("]");
		this.data = sb.toString();
	}
	
	/* (non-Javadoc)
	 * <p>toString</p>
	 * <p>输出图例字符串</p>
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg)).append("legend : {").append(Option.HH);
		sb.append(data).append(Option.HH);
		sb.append(Option.setKG(this.kg)).append("}");
		return sb.toString();
	}

}
