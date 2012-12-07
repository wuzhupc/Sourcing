package com.wuzhupc.Sourcing.view;

import com.wuzhupc.Sourcing.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class PersonView extends BaseView
{

	public PersonView(Context context, long fatherchannelid)
	{
		super(context, fatherchannelid);
	}

	@Override
	public void loadData(Boolean isfirstload)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void reflashData()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void initContentView()
	{
		View v = LayoutInflater.from(mContext).inflate(R.layout.view_person, mll_content, false);
		setContentView(v);
	}

	@Override
	public void reflashContentView()
	{
		// TODO Auto-generated method stub

	}

}
