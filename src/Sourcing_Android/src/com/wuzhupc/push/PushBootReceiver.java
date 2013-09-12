package com.wuzhupc.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class PushBootReceiver extends BroadcastReceiver {
	
	private Context mContext;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.mContext = context;
		Intent newIntent = new Intent(PushService.CSTR_ACTION_PUSH_SERVICE);
		//newIntent.putExtra("alarm", true);
		mContext.startService(newIntent);
	}

}
