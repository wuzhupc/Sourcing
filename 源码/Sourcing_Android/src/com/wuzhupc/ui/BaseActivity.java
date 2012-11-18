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
 * @version 创建时间：2012-11-18 下午8:48:47
 */
public class BaseActivity extends Activity
{
	protected static final String  TAG=BaseActivity.class.getSimpleName();
	
	
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
