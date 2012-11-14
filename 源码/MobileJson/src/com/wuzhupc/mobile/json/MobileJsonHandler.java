package com.wuzhupc.mobile.json;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.wuzhupc.mobile.interoperate.JsonMarshal;
import com.wuzhupc.mobile.vo.MobileRequestVO;
import com.wuzhupc.utils.StringUtils;

public class MobileJsonHandler
{
	public static MobileRequestVO parseMobileRequest(String requestjson)
	{	
		return parseMobileRequest(StringUtils.StringToInputStream(requestjson));
	}
	
	public static MobileRequestVO parseMobileRequest(InputStream requestjson)
	{
		if(requestjson==null)
			return null;
		MobileRequestVO vo=new MobileRequestVO();
		JsonReader reader;
		try
		{
			reader=new JsonReader(new InputStreamReader(requestjson,"UTF-8"));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}
		Gson gson=new Gson();
		vo=gson.fromJson(reader, MobileRequestVO.class);
		
		return vo;
	}
	

	
	/**
	 * @param id
	 */
	public static String successInfo(String id) {
		JsonMarshal marshal = new JsonMarshal(id, 1, null);
		return marshal.toJson();
	}
	
	/**
	 * @param id
	 * @param message
	 */
	public static String successInfo(String id, String message) {
		JsonMarshal marshal = new JsonMarshal(id, 1, message);
		return marshal.toJson();
	}
	
	/**
	 * @param id
	 * @param message
	 */
	public static String errorInfo(String id, String message) {
		JsonMarshal marshal = new JsonMarshal(id, 2, message);
		return marshal.toJson();
	}
	
	/**
	 * @param id
	 * @param e
	 */
	public static String errorInfo(String id, Exception e) {
		String message = e.getMessage();
		if (message == null || message.isEmpty()) {
			message = e.getClass().getSimpleName() + ":" + e.getStackTrace()[0].toString();
		}
		return errorInfo(id, message);
	}
}
