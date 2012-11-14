package com.wuzhupc.mobile.interoperate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.stream.JsonWriter;

/**
 * 生成响应报文格式的Json
 *
 */
public class JsonMarshal {

	public static final int PRIORITY_VERY_HIGH = 10000;
	public static final int PRIORITY_NORMAL = 0;
	public static final int PRIORITY_LOW = -10;
	public static final int PRIORITY_VERY_LOW = -20;

	private int ret;
	private String msg;
	private String id;

	private ConvertLookup convertLookup = new ConvertLookup();
	private List<Source> sources = new ArrayList<Source>();

	/**
	 * 构�?响应报文，根据基本属�?
	 * @param id
	 * @param ret 1:成功
	 * @param msg
	 */
	public JsonMarshal(String id, int ret, String msg) {
		this.id = id;
		this.ret = ret;
		this.msg = msg;
		setupConverters();
	}

	/**
	 * 添加响应报文内容对象
	 * @param refName
	 * @param object
	 */
	public void add(String refName, Object object) {
		sources.add(new Source(refName, object));
	}
	
	/**
	 * 生成json
	 * @return
	 */
	public String toJson()
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try
		{
			JsonWriter writer=new JsonWriter(new OutputStreamWriter(os, "UTF-8"));
			writer.setHtmlSafe(true);
			//writer.setSerializeNulls(true);
			
			writer.beginObject();//1
			if(this.id!=null&&!this.id.equals(""))
				writer.name(SchemaName.ELEMENT_NAME_ID).value(this.id);
			
			writer.name(SchemaName.ELEMENT_NAME_RET);
			writer.beginObject();//2
			writer.name(SchemaName.ATTRIBUTE_NAME_CODE).value(""+this.ret);
			if(this.msg!=null&&!this.msg.equals(""))
				writer.name(SchemaName.ATTRIBUTE_NAME_MSG).value(this.msg);
			writer.endObject();//2
			
			if(sources!=null&&!sources.isEmpty())
			{
				writer.name(SchemaName.ELEMENT_NAME_DATA);			
				convertSource(writer);
			}
			writer.endObject();//1
			writer.close();
			return os.toString("UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
	
	private void convertSource(JsonWriter writer) throws IOException
	{
		writer.beginObject();
		for (Source source : sources) {
			convertLookup.convert(source.getObject(), writer, source.getRefName());
		}
		writer.endObject();
	}

	/**
	 * 设置转换�?
	 */
	private void setupConverters() {
		convertLookup.registerConverter(new VOConverter(convertLookup), PRIORITY_VERY_LOW);
		convertLookup.registerConverter(new ArrayListConverter(convertLookup), PRIORITY_LOW);
		convertLookup.registerConverter(new BasicConverter(), PRIORITY_NORMAL);
		convertLookup.registerConverter(new DateConverter(), PRIORITY_NORMAL);
		convertLookup.registerConverter(new NullConverter(), PRIORITY_VERY_HIGH);
	}

	/**
	 * 内部�?
	 * 待生成的对象，包含ref name和object
	 *
	 */
	class Source {
		public Source(String refName, Object object) {
			this.refName = refName;
			this.object = object;
		}
		
		private String refName;
		private Object object;
		
		public String getRefName() {
			return refName;
		}
		public void setRefName(String refName) {
			this.refName = refName;
		}
		public Object getObject() {
			return object;
		}
		public void setObject(Object object) {
			this.object = object;
		}
	}
	
}
