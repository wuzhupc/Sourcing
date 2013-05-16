package com.wuzhupc.mobile.interoperate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.stream.JsonWriter;

/**
 * Array List 转换
 *
 */
public class ArrayListConverter implements Converter {

	private ConvertLookup convertLookup;

	public ArrayListConverter(ConvertLookup convertLookup) {
		this.convertLookup = convertLookup;
	}

	@Override
	public boolean canConvert(Class<?> type) {
		return type.equals(ArrayList.class);
	}

	/**
	 * [ref]:{[]}
	 * old:list_[ref]:{[]}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void marshal(Object source, JsonWriter writer, String ref)
	{		
		try
		{
			writer.name(ref);
			writer.beginArray();
			for (Object object : ((List<Object>) source)) {
				writer.beginObject();
				convertLookup.convert(object, writer, null);
				writer.endObject();
			}
			writer.endArray();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	

}
