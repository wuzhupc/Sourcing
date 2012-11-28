package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.adapter.ListBaseAdapter;
import com.wuzhupc.Sourcing.vo.NewsVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.widget.MoreButton;
import com.wuzhupc.widget.refreshview.PullToRefreshListView;

/**
 * 资讯类展示信息
 * 
 * @author wuzhu email:wuzhupc@gmail.com
 * @version 创建时间：2012-11-28 下午9:13:33
 */
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

	/**
	 * 构造函数
	 * 
	 * @param context
	 * @param fatherchannelid
	 *            栏目ID
	 */
	public ListBaseView(Context context, long fatherchannelid)
	{
		super(context, fatherchannelid);
	}
	
	/**
	 * 读取显示数据
	 * @note 本地数据库加载数据，本地无数据，则从服务端下载到本地完后再进行加载
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void loadData(Boolean isfirstload)
	{
		mDataList.clear(); // 切换频道，清空列表数据
		if (mMoreButton == null)
			mMoreButton = new MoreButton();
		long channelId = getNowChannelID(); // 当前频道id
		//TODO 读取本地缓存的数据
		List<NewsVO> list = new ArrayList<NewsVO>();
		if (list != null && !list.isEmpty())
		{
			getLastNewsId(list);
			mDataList.addAll(list);
			// 判断是否在列表底部加上更多按钮
			if (list.size() >= Constants.CINT_PAGE_SIZE)
			{
				mMoreButton.setShowButton(mContext
						.getString(R.string.list_item_more_msg));
				mDataList.add(mMoreButton);
			}
			mAdapter.notifyDataSetChanged();
			mlv_DataList.setSelectionAfterHeaderView(); // 列表选择项置回头部
			if (isfirstload)// 首次进入频道，从服务端获取最新数据
				loadNewData();
		} else// 本地无数据，从服务端获取
		{ 
			loadNewData();
		}
	}

	@Override
	public void reflashData()
	{
		loadNewData();
	}

	@Override
	public void initContentView()
	{
		// 初始化View
		View v = LayoutInflater.from(mContext).inflate(R.layout.listitem_base, mll_content, false);
		setContentView(v);

		mPullRefreshListView = (PullToRefreshListView) v.findViewById(R.id.list_base_datalist_lv);
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView
				.setOnRefreshListener(new com.wuzhupc.widget.refreshview.PullToRefreshBase.OnRefreshListener()
				{
					@Override
					public void onRefresh()
					{
						// Do work to refresh the list here.
						// new GetDataTask().execute();
						loadNewData();
					}
				});
		mlv_DataList = mPullRefreshListView.getRefreshableView();
		mlv_DataList.setOnItemClickListener(getDataListItemClickListener());
		setViewGestureDetector(mlv_DataList);
		// 数据初始化
		mDataList = new ArrayList();
		// adapter 初始化
		mAdapter = new ListBaseAdapter(mContext, mDataList);
		mlv_DataList.setAdapter(mAdapter);
	}

	@Override
	public void reflashContentView()
	{
		// TODO Auto-generated method stub

	}
	
	/**
	 * 获取数据项点击Listener
	 * 
	 * @return
	 */
	protected OnItemClickListener getDataListItemClickListener()
	{
		OnItemClickListener itemClickListener = new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				position -= mlv_DataList.getHeaderViewsCount(); // 减去列表 header个数

				if (position < 0 || position >= mDataList.size())
					return;

				Object obj = mDataList.get(position);
				if (obj == null)
					return;
				if (obj instanceof String)
					return;
				if (obj instanceof MoreButton)
				{
					loadMoreData();
					return;
				}
				if (obj instanceof NewsVO)
				{
					runDetailActivity(position);
				}
			}
		};
		return itemClickListener;
	}
	
	/**
	 * 跳转到详情界面
	 * 
	 * @param position
	 */
	protected void runDetailActivity(int position)
	{
		//TODO
	}
	/**
	 * 获取列表最后一条新闻id
	 * @param list
	 */
	private void getLastNewsId(List<?> list)
	{
		if (list == null || list.isEmpty())
			return;
		for (int i = list.size() - 1; i >= 0; i--)
		{
			Object obj = list.get(i);
			if (obj instanceof NewsVO)
			{
				mLastNewsId = Long.toString(((NewsVO) obj).getNewsid());
				break;
			}
		}
	}
	/**
	 * 从服务端获取最新数据
	 */
	protected void loadNewData()
	{
		//TODO
	}
	/**
	 * 从服务端获取更多数据
	 */
	private void loadMoreData()
	{
		if(mMoreButton==null)
			return;
		mMoreButton.setShowProgress(mContext
				.getString(R.string.list_item_loading_data)); // 切换更多按钮状态 为 加载状态
		
		//TODO 切换标题栏更新按钮状态
		
	}
}
