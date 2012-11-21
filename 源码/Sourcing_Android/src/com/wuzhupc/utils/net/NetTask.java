package com.wuzhupc.utils.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;

public class NetTask extends AsyncTask<String, Integer, String> {

	// ������
	private ProgressDialog pdialog;

	// ������context���ʾҪ��ʾ������
	private Context context;

	// �Ƿ�����ȡ��
	private boolean canCancel;
	
	// ��ʾ������
	private boolean showProgress;

	// ����������
	private String progressTitle;
	
	// ����������
	private String progressContent;

	// �����URL��ַ
	private String url;

	// �������
	private Hashtable para;

	// ҳ�����
	private String pageCode;

	// ����ʽ 0.get��ʽ 1.post��ʽ
	private int method;

	// ������ɻص��ӿ�
	private INetComplete completeController;

	// ������̽��Ȼص��ӿ�
	private INetProcessing processingController;

	// ���������Ƿ�ɹ� trueΪ���� falseΪ
	private boolean isSuccess = false;

	/**
	 * ���췽��
	 * 
	 * @param pageCode
	 *            �����ҳ�������utf-8,gbk��
	 * @param completeController
	 *            ��������Ļص��ӿ�
	 */
	public NetTask(String pageCode, INetComplete completeController) {
		this.pageCode = pageCode;
		this.completeController = completeController;
	}

	/**
	 * ���췽��
	 * 
	 * @param para
	 *            URL����hashtable
	 * @param pageCode
	 *            ҳ�����
	 * @param completeController
	 *            ��������ص��ӿ�
	 */
	public NetTask(Hashtable para, String pageCode,
			INetComplete completeController) {
		this(pageCode, completeController);

		this.para = para;
	}

	public NetTask(Hashtable para, String pageCode, int method,
			INetComplete completeController) {
		this(para, pageCode, completeController);

		this.method = method;
	}

	/**
	 * ��ʾ������
	 */
	private void displayProcessBar() {

		// ������context���ʾҪ��ʾ������
		if (context == null || !showProgress)
			return;
		
		if (StringUtil.isEmpty(progressContent)) {
			progressContent = context.getString(R.string.dl_msg_receiving_data);
		}
		
		pdialog = ProgressDialog.show(context, progressTitle, progressContent, true, true, new OnCancelListener() {
			
			@Override
			public void onCancel(DialogInterface dialog) {
				dialog.cancel();
				NetTask.this.cancel(true);
			}
		});
	}

	/**
	 * ��ִ�� ����ֵ���� �ɼ̳��ж���
	 */
	@Override
	protected String doInBackground(String... params) {
		/*********************** ����ר�� *********************************************/
//		try {
//			FileInputStream fis = ((BaseActivity) context).openFileInput("newslist");
//			
//			byte[] buf = new byte[1024];
//			int len = 0;
//			while ((len = fis.read(buf)) != -1) {
//				
//			}
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (Exception e) {
//			// 
//			e.printStackTrace();
//		}
//		InputStream in;
//		String content = null;
//		try {
//			String file_name = params[0].substring(params[0].indexOf("_")+1);
//			in = ((BaseActivity) context).getAssets().open(file_name+".xml");
//			BufferedReader rd = new BufferedReader(new InputStreamReader(in, pageCode));
//			String tempLine = rd.readLine();
//			StringBuffer temp = new StringBuffer();
//			String crlf = System.getProperty("line.separator");
//			while (tempLine != null) {
//				temp.append(tempLine);
//				temp.append(crlf);
//				tempLine = rd.readLine();
//			}
//			content = temp.toString();
//			rd.close();
//			in.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		isSuccess = true;
//		
//		return content;
		/**************************************************************************************/
		
		this.url = params[0];
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer sb = new StringBuffer();
			for (Iterator iter = para.entrySet().iterator(); iter.hasNext();) {
				Entry element = (Entry) iter.next();
				sb.append(element.getKey().toString());
				sb.append("=");
				sb.append(URLEncoder.encode(element.getValue().toString(), pageCode));
				sb.append("&");
			}
			if (sb.length() > 0) {
				sb = sb.deleteCharAt(sb.length() - 1);
			}
			
			URL url = new URL(this.url);
			url_con = (HttpURLConnection) url.openConnection();
			
			if (method == Constants.METHOD_GET) {
				url_con.setRequestMethod("GET");
			}
			else if (method == Constants.METHOD_POST) {
				url_con.setRequestMethod("POST");
			}
			
			url_con.setConnectTimeout(Constants.CONNECT_TIME_OUT*1000);// ���ӳ�ʱ
			url_con.setReadTimeout(Constants.READ_TIME_OUT*1000);// ��������ʱ
			url_con.setDoOutput(true);
			
			byte[] b = sb.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, pageCode));
			String tempLine = rd.readLine();
			StringBuffer temp = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				temp.append(tempLine);
				temp.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = temp.toString();
			rd.close();
			in.close();
				
