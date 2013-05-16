package com.wuzhupc.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 字符串工具类
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-17 下午12:05:26
 */
public class StringUtil
{
	/**
	 * 空字符串
	 */
	public static final String STR_EMPTY="";
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || STR_EMPTY.equals(str.trim());
	}
	

	/**
	 * 字符串转stream
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
