package com.wuzhupc.utils;

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
	 * 获取客户端版本
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
	 * 检测是否是第一次启动
	 * @param c 要检测Activity
	 * @param recordstart true时记录已经启动过 false则不进行记录
	 * @return true 第一次启动 false 非第一次启动
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
