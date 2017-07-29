/**
 * <p>Title: Option.java</p>
 * <p>报表数据</p>
 * @author damon
 * @date 2015年1月6日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils.echart;

/**
 * <p>Title: Option</p>
 * <p>报表数据</p> 
 * @author damon
 * @date 2015年1月6日
 */
public class Option {

	/**
	 * KG 空格
	 */
	public static final String KG = "    ";
	/**
	 * HH 换行
	 */
	public static final String HH = " \r\n";
	
	/**
	 * <p>setKG</p>
	 * <p>填充空格</p>
	 * @author damon
	 * @date 2015年1月7日
	 * @param kg
	 * @return
	 */
	public static String setKG(int kg) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < kg; i++){
			sb.append(KG);
		}
		return sb.toString();
	}
	
	//-------- -------- -------- -------- -------- --------//
	/*	结构如下：
	 *	option = {
			@see com.xiaoma.monitor.EChart.Timeline,
			@see com.xiaoma.monitor.EChart.Options
     *	}
     */
	//-------- -------- -------- -------- -------- --------//
	
	private int kg;

	private Timeline mtimeline;
	
	private Options moptions;
	
	/**
	 * <p></p>
	 * @param kg
	 * @param mtimeline
	 * @param moptions
	 */
	public Option(int kg, Timeline mtimeline, Options moptions) {
		this.kg = kg;
		this.mtimeline = mtimeline;
		this.moptions = moptions;
		
	}
	
	/* (non-Javadoc)
	 * <p>toString</p>
	 * <p>输出数据</p>
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg)).append("option = {").append(Option.HH);
		sb.append(this.mtimeline.toString()).append(",").append(Option.HH);
		sb.append(this.moptions.toString()).append(Option.HH);
		sb.append(Option.setKG(this.kg)).append("}");
		return sb.toString();
	}
	
//	public static void main(String[] args) {
//		int kg = 0;
//		// timeline
//		int kg1 = kg + 1;
//		String[] timelinedatas = {"2013-01-01","2013-02-01","2013-03-01"};
//		Timeline timeline = new Timeline(kg1, timelinedatas);
//		
//		// options
//		int kg2 = kg + 1;
//		String[] legenddatas = {"Chrome","Firefox"};
//		Legend legend = new Legend(kg2 + 2, legenddatas);
//		
//		List<Serie> series = new ArrayList<Serie>();
//		String[] keys = {"Chrome","Firefox"};
//		Map<String,Integer> map1 = new HashMap<String,Integer>();
//		map1.put("Chrome", 64);
//		map1.put("Firefox", 128);
//		Serie serie1 = new Serie(kg2 + 2, "浏览器", map1, keys);
//		series.add(serie1);
//		
//		Map<String,Integer> map2 = new HashMap<String,Integer>();
//		map2.put("Chrome", 128);
//		map2.put("Firefox", 64);
//		Serie serie2 = new Serie(kg2 + 2, "浏览器", map2, keys);
//		series.add(serie2);
//		
//		Map<String,Integer> map3 = new HashMap<String,Integer>();
//		map3.put("Chrome", 96);
//		map3.put("Firefox", 96);
//		Serie serie3 = new Serie(kg2 + 2, "浏览器", map3, keys);
//		series.add(serie3);
//		
//		Options options = new Options(kg2, "标题", "副标题", legend, series);
//		
//		Option option = new Option(kg, timeline, options);
//		System.out.println(option.toString());
//	}
	
}
