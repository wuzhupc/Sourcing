package com.wuzhupc.mobile.interoperate;

import java.util.ArrayList;
import java.util.Collections;


import com.google.gson.stream.JsonWriter;

/**
 * 选择运行对应的转换器
 *
 */
public class ConvertLookup {

	private ArrayList<PriorityItem> converters = new ArrayList<PriorityItem>();

	/**
	 * 根据优先级，注册转换�?
	 * @param converter
	 * @param priority 优先�?
	 */
	public void registerConverter(Converter converter, int priority) {
		converters.add(new PriorityItem(converter, priority));
		//sort converter by priority
		Collections.sort(converters);
	}
	
	/**
	 * 选择转换器，并且生成Json
	 * @param object
	 * @param writer
	 * @param ref
	 */
	public void convert(Object object, JsonWriter writer, String ref) {
		for (PriorityItem item : converters) {
			Converter converter = item.converter;
			if(object == null) continue;
			if (converter.canConvert(object.getClass())) {
				converter.marshal(object, writer, ref);
				return;
			}
		}
	}

	/**
	 * 内部�?优先级Item
	 * 完成排序接口
	 */
	static class PriorityItem implements Comparable<PriorityItem> {

		Converter converter;
		int priority;

		public PriorityItem(Converter converter, int priority) {
			this.converter = converter;
			this.priority = priority;
		}

		@Override
		public int compareTo(PriorityItem o) {
			PriorityItem other = (PriorityItem) o;
			return (other.priority - this.priority);
		}

	}

}
