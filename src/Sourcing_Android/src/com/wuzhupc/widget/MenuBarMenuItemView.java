package com.wuzhupc.widget;

import com.wuzhupc.Sourcing.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenuBarMenuItemView extends LinearLayout
{
	private ImageView mMenuIcon;
	private TextView mMenuText;
	private int mIndex;
	
	
	public MenuBarMenuItemView(Context context)
	{
		this(context,null);
	}
	
	public MenuBarMenuItemView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.widget_menubar_btn, this, true);
		mMenuIcon = (ImageView)this.findViewById(R.id.menubar_menuitem_icon);
		mMenuText = (TextView)this.findViewById(R.id.menubar_menuitem_text);
	}

	public ImageView getMenuIcon()
	{
		return mMenuIcon;
	}

	public void setMenuIcon(ImageView mMenuIcon)
	{
		this.mMenuIcon = mMenuIcon;
	}

	public TextView getMenuText()
	{
		return mMenuText;
	}

	public void setMenuText(TextView mMenuText)
	{
		this.mMenuText = mMenuText;
	}

	public int getIndex()
	{
		return mIndex;
	}

	public void setIndex(int mIndex)
	{
		this.mIndex = mIndex;
	}

}
