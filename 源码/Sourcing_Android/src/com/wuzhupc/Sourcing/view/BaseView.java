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
	protected Boolean misInitData;
	
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
	 * ���캯��
	 * @param context
	 * @param fatherchannelid ��ĿID
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
	 * ��ʼ�����ݣ������Ҫ��ʼ������true,����Ҫ��ʼ������false
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
	 * ��������ѡ�е���Ŀ
	 * @param channelid
	 */
	protected void setNowChannel(long channelid)
	{
		if(mNowChannelID == channelid)
			return;
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
		View v=LayoutInflater.from(mContext).inflate(R.layout.view_base, mvf_content, false);
		this.addView(v);
		initNavigation(v);
		mll_content = (LinearLayout)v.findViewById(R.id.base_context_ll);
	}
	/**
	 * ��ʼ����Ŀ��
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
