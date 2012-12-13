package com.wuzhupc.Sourcing.detail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.SettingUtil;

public class SettingActivity extends BaseActivity implements
		View.OnClickListener
{

	/**
	 * ����ѡ��Ի���
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
		mtv_fontsizeinfo.setText("��ǰ����Ϊ��"
				+ SettingUtil.getNewsFontSizeDesc(index));
		findViewById(R.id.setting_about_ll).setOnClickListener(this);
		findViewById(R.id.setting_clearcache_ll).setOnClickListener(this);
		findViewById(R.id.setting_fontsize_ll).setOnClickListener(this);

		mcb_push = (CheckBox) findViewById(R.id.setting_push_checkbox);
		// TODO
//		if (MsgAlarmService.service != null)
//		{
//			mcb_push.setChecked(true);
//		}
//		mcb_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//		{
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView,
//					boolean isChecked)
//			{
//				if (isChecked)
//				{
//					startService(new Intent(SettingActivity.this,
//							MsgAlarmService.class));
//				} else
//				{
//					stopService(new Intent(SettingActivity.this,
//							MsgAlarmService.class));
//				}
//			}
//		});
	}

	@Override
	protected Dialog onCreateDialog(int id)
	{
		Dialog dialog = null;
		Builder builder = new AlertDialog.Builder(this);
		switch (id)
		{
		case CINT_DIALOG_FONTSIZESETTING:
			builder.setTitle("�����ֺ�");
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
						mtv_fontsizeinfo.setText("��ǰ����Ϊ��"
							+ SettingUtil.getNewsFontSizeDesc(index));
					}
				}
			};
			builder.setPositiveButton("ȷ��", btnListener);
			dialog = builder.create();
			break;
		}
		return dialog;
	}

	/**
	 * ��ʾ���ڶԻ���
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

		case R.id.setting_fontsize_ll:
			showDialog(CINT_DIALOG_FONTSIZESETTING);
			break;
		}
	}

	private void clearCache()
	{
		AlertDialog.Builder adb = new AlertDialog.Builder(
				SettingActivity.this);
		adb.setTitle("��ʾ");
		adb.setMessage("��ȷ���������������");
		adb.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		{

			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				ProgressDialog pdialog = ProgressDialog.show(
						SettingActivity.this, null, "������������У����Ժ�...",
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
					displayToast("�������ɹ���");
				} else
				{
					displayToast("�������ʧ�ܣ�");
				}
				pdialog.dismiss();
			}
		});
		adb.setNegativeButton("ȡ��", null);
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
