package com.wuzhupc.mobile.interoperate;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;


import com.google.gson.stream.JsonWriter;

public class DateConverter implements Converter {

	DateFormat format;

	public DateConverter() {
	//	this.format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public DateConverter(DateFormat format) {
		this.format = format;
	}

	@Override
	public boolean canConvert(Class<?> type) {
		return type.equals(Date.class) || type.equals(Timestamp.class) || type.equals(java.sql.Date.class);
	}
	
	/**
	 * [ref]:{[]}
	 * old: prop_[ref]:{[]}
	 */
	@Override
	public void marshal(Object source,JsonWriter writer,String ref)
	{
		try
		{
			String str="";
			if(source instanceof Date)
			{
				Date d = (Date)source;
				str=String.valueOf(d.getTime());
			}
			else if(source instanceof Timestamp)
			{
				Timestamp t = (Timestamp)source;
				str=String.valueOf(t.getTime());
			}			
			//writer.name(matchElement()+"_"+ref).value(str);
			writer.name(ref).value(str);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
