package com.wuzhupc.utils;


/**
 * JAVA��������ת��Utils
 * @author wuzhu
 *
 */
public class JavaLangUtil
{
	/**
	 * �ַ���ת����
	 * @param str
	 * @param defvalue Ĭ��ֵ
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
	 * �ַ���ת����
	 * @param str
	 * @param defvalue Ĭ��ֵ
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
	 * �ַ���ת����
	 * @param str 
	 * @param defvalue
	 * @return ���str=="true" ����str="1",�򷵻�true,���򷵻�false
	 */
	public static boolean StrToBool(String str, boolean defvalue)
	{
		if(StringUtil.isEmpty(str))
			return defvalue;
		return str.equalsIgnoreCase("true")||str.equals("1");
	}
	
	/**
	 * Longת�ַ���
	 * @param value
	 * @return ʧ�ܷ��ؿ��ַ���
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
