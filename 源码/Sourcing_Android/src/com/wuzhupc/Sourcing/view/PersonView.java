package com.wuzhupc.Sourcing.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wuzhupc.Sourcing.R;
import com.wuzhupc.Sourcing.adapter.ListBaseAdapter;
import com.wuzhupc.Sourcing.detail.NewsDetailActivity;
import com.wuzhupc.Sourcing.vo.BaseVO;
import com.wuzhupc.Sourcing.vo.ChannelVO;
import com.wuzhupc.Sourcing.vo.ResponseVO;
import com.wuzhupc.config.Constants;
import com.wuzhupc.services.MobileInfoService;
import com.wuzhupc.services.BaseJsonService.IBaseReceiver;
import com.wuzhupc.utils.CacheUtil;
import com.wuzhupc.utils.SettingUtil;
import com.wuzhupc.utils.StringUtil;
import com.wuzhupc.utils.json.JsonParser;
import com.wuzhupc.widget.MoreButton;
import com.wuzhupc.widget.refreshview.PullToRefreshListView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 服务View
 * 
 * @author wuzhu_000
 * 
 */
public class PersonView extends BaseView
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

	public PersonView(Context context, long fatherchannelid)
	{
		super(context, fatherchannelid, true, true);
	}

	/**
	 * 读取显示数据
	 * 
	 * @note 本地数据库加载数据，本地无数据，则从服务端下载到本地完后再进行加载
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void loadData(Boolean isfirstload)
	{
		clearDataList();
		if (mMoreButton == null)
			mMoreButton = new MoreButton();
		// 读取本地缓存的数据
		List list = null;
		String content = CacheUtil.getCacheContent(getNowChannelInfo());
		if (!StringUtil.isEmpty(content))
			list = JsonParser.parseJsonToList(content, new ResponseVO());
		if (list != null && !list.isEmpty())
		{
			getLastNewsId(list);
			addDataList(list);
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
				loadNewData(StringUtil.STR_EMPTY);
		} else
		// 本地无数据，从服务端获取
		{
			loadNewData(StringUtil.STR_EMPTY);
		}

	}

	@Override
	public void reflashData()
	{
		loadNewData(met_search.getText().toString());
	}

	@Override
	public void initContentView()
	{
		if(mPullRefreshListView==null)
		{
		View v = LayoutInflater.from(mContext).inflate(R.layout.view_person,
				mll_content, false);
		setContentView(v);
		
		mPullRefreshListView = (PullToRefreshListView) v.findViewById(R.id.person_datalist_lv);
		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView
				.setOnRefreshListener(new com.wuzhupc.widget.refreshview.PullToRefreshBase.OnRefreshListener()
				{
					@Override
					public void onRefresh()
					{
						// Do work to refresh the list here.
						// new GetDataTask().execute();
						loadNewData(met_search.getText().toString());
					}
				});
		mlv_DataList = mPullRefreshListView.getRefreshableView();
		mlv_DataList.setOnItemClickListener(getDataListItemClickListener());
		setViewGestureDetector(mPullRefreshListView);
		}
		// 数据初始化
		clearDataList();
	}

	@Override
	public void reflashContentView()
	{
	}

	@Override
	public void searchAction(EditText et)
	{
		if (StringUtil.isEmpty(et.getText().toString()))
		{
			showHint(R.string.person_search_empty);
			et.requestFocus();
			return;
		}

		// 隐藏软键盘
		hideIme();

		loadNewData(et.getText().toString());
	}
	
	/**
	 * 清除所有数据
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void clearDataList()
	{
		if(mDataList==null)
		{
			mDataList = new ArrayList();
			// adapter 初始化
			mAdapter = new ListBaseAdapter(mContext, mDataList);
			mlv_DataList.setAdapter(mAdapter);
		}
		else
			mDataList.clear();
		//增加无数据提示
		mDataList.add(mContext.getString(R.string.list_item_no_data));
		mAdapter.notifyDataSetChanged();
	}
	
	/**
	 * 增加列表数据
	 * @param list
	 */
	@SuppressWarnings({ "unchecked"})
	private void addDataList(List<?> list)
	{
		if(mDataList==null)			
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
					loadMoreData(met_search.getText().toString());
					return;
				}
				if (obj instanceof BaseVO)
				{
					//点击显示详情处理
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
		if(position<0||mDataList==null||mDataList.isEmpty()||position>=mDataList.size())
			return;
		Object o = mDataList.get(position);
		if(o==null||!(o instanceof BaseVO))
			return;
		BaseVO baseVO=(BaseVO)o;
		Intent intent = new Intent(mContext, NewsDetailActivity.class);
		intent.putExtra(NewsDetailActivity.CSTR_EXTRA_NEWSDETAIL_TITLE, getNowChannelInfo().getChannelName()+"详情");
		intent.putExtra(NewsDetailActivity.CSTR_EXTRA_NEWSDETAIL_DATA, baseVO);
		mContext.startActivity(intent);
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
			if (obj instanceof BaseVO)
			{
				mLastNewsId = ((BaseVO) obj).getId();
				break;
			}
		}
	}
	
	/**
	 * 从服务端获取最新数据
	 */
	protected void loadNewData(String searchkey)
	{
		MobileInfoService newsService= new MobileInfoService(mContext);
		ChannelVO vo = getNowChannelInfo();
		if(vo==null)
			return;
		if(mPullRefreshListView!=null)
			mPullRefreshListView.setRefreshing(true);//   数据刷新提示
		keepMainTitleReflashStauts();
		if(vo.isPositionChannel())
		{
			newsService.getJobList(searchkey, 0l, getNewDataReceiver(vo.getChannelID()));
			return;
		}
		if(vo.isProjectChannel())
		{
			newsService.getProjectList(searchkey, 0l, getNewDataReceiver(vo.getChannelID()));
			return;
		}
		if(vo.isResumeChannel())
		{
			newsService.getResumeList(searchkey, 0l, getNewDataReceiver(vo.getChannelID()));
			return;
		}
		if(vo.isTrainChannel())
		{
			newsService.getTrainList(searchkey, 0l, getNewDataReceiver(vo.getChannelID()));
			return;
		}
		
	}
	/**
	 * 从服务端获取更多数据
	 */
	private void loadMoreData(String searchkey)
	{
		if(mMoreButton==null)
			return;
		mMoreButton.setShowProgress(mContext
				.getString(R.string.list_item_loading_data)); // 切换更多按钮状态 为 加载状态
		
		//切换标题栏更新按钮状态		
		MobileInfoService newsService= new MobileInfoService(mContext);
		ChannelVO vo = getNowChannelInfo();
		if(vo==null)
			return;
		keepMainTitleReflashStauts();
		if(vo.isPositionChannel())
		{
			newsService.getJobList(searchkey, mLastNewsId, getNewDataReceiver(vo.getChannelID()));
			return;
		}
		if(vo.isProjectChannel())
		{
			newsService.getProjectList(searchkey, mLastNewsId, getNewDataReceiver(vo.getChannelID()));
			return;
		}
		if(vo.isResumeChannel())
		{
			newsService.getResumeList(searchkey, mLastNewsId, getNewDataReceiver(vo.getChannelID()));
			return;
		}
		if(vo.isTrainChannel())
		{
			newsService.getTrainList(searchkey, mLastNewsId, getNewDataReceiver(vo.getChannelID()));
			return;
		}
	}
	
	public IBaseReceiver getNewDataReceiver(final long channelId)
	{
		return new IBaseReceiver()
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
				clearDataList();// 加入无数据提示
				if (list != null && !list.isEmpty())
				{
					getLastNewsId(list);
					addDataList(list);
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
		};
	}
	
	public IBaseReceiver getMoreDataReciiver(final long channelId)
	{
		return new IBaseReceiver()
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
					addDataList(list);
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
		};
	}
}
