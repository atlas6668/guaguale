/**
 * <p>Title: Timeline.java</p>
 * <p>渲染时间轴</p>
 * @author damon
 * @date 2015年1月7日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils.echart;

import com.xiaoma.guaguale.common.utils.StringUtil;

/**
 * <p>Title: Timeline</p>
 * <p>渲染时间轴</p> 
 * @author damon
 * @date 2015年1月7日
 */
public class Timeline {
	
//	public static void main(String[] args) {
//		// 奇数
////		String[] datas = {"2013-01-01","2013-02-01","2013-03-01","2013-04-01","2013-05-01","2013-06-01","2013-07-01"};
//		// 偶数
//		String[] datas = {"2013-01-01","2013-02-01","2013-03-01","2013-04-01","2013-05-01","2013-06-01","2013-07-01","2013-08-01"};
//		Timeline timeline = new Timeline(0, datas);
//		System.out.println(timeline.toString());
//	}
	
	//-------- -------- -------- -------- -------- --------//
	/*	结构如下：
	 *	timeline : {
    		data : [
            	'2013-01-01'
        	],
        	label : {
            	formatter : function(s) {
                	return s.slice(0, 10);
            	}
     		}
     *	}
     */
	//-------- -------- -------- -------- -------- --------//
	private int kg;
	
	private String[] datas;
	
	/**
	 * <p></p>
	 * @param kg 空格
	 * @param datas 数据
	 */
	public Timeline(int kg, String[] datas) {
		this.kg = kg;
		this.datas = datas;
		setData();
		setLabel();
	}
	
	/**
	 * <p>getMiddle</p>
	 * <p>渲染并获取中间一个</p>
	 * @author damon
	 * @date 2015年1月7日
	 * @param data
	 * @return
	 */
	private String getMiddle(String data) {
		StringBuffer sb = new StringBuffer();
		sb.append("{ name:'").append(data).append("', symbol:'emptyStar6', symbolSize:8 }");
		return sb.toString();
	}
	
	/**
	 * <p>getLast</p>
	 * <p>渲染并获取最后一个</p>
	 * @author damon
	 * @date 2015年1月7日
	 * @param data
	 * @return
	 */
	private String getLast(String data) {
		StringBuffer sb = new StringBuffer();
		sb.append("{ name:'").append(data).append("', symbol:'star6', symbolSize:8 }");
		return sb.toString();
	}
	
	private String data;
	
	private String label;
	
	/* (non-Javadoc)
	 * <p>toString</p>
	 * <p>输出时间轴字符串</p>
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg)).append("timeline : {").append(Option.HH);
		sb.append(data).append(",").append(Option.HH);
		sb.append(label).append(Option.HH);
		sb.append(Option.setKG(this.kg)).append("}");
		return sb.toString();
	}
	
	/**
	 * <p>setData</p>
	 * <p>填充数据</p>
	 * @author damon
	 * @date 2015年1月7日
	 */
	private void setData() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg + 1)).append("data : [").append(Option.HH);
		for(int i = 0; i < this.datas.length; i++){
			// 数组有且只有1个元素
			if(this.datas.length == 1){
				sb.append(Option.setKG(this.kg + 2)).append(this.getLast(this.datas[i])).append(Option.HH);
			// 数组有且只有2个元素
			} else if(this.datas.length == 2){
				if(i == 0){
					sb.append(Option.setKG(this.kg + 2)).append(this.getMiddle(this.datas[i])).append(",").append(Option.HH);
				} else {
					sb.append(Option.setKG(this.kg + 2)).append(this.getLast(this.datas[i])).append(Option.HH);
				}
			// 3个以上元素
			} else {
				// 偶数数组
				if(this.datas.length % 2 == 0){
					// 中间元素
					if(i + 1 == this.datas.length / 2){
						sb.append(Option.setKG(this.kg + 2)).append(this.getMiddle(datas[i])).append(",").append(Option.HH);
						// 最后元素
					} else if(i + 1 == this.datas.length){
						sb.append(Option.setKG(this.kg + 2)).append(this.getLast(this.datas[i])).append(Option.HH);
						// 其他
					} else {
						sb.append(Option.setKG(this.kg + 2)).append("'").append(this.datas[i]).append("'").append(",").append(Option.HH);
					}
				}
				// 奇数数组
				if(this.datas.length % 2 == 1){
					// 中间元素
					if(i + 1 == this.datas.length / 2 + 1){
						sb.append(Option.setKG(this.kg + 2)).append(this.getMiddle(this.datas[i])).append(",").append(Option.HH);
						// 最后元素
					} else if(i + 1 == this.datas.length){
						sb.append(Option.setKG(this.kg + 2)).append(this.getLast(this.datas[i])).append(Option.HH);
						// 其他
					} else {
						sb.append(Option.setKG(this.kg + 2)).append("'").append(this.datas[i]).append("'").append(",").append(Option.HH);
					}
				}
			}
		}
		sb.append(Option.setKG(this.kg + 1)).append("]");
		this.data = sb.toString();
	}
	
	/**
	 * <p>setLabel</p>
	 * <p>填充数据</p>
	 * @author damon
	 * @date 2015年1月7日
	 */
	private void setLabel() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg + 1)).append("label : {	").append(Option.HH);
		sb.append(Option.setKG(this.kg + 2)).append("formatter : function(s) {").append(Option.HH);
		sb.append(Option.setKG(this.kg + 3)).append("return s.slice(0, ").append("10").append(");").append(Option.HH);
		sb.append(Option.setKG(this.kg + 2)).append("}").append(Option.HH);
		sb.append(Option.setKG(this.kg + 1)).append("}");
		this.label = sb.toString();
	}

	/**
	 * <p>calcuSegs</p>
	 * <p>计算片段</p>
	 * @author damon
	 * @date 2015年1月12日
	 * @return
	 */
	@SuppressWarnings("unused")
	private int calcuSegs() {
		String first = datas[0];
		String last = datas[datas.length - 1];
		int segs = subInt(last) - subInt(first) + 1;
		return segs > 0 ? segs : 1;
	}

	/**
	 * <p>subInt</p>
	 * <p>处理 "2013-01-01" 取 20130101</p>
	 * @author damon
	 * @date 2015年1月12日
	 * @param last
	 * @return
	 */
	private int subInt(String last) {
		String str = StringUtil.subStrRemoveLine(last, "-");
		if(StringUtil.isValid(str)){
			return Integer.parseInt(str);
		}
		return 1;
	}
	
}
