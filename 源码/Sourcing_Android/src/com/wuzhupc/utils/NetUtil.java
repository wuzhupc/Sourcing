package com.wuzhupc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil
{
	/**
	 * 获得当前联网类型
	 * 
	 * @return -1: 无连接类型; ConnectivityManager.TYPE_WIFI: wifi;
	 *         ConnectivityManager.TYPE_MOBILE:mobile。判断网络连接状态为时mobile最好增加判断是net还是wap网络
	 */
	public static int getNetWorkType(Context c)
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		if(connectivityManager==null)
			return -1;
		
		NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
		
		if (networkInfo != null&&networkInfo.isAvailable())
			return networkInfo.getType();
		
		return -1;
	}
}
