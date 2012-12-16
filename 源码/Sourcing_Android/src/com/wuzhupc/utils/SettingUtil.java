package com.wuzhupc.utils;

import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;

public class SettingUtil
{
	private final static String CStr_SettingSharedPreferencesKey="sourcingsetting";
	
	private final static String CStr_NewsFontSize_Key="NewsFontSize";

	private final static String CStr_LastCheckPushMsgTime="LastCheckTime";
	
	private static final String CStr_PushService = "pushservice";
	
	/**
	 * 字体列表
	 */
	public final static  TextSize[] NewsFontSizes = { 
			WebSettings.TextSize.SMALLER, WebSettings.TextSize.NORMAL,
			WebSettings.TextSize.LARGER, WebSettings.TextSize.LARGEST };
	/**
	 * 字体描述列表
	 */
	public final static  String[] NewsFontSizesDesc={"小号字","中号字","大号字","特大字号"};
	
	/**
	 * 获取某栏目最后更新时间
	 * @param c
	 * @param channelid
	 * @return　如果没有记录返回null
	 */
	public static Date getChannelLastUpdateTime(Context c,long fatherchannelid,long channelid)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_READABLE);
		String key="channel_lastupdatetime_"+fatherchannelid+"_"+channelid;
		
		String lastupdatetime = sp.getString(key, "");
		if(StringUtil.isEmpty(lastupdatetime))
			return null;
		return TimeUtil.strToDate(lastupdatetime, null);
	}
	
	/**
	 * 获取某栏目最后更新时间
	 * @param c
	 * @param channelid
	 * @return
	 */
	public static void setChannelLastUpdateTime(Context c,long fatherchannelid,long channelid,Date lastupdatetime)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_WRITEABLE);
		String key="channel_lastupdatetime_"+fatherchannelid+"_"+channelid;
		Editor editor=sp.edit();
		editor.putString(key, TimeUtil.dateToString(lastupdatetime));
		editor.commit();
	}
	
	/**
	 * 设置是否启用推送服务
	 * @param c
	 * @param pushservice
	 */
	public static void setPushService(Context c,boolean pushservice)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_WRITEABLE);
		Editor editor = sp.edit();
		editor.putBoolean(CStr_PushService, pushservice);
		editor.commit();
	}

	/**
	 *  获取是否启用信息推送
	 * @param c
	 * @return
	 */
	public static boolean getPushService(Context c)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_READABLE);
		return sp.getBoolean(CStr_PushService, true);
	}
	
	
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
	/**
	 * 获取字体索引
	 * @param textSize
	 * @return
	 */
	public static int getIndexFromNewsFontSizes(TextSize textSize)
	{
		for(int i=0;i<NewsFontSizes.length;i++)
			if(textSize.equals(NewsFontSizes[i]))
				return i;
		return -1;
	}
	/**
	 * 获取有效的索引值
	 * @param index
	 * @return
	 */
	private static int getEffIndex(int index)
	{
		if(index<0)
			index=0;
		if(index>=NewsFontSizes.length)
			index=NewsFontSizes.length-1;
		return index;
	}
	/**
	 * 根据索引获取字体
	 * @param index
	 * @return
	 */
	public static TextSize getNewsFontSizeFromIndex(int index)
	{
		return NewsFontSizes[getEffIndex(index)];
	}
	/**
	 * 获取字体对应的描述
	 * @param textSize
	 * @return
	 */
	public static String getNewsFontSizeDesc(TextSize textSize)
	{
		return getNewsFontSizeDesc(getIndexFromNewsFontSizes(textSize));
	}
	
	/**
	 * 获取字体对应的描述
	 * @param index
	 * @return
	 */
	public static String getNewsFontSizeDesc(int index)
	{
		if(index<0||index>=NewsFontSizesDesc.length)
			return "字体不存在!";
		return NewsFontSizesDesc[index];
	}
	
	/**
	 * 获取新闻字体设置
	 * @param c
	 * @return
	 */
	public static TextSize getNewsFontSize(Context c)
	{		
		return NewsFontSizes[getNewsFontSizeIndex(c)];
	} 
	
	/**
	 * 获取新闻字体设置
	 * @param c
	 * @return
	 */
	public static int getNewsFontSizeIndex(Context c)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_READABLE);
		int index=sp.getInt(CStr_NewsFontSize_Key, 1);
		return getEffIndex(index);
	} 
	/**
	 * 设置字体设置
	 * @param c
	 * @param textSize
	 */
	public static void setNewsFontSize(Context c,TextSize textSize)
	{
		int index=getIndexFromNewsFontSizes(textSize);
		setNewsFontSize(c, index);
	}
	
	/**
	 * 设置字体设置
	 * @param c
	 * @param index
	 */
	public static void setNewsFontSize(Context c,int index)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_WRITEABLE);
		Editor editor=sp.edit();
		editor.putInt(CStr_NewsFontSize_Key,index);
		editor.commit();
	}

	
	/**
	 * 设置上次获取推送消息时间
	 * @param c
	 * @param time
	 */
	public static void setLastCheckPushMsgTime(Context c, String time) {
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_WRITEABLE);
		Editor editor=sp.edit();
		editor.putString(CStr_LastCheckPushMsgTime, time);
		editor.commit();
	}
	
	/**
	 * 获取上次获取推送消息时间
	 * @param c
	 * @return
	 */
	public static String getLastCheckPushMsgTime(Context c) {
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_READABLE);
		
		return sp.getString(CStr_LastCheckPushMsgTime, "");
	}
}
