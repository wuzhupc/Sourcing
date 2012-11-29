package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;

import com.wuzhupc.Sourcing.HomeActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.ChannelVO;
import com.wuzhupc.widget.ExViewFlipper;
import com.wuzhupc.widget.SubChannelTabView;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
	private GestureDetector mgd_content;
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
	protected ExViewFlipper mvf_content;
	
	protected LinearLayout mll_content;
	
	/**
	 * 栏目列表
	 */
	protected ArrayList<SubChannelTabView> mChannelList;
	
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
			mvf_content=((HomeActivity)mContext).getViewFlipper();
		mNowChannelID=-1l;
		setFatherChannelID(fatherchannelid);
	}
	
	/**
	 * 初始化数据，如果需要初始化返回true,不需要初始化返回false
	 */
	public  boolean initData()
	{
		if(misInitData)
			return false;
		initView();
		initContentView();
		misInitData = true;
		setNowChannel(getChannelIDFromList(-1));
		return misInitData;
	}
	/**
	 * 设置现在选中的栏目
	 * @param channelid
	 */
	protected void setNowChannel(long channelid)
	{
		if(mNowChannelID == channelid)
			return;
		mNowChannelID=channelid;
		//设置栏目状态
		setNavigationSel(mNowChannelID);
		//根据栏目ID设置内容视图
		reflashContentView();
		//增加对是否是第一次载入的判断，如果是第一次载入，则自动调用刷新功能
		boolean isfirstload = false;
		ChannelVO vo = getNowChannelInfo();
		if(vo!=null)
		{
			isfirstload = vo.isFirstLoad;
			vo.isFirstLoad = false;
		}
		loadData(isfirstload);
	}
	/**
     * 设置某个栏目项为选中状态
     * @param channelid
     */
    private void setNavigationSel(long channelid)
    {
    	if(mChannelList==null||mChannelList.isEmpty())
    		return;
    	for(int i=0;i<mChannelList.size();i++)
    	{
    		SubChannelTabView tabview=mChannelList.get(i);
    		tabview.setSelected(tabview.getChannelVO().getChannelID()==channelid);
    	}
    }

    /**
	 * 根据当前栏目ID（mNowChannelID），加载内容数据
	 * @param isfirstload 是否是第一次载入
	 */
	public abstract void loadData(Boolean isfirstload);
	
	/**
	 * 根据当前栏目ID（mNowChannelID），刷新数据
	 */
	public abstract void reflashData();
	
	/**
	 * 初始化内容部分的View
	 */
	public abstract void initContentView();
	
	/**
	 * 刷新内容视图
	 */
	public abstract void reflashContentView();
	/**
	 * 设置内容视图
	 * @param v
	 */
	public void setContentView(View v)
	{
		mll_content.removeAllViews();
		mll_content.addView(v);
	}
	/**
	 * 初始化View
	 */
	private void initView()
	{
		View v=LayoutInflater.from(mContext).inflate(R.layout.view_base, mvf_content, false);
		this.addView(v);
		initNavigation(v);
		mll_content = (LinearLayout)v.findViewById(R.id.base_context_ll);
	}
	/**
	 * 初始化栏目栏
	 */
	protected void initNavigation(View v)
	{
		ScrollView sv = (ScrollView)findViewById(R.id.base_subchannel_sv);
		LinearLayout ll = (LinearLayout)findViewById(R.id.base_subchannel_ll);
		sv.setVisibility(View.GONE);
		if(!(mContext instanceof HomeActivity))
			return;
		ArrayList<ChannelVO> channelVOs = ChannelVO.getChannels(
				((HomeActivity)mContext).getAllChannelVOs(),mFatherChannelID);
		if(channelVOs==null||channelVOs.isEmpty())
			return;
		mChannelList = new ArrayList<SubChannelTabView>(channelVOs.size());
		for(int i=0;i<mChannelList.size();i++)
		{
			SubChannelTabView tabView = new SubChannelTabView(mContext);
			tabView.setChannelVO(channelVOs.get(i));
			tabView.setIndex(i);
			tabView.setOnClickListener(mSubChannelTabViewClickListener);
			mChannelList.add(tabView);
			ll.addView(tabView);
		}
		sv.setVisibility(View.VISIBLE);
	}
	
	/**
	 * 子栏目栏点击监听（根据点击ID初始化ViewFlipper里的内容）
	 */
	private View.OnClickListener mSubChannelTabViewClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			if(!(v instanceof SubChannelTabView))
				return;
			SubChannelTabView tabView = (SubChannelTabView)v;
			setNowChannel(tabView.getChannelVO().getChannelID());
		}
	};
	
	/**
     * 根据索引获取栏目ID从列表中
     * @param index　索引，为-1时，取默认显示值 
     * @return
     */
    private long getChannelIDFromList(int index)
    {
    	if(mChannelList==null||mChannelList.isEmpty())
    		return -1l;
    	if(index<0||index>=mChannelList.size())
    	{
    		//如果index<0则默认为获取列表里ChannelVO里isdefault值为true的的ID
    		for(int i=0;i<mChannelList.size();i++)
    			if(mChannelList.get(i).getChannelVO().getIsDefault()==1)
    				return mChannelList.get(i).getChannelVO().getChannelID();
    		//如果index<0且列表里ChannelVO里isdefault值都为false的，则返回第一项
    		return mChannelList.get(0).getChannelVO().getChannelID();
    	}
    	return mChannelList.get(index).getChannelVO().getChannelID();
    }
    
    /**
     * 获取当前栏目信息
     * @return
     */
    public ChannelVO getNowChannelInfo()
    {
    	if(mChannelList==null||mChannelList.isEmpty())
    		return null;
    	for(int i=0;i<mChannelList.size();i++)
			if(mChannelList.get(i).getChannelVO().getChannelID()==mNowChannelID)
				return mChannelList.get(i).getChannelVO();
		return null;
    }
    /**
	 * 获取当前栏目ID
	 * @return
	 */
	public long getNowChannelID()
	{
		return mNowChannelID;
	}

	public long getFatherChannelID()
	{
		return mFatherChannelID;
	}

	public void setFatherChannelID(long fatherChannelID)
	{
		this.mFatherChannelID = fatherChannelID;
	}

	public GestureDetector getGestureDetectorContent()
	{
		return mgd_content;
	}

	public void setGestureDetectorContent(GestureDetector gd)
	{
		this.mgd_content = gd;
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
				
				return mgd_content.onTouchEvent(arg1);
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
	
	
	/**
	 * 设置主标题栏刷新按钮刷新状态停止
	 */
	protected void stopMainTitleReflashStauts()
	{
		//切换栏目后就设置为非刷新状态
		if(mContext instanceof HomeActivity)
			((HomeActivity)mContext).setMainTitleRefStatus(false);
	}
	
	/**
	 * 保持主标题栏刷新按钮刷新状态
	 */
	protected void keepMainTitleReflashStauts()
	{
		//切换栏目后就设置为非刷新状态
		if(mContext instanceof HomeActivity)
			((HomeActivity)mContext).setMainTitleRefStatus(true);
	}
	
	/**
	 * 设置主标题栏刷新按钮是否可见
	 */
	protected void setMainTitleReflashVisibility(Boolean bvisibility)
	{
		if(mContext instanceof HomeActivity)
			((HomeActivity)mContext).setMainTitleRefVisibility(bvisibility);
	}
}
