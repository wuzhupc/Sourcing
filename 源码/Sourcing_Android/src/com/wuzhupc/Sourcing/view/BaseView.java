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
	 * ����view
	 */
	private View mContentView;
	
	/**
	 * ����ʶ��
	 */
	private GestureDetector mgd_content;
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
	 * ��Ŀ�б�
	 */
	protected ArrayList<SubChannelTabView> mChannelList;
	
	/**
	 * �Ƿ�������Ŀ����
	 */
	private boolean mHasNavigation;
	
	/**
	 * �Ƿ������������
	 */
	private boolean mHasSearch;
	
	/**
	 * ���������
	 */
	protected EditText met_search;
	
	/**
	 * �Ƿ���ˢ�°�ť
	 */
	private boolean mHasRefButton;
	
	/**
	 * ���캯��
	 * @param context
	 * @param fatherchannelid ��ĿID
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
	 * �������뷨
	 */
	protected void hideIme()
	{
		InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
		if(met_search==null||!imm.isActive()) return;
		imm.hideSoftInputFromWindow(met_search.getWindowToken(), 0);
		met_search.setText("");
	}
	
	/**
	 * ��ʼ�����ݣ�ÿ���л�����Ŀʱ������ �����Ҫ��ʼ������true,����Ҫ��ʼ������false
	 */
	public boolean initData()
	{
		//�����Ƿ���ʾˢ�°�ť
		if(mContext instanceof HomeActivity)
		{
			HomeActivity home = (HomeActivity)mContext;
			home.setMainTitleRefVisibility(mHasRefButton);
		}
		//�Ƿ��Ѿ���ʼ�������ݣ��Ѿ���ʼ���򲻴���
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
			//������ĿID����������ͼ
			reflashContentView();
			loadData(true);
		}
		return misInitData;
	}
	
	/**
	 * ��������ѡ�е���Ŀ
	 * @param channelid
	 */
	protected void setNowChannel(long channelid)
	{
		if(mNowChannelID == channelid)
			return;
		hideIme();
		mNowChannelID=channelid;
		//������Ŀ״̬
		setNavigationSel(mNowChannelID);
		//������ĿID����������ͼ
		reflashContentView();
		
		//���Ӷ��Ƿ��ǵ�һ��������жϣ�����ǵ�һ�����룬���Զ�����ˢ�¹���
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
     * ����ĳ����Ŀ��Ϊѡ��״̬
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
	 * ���ݵ�ǰ��ĿID��mNowChannelID����������������
	 * @param isfirstload �Ƿ��ǵ�һ������
	 */
	public abstract void loadData(Boolean isfirstload);
	
	/**
	 * ���ݵ�ǰ��ĿID��mNowChannelID����ˢ������
	 */
	public abstract void reflashData();
	
	/**
	 * ��ʼ�����ݲ��ֵ�View
	 */
	public abstract void initContentView();
	
	/**
	 * ˢ��������ͼ
	 */
	public abstract void reflashContentView();
	
	/**
	 * �����¼�
	 * @param et
	 */
	public abstract void searchAction(EditText et);
	
	/**
	 * ����������ͼ
	 * @param v
	 */
	public void setContentView(View v)
	{
		mll_content.removeAllViews();
		mll_content.addView(v);
	}
	/**
	 * ��ʼ��View
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
	 * ��ʼ����Ŀ��
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
	 * ����Ŀ��������������ݵ��ID��ʼ��ViewFlipper������ݣ�
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
     * ����������ȡ��ĿID���б���
     * @param index��������Ϊ-1ʱ��ȡĬ����ʾֵ 
     * @return
     */
    private long getChannelIDFromList(int index)
    {
    	if(mChannelList==null||mChannelList.isEmpty())
    		return -1l;
    	if(index<0||index>=mChannelList.size())
    	{
    		//���index<0��Ĭ��Ϊ��ȡ�б���ChannelVO��isdefaultֵΪtrue�ĵ�ID
    		for(int i=0;i<mChannelList.size();i++)
    			if(mChannelList.get(i).getChannelVO().getIsDefault()==1)
    				return mChannelList.get(i).getChannelVO().getChannelID();
    		//���index<0���б���ChannelVO��isdefaultֵ��Ϊfalse�ģ��򷵻ص�һ��
    		return mChannelList.get(0).getChannelVO().getChannelID();
    	}
    	return mChannelList.get(index).getChannelVO().getChannelID();
    }
    
    /**
     * ��ȡ��ǰ��Ŀ��Ϣ
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
	 * ��ȡ��ǰ��ĿID
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
	 * ����ĳ��View֧������ʶ��
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
	
	
	/**
	 * ������������ˢ�°�ťˢ��״ֹ̬ͣ
	 */
	protected void stopMainTitleReflashStauts()
	{
		//�л���Ŀ�������Ϊ��ˢ��״̬
		if(mContext instanceof HomeActivity)
			((HomeActivity)mContext).setMainTitleRefStatus(false);
	}
	
	/**
	 * ������������ˢ�°�ťˢ��״̬
	 */
	protected void keepMainTitleReflashStauts()
	{
		//�л���Ŀ�������Ϊ��ˢ��״̬
		if(mContext instanceof HomeActivity)
			((HomeActivity)mContext).setMainTitleRefStatus(true);
	}
	
	/**
	 * ������������ˢ�°�ť�Ƿ�ɼ�
	 */
	protected void setMainTitleReflashVisibility(Boolean bvisibility)
	{
		if(mContext instanceof HomeActivity)
			((HomeActivity)mContext).setMainTitleRefVisibility(bvisibility);
	}
	
	
}
