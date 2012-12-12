package com.wuzhupc.Sourcing.detail;

import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.wuzhupc.Sourcing.BaseActivity;
import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.adapter.ListBaseAdapter;
import com.wuzhupc.Sourcing.vo.BaseVO;
import com.wuzhupc.utils.FavoriteUtil;
import com.wuzhupc.utils.StringUtil;

/**
 * �ղ���Ϣ
 * @author wuzhu_000
 *
 */
public class FavInfoActivity extends BaseActivity
{
	protected static final String TAG = FavInfoActivity.class.getSimpleName();
	/**
	 * �ղر䶯
	 */
	public static final String CSTR_ACTION_FAV = "com.wuzhupc.ACTION_FAV";
	/**
	 * �����б�
	 */
	@SuppressWarnings("rawtypes")
	protected ArrayList mDataList;
	
	/**
	 * ��ʾ���ݵ��б�
	 */
	protected ListView mlv_DataList;

	protected ListBaseAdapter mAdapter; // �б� adapter
	
	private FavChangeBroadcastReceiver mBroadcastReceiver;
	
	@Override
	protected void initDataContent()
	{
		FavoriteUtil favoriteUtil = FavoriteUtil.getFavoriteUtil();		
		setDataList(favoriteUtil.getDataList());
	}

	@Override
	protected void initView()
	{
		setContentView(R.layout.activity_favinfo);
		mlv_DataList = (ListView)findViewById(R.id.favinfo_datalist_lv);
		mlv_DataList.setOnItemClickListener(getDataListItemClickListener());
		setTitleText(getResources().getString(R.string.favinfo_title));
	}
	
	protected OnItemClickListener getDataListItemClickListener()
	{
		OnItemClickListener itemClickListener = new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				Object obj = mDataList.get(position);
				if (obj == null)
					return;
				if (obj instanceof BaseVO)
				{
					runDetailActivity(position);
				}
			}
		};
		return itemClickListener;
	}
	
	/**
	 * ��ת���������
	 * 
	 * @param position
	 */
	protected void runDetailActivity(int position)
	{
		if(position<0||mDataList==null||mDataList.isEmpty()||position>=mDataList.size())
			return;
		Object o = mDataList.get(position);
		if(o==null||!(o instanceof BaseVO))
			return;
		
		Intent intent = new Intent(FavInfoActivity.this, NewsDetailActivity.class);
		intent.putExtra(NewsDetailActivity.CSTR_EXTRA_NEWSDETAIL_TITLE, "�ղ�����");
		intent.putExtra(NewsDetailActivity.CSTR_EXTRA_NEWSDETAIL_DATA, (BaseVO)o);
		runIntent(false, intent);
	}

	/**
	 * ע�����board��Ϣ
	 */
	private void regBoardReceiver()
	{
		IntentFilter filter = new IntentFilter(CSTR_ACTION_FAV);
		mBroadcastReceiver =new FavChangeBroadcastReceiver();
		registerReceiver(mBroadcastReceiver, filter);
	}
	
	private void unregBoardReceiver()
	{
		if(mBroadcastReceiver!=null)
			unregisterReceiver(mBroadcastReceiver);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		regBoardReceiver();
	}

	@Override
	protected void onDestroy()
	{
		unregBoardReceiver();
		super.onDestroy();
	}
	
	/**
	 * �����������
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void clearDataList()
	{
		if(mDataList==null)
		{
			mDataList = new ArrayList();
			// adapter ��ʼ��
			mAdapter = new ListBaseAdapter(FavInfoActivity.this, mDataList);
			mlv_DataList.setAdapter(mAdapter);
		}
		else
			mDataList.clear();
		//������������ʾ
		mDataList.add(FavInfoActivity.this.getString(R.string.favinfo_nodata));
		mAdapter.notifyDataSetChanged();
	}
	
	/**
	 * �����б�����
	 * @param list
	 */
	@SuppressWarnings({ "unchecked"})
	private void setDataList(List<?> list)
	{
		clearDataList();
		if(list==null||list.isEmpty())
			return;
		if(mDataList.size()==1)
		{
			Object o = mDataList.get(0);
			if(o instanceof String)
				mDataList.clear();
		}
		mDataList.addAll(list);
		mAdapter.notifyDataSetChanged();
	}
	
	public class FavChangeBroadcastReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context arg0, Intent arg1)
		{
			if(arg1==null||StringUtil.isEmpty(arg1.getAction()))
				return;
			if(CSTR_ACTION_FAV.equalsIgnoreCase(arg1.getAction()))
				initDataContent();
		}
	}
}
