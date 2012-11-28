package com.wuzhupc.widget;

import com.wuzhupc.Sourcing.R;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MoreButton
{

	private String title;
	private boolean showProgress;
	private View view;

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public boolean isShowProgress()
	{
		return showProgress;
	}

	public void setShowProgress(boolean showProgress)
	{
		this.showProgress = showProgress;
	}

	public View getView()
	{
		return view;
	}

	public void setView(View view)
	{
		this.view = view;

		notifyView();
	}

	/**
	 * 设置显示更多按钮
	 * 
	 * @param title
	 */
	public void setShowButton(String title)
	{
		setTitle(title);
		setShowProgress(false);
		notifyView();
	}

	/**
	 * 设置显示加载进度
	 * 
	 * @param title
	 */
	public void setShowProgress(String title)
	{
		setTitle(title);
		setShowProgress(true);
		notifyView();
	}

	private void notifyView()
	{
		if (view == null)
			return;
		//widget_morebutton.xml
		((TextView) view.findViewById(R.id.morebutton_tv)).setText(title);
		((ProgressBar) view.findViewById(R.id.morebotton_pb)).setVisibility(showProgress ? View.VISIBLE : View.GONE);
	}

}
