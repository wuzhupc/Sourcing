package com.wuzhupc.Sourcing.detail;

import android.view.View;
import android.widget.EditText;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.utils.StringUtil;

/**
 * ÓÃ»§µÇÂ¼
 * @author wuzhu
 *
 */
public class UserLoginActivity extends BaseActivity
{
	protected static final String TAG = UserLoginActivity.class
			.getSimpleName();
	private EditText met_Account;
	private EditText met_Pwd;
	
	@Override
	protected void initDataContent()
	{
	}

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_userlogin);
		met_Account = (EditText)findViewById(R.id.userlogin_account_et);
		met_Pwd = (EditText)findViewById(R.id.userlogin_pwd_et);
	}

	public void forgetPwdClick(View v)
	{
		//Íü¼ÇÃÜÂë
		runBrowser(getResources().getString(R.string.url_forget_pwd_progress));
	}
	
	public void loginClick(View v)
	{
		String account = met_Account.getEditableText().toString().trim();
		if (StringUtil.isEmpty(account))
		{
			displayToast(R.string.userlogin_account_empty);
			met_Account.requestFocus();
			return;
		}
		String pwd = met_Pwd.getEditableText().toString().trim();
		if (StringUtil.isEmpty(pwd))
		{
			displayToast(R.string.userlogin_pwd_empty);
			met_Pwd.requestFocus();
			return;
		}
		//TODO
	}
	
	public void regClick(View v)
	{
		runBrowser(getResources().getString(R.string.url_reg_progress));
	}
}
