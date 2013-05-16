package com.wuzhupc.mobile.interoperate;

import java.io.IOException;


import com.google.gson.stream.JsonWriter;

public class NullConverter implements Converter {

	public NullConverter() {
		//
	}

	@Override
	public boolean canConvert(Class<?> type) {
		return (type == null);
	}
	

	/**
	 *  [name]:value
	 *	old: prop_[name]:value
	 */
	@Override
	public void marshal(Object source,JsonWriter writer,String ref)
	{
		try
		{
			//writer.name(matchElement()+"_"+ref).nullValue();
			writer.name(ref).nullValue();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
