package com.wuzhupc.widget;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.vo.ChannelVO;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 子栏目标题显示
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-26 下午9:47:18
 */
public class SubChannelTabView extends RelativeLayout
{
	private  ChannelVO mChannelVO;
	private int mIndex;
	private TextView mTabText;
	private ImageView mTabImg;

	public SubChannelTabView(Context context)
	{
		this(context,null);
	}

	public SubChannelTabView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.widget_subchannel_tab, this, true);
		mTabImg = (ImageView)this.findViewById(R.id.subchannel_tab_icon);
		mTabText = (TextView)this.findViewById(R.id.subchannel_tab_text);
	}

	public ImageView getTabImg()
	{
		return mTabImg;
	}

	public void setTabImg(ImageView tabImg)
	{
		this.mTabImg = tabImg;
	}

	public int getIndex()
	{
		return mIndex;
	}

	public void setIndex(int index)
	{
		this.mIndex = index;
	}

	public TextView getTabText()
	{
		return mTabText;
	}

	public void setTabText(TextView tabText)
	{
		this.mTabText = tabText;
	}

	public ChannelVO getChannelVO()
	{
		return mChannelVO;
	}

	public void setChannelVO(ChannelVO channelVO)
	{
		this.mChannelVO = channelVO;
		if(channelVO!=null)
			mTabText.setText(channelVO.getChannelName());
		else
			mTabText.setText("");
	}

	@Override
	public void setSelected(boolean selected)
	{
		super.setSelected(selected);
		mTabImg.setSelected(selected);
		mTabText.setSelected(selected);
		mTabText.setTextColor(getContext().getResources().getColor(selected?R.color.subchanneltab_sel_textcolor:R.color.subchanneltab_textcolor));
	}

}
