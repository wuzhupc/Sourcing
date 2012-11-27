package com.wuzhupc.utils;

import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class SettingUtil
{
	private final static String CStr_SettingSharedPreferencesKey="sourcingsetting";
	
	/**
	 * ��ȡĳ��Ŀ������ʱ��
	 * @param c
	 * @param channelid
	 * @return�����û�м�¼����null
	 */
	public static Date getChannelLastUpdateTime(Context c,long channelid)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_READABLE);
		String key="channel_lastupdatetime_"+channelid;
		
		String lastupdatetime = sp.getString(key, "");
		if(StringUtil.isEmpty(lastupdatetime))
			return null;
		return TimeUtil.strToDate(lastupdatetime, null);
	}
	
	/**
	 * ��ȡĳ��Ŀ������ʱ��
	 * @param c
	 * @param channelid
	 * @return
	 */
	public static void setChannelLastUpdateTime(Context c,long channelid,Date lastupdatetime)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_WRITEABLE);
		String key="channel_lastupdatetime_"+channelid;
		Editor editor=sp.edit();
		editor.putString(key, TimeUtil.dateToString(lastupdatetime));
		editor.commit();
		return;
	}
	
	/**
	 * ��ȡ�ͻ��˰汾
	 * @param c
	 * @return
	 */
	public static String getClientVersion(Context c)
	{
		PackageManager manager = c.getPackageManager();
		try {
			PackageInfo info=manager.getPackageInfo(c.getPackageName(), 0);	
			return info.versionName;
		} catch (Exception e) {
			return "1.0.0";			
		}
	}
	

	/**
	 * ����Ƿ��ǵ�һ������
	 * @param c Ҫ���Activity
	 * @param recordstart trueʱ��¼�Ѿ������� false�򲻽��м�¼
	 * @return true ��һ������ false �ǵ�һ������
	 */
	@SuppressLint({ "WorldReadableFiles", "WorldWriteableFiles" })
	public static Boolean isFirstStart(Activity c,Boolean recordstart)
	{
		String key="fs_"+c.getClass().getSimpleName();
		
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_READABLE|Context.MODE_WORLD_WRITEABLE);
		Boolean firststart=sp.getBoolean(key, true);
		
		if(recordstart&&firststart)
		{
			Editor editor=sp.edit();
			editor.putBoolean(key, false);
			editor.commit();
		}
		
		return firststart;
	}
}
