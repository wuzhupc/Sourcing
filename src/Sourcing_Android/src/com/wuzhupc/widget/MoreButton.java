package com.wuzhupc.widget;

import com.wuzhupc.Sourcing.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MoreButton extends RelativeLayout
{
	public MoreButton(Context context)
	{
		this(context,null);
	}
	public MoreButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.widget_morebutton, this, true);
	}

	private String title;
	private boolean showProgress;
	
	public void setShowButton(String title)
	{
		this.title= title;
		this.showProgress= false;
		notifyView();
	}
	public void setShowProgress(String title)
	{
		this.title= title;
		this.showProgress= true;
		notifyView();
	}

	private void notifyView()
	{
		//widget_morebutton.xml
		((TextView) this.findViewById(R.id.morebutton_tv)).setText(title);
		((ProgressBar) this.findViewById(R.id.morebotton_pb)).setVisibility(showProgress ? View.VISIBLE : View.GONE);
	}
}
