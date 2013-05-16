package com.wuzhupc.utils.json;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.util.Log;

import com.google.gson.stream.JsonWriter;
import com.wuzhupc.utils.StringUtil;

/**
 * Json报文生成
 * @author wuzhu
 *
 */
public class JsonCreater
{
	/**
	 *  参数集
	 */
	private Map<String, String> mParamsMap;	//
	
	private JsonCreater() 
	{
		mParamsMap = new HashMap<String, String>();
	}
	
	/**
	 * 创建Json生成器
	 */
	public static JsonCreater startJson() {
		return new JsonCreater();
	}
	
	/**
	 * 添加参数
	 * @param name 参数名，如果为null，则忽略
	 * @param value 参数值，如果为null，则忽略
	 */
	public void setParam(String name, String value) {
		if (name == null || value == null) {
			Log.e("JsonCreater", "setParam("+name+", "+value+")");
			return;
		}
		mParamsMap.put(name, value);
	}
	
	public void setParam(String name,int value)
	{
		setParam(name,String.valueOf(value));
	}
	
	public void setParam(String name,long value)
	{
		setParam(name,String.valueOf(value));
	}
	
	public String createJson(String id, String commandname)
	{
		if(StringUtil.isEmpty(id))
			id = Long.toString(System.currentTimeMillis());
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try
		{
			JsonWriter writer=new JsonWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.setHtmlSafe(true);
			writer.setSerializeNulls(true);
			writer.beginObject();//2
			writer.name(TAG_ID).value(id);
			writer.name(TAG_COMMANDNAME).value(commandname);
			if(!mParamsMap.isEmpty())
			{
				writer.name(TAG_PARAMS);
				writer.beginObject();//3
				addChild(writer,mParamsMap);
				writer.endObject();//3
			}
			writer.endObject();//2
			writer.close();
			return os.toString("UTF-8");
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	private void addChild(JsonWriter writer, Map<String, String> map) throws IOException 
	{
		Iterator<?> it = map.entrySet().iterator();
		while (it.hasNext()) 
		{
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) it.next();
			String name = (String) entry.getKey();
			String value = (String) entry.getValue();
			writer.name(name).value(value);
		}
	}

	private static final String TAG_ID = "id";
	private static final String TAG_COMMANDNAME = "command";
	private static final String TAG_PARAMS = "params";
}
