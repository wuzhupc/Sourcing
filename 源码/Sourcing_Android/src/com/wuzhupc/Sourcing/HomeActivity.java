package com.wuzhupc.Sourcing;

import java.util.ArrayList;

import com.wuzhupc.Sourcing.view.BaseView;
import com.wuzhupc.Sourcing.view.ListBaseView;
import com.wuzhupc.Sourcing.vo.ChannelVO;
import com.wuzhupc.utils.ViewUtil;
import com.wuzhupc.widget.ExViewFlipper;
import com.wuzhupc.widget.MenuBarMenuItemView;
import com.wuzhupc.widget.OnDisplayerChildChangeListener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * 主界面
 * 
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
	private int mAllowFlingPix = 120;

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

	/**
	 * 底部菜单栏列表
	 */
	protected MenuBarMenuItemView[] mv_menubarlist;

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_home);
		setCatureBackKey(true);
		mAllowFlingPix = ViewUtil.getScreenWidth(HomeActivity.this) * 2 / 3;
		mChannelVOs = ChannelVO.initChannelsFormAssets(this);
		if (mChannelVOs == null || mChannelVOs.isEmpty())
		{
			hitCloseApplication(R.string.home_initchannel_fail);
			return;
		}
		// setTitleTextBold();
		// 设置标题栏
		initMainTitleBar();
		// 设置菜单栏
		initMenuBar();
		// 初始化ViewFlipper
		initVFContext();

		// Boolean isfirststart=SettingUtil.isFirstStart(this, true);
		//
		// if (isfirststart) {
		// startGuideHelper(null);
		// }
	}

	@Override
	protected void initDataContent()
	{
		// 根据默认选中的ID初始化
		setViewFlipper(getChannelIDFromList(-1), true);
		//reflashContent();
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
				reflashContent();
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
		if (mFatherChannelVOs == null || mFatherChannelVOs.isEmpty())
			return;
		// 根据栏目数据初始化各级View
		for (int i = 0; i < mFatherChannelVOs.size(); i++)
		{
			ChannelVO vo = mFatherChannelVOs.get(i);
			if (vo.getType() == ChannelVO.TYPE_FATHER_NEWS)
			{
				ListBaseView view = new ListBaseView(HomeActivity.this, vo.getChannelID());
				view.setGestureDetectorContent(mgd_content);
				mvf_content.addView(view);
				continue;
			}
			if (vo.getType() == ChannelVO.TYPE_FATHER_PERSON)
			{
				// TODO 增加人才视图
				continue;
			}
			if (vo.getType() == ChannelVO.TYPE_FATHER_USER)
			{
				// TODO 增加个人信息视图
				continue;
			}
		}
		// 设置切换效果
		mvf_content.setOnDisplayerChildChangeListener(new OnDisplayerChildChangeListener()
		{
			@Override
			public void onChanged(int oldWhichChild, int whichChild)
			{
				// 判断相应的子级View是否已经初始化数据，未初始化数据则调用初始化数据
				BaseView bv = getDisplayedChildView();
				if (bv != null)
					bv.initData();
			}
		});
	}

	/**
	 * 更新数据
	 */
	private void reflashContent()
	{
		// 根据当前在那个页,刷新相应的内容
		BaseView bv = getDisplayedChildView();
		if (bv != null)
		{
			setMainTitleRefStatus(true);
			bv.reflashData();
		}
	}

	/**
	 * 设置标题栏刷新状态
	 * 
	 * @param ref
	 *            　是否正在刷新数据
	 */
	public void setMainTitleRefStatus(boolean ref)
	{
		if (ref)
		{
			mpb_DataRef.setVisibility(View.VISIBLE);
			miv_DataRef.setVisibility(View.GONE);
		} else
		{
			mpb_DataRef.setVisibility(View.GONE);
			miv_DataRef.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 设置标题栏刷新按钮是否可见
	 * 
	 * @param bvisibility
	 */
	public void setMainTitleRefVisibility(boolean bvisibility)
	{
		if (mpb_DataRef.getVisibility() == View.VISIBLE && bvisibility)
		{
			mpb_DataRef.setVisibility(View.VISIBLE);
			miv_DataRef.setVisibility(View.GONE);
		} else
		{
			mpb_DataRef.setVisibility(View.GONE);
			miv_DataRef.setVisibility(bvisibility ? View.VISIBLE : View.GONE);
		}
	}

	/**
	 * 获取当前显示的View
	 * 
	 * @return
	 */
	private BaseView getDisplayedChildView()
	{
		int whichChild = mvf_content.getDisplayedChild();
		View view = mvf_content.getChildAt(whichChild);
		if (view instanceof BaseView)
		{
			BaseView bv = (BaseView) view;
			return bv;
		}
		return null;
	}

	/**
	 * 初始化菜单栏
	 */
	private void initMenuBar()
	{
		// 初始化栏目数据
		mFatherChannelVOs = ChannelVO.getChannels(mChannelVOs, ChannelVO.CHANNELID_FATHER);

		// 初始化菜单栏
		mv_menubarlist = new MenuBarMenuItemView[4];

		mv_menubarlist[0] = (MenuBarMenuItemView) findViewById(R.id.home_menubar_m1_v);
		mv_menubarlist[1] = (MenuBarMenuItemView) findViewById(R.id.home_menubar_m2_v);
		mv_menubarlist[2] = (MenuBarMenuItemView) findViewById(R.id.home_menubar_m3_v);
		mv_menubarlist[3] = (MenuBarMenuItemView) findViewById(R.id.home_menubar_m4_v);

		for (int i = 0; i < mv_menubarlist.length; i++)
			mv_menubarlist[i].setVisibility(View.GONE);

		if (mFatherChannelVOs == null && mFatherChannelVOs.isEmpty())
			return;

		for (int i = 0; i < mFatherChannelVOs.size() && i < mv_menubarlist.length; i++)
		{
			mv_menubarlist[i].setVisibility(View.VISIBLE);
			ChannelVO vo = mFatherChannelVOs.get(i);
			mv_menubarlist[i].getMenuText().setText(vo.getChannelName());
			if (vo.getType() == ChannelVO.TYPE_FATHER_NEWS)
				mv_menubarlist[i].getMenuIcon().setImageResource(R.drawable.icon_home_tb_news);
			if (vo.getType() == ChannelVO.TYPE_FATHER_PERSON)
				mv_menubarlist[i].getMenuIcon().setImageResource(R.drawable.icon_home_tb_person);
			if (vo.getType() == ChannelVO.TYPE_FATHER_USER)
				mv_menubarlist[i].getMenuIcon().setImageResource(R.drawable.icon_home_tb_user);
			if (vo.getType() == ChannelVO.TYPE_FATHER_MORE)
				mv_menubarlist[i].getMenuIcon().setImageResource(R.drawable.icon_home_tb_more);
			mv_menubarlist[i].setOnClickListener(mMenuBarClickListener());
			mv_menubarlist[i].setIndex(i);
		}
	}

	/**
	 * 菜单点击监听（根据点击ID初始化ViewFlipper里的内容）
	 */
	private View.OnClickListener mMenuBarClickListener()
	{
		return new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (mFatherChannelVOs == null && mFatherChannelVOs.isEmpty())
					return;

				if (v instanceof MenuBarMenuItemView)
				{
					MenuBarMenuItemView menuItemView = (MenuBarMenuItemView) v;
					if (mFatherChannelVOs.size() <= menuItemView.getIndex() || menuItemView.getIndex() < 0)
						return;
					final ChannelVO vo = mFatherChannelVOs.get(menuItemView.getIndex());

					if (vo.getType() != ChannelVO.TYPE_FATHER_MORE)
					{
						setViewFlipper(vo.getChannelID(), false);
						return;
					}
					// 点击更多时处理
					final ArrayList<ChannelVO> morelist = ChannelVO.getChannels(mChannelVOs, vo.getChannelID());
					if (morelist == null || morelist.isEmpty())
						return;
					final String[] menus = new String[morelist.size()];
					for (int i = 0; i < morelist.size(); i++)
						menus[i] = morelist.get(i).getChannelName();
					DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							// 设置更多不为选中
							setMenuBarNoSel(vo.getChannelID());
							if (which < 0 || which >= morelist.size())
								return;
							switch (morelist.get(which).getType())
							{
							case ChannelVO.TYPE_MORE_EXIT:
								askCloseApplication();
								break;
							case ChannelVO.TYPE_MORE_FAV:
								// TODO　显示收藏窗口
								break;
							case ChannelVO.TYPE_MORE_SETTING:
								// TODO 显示设置窗口
								break;
							}
						}
					};
					AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this).setItems(menus, listener);
					builder.setOnCancelListener(new OnCancelListener()
					{
						@Override
						public void onCancel(DialogInterface dialog)
						{
							setMenuBarNoSel(vo.getChannelID());
						}
					});
					setMenuBarSel(vo.getChannelID(), false);
					builder.show();
				}
			}
		};
	}

	public ArrayList<ChannelVO> getAllChannelVOs()
	{
		return mChannelVOs;
	}

	/**
	 * 获取当前显示的父栏目ID
	 * 
	 * @return
	 */
	private long getNowFatherChannelID()
	{
		BaseView bv = getDisplayedChildView();
		if (bv != null)
			return bv.getFatherChannelID();
		return -1l;
	}

	/**
	 * 根据索引获取栏目ID从列表中
	 * 
	 * @param index
	 *            索引，为-1时，取默认显示值
	 * @return
	 */
	private long getChannelIDFromList(int index)
	{
		// 从栏目列表mFatherChannelList里获取相应index的ID
		if (mFatherChannelVOs == null || mFatherChannelVOs.isEmpty())
			return -1l;
		if (index < 0 || index >= mFatherChannelVOs.size())
		{
			// 如果index<0则默认为获取列表里ChannelVO里isdefault值为true的的ID
			for (int i = 0; i < mFatherChannelVOs.size(); i++)
				if (mFatherChannelVOs.get(i).getIsDefault() == 1)
					return mFatherChannelVOs.get(i).getChannelID();
			// 如果index<0且列表里ChannelVO里isdefault值都为false的，则返回第一项
			return mFatherChannelVOs.get(0).getChannelID();
		}

		return mFatherChannelVOs.get(index).getChannelID();
	}

	/**
	 * 根据栏目ＩＤ　获取在父栏目中的索引值
	 * 
	 * @param channelID
	 * @return　如果未找到返回-1
	 */
	private int getIndexFromList(long channelID)
	{
		if (channelID == -1l)
			return -1;
		if (mFatherChannelVOs == null || mFatherChannelVOs.isEmpty())
			return -1;
		for (int i = 0; i < mFatherChannelVOs.size(); i++)
			if (mFatherChannelVOs.get(i).getChannelID() == channelID)
				return i;
		return -1;
	}

	/**
	 * 设置某个菜单项为未选中中状态
	 * 
	 * @param channelid
	 */
	private void setMenuBarNoSel(long channelid)
	{
		int index = getIndexFromList(channelid);
		if (index < 0 || index >= mv_menubarlist.length)
			return;
		mv_menubarlist[index].setSelected(false);
	}

	/**
	 * 设置某个菜单项为选中状态
	 * 
	 * @param channelid
	 *            要设置为选中状态的菜单ID
	 * @param noselother
	 *            是否其它菜单项都为未选中
	 */
	private void setMenuBarSel(long channelid, Boolean noselother)
	{
		if (mFatherChannelVOs == null || mFatherChannelVOs.isEmpty())
			return;
		for (int i = 0; i < mFatherChannelVOs.size() && i < mv_menubarlist.length; i++)
		{
			if (mFatherChannelVOs.get(i).getChannelID() == channelid)
			{
				mv_menubarlist[i].setSelected(true);
				if (!noselother)
					return;
			} else if (noselother)
			{
				mv_menubarlist[i].setSelected(false);
			}
		}
	}

	/**
	 * 设置ViewFlipper的内容
	 * 
	 * @param channelid
	 * @param bstart
	 *            刚启动
	 */
	private void setViewFlipper(long channelid, Boolean bstart)
	{
		if (!bstart && getNowFatherChannelID() == channelid)
			return;
		//
		setMainTitleRefVisibility(true);
		// 停止主菜单刷新按钮
		setMainTitleRefStatus(false);
		setMenuBarSel(channelid, true);
		int index = getIndexFromList(channelid);
		if (index == -1)
			return;
		if (mvf_content.getDisplayedChild() > index)
		{
			mvf_content.setInAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.anim_view_right_in));
			mvf_content.setInAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.anim_view_right_out));
		} else
		{
			mvf_content.setInAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.anim_view_left_in));
			mvf_content.setInAnimation(AnimationUtils.loadAnimation(HomeActivity.this, R.anim.anim_view_left_out));
		}
		mvf_content.setDisplayedChild(index);
	}

	@Override
	public boolean onDown(MotionEvent arg0)
	{
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
	{
		if (e1 == null || e2 == null)
			return false;
		if (Math.abs(e1.getX() - e2.getX()) < mAllowFlingPix)
			return false;
		int nowchild = mvf_content.getDisplayedChild();
		int childcount = mvf_content.getChildCount();
		// 如果是从右向左滑动
		if (e1.getX() - e2.getX() < 0)
		{
			nowchild--;
			if (nowchild < 0)
				nowchild = childcount - 1;
		} else
		{
			nowchild++;
			if (nowchild >= childcount)
				nowchild = 0;
		}
		setViewFlipper(getChannelIDFromList(nowchild), false);
		return true;
	}

	@Override
	public void onLongPress(MotionEvent e)
	{
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e)
	{
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e)
	{
		return false;
	}

}
