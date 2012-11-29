package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;
import java.util.Date;
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
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.utils.CacheUtil;
import com.wuzhupc.utils.SettingUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;
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

	protected long mLastNewsId; // 最后一条消息id

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
		//读取本地缓存的数据
		List<NewsVO> list = null;
		String content = CacheUtil.getCacheContent(getNowChannelInfo());
		if(!StringUtil.isEmpty(content))
			list = (List<NewsVO>) JsonParser.parseJsonToList(content, new ResponseVO());
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
				mLastNewsId = ((NewsVO) obj).getNewsid();
				break;
			}
		}
	}
	
	/**
	 * 从新闻列表里获取 新闻主题 的条目(除去列表头与尾的条目)
	 * 
	 * @param list
	 * @return
	 */
	protected ArrayList<NewsVO> getNewsList(List list)
	{
		ArrayList<NewsVO> ls = new ArrayList<NewsVO>();

		for (Object obj : list)
		{
			if (obj instanceof NewsVO)
			{
				ls.add((NewsVO) obj);
			}
		}

		return ls;
	}
	
	/**
	 * 从服务端获取最新数据
	 */
	protected void loadNewData()
	{
		MobileInfoService newsService= new MobileInfoService(mContext);
		final long channelId = getNowChannelID();
		if(mPullRefreshListView!=null)
			mPullRefreshListView.setRefreshing(true);//   数据刷新提示
		keepMainTitleReflashStauts();
		newsService.getNewsList(getNowChannelInfo().getType(), 0l, new IBaseReceiver()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				// 刷新过程中切换频道，不对列表结果进行处理
				if(channelId!=getNowChannelID())
					return;
				stopMainTitleReflashStauts();
				if(mPullRefreshListView!=null)
					 mPullRefreshListView.onRefreshComplete();// 停止刷新按钮动画
				if(!isSuc)
				{
					showHint(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				List<?> list = JsonParser.parseJsonToList(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					// 报文返回错误
					showHint(respVO.getMsg());
					return;
				}
				// 报文返回正常
				// 存储最近刷新的列表
				CacheUtil.cacheContent(getNowChannelInfo(), content);
				SettingUtil.setChannelLastUpdateTime(mContext, getNowChannelID(), new Date());
				mDataList.clear(); // 刷新列表，清空列表数据
				if (list == null || list.isEmpty())// 加入无数据提示
					mDataList.add(mContext.getString(R.string.list_item_no_data));
				else
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
				}
				mAdapter.notifyDataSetChanged();
				mlv_DataList.setSelectionAfterHeaderView(); // 列表选择项置回头部
			}
		});
		
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
		
		//切换标题栏更新按钮状态		
		MobileInfoService newsService= new MobileInfoService(mContext);
		final long channelId = getNowChannelID();
		keepMainTitleReflashStauts();
		newsService.getNewsList(getNowChannelInfo().getType(), mLastNewsId, new IBaseReceiver()
		{
			@SuppressWarnings("unchecked")
			@Override
			public void receiveCompleted(boolean isSuc, String content)
			{
				// 刷新过程中切换频道，不对列表结果进行处理
				if(channelId!=getNowChannelID())
					return;
				stopMainTitleReflashStauts();
				if(!isSuc)
				{
					mMoreButton.setShowButton(mContext.getString(R.string.list_item_more_msg)); // 切换更多按钮状态
					showHint(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				List<?> list = JsonParser.parseJsonToList(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					// 报文返回错误
					mMoreButton.setShowButton(mContext.getString(R.string.list_item_more_msg)); // 切换更多按钮状态
					showHint(respVO.getMsg());
					return;
				}
				// 报文返回正常
				if (mDataList.contains(mMoreButton))
				{ // 返回正常，移除更多按钮，根据获取到的消息数判断是否继续添加到列表末尾
					mDataList.remove(mMoreButton);
				}
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
				}
				mAdapter.notifyDataSetChanged();
			}
		});
		
		
	}
}
