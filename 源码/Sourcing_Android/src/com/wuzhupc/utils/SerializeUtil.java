package com.wuzhupc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import android.util.Base64;
import android.util.Log;

/**
 * 序列化工具类
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-2 下午8:15:39
 */
public class SerializeUtil
{
	private static final String TAG = SerializeUtil.class.getSimpleName();
	
	/**
	 * 序列化为字符串
	 * @param obj
	 * @return
	 */
	public static String objectSerialzeTOString(Serializable obj)
	{
		String objBody = null;
		ByteArrayOutputStream baops = null;
		ObjectOutputStream oos = null;
		try
		{
			baops = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baops);
			oos.writeObject(obj);
			byte[] bytes = baops.toByteArray();
			objBody = new String(Base64.encode(bytes, Base64.NO_WRAP));
			//objBody = new String(bytes);
		} catch (IOException e)
		{
			Log.e(TAG, e.toString());
		} finally
		{
			try
			{
				if (oos != null)
					oos.close();
				if (baops != null)
					baops.close();
			} catch (IOException e)
			{
				Log.e(TAG, e.toString());
			}
		}
		return objBody;
	}
	
	/**
	 * 字符串反序列化为对象
	 * @param objBody
	 * @param clazz
	 * @return
	 */
	public static Object getObjectFromString(String objBody)
	{
		if(StringUtil.isEmpty(objBody))
			return null;
		byte[] bytes=Base64.decode(objBody, Base64.NO_WRAP);
		if(bytes==null||bytes.length==0)
			return null;
		ObjectInputStream ois = null;
		Object obj = null;
		try
		{
			ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
			obj = ois.readObject();
		} catch (IOException e)
		{
			Log.e(TAG, e.toString());
		} catch (ClassNotFoundException e)
		{
			Log.e(TAG, e.toString());
		} finally
		{
			try
			{
				if (ois != null)
					ois.close();
			} catch (IOException e)
			{
				Log.e(TAG, e.toString());
			}
		}

		return obj;
	}
}
