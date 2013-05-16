package com.wuzhupc.push;

import com.wuzhupc.utils.SettingUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class PushBootReceiver extends BroadcastReceiver {
	
	private Context mContext;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.mContext = context;
		if(!SettingUtil.getPushService(mContext))
			return;
		Intent newIntent = new Intent(PushService.CSTR_ACTION_PUSH_SERVICE);
		//newIntent.putExtra("alarm", true);
		mContext.startService(newIntent);
	}

}
