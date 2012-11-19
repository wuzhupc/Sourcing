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
 * @version ����ʱ�䣺2012-11-18 ����8:48:47
 */
public class BaseActivity extends Activity
{
	protected static final String  TAG=BaseActivity.class.getSimpleName();
	

	/**
	 * ���񷵻ؼ�
	 */
	private Boolean mCaptureBackKey = false;
	private OnKeyDownListener mKeyDownListener = null;

	/**
	 * �رճ���ʱ����ʾ��Ϣ
	 */
	private String mCloseApplicationAskMsg = "";
	
	/**
	 * ��黺���ļ����Ƿ����
	 */
	protected void checkCacheFolder()
	{
		//�жϴ洢���Ƿ����
		if(!FileUtil.hasSDCard())
		{
			//��ʾ
			hitCloseApplication(R.string.Base_hit_nosdcard);
			return;
		}
		//�ж���Դ�ļ��Ƿ���Դ���
		if(!FileUtil.isExistFolder(Constants.CSTR_DATASTOREDIR))
		{
			hitCloseApplication(R.string.Base_hit_createstorefolderfail);
			return;
		}
	}
	
	/**
	 * ��ʾ�˳�
	 * @param resid
	 */
	public void hitCloseApplication(int resid)
	{
		hitCloseApplication(getResources().getString(resid),true);
	}
	
	/**
	 * ��ʾ�˳�
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
	

	/** ���ǰ����¼� */
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

			// TODO ����Back����ֱ���˳� Ŀǰ������ ������Ч
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
	 * ѯ���Ƿ�ر�Ӧ�ó���
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
	 * ������һ��Activity
	 * 
	 * @param closeMe
	 *            �Ƿ�ص�����
	 * @param c
	 *            Ҫ������Activity
	 */
	public void runActivity(boolean closeMe, Class<?> c)
	{
		if (closeMe)
			this.finish();
		Intent intent = new Intent(this, c);
		startActivity(intent);
	}

	/**
	 * ������һ��Activity
	 * 
	 * @param closeMe
	 *            �Ƿ�ص�����
	 * @param intent
	 *            Ҫ������intent
	 */
	public void runIntent(boolean closeMe, Intent intent)
	{
		if (closeMe)
			this.finish();
		if (intent != null)
			startActivity(intent);
	}

	/**
	 * ����Ϊ�ɲ��񷵻ؼ�
	 * 
	 * @param bcature
	 */
	protected void setCatureBackKey(Boolean bcature)
	{
		mCaptureBackKey = bcature;
	}

	/**
	 * ���ùرճ���ʱ�˳�ʱ��ʾ��Ϣ
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
	 * ��ʾToast��ʾ��Ϣ
	 * 
	 * @param str
	 *            Ҫ��ʾ����ʾ��Ϣ
	 */
	public void displayToast(String str)
	{
		Toast toast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * ��ʾToast��ʾ��Ϣ
	 * 
	 * @param strresid
	 *            ��ʾ��Ϣ�ַ�����ԴID
	 */
	public void displayToast(int strresid)
	{
		displayToast(getResources().getString(strresid));
	}
	/**
	 * ��ȡȫ�ֱ���
	 * 
	 * @return
	 */
	public ApplicationSet getApplicationSet()
	{
		return (ApplicationSet) this.getApplicationContext();
	}
}
