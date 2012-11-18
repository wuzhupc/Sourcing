package com.wuzhupc.ui;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.FileUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

/**
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-18 ����8:48:47
 */
public class BaseActivity extends Activity
{
	protected static final String  TAG=BaseActivity.class.getSimpleName();
	
	
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
		AlertDialog.Builder builder = new Builder(this); 
        builder.setMessage(str); 
        builder.setTitle(R.string.Base_hit_title); 
        builder.setPositiveButton(R.string.dl_btn_ok, 
                new android.content.DialogInterface.OnClickListener() { 
                    @Override 
                    public void onClick(DialogInterface dialog, int which) { 
                        dialog.dismiss(); 
                        if(isExit)
                           System.exit(0);
                    } 
                }); 
        builder.create().show();
	}
}
