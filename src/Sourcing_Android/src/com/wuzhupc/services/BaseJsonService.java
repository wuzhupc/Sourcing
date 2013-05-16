package com.wuzhupc.services;

import java.util.Hashtable;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.utils.FileUtil;
import com.wuzhupc.utils.NetUtil;
import com.wuzhupc.utils.PhoneInfoUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.net.NetTask;
import com.wuzhupc.utils.net.NetTask.INetComplete;

import android.content.Context;
import android.util.Log;

public abstract class BaseJsonService
{
	private static final String TAG=BaseJsonService.class.getSimpleName();
	private static final Boolean DEBUG=true;
	/**
	 * ��ȡ�����ļ�(assets�ļ����е��ļ�)
	 */
	private static final Boolean LOCAL_DEBUG = false;
	/**
	 * ��ǰִ������
	 */
	protected String mCommandName;
	/**
	 * ��׺����
	 */
	protected String mSuffixStr;
	
	protected Context mContext;
	
	protected boolean mRandomReadData;
	protected int mRandomMax;
	
	public BaseJsonService(Context c)
	{
		mContext=c;
		mSuffixStr = "";
		mCommandName = "";
		mRandomMax = 10;
		mRandomReadData = false;
	}
	
	protected String getDevID()
	{
		return PhoneInfoUtil.GetIMEI(mContext);
	}
	
	protected UserVO getUserVO()
	{
		if(mContext instanceof BaseActivity)
		{
			return ((BaseActivity)mContext).getApplicationSet().getUserVO();
		}
		return null;
	}
	
	/**
	 * ��ȡ����
	 * @param json��������
	 * @param iReceiver
	 */
	protected void getData(String json,final IBaseReceiver iReceiver)
	{
		getData(json,null, iReceiver,false,null);
	}

	/**
	 * ��ȡ����
	 * @param json��������
	 * @param iReceiver
	 * @param showprogress ��ʾ�������
	 */
	protected void getData(String json,final IBaseReceiver iReceiver,Boolean showprogress)
	{
		getData(json,null, iReceiver,showprogress,null);
	}
	
	/**
	 * ��ȡ����
	 * @param json��������
	 * @param iReceiver
	 * @param showprogress ��ʾ�������
	 */
	protected void getData(String json,final IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
	{
		getData(json,null, iReceiver,showprogress,progressshowcontent);
	}

	/**
	 * ��ȡ����
	 * @param json��������
	 * @param url����ַ
	 * @param iReceiver
	 * @param showprogress ��ʾ�������
	 */
	protected void getData(String json,String url,final IBaseReceiver iReceiver,Boolean showprogress)
	{
		getData(json,url,iReceiver,showprogress,null);
	}
	
	/**
	 * ��ȡ����
	 * @param json��������
	 * @param url����ַ
	 * @param iReceiver
	 * @param showprogress ��ʾ�������
	 * @param progressshowcontent	��ʾ���������ʾ���ݣ����Ϊnull����ʾĬ����ʾ
	 */
	protected void getData(String json,String url,final IBaseReceiver iReceiver,Boolean showprogress,String progressshowcontent)
	{
		if(StringUtil.isEmpty(json)||iReceiver==null)
			return;

		if(DEBUG)Log.d(TAG, " json = "+json);
		
		//
		if(LOCAL_DEBUG)
		{
			String datafilename = mCommandName+"_response_data"+(StringUtil.isEmpty(mSuffixStr)?"":"_"+mSuffixStr)+".json";
			if(mRandomReadData)
			{
				int random = (int) (Math.random()*mRandomMax);
				datafilename = mCommandName+"_response_data_"+Integer.toString(random) +(StringUtil.isEmpty(mSuffixStr)?"":"_"+mSuffixStr)+".json";
			}
			String localjsoncontent = FileUtil.readFileFromAssetsFile(mContext, datafilename);			
			if(StringUtil.isEmpty(localjsoncontent))
				iReceiver.receiveCompleted(false, "��ǰ������Ѷ��Ϣ");
			else
				iReceiver.receiveCompleted(true, localjsoncontent);
			return;
		}
		if(NetUtil.getNetWorkType(mContext)==-1)
		{
			if(DEBUG)Log.d(TAG, "��ǰ�޿�������");
			iReceiver.receiveCompleted(false, "��ǰ�޿�������");
			return;
		}
		
		Hashtable<String, String> param = new Hashtable<String, String>();
		param.put("request",json);
		NetTask netTask = new NetTask(param, "utf-8", Constants.METHOD_POST, 
				new INetComplete() 
		{			
			@Override
			public void complete(boolean isSuc, String content) {
				if(DEBUG)Log.d(TAG, " content = "+content);
				String jsoncontent = isSuc ? content : mContext.getString(R.string.prompt_network_error);
//				if(isSuc)
//				{
//					ResponseVO respVO =JsonParser.parseJsonToResponse(content);
//					if(respVO.getCode() == ResponseVO.RESPONSE_CODE_FAIL)
//					{
//						isSuc=false;
//						jsoncontent=respVO.getMsg();
//					}
//				}
				iReceiver.receiveCompleted(isSuc, jsoncontent);
			}
		});
		
		netTask.setContext(mContext);
		netTask.setProgressContent(progressshowcontent);
		netTask.setShowProgress(showprogress);		
		netTask.execute(StringUtil.isEmpty(url)?mContext.getResources().getString(R.string.jsonurl):url);
	}
	
	/**
	 * ��Ϣ���սӿ�
	 * @author wuzhu
	 *
	 */
	public interface IBaseReceiver {
		
		/**
		 * �������
		 * @param isSuc  �Ƿ�ɹ�
		 * @param content ��ȡ�ɹ����������ݣ�ʧ�ܣ�����ʧ��ԭ��
		 */
		public void receiveCompleted(boolean isSuc, String content);
	}
}
