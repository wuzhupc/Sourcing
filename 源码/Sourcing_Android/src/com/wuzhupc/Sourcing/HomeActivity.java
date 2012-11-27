package com.wuzhupc.Sourcing;

import java.util.ArrayList;

import com.wuzhupc.Sourcing.vo.ChannelVO;
import com.wuzhupc.utils.ViewUtil;
import com.wuzhupc.widget.ExViewFlipper;
import com.wuzhupc.widget.MenuBarMenuItemView;

import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * 主界面
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-22 下午3:32:01
 */
public class HomeActivity extends BaseActivity implements OnGestureListener
{
	/**
	 * 手势识别
	 */
	private GestureDetector mgd_content;
	
	/**
	 * 允许栏目切换的宽度
	 */
	private int mAllowFlingPix=120;
	
	private ExViewFlipper mvf_content;
	
	/**
	 * 数据刷新状态
	 */
	private ProgressBar mpb_DataRef;
	
	/**
	 * 数据刷新按钮
	 */
	private ImageView miv_DataRef;
	
	/**
	 * 所有栏目列表
	 */
	private ArrayList<ChannelVO> mChannelVOs;
	
	/**
	 * 父栏目列表
	 */
	protected ArrayList<ChannelVO> mFatherChannelVOs;
	
	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_home);
		setCatureBackKey(true);
		mAllowFlingPix=ViewUtil.getScreenWidth(HomeActivity.this)*2/3;
		mChannelVOs = ChannelVO.initChannelsFormAssets(this);
		if(mChannelVOs==null||mChannelVOs.isEmpty())
		{
			hitCloseApplication(R.string.home_initchannel_fail);
			return;
		}
		//setTitleTextBold();
		//设置标题栏
		initMainTitleBar();
		// 设置菜单栏
		initMenuBar();
		// 初始化ViewFlipper
		initVFContext();
		
