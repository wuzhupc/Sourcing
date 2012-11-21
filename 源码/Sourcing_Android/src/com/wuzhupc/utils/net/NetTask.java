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

	// 进度条
	private ProgressDialog pdialog;

	// 有设置context则表示要显示进度条
	private Context context;

	// 是否允许取消
	private boolean canCancel;
	
	// 显示进度条
	private boolean showProgress;

	// 进度条标题
	private String progressTitle;
	
	// 进度条内容
	private String progressContent;

	// 请求的URL地址
	private String url;

	// 请求参数
	private Hashtable para;

	// 页面编码
	private String pageCode;

	// 请求方式 0.get方式 1.post方式
	private int method;

	// 请求完成回调接口
	private INetComplete completeController;

	// 请求过程进度回调接口
	private INetProcessing processingController;

	// 网络请求是否成功 true为正常 false为
	private boolean isSuccess = false;

	/**
	 * 构造方法
	 * 
	 * @param pageCode
	 *            请求的页面编码如utf-8,gbk等
	 * @param completeController
	 *            请求结束的回调接口
	 */
	public NetTask(String pageCode, INetComplete completeController) {
		this.pageCode = pageCode;
		this.completeController = completeController;
	}

	/**
	 * 构造方法
	 * 
	 * @param para
	 *            URL参数hashtable
	 * @param pageCode
	 *            页面编码
	 * @param completeController
	 *            请求结束回调接口
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
	 * 显示进度条
	 */
	private void displayProcessBar() {

		// 有设置context则表示要显示进度条
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
	 * 主执行 返回值类型 由继承中定义
	 */
	@Override
	protected String doInBackground(String... params) {
		/*********************** 测试专用 *********************************************/
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
			
			url_con.setConnectTimeout(Constants.CONNECT_TIME_OUT*1000);// 连接超时
			url_con.setReadTimeout(Constants.READ_TIME_OUT*1000);// 读操作超时
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
				
			/****************** Base64解码、解压 *******************/ 
				
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
	 * 任务执行完毕
	 */
	@Override
	protected void onPostExecute(String result) {
		onProgressUpdate(100);
		if (pdialog != null)
			pdialog.dismiss();
		completeController.complete(isSuccess, result);

	}

	/**
	 * 任务执行前执行
	 */
	@Override
	protected void onPreExecute() {
		// 显示进度条
		displayProcessBar();

		onProgressUpdate(0);
	}

	/**
	 * 进度更新
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		if (pdialog != null)
			pdialog.setProgress(values[0]);
		if (processingController != null)
			processingController.processing(values[0]);
	}

	/**
	 * 准备完整的URL地址
	 */
	public void prepUrl() {
		// url 预处理
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
	 * 执行上报操作
	 * @param strurl　地址
	 * @author wuzhu 20110608
	 * @return 返回上报结果，如果出错，则返回错误原因，可获取getIsSuccess()判断执行是否成功
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
			
			url_con.setConnectTimeout(Constants.CONNECT_TIME_OUT*1000);// 连接超时
			url_con.setReadTimeout(Constants.READ_TIME_OUT*1000);// 读操作超时
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
				
			/****************** Base64解码、解压 *******************/
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
	 * 执行上报文件操作
	 * 
	 * @param strurl
	 *            　地址
	 * @author wuzhu 20110811
	 * @return 返回上报结果，如果出错，则返回错误原因，可获取getIsSuccess()判断执行是否成功
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
				return "文件不存在!";
			}

			URL url = new URL(this.url);
			url_con = (HttpURLConnection) url.openConnection();

			url_con.setRequestMethod("POST");

			//设置文件类型
			url_con.setRequestProperty("file-format",
					FileUtil.getFileExtension(filename));
			url_con.setRequestProperty("Content-type", "application/octet-stream");
			url_con.setRequestProperty("Content-Length",String.valueOf(file.length()));
			url_con.setRequestProperty("Connection", "Keep-Alive");
			url_con.setConnectTimeout(Constants.CONNECT_TIME_OUT * 1000);// 连接超时
			url_con.setReadTimeout(Constants.READ_TIME_OUT * 1000);// 读操作超时
			url_con.setDoOutput(true);

			DataOutputStream ds = new DataOutputStream(
					url_con.getOutputStream());
			FileInputStream fStream = new FileInputStream(file);

			/* 设置每次写入1024bytes */
			int bufferSize = 1024;
			byte[] buffer = new byte[bufferSize];

			int length = -1;
			/* 从文件读取数据至缓冲区 */
			while ((length = fStream.read(buffer)) != -1)
			{
				/* 将资料写入DataOutputStream中 */
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

			/****************** Base64解码、解压 *******************/
			//Log.d("NetTask", "response:\n" + responseContent);
			
			//错误标识
			if(StringUtil.isEmpty(responseContent))
			{
				isSuccess=false;
				return "服务器端无返回结果！";
			}			
			//需要对返回报文进行解析
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
	 * 获取执行结果是否成功
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

	/************************************ 接口定义 **************************************/
	
	public interface INetComplete {
		
		/**
		 * 网络请求返回处理接口
		 * 网络访问请求结束时，返回并调用此接口
		 * 进行后续处理,isSuccess表示网络访问
		 * 是否成功.content为访问网络请求返回
		 * 的字串
		 * by haicao 
		 * 2011-3-10
		 */
		public void complete(boolean isSuccess, String content);
		
	}
	
	public interface INetProcessing {
		/**
		 * 下载完成百分比
		 * @param percent 下载百分比
		 */
		public void processing(int percent);
	}
}
