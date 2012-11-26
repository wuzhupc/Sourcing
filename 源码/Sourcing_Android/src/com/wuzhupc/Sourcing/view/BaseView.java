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
 * BaseView 所有的ViewFlipper上的子级View都应继承自本类
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-25 下午8:35:31
 * @description 在View切换显示时被调用initData（即在子频道目（或栏目）切换时）
 *  由initData接着调用子类initContextView->reflashContextView->loadData
 */
public abstract class BaseView extends LinearLayout
{
	protected final static String Tag=BaseView.class.getSimpleName();

	protected Context mContext;
	
	/**
	 * 手势识别
	 */
	private GestureDetector mgd_context;
	/**
	 * 栏目ID
	 */
	private long mFatherChannelID;
	
	/**
	 * 当前栏目ID
	 */
	private long mNowChannelID;

	/**
	 * 是否已经初始化过数据
	 */
	protected Boolean misInitData;
	
	/**
	 * 
	 */
	protected ExViewFlipper mvf_context;	
	
	/**
	 * 栏目列表
	 */
	protected ArrayList<ChannelVO> mChannelList;
	
	/**
	 * 构造函数
	 * @param context
	 * @param fatherchannelid 栏目ID
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
	 * 设置某个View支持手势识别
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
	 * 显示提示信息
	 * @param resid
	 */
	public void showHint(int resid)
	{
		showHint(getResources().getString(resid));
	}
	/**
	 * 显示提示信息
	 * @param resid
	 */
	public void showHint(String message)
	{
		Toast toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
		toast.show();
	}
}
