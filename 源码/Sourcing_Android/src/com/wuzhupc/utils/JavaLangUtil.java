package com.wuzhupc.utils;


/**
 * JAVA基本类型转换Utils
 * @author wuzhu
 *
 */
public class JavaLangUtil
{
	/**
	 * 字符串转数字
	 * @param str
	 * @param defvalue 默认值
	 * @return
	 */
	public static int StrToInteger(String str,int defvalue)
	{
		try
		{
			return Integer.parseInt(str);
		}
		catch (Exception e) {
			return defvalue;
		}
	}
	
	/**
	 * 字符串转数字
	 * @param str
	 * @param defvalue 默认值
	 * @return
	 */
	public static Long StrToLong(String str,Long defvalue)
	{
		try
		{
			return Long.parseLong(str);
		}
		catch (Exception e) {
			return defvalue;
		}
	}
	
	/**
	 * 字符串转布尔
	 * @param str 
	 * @param defvalue
	 * @return 如果str=="true" 或者str="1",则返回true,否则返回false
	 */
	public static boolean StrToBool(String str, boolean defvalue)
	{
		if(StringUtil.isEmpty(str))
			return defvalue;
		return str.equalsIgnoreCase("true")||str.equals("1");
	}
	
	/**
	 * Long转字符串
	 * @param value
	 * @return 失败返回空字符串
	 */
	public static String LongToStr(Long value)
	{
		if(value==null)
			return "";
		try
		{
		return Long.toString(value);
		}catch(Exception e)
		{
			return "";
		}
	}
}
