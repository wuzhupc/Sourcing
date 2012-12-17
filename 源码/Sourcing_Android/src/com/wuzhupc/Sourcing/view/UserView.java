package com.wuzhupc.Sourcing.view;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.detail.UserChangePwdActivity;
import com.wuzhupc.Sourcing.detail.UserLoginActivity;
import com.wuzhupc.Sourcing.dialog.BaseDialog;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.services.ImageService;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * �û���ϢView
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-12-5 ����9:53:48
 */
public class UserView extends BaseView
{
	/**
	 * �û�ͷ��
	 */
	private ImageView miv_userpic;
	private TextView mtv_username;
	private TextView mtv_usertype;
	private Button mbt_login_pwd;
	private Button mbt_reg_account;

	public UserView(Context context, long fatherchannelid)
	{
		super(context, fatherchannelid, false, false, false);
	}

	@Override
	public void loadData(Boolean isfirstload)
	{
		setShowUserInfo();
	}

	/**
	 * TODO ���������û��������UserView�����ʾ
	 */
	private void setShowUserInfo()
	{
		UserVO userVO = getNowUser();
		if (userVO == null)
		{
			miv_userpic.setImageResource(R.drawable.icon_userpic_bg);
			mtv_username.setText(R.string.userview_nologin);
			mtv_usertype.setText("");
			mbt_login_pwd.setText(R.string.userview_login);
			mbt_reg_account.setText(R.string.userview_register);
		} else
		{
			ImageService imageService = new ImageService(mContext);
			imageService.setThumbnail(miv_userpic, userVO.getUserphoto(),
					getResources().getDrawable(R.drawable.icon_userpic_bg));
			mtv_username.setText(userVO.getUsername());
			mtv_usertype.setText(userVO.getStrUserType());
			mbt_login_pwd.setText(R.string.userview_changepwd);
			mbt_reg_account.setText(R.string.userview_changeaccount);
		}
	}

	private UserVO getNowUser()
	{
		UserVO userVO = null;
		if (mContext instanceof BaseActivity)
			userVO = ((BaseActivity) mContext).getApplicationSet().getUserVO();
		return userVO;
	}

	@Override
	public void reflashData()
	{
	}

	@Override
	public void initContentView()
	{
		if (miv_userpic == null)
		{
			View v = LayoutInflater.from(mContext).inflate(R.layout.view_user,
					mll_content, false);
			setContentView(v);
			miv_userpic = (ImageView) v.findViewById(R.id.userview_userpic_iv);
			mtv_username = (TextView) v.findViewById(R.id.userview_username_tv);
			mtv_usertype = (TextView) v.findViewById(R.id.userview_usertype_tv);
			mbt_login_pwd = (Button) v.findViewById(R.id.userview_login_pwd_bt);
			mbt_reg_account = (Button) v
					.findViewById(R.id.userview_reg_account_bt);
			mbt_login_pwd.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					onLogin_PwdClick(v);
				}
			});
			mbt_reg_account.setOnClickListener(new OnClickListener()
			{	
				@Override
				public void onClick(View v)
				{
					onReg_AccountClick(v);
				}
			});
		}
	}

	/**
	 * ע����л��ʺŰ�ť�¼�
	 * 
	 * @param v
	 */
	public void onReg_AccountClick(View v)
	{
		UserVO userVO = getNowUser();
		if (userVO == null)
			// ע��
			((BaseActivity) mContext).runBrowser(getResources().getString(
					R.string.url_reg_progress));
		else
			changeAccount();
	}

	/**
	 * �л��ʺ�
	 */
	private void changeAccount()
	{
		BaseDialog dialog = new BaseDialog(mContext,
				BaseDialog.DIALOG_TYPE_TWOBTN);
		dialog.setTitle(R.string.userview_changeaccount);
		dialog.setMessage(R.string.userview_changeaccount_hit);
		dialog.setBtnText(BaseDialog.BTN_TYPE_LEFT, R.string.dl_btn_confirm);
		dialog.setOnDialogClickListener(new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				BaseActivity activity = (BaseActivity) mContext;
				if (activity == null)
				{
					dialog.dismiss();
					return;
				}
				activity.getApplicationSet().setUserVO(null, true);
				setShowUserInfo();
				activity.runActivity(false, UserLoginActivity.class);
			}
		}, BaseDialog.BTN_TYPE_LEFT);
		dialog.setBtnText(BaseDialog.BTN_TYPE_RIGHT, R.string.dl_btn_cancel);
		dialog.show();
	}

	/**
	 * ��¼���޸������¼���ť
	 * 
	 * @param v
	 */
	public void onLogin_PwdClick(View v)
	{
		// TODO
		UserVO userVO = getNowUser();
		if (userVO == null)
		{
			// ��¼
			((BaseActivity) mContext).runActivity(false,
					UserLoginActivity.class);
		} else
		{
			// �޸�����
			((BaseActivity) mContext).runActivity(false,
					UserChangePwdActivity.class);
		}
	}

	@Override
	public void reflashContentView()
	{
	}

	@Override
	protected void initNavigation(View v)
	{
		// ������ʾ����Ŀ��
		LinearLayout ll = (LinearLayout) findViewById(R.id.base_subchannel_ll);
		ll.setVisibility(View.GONE);
	}

	@Override
	public void searchAction(EditText et)
	{
	}

}
