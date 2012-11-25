package com.wuzhupc.Sourcing.view;

import com.wuzhupc.widget.ExViewFlipper;

import android.content.Context;
import android.view.GestureDetector;
import android.widget.LinearLayout;

/**
 * BaseView ���е�ViewFlipper�ϵ��Ӽ�View��Ӧ�̳��Ա���
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-25 ����8:35:31
 * @description ��View�л���ʾʱ������initData��������Ƶ��Ŀ������Ŀ���л�ʱ��
 *  ��initData���ŵ�������initContextView->reflashContextView->loadData
 */
public abstract class BaseView extends LinearLayout
{
	protected final static String Tag=BaseView.class.getSimpleName();

	protected Context mContext;
	
	/**
	 * ����ʶ��
	 */
	private GestureDetector mgd_context;
	/**
	 * ��ĿID
	 */
	private long mFatherChannelID;
	
	/**
	 * ��ǰ��ĿID
	 */
	private long mNowChannelID;

	/**
	 * �Ƿ��Ѿ���ʼ��������
	 */
	protected Boolean misInitData;
	
	/**
	 * 
	 */
	protected ExViewFlipper mvf_context;
	
	/**
	 * ���캯��
	 * @param context
	 * @param fatherchannelid ��ĿID
	 */
	public BaseView(Context context,long fatherchannelid) {
		super(context);
		mContext=context;
		misInitData=false;
		
	}
	
	
}
