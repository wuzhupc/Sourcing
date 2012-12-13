package com.wuzhupc.Sourcing.detail;

import android.view.View;
import android.widget.TextView;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.utils.SettingUtil;

public class AboutActivity extends BaseActivity
{

	@Override
	protected void initDataContent()
	{
	}

	@Override
	protected void initView()
	{
		//≥ı ºªØView
		setContentView(R.layout.activity_welcome);
		((TextView)findViewById(R.id.welcome_ver_tv)).setText(String.format(getResources().getString(R.string.welcome_ver), SettingUtil.getClientVersion(this)));
		findViewById(R.id.welcome_close_ib).setVisibility(View.VISIBLE);
	}

}