			/****************** Base64���롢��ѹ *******************/ 
				
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			if (pdialog != null)
				pdialog.dismiss();
			e.printStackTrace();
			responseContent = e.toString();
		}
		
		return responseContent;
	}

	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	/**
	 * ����ִ�����
	 */
	@Override
	protected void onPostExecute(String result) {
		onProgressUpdate(100);
		if (pdialog != null)
			pdialog.dismiss();
		completeController.complete(isSuccess, result);

	}

	/**
	 * ����ִ��ǰִ��
	 */
	@Override
	protected void onPreExecute() {
		// ��ʾ������
		displayProcessBar();

		onProgressUpdate(0);
	}

	/**
	 * ���ȸ���
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		if (pdialog != null)
			pdialog.setProgress(values[0]);
		if (processingController != null)
			processingController.processing(values[0]);
	}

	/**
	 * ׼��������URL��ַ
	 */
	public void prepUrl() {
		// url Ԥ����
		if (para != null) {
			if (url.indexOf("?") == -1)
				url = url + "?";
			for (Iterator iter = para.entrySet().iterator(); iter.hasNext();) {
				Entry element = (Entry) iter.next();
				url = url + element.getKey().toString() + "="
						+ element.getValue().toString() + "&";
			}
			if (url.indexOf("&") != -1)
				url = url.substring(0, url.length() - 1);
		}
	}
	
	/**
	 * ִ���ϱ�����
	 * @param strurl����ַ
	 * @author wuzhu 20110608
	 * @return �����ϱ��������������򷵻ش���ԭ�򣬿ɻ�ȡgetIsSuccess()�ж�ִ���Ƿ�ɹ�
	 */
	public  String uploadInfo(String strurl) {
		this.url =strurl;
		HttpURLConnection url_con = null;
		String responseContent = null;
		try {
			StringBuffer sb = new StringBuffer();
			for (Iterator iter = para.entrySet().iterator(); iter.hasNext();) {
				Entry element = (Entry) iter.next();
				sb.append(element.getKey().toString());
				sb.append("=");
				sb.append(URLEncoder.encode(element.getValue().toString(), pageCode));
				sb.append("&");
			}
			if (sb.length() > 0) {
				sb = sb.deleteCharAt(sb.length() - 1);
			}
			
			URL url = new URL(this.url);
			url_con = (HttpURLConnection) url.openConnection();
			
			if (method == Constants.METHOD_GET) {
				url_con.setRequestMethod("GET");
			}
			else if (method == Constants.METHOD_POST) {
				url_con.setRequestMethod("POST");
			}
			
			url_con.setConnectTimeout(Constants.CONNECT_TIME_OUT*1000);// ���ӳ�ʱ
			url_con.setReadTimeout(Constants.READ_TIME_OUT*1000);// ��������ʱ
			url_con.setDoOutput(true);
			
			byte[] b = sb.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in, pageCode));
			String tempLine = rd.readLine();
			StringBuffer temp = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null) {
				temp.append(tempLine);
				temp.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = temp.toString();
			rd.close();
			in.close();
				
			/****************** Base64���롢��ѹ *******************/
			//Log.d("NetTask", "response:\n"+responseContent);
//			s = StringUtil.decompress(StringUtil.decode(s));
//			Log.i("NetTask", "__________s = "+s);
				
			isSuccess = true;
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			responseContent = e.toString();
		}
		
		return responseContent;
	}
	
	
	/**
	 * ִ���ϱ��ļ�����
	 * 
	 * @param strurl
	 *            ����ַ
	 * @author wuzhu 20110811
	 * @return �����ϱ��������������򷵻ش���ԭ�򣬿ɻ�ȡgetIsSuccess()�ж�ִ���Ƿ�ɹ�
	 */
	public String uploadFile(String strurl, String filename)
	{
		this.url = strurl;
		HttpURLConnection url_con = null;
		String responseContent = null;
		try
		{

			File file = new File(filename);

			if (!file.exists())
			{
				isSuccess = false;
				return "�ļ�������!";
			}

			URL url = new URL(this.url);
			url_con = (HttpURLConnection) url.openConnection();

			url_con.setRequestMethod("POST");

			//�����ļ�����
			url_con.setRequestProperty("file-format",
					FileUtil.getFileExtension(filename));
			url_con.setRequestProperty("Content-type", "application/octet-stream");
			url_con.setRequestProperty("Content-Length",String.valueOf(file.length()));
			url_con.setRequestProperty("Connection", "Keep-Alive");
			url_con.setConnectTimeout(Constants.CONNECT_TIME_OUT * 1000);// ���ӳ�ʱ
			url_con.setReadTimeout(Constants.READ_TIME_OUT * 1000);// ��������ʱ
			url_con.setDoOutput(true);

			DataOutputStream ds = new DataOutputStream(
					url_con.getOutputStream());
			FileInputStream fStream = new FileInputStream(file);

			/* ����ÿ��д��1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			int length = -1;
			/* ���ļ���ȡ������������ */
			while ((length = fStream.read(buffer)) != -1)
			{
				/* ������д��DataOutputStream�� */
				ds.write(buffer, 0, length);
			}
			fStream.close();
			ds.flush();

			/**
			 ObjectInputStream in = new ObjectInputStream(url_con.getInputStream());
   
   responseContent = (String) in.readObject();
   in.close();
			 */
			InputStream in = url_con.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(in,
					pageCode));
			String tempLine = rd.readLine();
			StringBuffer temp = new StringBuffer();
			String crlf = System.getProperty("line.separator");
			while (tempLine != null)
			{
				temp.append(tempLine);
				temp.append(crlf);
				tempLine = rd.readLine();
			}
			responseContent = temp.toString();
			rd.close();
			in.close();

			/****************** Base64���롢��ѹ *******************/
			//Log.d("NetTask", "response:\n" + responseContent);
			
			//�����ʶ
			if(StringUtil.isEmpty(responseContent))
			{
				isSuccess=false;
				return "���������޷��ؽ����";
			}			
			//��Ҫ�Է��ر��Ľ��н���
			ResponseVO vo=new ResponseVO();
			Map<String, String> vmap= JsonParser.parseJsonToMap(responseContent, vo);
			if(vo.getCode()==ResponseVO.RESPONSE_CODE_SUCESS)
			{
				isSuccess = true;
				responseContent=vmap.get("videoUrl");
			}
			else
			{
				isSuccess=false;
				responseContent=vo.getMsg();
			}
		} catch (Exception e)
		{
			isSuccess = false;
			e.printStackTrace();
			responseContent = e.toString();
		}

		return responseContent;
	}
	
	/**
	 * ��ȡִ�н���Ƿ�ɹ�
	 * @return
	 */
	public Boolean getIsSuccess()
	{
		return isSuccess;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public INetProcessing getProcessingController() {
		return processingController;
	}

	public void setProcessingController(INetProcessing processingController) {
		this.processingController = processingController;
	}

	public boolean isCanCancel() {
		return canCancel;
	}

	public void setCanCancel(boolean canCancel) {
		this.canCancel = canCancel;
	}

	public boolean isShowProgress() {
		return showProgress;
	}

	public void setShowProgress(boolean showProgress) {
		this.showProgress = showProgress;
	}

	public String getProgressTitle() {
		return progressTitle;
	}

	public void setProgressTitle(String progressTitle) {
		this.progressTitle = progressTitle;
	}

	public String getProgressContent() {
		return progressContent;
	}

	public void setProgressContent(String progressContent) {
		this.progressContent = progressContent;
	}

	/************************************ �ӿڶ��� **************************************/
	
	public interface INetComplete {
		
		/**
		 * �������󷵻ش���ӿ�
		 * ��������������ʱ�����ز����ô˽ӿ�
		 * ���к�������,isSuccess��ʾ�������
		 * �Ƿ�ɹ�.contentΪ�����������󷵻�
		 * ���ִ�
		 * by haicao 
		 * 2011-3-10
		 */
		public void complete(boolean isSuccess, String content);
		
	}
	
	public interface INetProcessing {
		/**
		 * ������ɰٷֱ�
		 * @param percent ���ذٷֱ�
		 */
		public void processing(int percent);
	}
}
