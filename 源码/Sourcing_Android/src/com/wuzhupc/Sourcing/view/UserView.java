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
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 用户信息View
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-12-5 下午9:53:48
 */
public class UserView extends BaseView
{
	/**
	 * 用户头像
	 */
	private ImageView miv_userpic;
	private TextView mtv_username;
	private TextView mtv_usertype;
	private Button mbt_login_pwd;
	private Button mbt_reg_account;
	
	private LinearLayout mll_consult_info;
	private LinearLayout mll_audit_info;
	private LinearLayout mll_declare_info;
	private LinearLayout mll_notifier_info;
	
	private TextView mtv_consult_info;
	private TextView mtv_audit_info;
	private TextView mtv_declare_info;
	private TextView mtv_notifier_info;

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
	 * 根据现有用户情况设置UserView相关显示
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
			mll_audit_info.setVisibility(View.GONE);
			mll_consult_info.setVisibility(View.GONE);
			mll_declare_info.setVisibility(View.GONE);
			mll_notifier_info.setVisibility(View.GONE);
		} else
		{
			ImageService imageService = new ImageService(mContext);
			imageService.setThumbnail(miv_userpic, userVO.getUserphoto(),
					getResources().getDrawable(R.drawable.icon_userpic_bg));
			mtv_username.setText(userVO.getUsername());
			mtv_usertype.setText(userVO.getStrUserType());
			mbt_login_pwd.setText(R.string.userview_changepwd);
			mbt_reg_account.setText(R.string.userview_changeaccount);
			
			mtv_audit_info.setText(Html.fromHtml(String.format(getResources().getString(R.string.userview_audit_info), userVO.getAuditcount(),userVO.getAllauditcount())));
			mtv_consult_info.setText(Html.fromHtml(String.format(getResources().getString(R.string.userview_consult_info), userVO.getConsultcount(),userVO.getAllconsultcount())));
			mtv_declare_info.setText(Html.fromHtml(String.format(getResources().getString(R.string.userview_declare_info), userVO.getDeclarecount(),userVO.getAlldeclarecount())));
			mtv_notifier_info.setText(Html.fromHtml(String.format(getResources().getString(R.string.userview_notifier_info), userVO.getNotifiercount(),userVO.getAllnotifiercount())));

			mll_audit_info.setVisibility(userVO.hasAudit()?View.VISIBLE:View.GONE);
			mll_consult_info.setVisibility(userVO.hasConsult()?View.VISIBLE:View.GONE);
			mll_declare_info.setVisibility(userVO.hasDeclare()?View.VISIBLE:View.GONE);
			mll_notifier_info.setVisibility(userVO.hasNotifier()?View.VISIBLE:View.GONE);
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
			mll_audit_info=(LinearLayout)v.findViewById(R.id.userview_audit_info_ll);
			mll_audit_info.setOnClickListener(new OnClickListener()
			{	
				@Override
				public void onClick(View v)
				{
					onAuditInfoClick(v);
				}
			});
			mll_consult_info=(LinearLayout)v.findViewById(R.id.userview_consult_info_ll);
			mll_consult_info.setOnClickListener(new OnClickListener()
			{	
				@Override
				public void onClick(View v)
				{
					onConsultInfoClick(v);
				}
			});
			mll_declare_info=(LinearLayout)v.findViewById(R.id.userview_declare_info_ll);
			mll_declare_info.setOnClickListener(new OnClickListener()
			{	
				@Override
				public void onClick(View v)
				{
					onDeclareInfoClick(v);
				}
			});
			mll_notifier_info=(LinearLayout)v.findViewById(R.id.userview_notifier_info_ll);
			mll_notifier_info.setOnClickListener(new OnClickListener()
			{	
				@Override
				public void onClick(View v)
				{
					onNotifierInfoClick(v);
				}
			});
			mtv_audit_info=(TextView)v.findViewById(R.id.userview_audit_info_tv);
			mtv_consult_info=(TextView)v.findViewById(R.id.userview_consult_info_tv);
			mtv_declare_info=(TextView)v.findViewById(R.id.userview_declare_info_tv);
			mtv_notifier_info=(TextView)v.findViewById(R.id.userview_notifier_info_tv);
			
		}
	}
	
	/**
	 * 咨询信息点击
	 * @param v
	 */
	public void onConsultInfoClick(View v)
	{
		//TODO 咨询信息点击
	}
	
	/**
	 * 审核结果点击
	 * @param v
	 */
	public void onAuditInfoClick(View v)
	{
		//TODO 审核结果点击
	}
	
	/**
	 * 申报进度点击
	 * @param v
	 */
	public void onDeclareInfoClick(View v)
	{
		//TODO 申报进度点击
	}
	
	/**
	 * 通知提醒点击
	 * @param v
	 */
	public void onNotifierInfoClick(View v)
	{
		//TODO 通知提醒点击
	}

	/**
	 * 注册或切换帐号按钮事件
	 * 
	 * @param v
	 */
	public void onReg_AccountClick(View v)
	{
		UserVO userVO = getNowUser();
		if (userVO == null)
			// 注册
			((BaseActivity) mContext).runBrowser(getResources().getString(
					R.string.url_reg_progress));
		else
			changeAccount();
	}

	/**
	 * 切换帐号
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
				dialog.dismiss();
			}
		}, BaseDialog.BTN_TYPE_LEFT);
		dialog.setBtnText(BaseDialog.BTN_TYPE_RIGHT, R.string.dl_btn_cancel);
		dialog.show();
	}

	/**
	 * 登录或修改密码事件按钮
	 * 
	 * @param v
	 */
	public void onLogin_PwdClick(View v)
	{
		// TODO
		UserVO userVO = getNowUser();
		if (userVO == null)
		{
			// 登录
			((BaseActivity) mContext).runActivity(false,
					UserLoginActivity.class);
		} else
		{
			// 修改密码
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
		// 不用显示子栏目栏
		LinearLayout ll = (LinearLayout) findViewById(R.id.base_subchannel_ll);
		ll.setVisibility(View.GONE);
	}

	@Override
	public void searchAction(EditText et)
	{
	}

}