//		Boolean isfirststart=SettingUtil.isFirstStart(this, true);
//		
//		if (isfirststart) {
//			startGuideHelper(null);
//		}
	}

	@Override
	protected void initActions()
	{
		// 根据默认选中的ID初始化
		setViewFlipper(getChannelIDFromList(-1), true);	
		
		reflashContext();
	}

	public ExViewFlipper getViewFlipper()
	{
		return mvf_content;
	}
	/**
	 * 设置标题栏(包括刷新按钮)
	 */
	private void initMainTitleBar()
	{
		mpb_DataRef = (ProgressBar) findViewById(R.id.home_data_ref_pb);
		miv_DataRef = (ImageView) findViewById(R.id.home_data_ref_iv);
		miv_DataRef.setOnClickListener(new OnClickListener()
		{	
			@Override
			public void onClick(View v)
			{
				reflashContext();
			}
		});
	}
	
	/**
	 * 初始化ViewFlipper中的视图内容
	 */
	private void initVFContext()
	{
		mgd_content = new GestureDetector(this);
		mvf_content = (ExViewFlipper) findViewById(R.id.home_content_vf);		
		//TODO 根据栏目数据初始化各级View
		//TODO 设置切换效果
	}
	
	/**
	 * 更新数据
	 */
	private void reflashContext()
	{
		//TODO 更新数据
	}
	
	/**
	 * 设置标题栏刷新状态
	 * @param ref　是否正在刷新数据
	 */
	public void setMainTitleRefStatus(boolean ref)
	{
		if(ref)
		{
			mpb_DataRef.setVisibility(View.VISIBLE);
			miv_DataRef.setVisibility(View.GONE);
		}
		else
		{
			mpb_DataRef.setVisibility(View.GONE);
			miv_DataRef.setVisibility(View.VISIBLE);
		}
	}
	
	/**
	 * 设置标题栏刷新按钮是否可见
	 * @param bvisibility
	 */
	public void setMainTitleRefVisibility(boolean bvisibility)
	{
		if(mpb_DataRef.getVisibility()==View.VISIBLE&&bvisibility)
		{
			mpb_DataRef.setVisibility(View.VISIBLE);
			miv_DataRef.setVisibility(View.GONE);
		}
		else
		{
			mpb_DataRef.setVisibility(View.GONE);
			miv_DataRef.setVisibility(bvisibility?View.VISIBLE:View.GONE);
		}
	}
	
	/**
	 * 初始化菜单栏
	 */
	private void initMenuBar()
	{
		//初始化栏目数据
		mFatherChannelVOs = ChannelVO.getChannels(mChannelVOs,ChannelVO.CHANNELID_FATHER);
		//初始化菜单栏
		MenuBarMenuItemView[] home_menubar_v=new MenuBarMenuItemView[4];

		home_menubar_v[0] = (MenuBarMenuItemView) findViewById(R.id.home_menubar_m1_v);
		home_menubar_v[1] = (MenuBarMenuItemView) findViewById(R.id.home_menubar_m2_v);
		home_menubar_v[2] = (MenuBarMenuItemView) findViewById(R.id.home_menubar_m3_v);
		home_menubar_v[3] = (MenuBarMenuItemView) findViewById(R.id.home_menubar_m4_v);
		
		for(int i=0;i<home_menubar_v.length;i++)
			home_menubar_v[i].setVisibility(View.GONE);
		
		if(mFatherChannelVOs==null&&mFatherChannelVOs.isEmpty())
			return;

		for(int i=0;i<mFatherChannelVOs.size()&&i<home_menubar_v.length;i++)
		{
			home_menubar_v[i].setVisibility(View.VISIBLE);
			ChannelVO vo = mFatherChannelVOs.get(i);
			home_menubar_v[i].getMenuText().setText(vo.getChannelName());
			if(vo.getType()==ChannelVO.TYPE_FATHER_NEWS)
				home_menubar_v[i].getMenuIcon().setImageResource(R.drawable.icon_home_tb_news);
			if(vo.getType()==ChannelVO.TYPE_FATHER_PERSON)
				home_menubar_v[i].getMenuIcon().setImageResource(R.drawable.icon_home_tb_person);
			if(vo.getType()==ChannelVO.TYPE_FATHER_USER)
				home_menubar_v[i].getMenuIcon().setImageResource(R.drawable.icon_home_tb_user);
			if(vo.getType()==ChannelVO.TYPE_FATHER_MORE)
				home_menubar_v[i].getMenuIcon().setImageResource(R.drawable.icon_home_tb_more);
			home_menubar_v[i].setOnClickListener(mMenuBarClickListener);
			home_menubar_v[i].setIndex(i);
		}
	}
	
	private View.OnClickListener mMenuBarClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			if(mFatherChannelVOs==null&&mFatherChannelVOs.isEmpty())
				return;
			
			if(v instanceof MenuBarMenuItemView)
			{
				MenuBarMenuItemView menuItemView = (MenuBarMenuItemView)v;
				if(mFatherChannelVOs.size()<=menuItemView.getIndex()||menuItemView.getIndex()<0)
					return;
				ChannelVO vo = mFatherChannelVOs.get(menuItemView.getIndex());
				//TODO
			} 
		}
	};
	
	public ArrayList<ChannelVO> getAllChannelVOs()
	{
		return mChannelVOs;
	}
	
	/**
	 * 根据索引获取栏目ID从列表中
	 * 
	 * @param index
	 *            　索引，为-1时，取默认显示值
	 * @return
	 */
	private long getChannelIDFromList(int index)
	{
		//TODO 
		return -1l;
	}
	
	/**
	 * 设置ViewFlipper的内容
	 * 
	 * @param id
	 * @param bstart
	 *            刚启动
	 */
	private void setViewFlipper(long id, Boolean bstart)
	{
		//TODO 
	}
	
	@Override
	public boolean onDown(MotionEvent arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
