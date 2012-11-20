package com.wuzhupc.utils.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.dialog.BaseDialog;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.ImageUtil;
import com.wuzhupc.utils.StringUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

/**
 * 图片异步下载器
 */
public class ImageTask extends AsyncTask<String, Integer, File>
{

	private Context mContext;

	private String mUrl; // 下载地址
	private String mPath; // 存储路径

	private boolean isStop; // 取消下载
	private boolean canCancel; // 是否可以取消
	private boolean canShowErrMsg; // 是否显示错误提示
	private boolean canShowProgressDialog; // 是否显示进度对话框

	private String mDlTitle; // 进度对话框标题
	private String mDlMsg; // 进度对话框内容
	private ProgressDialog pDialog; // 下载进度提示框

	private IImageDownloader imageDownloader; // 下载完成接口
	private IProgressController progressController; // 下载进度接口

	/**
	 * 加载完成接口
	 */
	private IImageLoader mImageLoader;

	/**
	 * 加载完成后的图
	 */
	private Bitmap mBitmap;

	/**
	 * 加载图最大宽度
	 */
	private int mMaxWidth;
	/**
	 * 加载图最大高度
	 */
	private int mMaxHeight;
	
	/**
	 * 是否必须重新下载
	 */
	private Boolean mMustReDown=true;
	

	public ImageTask(Context ctx, String imgUrl, String imgPath,
			IImageDownloader imgDownloader)
	{

		this.mContext = ctx;

		this.mUrl = imgUrl;
		this.mPath = imgPath;
		this.imageDownloader = imgDownloader;

		isStop = false;
	}

	/**
	 * 加载图
	 * 
	 * @param ctx
	 * @param imgUrl
	 *            远程加载地址
	 * @param imgPath
	 *            本地文件地址，如果本地文件不存时，当imgUrl不为空则先下载完成后再加载
	 * @param maxwidth
	 *            加载最大宽度
	 * @param maxheight
	 *            加载最在高度
	 * @param imgLoader
	 *            加载完成接口
	 */
	public ImageTask(Context ctx, String imgUrl, String imgPath, int maxwidth,
			int maxheight, IImageLoader imgLoader)
	{
		this.mContext = ctx;

		this.mUrl = imgUrl;
		this.mPath = imgPath;
		this.mImageLoader = imgLoader;

		this.mMaxHeight = maxheight;
		this.mMaxWidth = maxwidth;
		isStop = false;
		mMustReDown=false;
	}
	


	/**
	 * 显示进度对话框
	 * 
	 * @param ctx
	 * @param title
	 * @param msg
	 */
	private void showProgressDialog(String title, String msg)
	{
		if (msg == null)
		{
			msg = mContext.getString(R.string.dl_msg_receiving_data);
		}

		pDialog = ProgressDialog.show(mContext, title, msg, true, true,
				new OnCancelListener()
				{

					@Override
					public void onCancel(DialogInterface dialog)
					{
						setStop(true); //
					}
				});
		pDialog.setCancelable(canCancel);
	}

	/**
	 * 设置取消下载
	 * 
	 * @param stop
	 */
	public void setStop(boolean stop)
	{
		isStop = stop;
	}

	/**
	 * 设置能否取消下载
	 * 
	 * @param canCancel
	 */
	public void setCanCancel(boolean canCancel)
	{
		this.canCancel = canCancel;
	}

	/**
	 * 设置出错提示与否
	 * 
	 * @param canShowErrMsg
	 */
	public void setCanShowErrMsg(boolean canShowErrMsg)
	{
		this.canShowErrMsg = canShowErrMsg;
	}

	/**
	 * 设置进度提示与否
	 * 
	 * @param canShowProgressDialog
	 */
	public void setCanShowProgressDialog(boolean canShowProgressDialog)
	{
		this.canShowProgressDialog = canShowProgressDialog;
	}

	/**
	 * 设置进度提示框标题
	 * 
	 * @param mDlTitle
	 */
	public void setmDlTitle(String mDlTitle)
	{
		this.mDlTitle = mDlTitle;
	}

	/**
	 * 设置进度提示框内容
	 * 
	 * @param mDlMsg
	 */
	public void setmDlMsg(String mDlMsg)
	{
		this.mDlMsg = mDlMsg;
	}

	/**
	 * 设置下载进度接口
	 * 
	 * @param progressController
	 */
	public void setProgressController(IProgressController progressController)
	{
		this.progressController = progressController;
	}

