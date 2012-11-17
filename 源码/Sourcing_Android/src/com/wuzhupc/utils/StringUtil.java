package com.wuzhupc.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * �ַ���������
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-17 ����12:05:26
 */
public class StringUtil
{
	/**
	 * ���ַ���
	 */
	public static final String STR_EMPTY="";
	
	/**
	 * �ж��ַ����Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || STR_EMPTY.equals(str.trim());
	}
	

	/**
	 * �ַ���תstream
	 * @param str
	 * @return
	 */
	public static InputStream StringToInputStream(String str)
	{ 
		if(StringUtil.isEmpty(str))
			return null;
		ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes()); 
		return stream; 
	}
}
