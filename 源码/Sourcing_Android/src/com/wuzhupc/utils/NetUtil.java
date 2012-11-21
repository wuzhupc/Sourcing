package com.wuzhupc.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil
{
	/**
	 * ��õ�ǰ��������
	 * 
	 * @return -1: ����������; ConnectivityManager.TYPE_WIFI: wifi;
	 *         ConnectivityManager.TYPE_MOBILE:mobile���ж���������״̬Ϊʱmobile��������ж���net����wap����
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
