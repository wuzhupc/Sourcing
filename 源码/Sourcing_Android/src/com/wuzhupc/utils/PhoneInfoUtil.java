package com.wuzhupc.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * ��ȡ
 * @author wuzhu
 *
 */
public class PhoneInfoUtil
{
	/**
	 * ��ȡ��֤��
	 * 
	 * @param c
	 * @param bsim
	 *            trueʱ��sim���е������ȣ���ȡ�ֻ���û����ȡIMSI����ȡ��������IMEI�š�falseʱֱ�ӷ���IMEI��
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
	 * ��ȡ�ֻ�IMEI��Ϣ
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
	 * ��ȡMAC�����ַ
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
	 * ��ȡ�ֻ�����
	 */
	public static String GetPhoneNumber(Context c)
	{
		TelephonyManager telephonyManager = (TelephonyManager) c
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getLine1Number();
	}

	/**
	 * ��ȡIMSI��
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
