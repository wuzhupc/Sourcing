package com.wuzhupc.utils.json;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.utils.StringUtil;

/**
 * Json���Ľ���
 * @author wuzhu
 *
 */
public class JsonParser
{
	private final static String PACKAGE_NAME = "com.wuzhupc.Sourcing.vo.";
	private final static String TAG_RET = "ret";
	private final static String TAG_DATA = "data";
	
	/**
	 * �жϷ��صĽ��
	 * @param jsoncontent
	 * @return
	 */
	public static ResponseVO parseJsonToResponse(String jsoncontent)
	{
		return parseJsonToResponse(StringUtil.StringToInputStream(jsoncontent));
	}
	/**
	 * �жϷ��صĽ��
	 * @param jsoncontent
	 * @return
	 */
	public static ResponseVO parseJsonToResponse(InputStream jsoncontent)
	{
		ResponseVO vo = new ResponseVO();
		JsonReader reader=parseJson(jsoncontent, vo);
		if(reader!=null)
		{
			try
			{
				reader.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	/**
	 * ��ȡret����
	 * @param vo
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	private static void readResponseVO(ResponseVO vo,JsonReader reader) throws IOException
	{
		reader.beginObject();
		while (reader.hasNext())
		{
			String name = reader.nextName();
			if(name.equals("code")&&(JsonToken.NUMBER.equals(reader.peek())||JsonToken.STRING.equals(reader.peek())))
				vo.setCode(reader.nextInt());
			else if(name.equals("msg")&&JsonToken.STRING.compareTo(reader.peek())==0)
				vo.setMsg(reader.nextString());
			else
				reader.skipValue();
		}
		reader.endObject();
	}
	
	/**
	 * ��ȡJson(ret�����ݱ�����data����֮ǰ)
	 * @param content ��������
	 * @param respVO�����ر���ResponseVO
	 * @return data���ֵ�reader,���û�з���null
	 */
	public static JsonReader parseJson(InputStream content,ResponseVO respVO)
	{
		if(respVO==null)
			respVO=new ResponseVO();
		if(content==null)
		{
			respVO.setCode(ResponseVO.RESPONSE_CODE_FAIL);
			return null;
		}
		JsonReader reader;
		try
		{
			reader=new JsonReader(new InputStreamReader(content,"UTF-8"));
		} catch (Exception e)
		{
			respVO.setCode(ResponseVO.RESPONSE_CODE_FAIL);
			respVO.setMsg(e.getMessage());
			e.printStackTrace();
			return null;
		}
		Boolean hasret=false; 
		try
		{
			//reader.beginObject();
			//if(reader.hasNext()&&JsonToken.NAME.equals(reader.peek())&&reader.nextName().equals(TAG_START))
			//{
				reader.beginObject();
				while (reader.hasNext())
				{
					if(!JsonToken.NAME.equals(reader.peek()))
					{
						reader.skipValue();
						continue;
					}
					String name = reader.nextName();
					if(name.equals(TAG_RET))
					{
						hasret=true;
						readResponseVO(respVO, reader);
					}
					else
					if(name.equals(TAG_DATA))
					{
						if(!hasret)
						{
							respVO.setCode(ResponseVO.RESPONSE_CODE_FAIL);
							respVO.setMsg("��Ч������!");
						}
						//�޸� �����Ƿ�ɹ�������reader
						//else
						//if(respVO.getCode() != Constants.RESPONSE_CODE_ERROR)
						{
							//�������dataֱ�ӷ���
							return reader;
						}
					}
					else
						reader.skipValue();
				}
				reader.endObject();
			//}
			//reader.endObject();
			reader.close();
			
		} catch (IOException e)
		{
			if(!hasret)
			{
				respVO.setCode(ResponseVO.RESPONSE_CODE_FAIL);
				respVO.setMsg(e.getMessage());
			}
			e.printStackTrace();
		}		
		return null;
	}
	
	/**
	 * ��ȡJson
	 * @param content ��������
	 * @param respVO�����ر���ResponseVO
	 * @return data���ֵ�reader
	 */
	public static JsonReader parseJson(String content,ResponseVO respVO)
	{
		return parseJson(StringUtil.StringToInputStream(content), respVO);
	}
	
	/**
	 * ����data����µ�entity ʵ��
	 * @param content
	 * @param respVO �洢����״̬��Ϣ(�����ʵ����)
	 * @return
	 */
	public static Object parseJsonToEntity(String content,ResponseVO respVO)
	{
		return parseJsonToEntity(StringUtil.StringToInputStream(content), respVO);
	}
	
	public static Object parseJsonToEntity(String content,ResponseVO respVO,String ref)
	{
		return parseJsonToEntity(StringUtil.StringToInputStream(content), respVO,ref);	
	}
	
	/**
	 * ����data����µ�entity ʵ��
	 * @param content
	 * @param respVO �洢����״̬��Ϣ(�����ʵ����)
	 * @return
	 */
	public static Object parseJsonToEntity(InputStream content,ResponseVO respVO)
	{
		return parseJsonToEntity(content, respVO,null);
	}
	
	/**
	 * ����data����µ�entity ʵ��
	 * @param content
	 * @param respVO �洢����״̬��Ϣ(�����ʵ����)
	 * @param ref ref���ݣ����ref==null��"" ���ȡ��һ��entity��ʹ������
	 * @return
	 */
	public static Object parseJsonToEntity(InputStream content,ResponseVO respVO,String ref)
	{
		JsonReader reader=parseJson(content, respVO);
		if(reader==null)
			return null;
		Object result=null;
		try
		{
			reader.beginObject();
			while (reader.hasNext())
			{
				if(!JsonToken.NAME.equals(reader.peek()))
				{
					reader.skipValue();
					continue;
				}
				String name=reader.nextName();
				//if(name.startsWith(TAG_ENTITY+"_")&&JsonToken.BEGIN_OBJECT.equals(reader.peek()))
				if(IsEntity(reader.peek()))
				{
					String classstr=getClassOrFieldStr(name);
					if(StringUtil.isEmpty(ref))
						result=convertToEntity(reader,classstr);
					else if(name.endsWith("_"+ref))
						result=convertToEntity(reader,classstr);
				}
				else
					reader.skipValue();
			}
			reader.endObject();	
			reader.close();
		} catch (Exception e)
		{
			Log.e("JsonParser parseJsonToEntity", e.getMessage());
			return null;
		}
		return result;
	}
	
	/**
	 * ���ؽ���µ�Prop����
	 * @param content
	 * @param respVO �洢����״̬��Ϣ(�����ʵ����)
	 * @return
	 */
	public static Map<String, String> parseJsonToMap(String content,ResponseVO respVO)
	{
		return parseJsonToMap(StringUtil.StringToInputStream(content), respVO);
	}
	
	/**
	 * ���ؽ���µ�Prop����
	 * @param content
	 * @param respVO �洢����״̬��Ϣ(�����ʵ����)
	 * @return
	 */
	public static Map<String, String> parseJsonToMap(InputStream content,ResponseVO respVO)
	{
		Map<String, String> result = new HashMap<String, String>();
		JsonReader reader=parseJson(content, respVO);
		if(reader==null)
			return result;
		try
		{
			reader.beginObject();
			while (reader.hasNext())
			{
				if(!JsonToken.NAME.equals(reader.peek()))
				{
					reader.skipValue();
					continue;
				}
				String name=reader.nextName();
				//if(name.startsWith(TAG_PROP+"_")&&!JsonToken.NULL.equals(reader.peek()))
				if(IsProp(reader.peek()))
				{
					String propname=getClassOrFieldStr(name);
					result.put(propname, reader.nextString());
				}
				else
					reader.skipValue();
				
			}
			reader.endObject();	
			reader.close();
		}
		catch (Exception e) {

			Log.e("JsonParser parseJsonToMap", e.getMessage());
		}
		return result;
	}
	
	
	private static Boolean IsProp(JsonToken jt)
	{
		return (!JsonToken.NULL.equals(jt))&&
		(JsonToken.BOOLEAN.equals(jt)||
				JsonToken.NUMBER.equals(jt)||
				JsonToken.STRING.equals(jt));
	}

	
	private static Boolean IsList(JsonToken jt)
	{
		return (!JsonToken.NULL.equals(jt))&&
		JsonToken.BEGIN_ARRAY.equals(jt);
	}
	

	
	private static Boolean IsEntity(JsonToken jt)
	{
		return (!JsonToken.NULL.equals(jt))&&
		(JsonToken.BEGIN_OBJECT.equals(jt));
	}
	
	/**
	 * ����list����µ�entity ʵ��
	 * @param content
	 * @param respVO �洢����״̬��Ϣ(�����ʵ����)
	 * @return
	 */
	public static List<?> parseJsonToList(String content,ResponseVO respVO)
	{
		return parseJsonToList(StringUtil.StringToInputStream(content), respVO, null);
	}

	/**
	 * ����list����µ�entity ʵ��
	 * @param content
	 * @param respVO �洢����״̬��Ϣ(�����ʵ����)
	 * @param ref ref���ݣ����ref==null��"" ���ȡ��һ��list��ʹ������
	 * @return
	 */
	public static List<?> parseJsonToList(String content,ResponseVO respVO,String ref)
	{
		return parseJsonToList(StringUtil.StringToInputStream(content), respVO, ref);
	}
	
	/**
	 * ����list����µ�entity ʵ��
	 * @param content
	 * @param respVO �洢����״̬��Ϣ(�����ʵ����)
	 * @param ref ref���ݣ����ref==null��"" ���ȡ��һ��list��ʹ������
	 * @return
	 */
	public static List<?> parseJsonToList(InputStream content,ResponseVO respVO,String ref)
	{
		JsonReader reader=parseJson(content, respVO);
		List<?> result = null;
		if(reader==null)
			return result;
		try
		{
			reader.beginObject();
			while (reader.hasNext())
			{
				if(!JsonToken.NAME.equals(reader.peek()))
				{
					reader.skipValue();
					continue;
				}
				String name=reader.nextName();
				//if(name.startsWith(TAG_LIST+"_")&&JsonToken.BEGIN_ARRAY.equals(reader.peek()))
				if(JsonToken.BEGIN_ARRAY.equals(reader.peek()))
				{
					if(StringUtil.isEmpty(ref))
						result=convertToList(reader);
					else if(name.endsWith("_"+ref))
						result=convertToList(reader);
				}
				else 
					reader.skipValue();
			}
			reader.endObject();	
			reader.close();
		}catch (Exception e)
		{
			Log.e("JsonParser parseJsonToList", e.getMessage());
			return result;
		}
		return result;
		
	}
	
	private static Object convertToEntity(JsonReader reader,String classStr)
	{
		Object obj = null;
		try
		{
			String classname=PACKAGE_NAME.concat(classStr);
			Class<?> clazz=Class.forName(classname);
			obj=clazz.newInstance();
			reader.beginObject();
			while(reader.hasNext())
			{
				if(!JsonToken.NAME.equals(reader.peek()))
				{
					reader.skipValue();
					continue;
				}
				String name = reader.nextName();
				String propName = null;	// ������
				Object nodeObj = null;
				String clzName = null;
				//if(name.startsWith(TAG_PROP+"_")&&!JsonToken.NULL.equals(reader.peek()))//prop_field
				if(IsProp(reader.peek()))//field
				{
					nodeObj=reader.nextString();
					clzName = "java.lang.String";
					propName=getClassOrFieldStr(name);
				}
				//else if(name.startsWith(TAG_ENTITY+"_")&&JsonToken.BEGIN_OBJECT.equals(reader.peek()))//entity_class_ref
				else if(IsEntity(reader.peek()))//class_ref
				{
					String subclass=getClassOrFieldStr(name);
					nodeObj = convertToEntity(reader,subclass);
					clzName = PACKAGE_NAME.concat(subclass);
					propName=getRefStr(name);
					
//					if(!StringUtil.isEmpty(propName)&&nodeObj!=null)
//					{
//						Field field = clazz.getField(propName);		// set�����޷���������ת�������Բ���ֱ�Ӹ�ֵ
//						field.set(obj, nodeObj);
//					}
//					continue;
				}
				//else if(name.startsWith(TAG_LIST+"_")&&JsonToken.BEGIN_ARRAY.equals(reader.peek()))//list_ref
				else if(IsList(reader.peek()))//list_ref
				{
					nodeObj = convertToList(reader);
					clzName = "java.util.List";
					propName=getClassOrFieldStr(name);
				}
				else
					reader.skipValue();
				
				if (nodeObj == null) 
				{	
					// ��ֵ�����и�ֵ
					continue;
				}
				Class<?> paraType = null;
				if (clzName != null) {
					paraType = Class.forName(clzName);
				}
				
				try
				{
					Method method = obj.getClass().getMethod("set"+propName.substring(0, 1).toUpperCase()+propName.substring(1), paraType);
					method.invoke(obj, nodeObj);
				}catch (Exception e)
				{
					Log.e("JsonParser.convertToEntity() "+"set"+propName.substring(0, 1).toUpperCase()+propName.substring(1), e.toString());
					continue;
				}
			}
			reader.endObject();
			
		} catch (Exception e)
		{
			Log.e("JsonParser.convertToEntity()", e.toString());
		}
		return obj;
	}
	/**
	 * ����json���ݵ�LIST
	 * @param reader
	 * @return
	 */
	private static List convertToList(JsonReader reader)
	{
		List list = new ArrayList();
		try
		{
			reader.beginArray();
			while (reader.hasNext())
			{
				reader.beginObject();
				while (reader.hasNext())
				{
					if(!JsonToken.NAME.equals(reader.peek()))
					{
						reader.skipValue();
						continue;
					}
					Object nodeObj = null;
					String name = reader.nextName();
					//if(name.startsWith(TAG_ENTITY+"_")&&JsonToken.BEGIN_OBJECT.equals(reader.peek()))
					if(IsEntity(reader.peek()))
					{
						nodeObj = convertToEntity(reader,getClassOrFieldStr(name));
					}
					//else if(name.startsWith(TAG_LIST+"_")&&JsonToken.BEGIN_OBJECT.equals(reader.peek()))
					else if(IsList(reader.peek()))
						nodeObj = convertToList(reader);
					//else if(name.startsWith(TAG_PROP+"_")&&!JsonToken.NULL.equals(reader.peek()))
					else if(IsProp(reader.peek()))
						nodeObj=reader.nextString();
					else
						reader.skipValue();	

					if(nodeObj!=null)
						list.add(nodeObj);
				}
				reader.endObject();
			}
			reader.endArray();
		} catch (Exception e)
		{
			Log.e("JsonParser convertToList", e.getMessage());
		}
		return list;
	}

	
	
	/**
	 * ����str[_ref]�е�str
	 * @param str
	 * @return
	 */
	private static String getClassOrFieldStr(String str)
	{
		if(StringUtil.isEmpty(str))
			return StringUtil.STR_EMPTY;;
		Integer index=str.indexOf("_");
		if(index==-1)
			return str;
		String result=str.substring(0,index);
		return result;
	}
	/**
	 * ����str[_ref]�е�ref
	 * @param str
	 * @return
	 */
	private static String getRefStr(String str)
	{
		if(StringUtil.isEmpty(str))
			return StringUtil.STR_EMPTY;
		Integer index=str.indexOf("_");
		if(index==-1)
			return StringUtil.STR_EMPTY;
		return str.substring(index+1);
	}
}
