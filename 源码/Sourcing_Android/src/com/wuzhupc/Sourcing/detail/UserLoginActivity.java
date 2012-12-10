package com.wuzhupc.Sourcing.detail;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.HomeActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.MobileUserService;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * �û���¼
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
		//��������
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
		MobileUserService service = new MobileUserService(UserLoginActivity.this);
		service.userLogin(account, pwd, new IBaseReceiver()
		{
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if(!isSuc)
				{
					displayToast(String.format(getResources().getString(R.string.userlogin_login_error),content));
					return;
				}
				ResponseVO responseVO = new ResponseVO();
				UserVO vo = (UserVO)JsonParser.parseJsonToEntity(content, responseVO);
				if(!responseVO.isSucess()||vo==null)
				{
					displayToast(String.format(getResources().getString(R.string.userlogin_login_error),responseVO.getMsg()));
					return;
				}
				getApplicationSet().setUserVO(vo, true);
				sendBroadcast(new Intent(HomeActivity.CSTR_ACTION_USERLOGINCOMPLETE));
			}
		}, true, getResources().getString(R.string.userlogin_logining));
	}
	
	public void regClick(View v)
	{
		runBrowser(getResources().getString(R.string.url_reg_progress));
	}
}
