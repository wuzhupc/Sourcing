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
	 * �����б�
	 */
	public final static  TextSize[] NewsFontSizes = { 
			WebSettings.TextSize.SMALLER, WebSettings.TextSize.NORMAL,
			WebSettings.TextSize.LARGER, WebSettings.TextSize.LARGEST };
	/**
	 * ���������б�
	 */
	public final static  String[] NewsFontSizesDesc={"С����","�к���","�����","�ش��ֺ�"};
	
	/**
	 * ��ȡĳ��Ŀ������ʱ��
	 * @param c
	 * @param channelid
	 * @return�����û�м�¼����null
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
	 * ��ȡĳ��Ŀ������ʱ��
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
	 * �����Ƿ��������ͷ���
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
	 *  ��ȡ�Ƿ�������Ϣ����
	 * @param c
	 * @return
	 */
	public static boolean getPushService(Context c)
	{
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_READABLE);
		return sp.getBoolean(CStr_PushService, true);
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
	/**
	 * ��ȡ��������
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
	 * ��ȡ��Ч������ֵ
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
	 * ����������ȡ����
	 * @param index
	 * @return
	 */
	public static TextSize getNewsFontSizeFromIndex(int index)
	{
		return NewsFontSizes[getEffIndex(index)];
	}
	/**
	 * ��ȡ�����Ӧ������
	 * @param textSize
	 * @return
	 */
	public static String getNewsFontSizeDesc(TextSize textSize)
	{
		return getNewsFontSizeDesc(getIndexFromNewsFontSizes(textSize));
	}
	
	/**
	 * ��ȡ�����Ӧ������
	 * @param index
	 * @return
	 */
	public static String getNewsFontSizeDesc(int index)
	{
		if(index<0||index>=NewsFontSizesDesc.length)
			return "���岻����!";
		return NewsFontSizesDesc[index];
	}
	
	/**
	 * ��ȡ������������
	 * @param c
	 * @return
	 */
	public static TextSize getNewsFontSize(Context c)
	{		
		return NewsFontSizes[getNewsFontSizeIndex(c)];
	} 
	
	/**
	 * ��ȡ������������
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
	 * ������������
	 * @param c
	 * @param textSize
	 */
	public static void setNewsFontSize(Context c,TextSize textSize)
	{
		int index=getIndexFromNewsFontSizes(textSize);
		setNewsFontSize(c, index);
	}
	
	/**
	 * ������������
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
	 * �����ϴλ�ȡ������Ϣʱ��
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
	 * ��ȡ�ϴλ�ȡ������Ϣʱ��
	 * @param c
	 * @return
	 */
	public static String getLastCheckPushMsgTime(Context c) {
		SharedPreferences sp=c.getSharedPreferences(CStr_SettingSharedPreferencesKey, Context.MODE_WORLD_READABLE);
		
		return sp.getString(CStr_LastCheckPushMsgTime, "");
	}
}
