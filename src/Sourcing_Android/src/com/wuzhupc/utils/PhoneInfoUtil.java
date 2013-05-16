package com.wuzhupc.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * 获取
 * @author wuzhu
 *
 */
public class PhoneInfoUtil
{
	/**
	 * 获取验证号
	 * 
	 * @param c
	 * @param bsim
	 *            true时：sim卡有的问优先，先取手机号没有则取IMSI，再取不到返回IMEI号。false时直接返回IMEI号
	 * @return
	 */
	public static String GetValidateID(Context c, Boolean bsim)
	{
		if (!bsim)
			return GetIMEI(c);
		String result = GetPhoneNumber(c);
		if (result == null || result.equals(""))
			result = GetIMSI(c);
		if (result == null || result.equals(""))
			result = GetIMEI(c);
		return result;
	}

	/**
	 * 获取手机IMEI信息
	 */
	public static String GetIMEI(Context c)
	{
		String result = "";
		TelephonyManager telephonyManager = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		if(telephonyManager!=null)
		{
			result = telephonyManager.getDeviceId();
		}
		if(StringUtil.isEmpty(result))
			result = GetLocalMacAddress(c);
		return result;
	}
	
	/**
	 * 读取MAC物理地址
	 * @param c
	 * @return
	 */
	public static String GetLocalMacAddress(Context c)
	{
		WifiManager wifi = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
		if(wifi != null)
		{
			WifiInfo info = wifi.getConnectionInfo(); 
			if(info!=null)
				return info.getMacAddress();
		}
		return "";
	}

	/**
	 * 获取手机号码
	 */
	public static String GetPhoneNumber(Context c)
	{
		TelephonyManager telephonyManager = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getLine1Number();
	}

	/**
	 * 获取IMSI号
	 * 
	 * @param c
	 * @return
	 */
	public static String GetIMSI(Context c)
	{
		TelephonyManager telephonyManager = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSubscriberId();
	}
}
