package com.wuzhupc.utils;

import com.wuzhupc.Sourcing.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class NotifyUtil {

	private static final String TAG = "NotifyUtil";
	
	private static final int NM_ID = 1;
	
	private static NotificationManager nm = null;
	
	//private static Context mContext;
	
	public static void setContext(Context context) {
		//mContext = context;
	}
	
	public static boolean notify(Context context, Intent intent, String title, String detail)
	{
		return notify(context, intent, title, detail, Notification.FLAG_AUTO_CANCEL);
	}
	/**
	 * 发布通知
	 * @param context
	 * @param intent
	 * @return 是否发送通知
	 */
	public static boolean notify(Context context, Intent intent, String title, String detail,int flags) {
		final CharSequence popMsg = context.getText(R.string.notify_pop_msg);
		long curTime = System.currentTimeMillis();
		
		if (StringUtil.isEmpty(title))
        {
            title = context.getString(R.string.notify_title);
        }
		if (StringUtil.isEmpty(detail))
        {
            detail = context.getString(R.string.notify_prompt);
        }
		
		nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification notification = new Notification(R.drawable.ic_launcher, popMsg, curTime);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		notification.setLatestEventInfo(context, title, detail, pendingIntent);
		//notification.flags |= Notification.FLAG_AUTO_CANCEL;	// 点击后取消显示
		notification.flags = flags;
		notification.defaults |= Notification.DEFAULT_SOUND;
		
		nm.notify(NM_ID, notification);
		
		return true;
	}
	
	public static void cancel(Context context) {
		nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Log.i(TAG, "cancel() :: NotificationManager = "+nm);
		
		nm.cancel(NM_ID);
		
		Log.i(TAG, "cancel() :: notification was canceled!!");
	}
	
}
