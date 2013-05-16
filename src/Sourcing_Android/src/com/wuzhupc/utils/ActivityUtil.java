package com.wuzhupc.utils;

import java.util.Iterator;
import java.util.List;

import com.wuzhupc.Sourcing.HomeActivity;
import com.wuzhupc.Sourcing.WelcomeActivity;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;

public class ActivityUtil {
	
	private static final String TAG=ActivityUtil.class.getSimpleName();

	/**
	 * 判断当前应用是否运行
	 * @param context
	 * @return
	 */
	public static ComponentName isCurAppRunning(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(0xff);
		Log.i(TAG, "num = "+(0xff)+"\t context package = "+context.getPackageName());
		Iterator<RunningTaskInfo> taskIt = taskList.iterator();
		
		Log.i(TAG, "activity name = "+WelcomeActivity.class.getName()+"\t "+HomeActivity.class.getName());
		while (taskIt.hasNext()) {
			ActivityManager.RunningTaskInfo taskInfo = (ActivityManager.RunningTaskInfo) taskIt.next();
			
			ComponentName componentName = taskInfo.baseActivity;
			Log.i(TAG, "component name = "+componentName.getClassName());
			
			if (context.getPackageName().equals(componentName.getPackageName())
					&& (componentName.getClassName().equals(WelcomeActivity.class.getName()) || 
							componentName.getClassName().equals(HomeActivity.class.getName()))) {
			    
			    Log.i(TAG, "topActivity name = "+taskInfo.topActivity.getClassName());
				return taskInfo.topActivity;
			}
		}
		
		return null;
	}
	
	/**
	 * 判断当前应用是否位于前端显示
	 * @param context
	 * @return
	 */
	public static boolean isCurAppRunningForeground(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(1);
		
		if (taskList == null || taskList.isEmpty()) {
			return false;
		}
		
		RunningTaskInfo taskInfo = taskList.get(0);
		
		ComponentName componentName = taskInfo.baseActivity;
		Log.i(TAG, "check foreground :: baseActivity = "+componentName.getClassName());
		if (context.getPackageName().equals(componentName.getPackageName())
				&& (componentName.getClassName().equals(WelcomeActivity.class.getName()) || 
						componentName.getClassName().equals(HomeActivity.class.getName()))) {
			return true;
		}
		
		return false;
	}
}
