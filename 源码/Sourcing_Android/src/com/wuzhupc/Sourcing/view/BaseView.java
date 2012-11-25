package com.wuzhupc.Sourcing.view;

import com.wuzhupc.widget.ExViewFlipper;

import android.content.Context;
import android.view.GestureDetector;
import android.widget.LinearLayout;

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
	 * 构造函数
	 * @param context
	 * @param fatherchannelid 栏目ID
	 */
	public BaseView(Context context,long fatherchannelid) {
		super(context);
		mContext=context;
		misInitData=false;
		
	}
	
	
}
