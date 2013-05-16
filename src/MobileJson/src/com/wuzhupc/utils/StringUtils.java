package com.wuzhupc.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class StringUtils
{

	/**
	 * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÎª¿Õ
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		return str == null || "".equals(str.trim());
	}
	
	public static InputStream StringToInputStream(String str)
	{ 
		if(StringUtils.isEmpty(str))
			return null;
		ByteArrayInputStream stream=null;
		try
		{
			stream = new ByteArrayInputStream(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} 
		return stream; 
	}
	
	public static String inputStreamToString(InputStream is) throws IOException
	{ 
		   BufferedReader in = new BufferedReader(new InputStreamReader(is)); 
		   StringBuffer buffer = new StringBuffer(); 
		   String line = ""; 
		   while ((line = in.readLine()) != null){ 
		     buffer.append(line+"\n");
		   } 
		   return buffer.toString(); 
		} 
}
