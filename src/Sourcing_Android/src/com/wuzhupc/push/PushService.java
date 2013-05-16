package com.wuzhupc.push;

import com.wuzhupc.utils.AlarmUtil;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class PushService extends Service {
	
	/**
	 * PushService 过滤action
	 */
	public static final String CSTR_ACTION_PUSH_SERVICE = "com.wuzhu.push.pushservice";

	
	private AlarmUtil mAlarm;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		if (mAlarm == null) {
			mAlarm = AlarmUtil.getPushMsgAlarm(this);
		}
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		handleCommand();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		handleCommand();
		
		return START_STICKY;
	}

	@Override
	public void onDestroy() 
	{
		mAlarm.stopTask();
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	private void handleCommand() {
		mAlarm.pendingBroadcastTask(new Intent(PushReceiver.CSTR_ACTION_PUSH_RECEIVER), -1);
	}
	
}
