package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;

import com.wuzhupc.Sourcing.HomeActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.ChannelVO;
import com.wuzhupc.Sourcing.vo.UserVO;
import com.wuzhupc.widget.ExViewFlipper;
import com.wuzhupc.widget.SubChannelTabView;

import android.content.Context;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
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
	 * 内容view
	 */
	private View mContentView;
	
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
	protected Boolean misInitData=false;
	
	public Boolean getIsInitData()
	{
		return misInitData;
	}

	public void setIsInitData(Boolean isInitData)
	{
		this.misInitData = isInitData;
	}

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
	 * 是否有子栏目导航
	 */
	private boolean mHasNavigation;
	
	/**
	 * 是否有搜索输入框
	 */
	private boolean mHasSearch;
	
	/**
	 * 搜索输入框
	 */
	protected EditText met_search;
	
	/**
	 * 是否有刷新按钮
	 */
	private boolean mHasRefButton;
	
	/**
	 * 构造函数
	 * @param context
	 * @param fatherchannelid 栏目ID
	 */
	public BaseView(Context context,long fatherchannelid,boolean hassearch,boolean hasnavigation,boolean hasrefbutton) {
		super(context);
		mContext=context;
		mHasSearch=hassearch;
		mHasNavigation=hasnavigation;
		mHasRefButton = hasrefbutton;
		misInitData=false;
		if(mContext instanceof HomeActivity)
			mvf_content=((HomeActivity)mContext).getViewFlipper();
		setFatherChannelID(fatherchannelid);
	}
	
	/**
	 * 隐藏输入法
	 */
	protected void hideIme()
	{
		InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(met_search==null||!imm.isActive()) return;
		imm.hideSoftInputFromWindow(met_search.getWindowToken(), 0);
		met_search.setText("");
	}
	
	/**
	 * 初始化数据，每次切换父栏目时被调用 如果需要初始化返回true,不需要初始化返回false
	 */
	public boolean initData()
	{
		//设置是否显示刷新按钮
		if(mContext instanceof HomeActivity)
		{
			HomeActivity home = (HomeActivity)mContext;
			home.setMainTitleRefVisibility(mHasRefButton);
		}
		//是否已经初始化过数据，已经初始化则不处理
		if(misInitData)
		{
			hideIme();
			return false;
		}
		mNowChannelID=-1l;
		initView();
		initContentView();
		misInitData = true;
		if(mHasNavigation)
		{
			setNowChannel(getChannelIDFromList(-1));
		}
		else
		{
			hideIme();
			//根据栏目ID设置内容视图
			reflashContentView();
			loadData(true);
		}
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
		hideIme();
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
	 * 搜索事件
	 * @param et
	 */
	public abstract void searchAction(EditText et);
	
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
		if(mContentView==null)
		{
			mContentView=LayoutInflater.from(mContext).inflate(R.layout.view_base, mvf_content, false);
			this.addView(mContentView);
			RelativeLayout rl_search = (RelativeLayout)mContentView.findViewById(R.id.base_search_rl);
			rl_search.setVisibility(mHasSearch?View.VISIBLE:View.GONE);
			met_search = (EditText)mContentView.findViewById(R.id.base_search_et);
			met_search.setOnEditorActionListener(new OnEditorActionListener()
			{
				@Override
				public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
				{
					if(actionId==EditorInfo.IME_ACTION_SEARCH)
					{
						searchAction(met_search);
						return true;
					}
					return false;
				}
			});
			mll_content = (LinearLayout)mContentView.findViewById(R.id.base_context_ll);
		}
		//
		initNavigation(mContentView);
		//
	}
	
	/**
	 * 初始化栏目栏
	 */
	protected void initNavigation(View v)
	{
		//HorizontalScrollView sv = (HorizontalScrollView)findViewById(R.id.base_subchannel_hsv);
		LinearLayout ll = (LinearLayout)v.findViewById(R.id.base_subchannel_ll);
		ll.setVisibility(View.GONE);
		ll.removeAllViews();
		if(!(mContext instanceof HomeActivity)||!mHasNavigation)
			return;
		String usertype = null;
		UserVO uservo = ((HomeActivity)mContext).getApplicationSet().getUserVO();
		if(uservo!=null)
			usertype = String.valueOf(uservo.getUsertype());
		ArrayList<ChannelVO> channelVOs = ChannelVO.getChannels(
				((HomeActivity)mContext).getAllChannelVOs(),mFatherChannelID,usertype);
		if(channelVOs==null||channelVOs.isEmpty())
			return;
		mChannelList = new ArrayList<SubChannelTabView>(channelVOs.size());
		for(int i=0;i<channelVOs.size();i++)
		{
			SubChannelTabView tabView = new SubChannelTabView(mContext);
			tabView.setChannelVO(channelVOs.get(i));
			tabView.setIndex(i);
			tabView.setOnClickListener(mSubChannelTabViewClickListener);
			mChannelList.add(tabView);
			ll.addView(tabView);
		}
		for(int i =0;i<ll.getChildCount();i++)
		{
			View view = ll.getChildAt(i);
			LayoutParams lp = (LayoutParams) view.getLayoutParams();
			lp.width = LayoutParams.FILL_PARENT;
			lp.weight = 1f;
			view.setLayoutParams(lp);
		}
		ll.setVisibility(View.VISIBLE);
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
