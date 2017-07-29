/**
 * <p>Title: Serie.java</p>
 * <p>渲染图表数据</p>
 * @author damon
 * @date 2015年1月7日
 * @version 1.0
 */
package com.xiaoma.guaguale.common.utils.echart;

import java.util.Map;

/**
 * <p>Title: Serie</p>
 * <p>渲染图表数据</p> 
 * @author damon
 * @date 2015年1月7日
 */
public class Serie {

//	public static void main(String[] args) {
//		Map<String,Integer> map = new HashMap<String,Integer>();
//		map.put("Chrome", 64);
//		map.put("Firefox", 128);
//		String[] keys = {"Firefox","Chrome"};
//		Serie serie = new Serie(0, "浏览器", map, keys);
//		System.out.println(serie.toString());
//	}
	
	//-------- -------- -------- -------- -------- --------//
	/*	结构如下：
	 *	series : [{
			name:'浏览器（数据纯属虚构）2',
			type:'pie',
			center: ['50%', '45%'],
            radius: '50%',
			data : [
				{value: 64,  name:'Chrome'},
                {value: 128,  name:'Firefox'}
			]
     *	}]
     */
	//-------- -------- -------- -------- -------- --------//
	
	private int kg;
	
	private String name;
	
	private String type = "type:'pie'";
	
	private String center = "center: ['50%', '45%']";
	
	private String radius = "radius: '50%'";
	
	private String data;
	
	/**
	 * <p></p>
	 * @param kg
	 * @param name
	 * @param datas
	 * @param keys
	 */
	public Serie(int kg, String name, Map<String,Integer> datas, String[] keys) {
		this.kg = kg;
		this.name = "name:'" + name + "'";
		setData(datas, keys);
	}
	
	/**
	 * <p>setData</p>
	 * <p>填充数据</p>
	 * @author damon
	 * @date 2015年1月8日
	 * @param datas
	 * @param keys 排序用，校正数据顺序
	 */
	private void setData(final Map<String, Integer> datas, final String[] keys) {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg + 1)).append("data : [").append(Option.HH);
		int count = 1;
		int value = 0;
		for(String key : keys){
			value = datas.get(key);
			sb.append(Option.setKG(this.kg + 2)).append(setKeyValue(key, value));
			// 最后元素
			if(count == keys.length){
				sb.append(Option.HH);
				// 其他
			} else {
				sb.append(",").append(Option.HH);
			}
			count++;
		}
		sb.append(Option.setKG(this.kg + 1)).append("]");
		this.data = sb.toString();
	}

	/**
	 * <p>setKeyValue</p>
	 * <p>渲染键值字符串 如{value: 64,  name:'Chrome'}</p>
	 * @author damon
	 * @date 2015年1月8日
	 * @param key
	 * @param value
	 * @return String
	 */
	private String setKeyValue(String key, Integer value) {
		StringBuffer sb = new StringBuffer();
		sb.append("{value:").append(value).append(", name:'").append(key).append("'}");
		return sb.toString();
	}
	
	/* (non-Javadoc)
	 * <p>toString</p>
	 * <p>输出图表数据字符串</p>
	 * @return
	 * @see java.lang.String#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(Option.setKG(this.kg)).append("series : [{").append(Option.HH);
		sb.append(Option.setKG(this.kg + 1)).append(name).append(",").append(Option.HH);
		sb.append(Option.setKG(this.kg + 1)).append(type).append(",").append(Option.HH);
		sb.append(Option.setKG(this.kg + 1)).append(center).append(",").append(Option.HH);
		sb.append(Option.setKG(this.kg + 1)).append(radius).append(",").append(Option.HH);
		sb.append(data).append(Option.HH);
		sb.append(Option.setKG(this.kg)).append("}]");
		return sb.toString();
	}

}
