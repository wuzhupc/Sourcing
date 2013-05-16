package com.wuzhupc.mobile.interoperate;


import com.google.gson.stream.JsonWriter;


/**
 * 数据转换接口
 *
 */
public interface Converter {

	static String VALUE_NULL = "[null]";
	
	/**
	 * 是否支持此种类型的数据转换
	 * @param type
	 * @return
	 */
	boolean canConvert(Class<?> type);
	
	void marshal(Object source,JsonWriter writer,String ref);

}
