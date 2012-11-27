package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;

import android.widget.ListView;

import com.wuzhupc.Sourcing.adapter.ListBaseAdapter;
import com.wuzhupc.widget.MoreButton;
import com.wuzhupc.widget.refreshview.PullToRefreshListView;

public class ListBaseView extends BaseView
{
	/**
	 * 数据列表
	 */
	@SuppressWarnings("rawtypes")
	protected ArrayList mDataList;


	/**
	 * 显示数据的列表
	 */
	protected ListView mlv_DataList;
	
	protected PullToRefreshListView mPullRefreshListView;

	protected MoreButton mMoreButton; //

	protected ListBaseAdapter mAdapter; // 列表 adapter

	protected String mLastNewsId; // 最后一条消息id

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
		// TODO Auto-generated method stub

	}

	@Override
	public void reflashContentView()
	{
		// TODO Auto-generated method stub

	}

}
