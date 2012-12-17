package com.wuzhupc.Sourcing.detail;
 

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.MobileUserService;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;

/**
 * 用户修改密码
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-17 下午9:33:52
 */
public class UserChangePwdActivity extends BaseActivity
{

	private EditText met_Pwd;
	private EditText met_NewPwd;
	private EditText met_ReNewPwd;
	@Override
	protected void initDataContent()
	{
	}

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_changepwd);
		met_NewPwd=(EditText)findViewById(R.id.userchangepwd_newpwd_et);
		met_ReNewPwd=(EditText)findViewById(R.id.userchangepwd_renewpwd_et);
		met_ReNewPwd.setImeActionLabel(getResources().getString(R.string.dl_btn_confirm), EditorInfo.IME_ACTION_DONE);
		met_ReNewPwd.setOnEditorActionListener(new OnEditorActionListener()
		{
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
				if(actionId==EditorInfo.IME_ACTION_DONE)
				{
					changePwdClick(findViewById(R.id.userchangepwd_changepwd_bt));
					return true;
				}
				return false;
			}
		});
		met_Pwd=(EditText)findViewById(R.id.userchangepwd_pwd_et);
	}

	/**
	 * 修改密码
	 * @param v
	 */
	public void changePwdClick(View v)
	{
		//隐藏输入法
		hideIme();
		//判断
		if(StringUtil.isEmpty(met_Pwd.getText().toString()))
		{
			met_Pwd.requestFocus();
			displayToast(R.string.userchangepwd_pwd_hit);
			return;
		}
		if(StringUtil.isEmpty(met_NewPwd.getText().toString()))
		{
			met_NewPwd.requestFocus();
			displayToast(R.string.userchangepwd_newpwd_hit);
			return;
		}
		
		if(!met_NewPwd.getText().toString().equals(met_ReNewPwd.getText().toString()))
		{
			met_ReNewPwd.setText("");
			met_ReNewPwd.requestFocus();
			displayToast(R.string.userchangepwd_newpwd_noequal);
			return;
		}
		final UserVO userVO = getApplicationSet().getUserVO();
		if(userVO==null)
		{
			UserChangePwdActivity.this.finish();
			displayToast(R.string.userview_nologin);
			return;
		}
		MobileUserService service = new MobileUserService(UserChangePwdActivity.this);
		service.changePwd(userVO.getUseraccount(), met_Pwd.getText().toString(), met_NewPwd.getText().toString(), new IBaseReceiver()
		{
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				if(!isSuc)
				{
					displayToast(String.format(getResources().getString(R.string.userchangepwd_change_error),content));
					return;
				}
				ResponseVO responseVO = JsonParser.parseJsonToResponse(content);
				if(!responseVO.isSucess())
				{
					displayToast(String.format(getResources().getString(R.string.userchangepwd_change_error),responseVO.getMsg()));
					return;
				}
				userVO.setPassword(met_NewPwd.getText().toString());
				getApplicationSet().setUserVO(userVO, true);
				displayToast(R.string.userchangepwd_change_sucess);
				UserChangePwdActivity.this.finish();
				return;
			}
		}, true	, getResources().getString(R.string.userchangepwd_changing));
	}
	
	/**
	 * 隐藏输入法
	 */
	protected void hideIme()
	{
		InputMethodManager imm = (InputMethodManager)UserChangePwdActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(!imm.isActive()) return;
		imm.hideSoftInputFromWindow(met_ReNewPwd.getWindowToken(), 0);
	}
}
