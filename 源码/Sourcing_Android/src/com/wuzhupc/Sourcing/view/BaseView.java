package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.HomeActivity;
import com.wuzhupc.Sourcing.vo.ChannelVO;
import com.wuzhupc.widget.ExViewFlipper;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

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
	 * ��Ŀ�б�
	 */
	protected ArrayList<ChannelVO> mChannelList;
	
	/**
	 * ���캯��
	 * @param context
	 * @param fatherchannelid ��ĿID
	 */
	public BaseView(Context context,long fatherchannelid) {
		super(context);
		mContext=context;
		misInitData=false;
		if(mContext instanceof HomeActivity)
			mvf_context=((HomeActivity)mContext).getViewFlipper();
		mNowChannelID=-1l;
		setFatherChannelID(fatherchannelid);
	}

	public long getFatherChannelID()
	{
		return mFatherChannelID;
	}

	public void setFatherChannelID(long fatherChannelID)
	{
		this.mFatherChannelID = fatherChannelID;
	}

	public GestureDetector getGestureDetectorContext()
	{
		return mgd_context;
	}

	public void setGestureDetectorContext(GestureDetector gd)
	{
		this.mgd_context = gd;
	}
	
	/**
	 * ����ĳ��View֧������ʶ��
	 * @param v
	 */
	public void setViewGestureDetector(View v)
	{
		v.setOnTouchListener(new OnTouchListener() 
		{			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				
				return mgd_context.onTouchEvent(arg1);
			}
		});
	}
	/**
	 * ��ʾ��ʾ��Ϣ
	 * @param resid
	 */
	public void showHint(int resid)
	{
		showHint(getResources().getString(resid));
	}
	/**
	 * ��ʾ��ʾ��Ϣ
	 * @param resid
	 */
	public void showHint(String message)
	{
		Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
		toast.show();
	}
}
