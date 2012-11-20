package com.wuzhupc.Sourcing;


import com.wuzhupc.utils.SettingUtil;

import android.os.Bundle;
import android.widget.TextView;
/**
 * 启动界面 显示闪屏，并从服务器端加载栏目信息
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-20 下午9:05:36
 */
public class WelcomeActivity extends BaseActivity
{
	protected static final String Tag=WelcomeActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setCatureBackKey(true);
		checkCacheFolder();
		initView();
	}
	
	/**
	 * 初始化View
	 */
	private void initView()
	{
		//初始化View
		setContentView(R.layout.activity_welcome);
		((TextView)findViewById(R.id.welcome_ver_tv)).setText(String.format(getResources().getString(R.string.welcome_ver), SettingUtil.getClientVersion(this)));
		
	}
}
