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
 * ͼƬ�첽������
 */
public class ImageTask extends AsyncTask<String, Integer, File>
{

	private Context mContext;

	private String mUrl; // ���ص�ַ
	private String mPath; // �洢·��

	private boolean isStop; // ȡ������
	private boolean canCancel; // �Ƿ����ȡ��
	private boolean canShowErrMsg; // �Ƿ���ʾ������ʾ
	private boolean canShowProgressDialog; // �Ƿ���ʾ���ȶԻ���

	private String mDlTitle; // ���ȶԻ������
	private String mDlMsg; // ���ȶԻ�������
	private ProgressDialog pDialog; // ���ؽ�����ʾ��

	private IImageDownloader imageDownloader; // ������ɽӿ�
	private IProgressController progressController; // ���ؽ��Ƚӿ�

	/**
	 * ������ɽӿ�
	 */
	private IImageLoader mImageLoader;

	/**
	 * ������ɺ��ͼ
	 */
	private Bitmap mBitmap;

	/**
	 * ����ͼ�����
	 */
	private int mMaxWidth;
	/**
	 * ����ͼ���߶�
	 */
	private int mMaxHeight;
	
	/**
	 * �Ƿ������������
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
	 * ����ͼ
	 * 
	 * @param ctx
	 * @param imgUrl
	 *            Զ�̼��ص�ַ
	 * @param imgPath
	 *            �����ļ���ַ����������ļ�����ʱ����imgUrl��Ϊ������������ɺ��ټ���
	 * @param maxwidth
	 *            ���������
	 * @param maxheight
	 *            �������ڸ߶�
	 * @param imgLoader
	 *            ������ɽӿ�
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
	 * ��ʾ���ȶԻ���
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
	 * ����ȡ������
	 * 
	 * @param stop
	 */
	public void setStop(boolean stop)
	{
		isStop = stop;
	}

	/**
	 * �����ܷ�ȡ������
	 * 
	 * @param canCancel
	 */
	public void setCanCancel(boolean canCancel)
	{
		this.canCancel = canCancel;
	}

	/**
	 * ���ó�����ʾ���
	 * 
	 * @param canShowErrMsg
	 */
	public void setCanShowErrMsg(boolean canShowErrMsg)
	{
		this.canShowErrMsg = canShowErrMsg;
	}

	/**
	 * ���ý�����ʾ���
	 * 
	 * @param canShowProgressDialog
	 */
	public void setCanShowProgressDialog(boolean canShowProgressDialog)
	{
		this.canShowProgressDialog = canShowProgressDialog;
	}

	/**
	 * ���ý�����ʾ�����
	 * 
	 * @param mDlTitle
	 */
	public void setmDlTitle(String mDlTitle)
	{
		this.mDlTitle = mDlTitle;
	}

	/**
	 * ���ý�����ʾ������
	 * 
	 * @param mDlMsg
	 */
	public void setmDlMsg(String mDlMsg)
	{
		this.mDlMsg = mDlMsg;
	}

	/**
	 * �������ؽ��Ƚӿ�
	 * 
	 * @param progressController
	 */
	public void setProgressController(IProgressController progressController)
	{
		this.progressController = progressController;
	}

	/**
	 * ��ִ̨����������
	 */
	@Override
	protected File doInBackground(String... params)
	{
		if (params != null && params.length > 0)
		{
			mUrl = params[0];
		}
		
		//���жϱ����ļ��Ƿ����
		File localFile = new File(mPath);
		if(!mMustReDown&&localFile.exists())
		{
			if(mImageLoader!=null)
			{
				mBitmap=ImageUtil.getBitmapFromFile(mPath, mMaxWidth,
						mMaxHeight);
				//���سɹ���ֱ�ӷ��أ�ʧ������������
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
			{ // �ж����أ�ɾ�����ظ���
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
	 * ��������ǰ��ϵͳ����
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
	 * ����ִ�й�����ͨ�� publishProgress (Progress... values) ��������
	 */
	@Override
	protected void onProgressUpdate(Integer... values)
	{
		if(progressController!=null)
		progressController.progressUpdate(values[0]);
	}

	/**
	 * �����������ϵͳ����
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
	 * ���ʹ�����Ϣ��ʾ��
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
	 * ��ʾ����Ի���
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

	/******************************************** �ӿ� *************************************************/

	/**
	 * ������ɽӿ�
	 */
	public interface IImageDownloader
	{

		public void downloadComplete(File file);
	}

	/**
	 * ������ɽӿ�
	 */
	public interface IImageLoader
	{
		public void loadComplete(File file, Bitmap bm);
	}

	/**
	 * ���ؽ��Ƚӿ�
	 */
	public interface IProgressController
	{

		public void progressUpdate(int progress);
	}

}
