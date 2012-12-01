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
 * ������
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version ����ʱ�䣺2012-11-22 ����3:32:01
 */
public class HomeActivity extends BaseActivity implements OnGestureListener
{
	/**
	 * ����ʶ��
	 */
	private GestureDetector mgd_content;

	/**
	 * ������Ŀ�л��Ŀ��
	 */
	private int mAllowFlingPix = 120;

	private ExViewFlipper mvf_content;

	/**
	 * ����ˢ��״̬
	 */
	private ProgressBar mpb_DataRef;

	/**
	 * ����ˢ�°�ť
	 */
	private ImageView miv_DataRef;

	/**
	 * ������Ŀ�б�
	 */
	private ArrayList<ChannelVO> mChannelVOs;

	/**
	 * ����Ŀ�б�
	 */
	protected ArrayList<ChannelVO> mFatherChannelVOs;

	/**
	 * �ײ��˵����б�
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
		// ���ñ�����
		initMainTitleBar();
		// ���ò˵���
		initMenuBar();
		// ��ʼ��ViewFlipper
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
		// ����Ĭ��ѡ�е�ID��ʼ��
		setViewFlipper(getChannelIDFromList(-1), true);
		//reflashContent();
	}

	public ExViewFlipper getViewFlipper()
	{
		return mvf_content;
	}

	/**
	 * ���ñ�����(����ˢ�°�ť)
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
	 * ��ʼ��ViewFlipper�е���ͼ����
	 */
	private void initVFContext()
	{
		mgd_content = new GestureDetector(this);
		mvf_content = (ExViewFlipper) findViewById(R.id.home_content_vf);
		if (mFatherChannelVOs == null || mFatherChannelVOs.isEmpty())
			return;
		// ������Ŀ���ݳ�ʼ������View
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
				// TODO �����˲���ͼ
				continue;
			}
			if (vo.getType() == ChannelVO.TYPE_FATHER_USER)
			{
				// TODO ���Ӹ�����Ϣ��ͼ
				continue;
			}
		}
		// �����л�Ч��
		mvf_content.setOnDisplayerChildChangeListener(new OnDisplayerChildChangeListener()
		{
			@Override
			public void onChanged(int oldWhichChild, int whichChild)
			{
				// �ж���Ӧ���Ӽ�View�Ƿ��Ѿ���ʼ�����ݣ�δ��ʼ����������ó�ʼ������
				BaseView bv = getDisplayedChildView();
				if (bv != null)
					bv.initData();
			}
		});
	}

	/**
	 * ��������
	 */
	private void reflashContent()
	{
		// ���ݵ�ǰ���Ǹ�ҳ,ˢ����Ӧ������
		BaseView bv = getDisplayedChildView();
		if (bv != null)
		{
			setMainTitleRefStatus(true);
			bv.reflashData();
		}
	}

	/**
	 * ���ñ�����ˢ��״̬
	 * 
	 * @param ref
	 *            ���Ƿ�����ˢ������
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
	 * ���ñ�����ˢ�°�ť�Ƿ�ɼ�
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
	 * ��ȡ��ǰ��ʾ��View
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
	 * ��ʼ���˵���
	 */
	private void initMenuBar()
	{
		// ��ʼ����Ŀ����
		mFatherChannelVOs = ChannelVO.getChannels(mChannelVOs, ChannelVO.CHANNELID_FATHER);

		// ��ʼ���˵���
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
	 * �˵�������������ݵ��ID��ʼ��ViewFlipper������ݣ�
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
					// �������ʱ����
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
							// ���ø��಻Ϊѡ��
							setMenuBarNoSel(vo.getChannelID());
							if (which < 0 || which >= morelist.size())
								return;
							switch (morelist.get(which).getType())
							{
							case ChannelVO.TYPE_MORE_EXIT:
								askCloseApplication();
								break;
							case ChannelVO.TYPE_MORE_FAV:
								// TODO����ʾ�ղش���
								break;
							case ChannelVO.TYPE_MORE_SETTING:
								// TODO ��ʾ���ô���
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
	 * ��ȡ��ǰ��ʾ�ĸ���ĿID
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
	 * ����������ȡ��ĿID���б���
	 * 
	 * @param index
	 *            ������Ϊ-1ʱ��ȡĬ����ʾֵ
	 * @return
	 */
	private long getChannelIDFromList(int index)
	{
		// ����Ŀ�б�mFatherChannelList���ȡ��Ӧindex��ID
		if (mFatherChannelVOs == null || mFatherChannelVOs.isEmpty())
			return -1l;
		if (index < 0 || index >= mFatherChannelVOs.size())
		{
			// ���index<0��Ĭ��Ϊ��ȡ�б���ChannelVO��isdefaultֵΪtrue�ĵ�ID
			for (int i = 0; i < mFatherChannelVOs.size(); i++)
				if (mFatherChannelVOs.get(i).getIsDefault() == 1)
					return mFatherChannelVOs.get(i).getChannelID();
			// ���index<0���б���ChannelVO��isdefaultֵ��Ϊfalse�ģ��򷵻ص�һ��
			return mFatherChannelVOs.get(0).getChannelID();
		}

		return mFatherChannelVOs.get(index).getChannelID();
	}

	/**
	 * ������Ŀ�ɣġ���ȡ�ڸ���Ŀ�е�����ֵ
	 * 
	 * @param channelID
	 * @return�����δ�ҵ�����-1
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
	 * ����ĳ���˵���Ϊδѡ����״̬
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
	 * ����ĳ���˵���Ϊѡ��״̬
	 * 
	 * @param channelid
	 *            Ҫ����Ϊѡ��״̬�Ĳ˵�ID
	 * @param noselother
	 *            �Ƿ������˵��Ϊδѡ��
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
	 * ����ViewFlipper������
	 * 
	 * @param channelid
	 * @param bstart
	 *            ������
	 */
	private void setViewFlipper(long channelid, Boolean bstart)
	{
		if (!bstart && getNowFatherChannelID() == channelid)
			return;
		//
		setMainTitleRefVisibility(true);
		// ֹͣ���˵�ˢ�°�ť
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
		// ����Ǵ������󻬶�
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
