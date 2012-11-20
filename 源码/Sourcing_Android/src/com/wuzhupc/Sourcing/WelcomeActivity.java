package com.wuzhupc.Sourcing;


import com.wuzhupc.utils.SettingUtil;

import android.os.Bundle;
import android.widget.TextView;
/**
 * �������� ��ʾ���������ӷ������˼�����Ŀ��Ϣ
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-20 ����9:05:36
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
	 * ��ʼ��View
	 */
	private void initView()
	{
		//��ʼ��View
		setContentView(R.layout.activity_welcome);
		((TextView)findViewById(R.id.welcome_ver_tv)).setText(String.format(getResources().getString(R.string.welcome_ver), SettingUtil.getClientVersion(this)));
		
	}
}