	/**
	 * 后台执行数据下载
	 */
	@Override
	protected File doInBackground(String... params)
	{
		if (params != null && params.length > 0)
		{
			mUrl = params[0];
		}
		
		//先判断本地文件是否存在
		File localFile = new File(mPath);
		if(!mMustReDown&&localFile.exists())
		{
			if(mImageLoader!=null)
			{
				mBitmap=ImageUtil.getBitmapFromFile(mPath, mMaxWidth,
						mMaxHeight);
				//加载成功则直接返回，失败则重新下载
				if(mBitmap!=null)
					return localFile;
			}
			else
				return localFile;
		}

		URL myURL;
		try
		{
			myURL = new URL(mUrl);
		} catch (MalformedURLException e)
		{
			sendErrorMsg(mContext.getString(R.string.prompt_network_url_error)
					+ "\t" + mUrl);
			return null;
		}

		HttpURLConnection conn;
		InputStream in;
		try
		{
			conn = (HttpURLConnection) myURL.openConnection();
			conn.setConnectTimeout(Constants.CONNECT_TIME_OUT * 1000);
			conn.setReadTimeout(Constants.READ_TIME_OUT * 1000);
			conn.connect();
			in = conn.getInputStream();
		} catch (IOException e)
		{
			sendErrorMsg(mContext.getString(R.string.prompt_network_error)
					+ "\t" + e.toString() + "\nerrCode:1001");
			return null;
		}

		if (in == null)
		{
			sendErrorMsg(mContext.getString(R.string.prompt_network_error)
					+ "\nerrCode:1002");
			return null;
		}

		try
		{
			String tmpFileName = mPath.concat(".").concat(
					Long.toString(System.currentTimeMillis()));
			File tmpFile = new File(tmpFileName);

			FileOutputStream fos = new FileOutputStream(tmpFile);

			byte buf[] = new byte[Constants.READ_DATA_LENGTH];
			//int count = 0;

			while (!isStop)
			{
				int numread = in.read(buf);
				if (numread <= 0)
				{
					break;
				}
				fos.write(buf, 0, numread);
				//count += numread;
			}

			fos.flush();
			fos.close();
			fos = null;
			in.close();
			in = null;
			conn = null;

			if (isStop)
			{ // 中断下载，删除下载副件
				if (tmpFile.exists())
				{
					tmpFile.delete();
				}
				return null;
			}

			if (!tmpFile.renameTo(localFile))
			{
				sendErrorMsg(mContext
						.getString(R.string.prompt_network_receiving_data_error)
						+ "\nerrCode:1003");
				return null;
			} else
			{
				if(mImageLoader!=null)
				{
					mBitmap=ImageUtil.getBitmapFromFile(mPath, mMaxWidth,
							mMaxHeight);
				}
				return localFile;
			}
		} catch (Exception e)
		{
			sendErrorMsg(mContext.getString(R.string.prompt_network_error)
					+ "\t" + e.toString() + "\nerrCode:1004");
			return null;
		}
	}

	/**
	 * 任务启动前由系统调用
	 */
	@Override
	protected void onPreExecute()
	{
		if(progressController!=null)
		   progressController.progressUpdate(-1);

		if (canShowProgressDialog)
		{
			showProgressDialog(mDlTitle, mDlMsg);
		}
	}

	/**
	 * 任务执行过程中通过 publishProgress (Progress... values) 方法调用
	 */
	@Override
	protected void onProgressUpdate(Integer... values)
	{
		if(progressController!=null)
		progressController.progressUpdate(values[0]);
	}

	/**
	 * 任务结束后由系统调用
	 */
	@Override
	protected void onPostExecute(File file)
	{
		if(progressController!=null)
		    progressController.progressUpdate(100);
		if (imageDownloader != null)
			imageDownloader.downloadComplete(file);

		if(mImageLoader!=null)
		{
			mImageLoader.loadComplete(file, mBitmap);
		}
		
		if (pDialog != null)
		{
			pDialog.dismiss();
			pDialog = null;
		}
	}

	/**
	 * 
	 */
	private Handler mHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			showErrorDialog(null, (String) msg.obj);

			super.handleMessage(msg);
		}

	};

	/**
	 * 发送错误信息提示框
	 * 
	 * @param errStr
	 */
	private void sendErrorMsg(String errStr)
	{
		if (!canShowErrMsg)
		{
			return;
		}

		Message msg = new Message();
		msg.obj = errStr;
		mHandler.sendMessage(msg);

		if (pDialog != null)
		{
			pDialog.dismiss();
			pDialog = null;
		}
	}

	/**
	 * 显示出错对话框
	 * 
	 * @param title
	 * @param errStr
	 */
	private void showErrorDialog(String title, String errStr)
	{
		if (StringUtil.isEmpty(errStr))
		{
			errStr = mContext.getString(R.string.prompt_network_error);
		}
		BaseDialog dialog = new BaseDialog(mContext, BaseDialog.DIALOG_TYPE_ONEBTN);
		dialog.setMessage(errStr);
		dialog.setHasTitle(true);
		dialog.setTitle(R.string.dl_title_prompt);
		
		dialog.setBtnText(BaseDialog.BTN_TYPE_LEFT, R.string.dl_btn_confirm);
		dialog.show();

//		Builder builder = new Builder(mContext)
//				.setTitle(R.string.dl_title_prompt).setMessage(errStr)
//				.setIcon(R.drawable.icon)
//				.setPositiveButton(R.string.dl_btn_confirm, null);
//		builder.create().show();
	}

	/******************************************** 接口 *************************************************/

	/**
	 * 下载完成接口
	 */
	public interface IImageDownloader
	{

		public void downloadComplete(File file);
	}

	/**
	 * 加载完成接口
	 */
	public interface IImageLoader
	{
		public void loadComplete(File file, Bitmap bm);
	}

	/**
	 * 下载进度接口
	 */
	public interface IProgressController
	{

		public void progressUpdate(int progress);
	}

}
