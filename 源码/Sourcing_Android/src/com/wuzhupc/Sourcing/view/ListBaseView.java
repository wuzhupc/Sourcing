package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;

import android.widget.ListView;

import com.wuzhupc.Sourcing.adapter.ListBaseAdapter;
import com.wuzhupc.widget.MoreButton;
import com.wuzhupc.widget.refreshview.PullToRefreshListView;

public class ListBaseView extends BaseView
{
	/**
	 * �����б�
	 */
	@SuppressWarnings("rawtypes")
	protected ArrayList mDataList;


	/**
	 * ��ʾ���ݵ��б�
	 */
	protected ListView mlv_DataList;
	
	protected PullToRefreshListView mPullRefreshListView;

	protected MoreButton mMoreButton; //

	protected ListBaseAdapter mAdapter; // �б� adapter

	protected String mLastNewsId; // ���һ����Ϣid

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
