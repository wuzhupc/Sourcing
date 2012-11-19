package com.wuzhupc.Sourcing;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.dialog.BaseDialog;
import com.wuzhupc.config.ApplicationSet;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.widget.OnKeyDownListener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

/**
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-18 下午8:48:47
 */
public class BaseActivity extends Activity
{
	protected static final String  TAG=BaseActivity.class.getSimpleName();
	

	/**
	 * 捕获返回键
	 */
	private Boolean mCaptureBackKey = false;
	private OnKeyDownListener mKeyDownListener = null;

	/**
	 * 关闭程序时的提示信息
	 */
	private String mCloseApplicationAskMsg = "";
	
	/**
	 * 检查缓存文件夹是否存在
	 */
	protected void checkCacheFolder()
	{
		//判断存储卡是否存在
		if(!FileUtil.hasSDCard())
		{
			//提示
			hitCloseApplication(R.string.Base_hit_nosdcard);
			return;
		}
		//判断资源文件是否可以创建
		if(!FileUtil.isExistFolder(Constants.CSTR_DATASTOREDIR))
		{
			hitCloseApplication(R.string.Base_hit_createstorefolderfail);
			return;
		}
	}
	
	/**
	 * 提示退出
	 * @param resid
	 */
	public void hitCloseApplication(int resid)
	{
		hitCloseApplication(getResources().getString(resid),true);
	}
	
	/**
	 * 提示退出
	 * @param str
	 */
	public void hitCloseApplication(String str,final boolean isExit)
	{
		BaseDialog dialog = new BaseDialog(BaseActivity.this, BaseDialog.DIALOG_TYPE_ONEBTN);
		dialog.setMessage(str);
		dialog.setBtnText(BaseDialog.BTN_TYPE_LEFT, R.string.dl_btn_ok);
		dialog.setHasTitle(true);
		dialog.setTitle(R.string.Base_hit_title);
		dialog.setOnDialogClickListener(new android.content.DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        dialog.dismiss(); 
                        if(isExit)
                           System.exit(0);
                    } 
                }, BaseDialog.BTN_TYPE_LEFT);
		dialog.show();
	}
	

	/** 覆盖按键事件 */
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (mCaptureBackKey)
		{
			if (mKeyDownListener != null)
				if (mKeyDownListener.onKeyDown(keyCode, event))
					return false;

			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
			{
				askCloseApplication();
				return false;
			}

			// TODO 长按Back键则直接退出 目前不作用 长按无效
			if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
					&& event.getRepeatCount() > 1)
			{
				this.finish();
				return false;
			}
		}
		return super.onKeyDown(keyCode, event);
	}


	/**
	 * 询问是否关闭应用程序
	 */
	public void askCloseApplication()
	{
		BaseDialog dialog = new BaseDialog(BaseActivity.this, BaseDialog.DIALOG_TYPE_TWOBTN);
		if (StringUtil.isEmpty(mCloseApplicationAskMsg))
			dialog.setMessage(R.string.Base_hit_defaskclosemsg);
		else
			dialog.setMessage(mCloseApplicationAskMsg);

		dialog.setHasTitle(true);
		dialog.setTitle(R.string.Base_hit_title);
		
		dialog.setBtnText(BaseDialog.BTN_TYPE_LEFT, R.string.dl_btn_ok);
		dialog.setOnDialogClickListener(new android.content.DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        	dialog.dismiss();
                           System.exit(0);
                    } 
                }, BaseDialog.BTN_TYPE_LEFT);

		dialog.setBtnText(BaseDialog.BTN_TYPE_RIGHT, R.string.dl_btn_back);
		dialog.show();
	}

	/**
	 * 启动另一个Activity
	 * 
	 * @param closeMe
	 *            是否关掉自身
	 * @param c
	 *            要启动的Activity
	 */
	public void runActivity(boolean closeMe, Class<?> c)
	{
		if (closeMe)
			this.finish();
		Intent intent = new Intent(this, c);
		startActivity(intent);
	}

	/**
	 * 启动另一个Activity
	 * 
	 * @param closeMe
	 *            是否关掉自身
	 * @param intent
	 *            要启动的intent
	 */
	public void runIntent(boolean closeMe, Intent intent)
	{
		if (closeMe)
			this.finish();
		if (intent != null)
			startActivity(intent);
	}

	/**
	 * 设置为可捕获返回键
	 * 
	 * @param bcature
	 */
	protected void setCatureBackKey(Boolean bcature)
	{
		mCaptureBackKey = bcature;
	}

	/**
	 * 设置关闭程序时退出时提示信息
	 * 
	 * @param msg
	 */
	public void setCloseApplicationAskMsg(String msg)
	{
		mCloseApplicationAskMsg = msg;
	}

	public String getCloseApplicationAskMsg()
	{
		return mCloseApplicationAskMsg;
	}


	/**
	 * 
	 * @param listener
	 *            the listener
	 */
	public void setKeyDownListener(OnKeyDownListener listener)
	{
		mKeyDownListener = listener;
	}


	/**
	 * 显示Toast提示信息
	 * 
	 * @param str
	 *            要显示的提示信息
	 */
	public void displayToast(String str)
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * 显示Toast提示信息
	 * 
	 * @param strresid
	 *            提示信息字符串资源ID
	 */
	public void displayToast(int strresid)
	{
		displayToast(getResources().getString(strresid));
	}
	/**
	 * 获取全局变量
	 * 
	 * @return
	 */
	public ApplicationSet getApplicationSet()
	{
		return (ApplicationSet) this.getApplicationContext();
	}
}
