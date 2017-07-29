/**
 * <p>Title: Options.java</p>
 * <p></p>
 * @author damon
 * @date 2015年1月7日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils.echart;

import java.util.List;

/**
 * <p>Title: Options</p>
 * <p></p> 
 * @author damon
 * @date 2015年1月7日
 */
public class Options {

//	public static void main(String[] args) {
//		int kg = 0;
//		String[] datas = {"Chrome","Firefox"};
//		Legend legend = new Legend(kg + 2, datas);
//		
//		List<Serie> series = new ArrayList<Serie>();
//		String[] keys = {"Chrome","Firefox"};
//		Map<String,Integer> map1 = new HashMap<String,Integer>();
//		map1.put("Chrome", 64);
//		map1.put("Firefox", 128);
//		Serie serie1 = new Serie(kg + 2, "浏览器", map1, keys);
//		series.add(serie1);
//		
//		Map<String,Integer> map2 = new HashMap<String,Integer>();
//		map2.put("Chrome", 128);
//		map2.put("Firefox", 64);
//		Serie serie2 = new Serie(kg + 2, "浏览器", map2, keys);
//		series.add(serie2);
//		
//		Map<String,Integer> map3 = new HashMap<String,Integer>();
//		map3.put("Chrome", 96);
//		map3.put("Firefox", 96);
//		Serie serie3 = new Serie(kg + 2, "浏览器", map3, keys);
//		series.add(serie3);
//		
//		Options options = new Options(kg, "标题", "副标题", legend, series);
//		System.out.println(options.toString());
//	}
	
	//-------- -------- -------- -------- -------- --------//
	/*	结构如下：
	 *  options : [
			{
				title : {
					text: '标题',
					subtext: '副标题'
				},
				tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
				@see com.xiaoma.monitor.EChart.Legend,
				toolbox: {
                    show : true,
                    feature : {
                        //mark : {show: true},
                        //dataView : {show: true, readOnly: false},
                        magicType : {
                            show: true, 
                            type: ['pie', 'funnel'],
                            option: {
                                funnel: {
                                    x: '25%',
                                    width: '50%',
                                    funnelAlign: 'left',
                                    max: 1700
                                }
                            }
                        },
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
				@see com.xiaoma.monitor.EChart.Serie
			},{
				@see com.xiaoma.monitor.EChart.Serie
			}
	 *	]
     */
	//-------- -------- -------- -------- -------- --------//
	
	private int kg;
	
	private String title;
	
	private String tooltip;
	
	private Legend mlegend;
	
	private String toolbox;
	
	private List<Serie> lSeries;
	
	/**
	 * <p></p>
	 * @param kg
	 * @param title
	 * @param subTitle
	 * @param mlegend
	 * @param lSeries
	 */
	public Options(int kg, String title, String subTitle, Legend mlegend, List<Serie> lSeries) {
		this.kg = kg;
		setTitle(title, subTitle);
		setTooltip();
		this.mlegend = mlegend;
		setToolbox();
		this.lSeries = lSeries;
	}

	/**
	 * <p>setTitle</p>
	 * <p>渲染标题</p>
	 * @author damon
	 * @date 2015年1月8日
	 * @param kg
	 * @param title
	 * @param subTitle
	 */
	private void setTitle(String title, String subTitle) {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg + 2)).append("title : {").append(Option.HH);
		sb.append(Option.setKG(this.kg + 3)).append("text: '").append(title).append("',").append(Option.HH);
		sb.append(Option.setKG(this.kg + 3)).append("subtext: '").append(subTitle).append("'").append(Option.HH);
		sb.append(Option.setKG(this.kg + 2)).append("}");
		this.title = sb.toString();
	}
	
	/**
	 * <p>setTooltip</p>
	 * <p>渲染tooltip</p>
	 * @author damon
	 * @date 2015年1月8日
	 */
	private void setTooltip() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg + 2)).append("tooltip : {").append(Option.HH);
		sb.append(Option.setKG(this.kg + 3)).append("trigger: '").append("item").append("',").append(Option.HH);
		sb.append(Option.setKG(this.kg + 3)).append("formatter: '{a} <br/>{b} : {c} ({d}%)'").append(Option.HH);
		sb.append(Option.setKG(this.kg + 2)).append("}");
		this.tooltip = sb.toString();
	}
	
	/**
	 * <p>setToolbox</p>
	 * <p>渲染toolbox</p>
	 * @author damon
	 * @date 2015年1月8日
	 */
	private void setToolbox() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg + 2)).append("toolbox: {").append(Option.HH);
			sb.append(Option.setKG(this.kg + 3)).append("show : true,").append(Option.HH);
			sb.append(Option.setKG(this.kg + 3)).append("feature : {").append(Option.HH);
				sb.append(Option.setKG(this.kg + 4)).append("magicType : {").append(Option.HH);
					sb.append(Option.setKG(this.kg + 5)).append("show: true,").append(Option.HH);
					sb.append(Option.setKG(this.kg + 5)).append("type: ['pie', 'funnel'],").append(Option.HH);
					sb.append(Option.setKG(this.kg + 5)).append("option: {").append(Option.HH);
						sb.append(Option.setKG(this.kg + 6)).append("funnel: {").append(Option.HH);
							sb.append(Option.setKG(this.kg + 7)).append("x: '25%',").append(Option.HH);
							sb.append(Option.setKG(this.kg + 7)).append("width: '50%',").append(Option.HH);
							sb.append(Option.setKG(this.kg + 7)).append("funnelAlign: 'left',").append(Option.HH);
							sb.append(Option.setKG(this.kg + 7)).append("max: 1700").append(Option.HH);
						sb.append(Option.setKG(this.kg + 6)).append("}").append(Option.HH);
					sb.append(Option.setKG(this.kg + 5)).append("}").append(Option.HH);
				sb.append(Option.setKG(this.kg + 4)).append("},").append(Option.HH);
				sb.append(Option.setKG(this.kg + 4)).append("restore : {show: true},").append(Option.HH);
				sb.append(Option.setKG(this.kg + 4)).append("saveAsImage : {show: true}").append(Option.HH);
			sb.append(Option.setKG(this.kg + 3)).append("}").append(Option.HH);
		sb.append(Option.setKG(this.kg + 2)).append("}");
		this.toolbox = sb.toString();
	}
	
	/* (non-Javadoc)
	 * <p>toString</p>
	 * <p>输出Options字符串</p>
	 * @return
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg)).append("options : [").append(Option.HH);
		if(this.mlegend != null && this.lSeries != null){
			for(int i = 0; i < this.lSeries.size(); i++){
				// 首个Serie
				if(i == 0){
					sb.append(Option.setKG(this.kg + 1)).append("{").append(Option.HH);
					sb.append(this.title).append(",").append(Option.HH);
					sb.append(this.tooltip).append(",").append(Option.HH);
					sb.append(this.mlegend.toString()).append(",").append(Option.HH);
					sb.append(this.toolbox).append(",").append(Option.HH);
					sb.append(this.lSeries.get(i).toString()).append(Option.HH);
					sb.append(Option.setKG(this.kg + 1)).append("}");
				} else {
					sb.append(",{").append(Option.HH);
					sb.append(this.lSeries.get(i).toString()).append(Option.HH);
					sb.append(Option.setKG(this.kg + 1)).append("}");
					// 最后Serie
					if(i + 1 == this.lSeries.size()){
						sb.append(Option.HH);
					}
				}
			}
		}
		sb.append(Option.setKG(this.kg)).append("]");
		return sb.toString();
	}
	
}
