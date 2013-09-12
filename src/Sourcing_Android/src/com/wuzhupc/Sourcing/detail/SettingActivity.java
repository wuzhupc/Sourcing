package com.wuzhupc.Sourcing.detail;

import java.util.Map;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.dialog.BaseDialog;
import com.wuzhupc.Sourcing.vo.ClientVerVO;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.SettingUtil;
import com.wuzhupc.utils.json.JsonParser;
import com.wuzhupc.services.ClientJsonService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;

public class SettingActivity extends BaseActivity implements
		View.OnClickListener
{

	/**
	 * 字体选择对话框
	 */
	private static final int CINT_DIALOG_FONTSIZESETTING = 1;

	private TextView mtv_fontsizeinfo;
	private CheckBox mcb_push;

	@Override
	protected void initDataContent()
	{
	}

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_setting);
		int index = SettingUtil.getNewsFontSizeIndex(this);
		mtv_fontsizeinfo = (TextView)findViewById(R.id.setting_fontsize_info_tv);
		mtv_fontsizeinfo.setText("当前字体为："
				+ SettingUtil.getNewsFontSizeDesc(index));
		findViewById(R.id.setting_about_ll).setOnClickListener(this);
		findViewById(R.id.setting_update_ll).setOnClickListener(this);
		findViewById(R.id.setting_clearcache_ll).setOnClickListener(this);
		findViewById(R.id.setting_fontsize_ll).setOnClickListener(this);

		mcb_push = (CheckBox) findViewById(R.id.setting_push_checkbox);
		mcb_push.setChecked(SettingUtil.getPushService(SettingActivity.this));
		mcb_push.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked)
			{
				SettingUtil.setPushService(SettingActivity.this, isChecked);
//				if (isChecked)
//				{
//					startService(new Intent(SettingActivity.this,
//							PushService.class));
//				} else
//				{
//					stopService(new Intent(SettingActivity.this,
//							PushService.class));
//				}
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		Dialog dialog = null;
		Builder builder = new AlertDialog.Builder(this);
		switch (id)
		{
		case CINT_DIALOG_FONTSIZESETTING:
			builder.setTitle("正文字号");
			final ChoiceOnClickListener choiceListener = new ChoiceOnClickListener();
			builder.setSingleChoiceItems(SettingUtil.NewsFontSizesDesc, SettingUtil.getNewsFontSizeIndex(SettingActivity.this),
					choiceListener);
			DialogInterface.OnClickListener btnListener = new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialogInterface, int which)
				{
					if (choiceListener.getClick())
					{
						int index = choiceListener.getWhich();
						SettingUtil.setNewsFontSize(SettingActivity.this,
							index);
						mtv_fontsizeinfo.setText("当前字体为："
							+ SettingUtil.getNewsFontSizeDesc(index));
					}
				}
			};
			builder.setPositiveButton("确定", btnListener);
			dialog = builder.create();
			break;
		}
		return dialog;
	}

	/**
	 * 显示关于对话框
	 */
	private void showAboutDialog()
	{
		runActivity(false, AboutActivity.class);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.setting_about_ll:
			showAboutDialog();
			break;
		case R.id.setting_clearcache_ll:
			clearCache();
			break;
		case R.id.setting_update_ll:
			checkUpdate();
			break;
		case R.id.setting_fontsize_ll:
			showDialog(CINT_DIALOG_FONTSIZESETTING);
			break;
		}
	}
	
	private void checkUpdate()
	{
		ClientJsonService clientService = new ClientJsonService(this);
		clientService.checkClientUpdate(0l,SettingUtil.getClientVersion(this), new IBaseReceiver() {
			
			@Override
			public void receiveCompleted(boolean isSuc, String content) {
				if (isSuc) 
				{
					ClientVerVO mClientVerVO = new ClientVerVO();
					ResponseVO respVO = new ResponseVO();
					Map<String, String> value=JsonParser.parseJsonToMap(content,respVO);
					if(value!=null&&!value.isEmpty())
					{
						mClientVerVO.setClientver(SettingUtil.getClientVersion(SettingActivity.this));
						mClientVerVO.setLastver(value.get("lastver"));
						mClientVerVO.setLastverurl(value.get("lastverurl"));
						mClientVerVO.setFilesize(value.get("filesize"));
						mClientVerVO.setForceupdate(value.get("forceupdate"));
						mClientVerVO.setUpdatelog(value.get("updatelog"));
					}
					completeGetVersionInfo(mClientVerVO);
				}
				else
				{
					completeGetVersionInfo(null);
				}
			}
		});
	}
	private void completeGetVersionInfo(ClientVerVO mClientVerVO)
	{
		//判断版本更新信息
		if(mClientVerVO==null||!mClientVerVO.hasUpdate())
		{
			hitCloseApplication("当前已经是最新版本!",false);
			return;
		}
		//显示更新信息
		showUpdateDialog(mClientVerVO);
	}
	
	/**
	 * 显示更新对话框
	 */
	private void showUpdateDialog(final ClientVerVO mClientVerVO)
	{
		BaseDialog dialog = new BaseDialog(SettingActivity.this,BaseDialog.DIALOG_TYPE_TWOBTN);
		dialog.setTitle(String.format(this.getResources().getString(mClientVerVO.getForceupdate()?R.string.welcome_update_title_ex:R.string.welcome_update_title),mClientVerVO.getLastver()));
		dialog.setMessage(
				Html.fromHtml(
					String.format(this.getResources().getString(mClientVerVO.getForceupdate()?R.string.welcome_update_content_ex:R.string.welcome_update_content),mClientVerVO.getUpdatelog())));
		dialog.setBtnText(BaseDialog.BTN_TYPE_LEFT, R.string.welcome_update_btn);
		dialog.setOnDialogClickListener(new android.content.DialogInterface.OnClickListener() { 
            @Override 
            public void onClick(DialogInterface dialog, int which) 
            { 
                dialog.dismiss();
                runBrowser(mClientVerVO.getLastverurl());
                System.exit(0);
            } 
        }, BaseDialog.BTN_TYPE_LEFT);
		dialog.setBtnText(BaseDialog.BTN_TYPE_RIGHT,mClientVerVO.getForceupdate()?R.string.dl_btn_quit:R.string.dl_btn_cancel);
		dialog.setOnDialogClickListener(new android.content.DialogInterface.OnClickListener() { 
            @Override 
            public void onClick(DialogInterface dialog, int which) 
            { 
                dialog.dismiss(); 
                if(mClientVerVO.getForceupdate())
                {
                   System.exit(0);
                   return;
                }
            } 
        }, BaseDialog.BTN_TYPE_RIGHT);
		dialog.show();
	}

	private void clearCache()
	{
		AlertDialog.Builder adb = new AlertDialog.Builder(
				SettingActivity.this);
		adb.setTitle("提示");
		adb.setMessage("你确定清除缓存数据吗？");
		adb.setPositiveButton("确定", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				ProgressDialog pdialog = ProgressDialog.show(
						SettingActivity.this, null, "清除缓存数据中，请稍候...",
						true);

				Boolean bclearsucess = true;
				String[] clearfilepaths = { Constants.CSTR_DATASTOREDIR + Constants.CSTR_DETAIL_DIR,
						Constants.CSTR_DATASTOREDIR + Constants.CSTR_IMAGECACHE_DIR };
				for (int i = 0; i < clearfilepaths.length; i++)
				{
					if (!FileUtil.delFolder(clearfilepaths[i]))
						bclearsucess = false;
				}
				if (bclearsucess)
				{
					displayToast("清除缓存成功！");
				} else
				{
					displayToast("清除缓存失败！");
				}
				pdialog.dismiss();
			}
		});
		adb.setNegativeButton("取消", null);
		adb.show();
	}

	private class ChoiceOnClickListener implements
			DialogInterface.OnClickListener
	{

		private int which = 0;
		private boolean isClick = false;

		@Override
		public void onClick(DialogInterface dialogInterface, int which)
		{
			this.which = which;
			isClick = true;
		}

		public int getWhich()
		{
			return which;
		}

		public boolean getClick()
		{
			return isClick;
		}
	}
}
