package com.wuzhupc.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

/**
 * ��ʱ
 * @author wuzhu
 *
 */
public class AlarmUtil
{
	private static final String TAG = AlarmUtil.class.getSimpleName();
	
	/**
	 * Ĭ�ϵ�һ��ˢ������������(��λS)
	 */
	public static final int CINT_DEFAULT_AFTERFIRSTSTART_DELAY = 5;

	/**
	 * Ĭ��ˢ��ʱ��(��λS)
	 */
	public static final int CINT_DEFAULT_REFRESH_DELAY = 60;
	
	/**
	 * �Ƿ�ֹͣ��ʱ��
	 */
	private boolean isPaused=false;
	
	/**
	 * Ĭ��ˢ��ʱ����
	 */
	private int minterval;
	
	private int mafterfirststart;
	
	private Context mContext;
	
	private  PendingIntent msender = null;
	private  AlarmManager mam = null;
	
	private static AlarmUtil pushMsgAlarm;		// ������Ϣר�ö�ʱ��
	
	public static AlarmUtil getPushMsgAlarm(Context context) {
		if (pushMsgAlarm == null) {
			pushMsgAlarm = new AlarmUtil(context);
		}
		
		return pushMsgAlarm;
	}
	
	/**
	 * ʹ��Ĭ�ϵ�ʱ��
	 * @param context
	 */
	public AlarmUtil(Context context)
	{
		this(context,CINT_DEFAULT_AFTERFIRSTSTART_DELAY,CINT_DEFAULT_REFRESH_DELAY);
	}
			
	
	/**
	 * 
	 * @param context ��ǰcontext
	 * @param afterfirststart ��һ�������������ô�������λS���������0
	 * @param interval �Ժ󴥷����ʱ�䣬��λS��������ڵ���0(����0��ʾֻ����һ��)
	 */
	public AlarmUtil(Context context,int afterfirststart,int interval)
	{
		mContext=context;
		if(afterfirststart>0)
			mafterfirststart=afterfirststart*1000;
		else
			mafterfirststart=CINT_DEFAULT_AFTERFIRSTSTART_DELAY*1000;
		if(interval>=0)
			minterval=interval*1000;
		else
			minterval=CINT_DEFAULT_REFRESH_DELAY*1000;
	}
	
	/**
	 * ����ִ�й㲥����(�Զ�����intervalֵ�����ظ�(ֵ����0ʱ)����ִֻ��һ��(ֵ����0ʱ))
	 * @param clazz extends BroadcastReceiver
	 */
	public void pendingBroadcastTask(String action)
	{
		Intent intent=new Intent(action);
		pendingBroadcastTask(intent);
	}
	
	/**
	 * ����ִ�й㲥����(�Զ�����intervalֵ�����ظ�(ֵ����0ʱ)����ִֻ��һ��(ֵ����0ʱ))
	 * @param clazz extends BroadcastReceiver
	 */
	public void pendingBroadcastTask(Class<?> clazz) 
	{
		Intent intent=new Intent(mContext,clazz);
		pendingBroadcastTask(intent);
		
	}
	
	/**
	 * ������һ�μ��interval��֮��ִ�й㲥����
	 * @param intent
	 * @param interval
	 */
	public void pendingBroadcastTask(Intent intent, int interval) {
		if (isPaused) {
			return;
		}
		
		if(interval>=0)
			minterval=interval*1000;
		else
			minterval=CINT_DEFAULT_REFRESH_DELAY*1000;
		
		long settime = SystemClock.elapsedRealtime() + minterval;
		
		msender=PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		mam=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
		
		mam.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, settime, msender);
		
		Log.i(TAG, "alarm next time at "+minterval/1000+" seconds later!!");
	}
	
	private void pendingBroadcastTask(Intent intent)
	{
		msender=PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		mam=(AlarmManager)mContext.getSystemService(Context.ALARM_SERVICE);
		long settime=SystemClock.elapsedRealtime()+mafterfirststart;
		
		if(minterval>0)
		{
			mam.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, settime, minterval,msender);
			Log.i(TAG, "start alarm repeating!!");
		}
		else
		{
			mam.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, settime, msender);
			Log.i(TAG, "start alarm once!!");
		}
	}
	
	/**
	 * ��ִֹ������
	 */
	public void stopTask()
	{
		if(mam!=null)
			mam.cancel(msender);
		Log.i(TAG, "stop alarm!!");
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

}
