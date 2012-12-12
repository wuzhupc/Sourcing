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
 * ����View
 * 
 * @author wuzhu_000
 * 
 */
public class PersonView extends BaseView
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

	protected long mLastNewsId; // ���һ����Ϣid

	public PersonView(Context context, long fatherchannelid)
	{
		super(context, fatherchannelid, true, true);
	}

	/**
	 * ��ȡ��ʾ����
	 * 
	 * @note �������ݿ�������ݣ����������ݣ���ӷ�������ص���������ٽ��м���
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void loadData(Boolean isfirstload)
	{
		clearDataList();
		if (mMoreButton == null)
			mMoreButton = new MoreButton();
		// ��ȡ���ػ��������
		List list = null;
		String content = CacheUtil.getCacheContent(getNowChannelInfo());
		if (!StringUtil.isEmpty(content))
			list = JsonParser.parseJsonToList(content, new ResponseVO());
		if (list != null && !list.isEmpty())
		{
			getLastNewsId(list);
			addDataList(list);
			// �ж��Ƿ����б�ײ����ϸ��ఴť
			if (list.size() >= Constants.CINT_PAGE_SIZE)
			{
				mMoreButton.setShowButton(mContext
						.getString(R.string.list_item_more_msg));
				mDataList.add(mMoreButton);
			}
			mAdapter.notifyDataSetChanged();
			mlv_DataList.setSelectionAfterHeaderView(); // �б�ѡ�����û�ͷ��
			if (isfirstload)// �״ν���Ƶ�����ӷ���˻�ȡ��������
				loadNewData(StringUtil.STR_EMPTY);
		} else
		// ���������ݣ��ӷ���˻�ȡ
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
		// ���ݳ�ʼ��
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

		// ���������
		hideIme();

		loadNewData(et.getText().toString());
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
			mAdapter = new ListBaseAdapter(mContext, mDataList);
			mlv_DataList.setAdapter(mAdapter);
		}
		else
			mDataList.clear();
		//������������ʾ
		mDataList.add(mContext.getString(R.string.list_item_no_data));
		mAdapter.notifyDataSetChanged();
	}
	
	/**
	 * �����б�����
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
	 * ��ȡ��������Listener
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
				position -= mlv_DataList.getHeaderViewsCount(); // ��ȥ�б� header����

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
					//�����ʾ���鴦��
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
		BaseVO baseVO=(BaseVO)o;
		Intent intent = new Intent(mContext, NewsDetailActivity.class);
		intent.putExtra(NewsDetailActivity.CSTR_EXTRA_NEWSDETAIL_TITLE, getNowChannelInfo().getChannelName()+"����");
		intent.putExtra(NewsDetailActivity.CSTR_EXTRA_NEWSDETAIL_DATA, baseVO);
		mContext.startActivity(intent);
	}
	
	/**
	 * ��ȡ�б����һ������id
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
	 * �ӷ���˻�ȡ��������
	 */
	protected void loadNewData(String searchkey)
	{
		MobileInfoService newsService= new MobileInfoService(mContext);
		ChannelVO vo = getNowChannelInfo();
		if(vo==null)
			return;
		if(mPullRefreshListView!=null)
			mPullRefreshListView.setRefreshing(true);//   ����ˢ����ʾ
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
	 * �ӷ���˻�ȡ��������
	 */
	private void loadMoreData(String searchkey)
	{
		if(mMoreButton==null)
			return;
		mMoreButton.setShowProgress(mContext
				.getString(R.string.list_item_loading_data)); // �л����ఴť״̬ Ϊ ����״̬
		
		//�л����������°�ť״̬		
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
				// ˢ�¹������л�Ƶ���������б������д���
				if(channelId!=getNowChannelID())
					return;
				stopMainTitleReflashStauts();
				if(mPullRefreshListView!=null)
					 mPullRefreshListView.onRefreshComplete();// ֹͣˢ�°�ť����
				if(!isSuc)
				{
					showHint(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				List<?> list = JsonParser.parseJsonToList(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					// ���ķ��ش���
					showHint(respVO.getMsg());
					return;
				}
				// ���ķ�������
				// �洢���ˢ�µ��б�
				CacheUtil.cacheContent(getNowChannelInfo(), content);
				SettingUtil.setChannelLastUpdateTime(mContext, getNowChannelID(), new Date());
				clearDataList();// ������������ʾ
				if (list != null && !list.isEmpty())
				{
					getLastNewsId(list);
					addDataList(list);
					// �ж��Ƿ����б�ײ����ϸ��ఴť
					if (list.size() >= Constants.CINT_PAGE_SIZE)
					{
						mMoreButton.setShowButton(mContext
								.getString(R.string.list_item_more_msg));
						mDataList.add(mMoreButton);
					}
				}
				mAdapter.notifyDataSetChanged();
				mlv_DataList.setSelectionAfterHeaderView(); // �б�ѡ�����û�ͷ��
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
				// ˢ�¹������л�Ƶ���������б������д���
				if(channelId!=getNowChannelID())
					return;
				stopMainTitleReflashStauts();
				if(!isSuc)
				{
					mMoreButton.setShowButton(mContext.getString(R.string.list_item_more_msg)); // �л����ఴť״̬
					showHint(content);
					return;
				}
				ResponseVO respVO = new ResponseVO();
				List<?> list = JsonParser.parseJsonToList(content, respVO);
				if(respVO.getCode()!=ResponseVO.RESPONSE_CODE_SUCESS)
				{
					// ���ķ��ش���
					mMoreButton.setShowButton(mContext.getString(R.string.list_item_more_msg)); // �л����ఴť״̬
					showHint(respVO.getMsg());
					return;
				}
				// ���ķ�������
				if (mDataList.contains(mMoreButton))
				{ // �����������Ƴ����ఴť�����ݻ�ȡ������Ϣ���ж��Ƿ������ӵ��б�ĩβ
					mDataList.remove(mMoreButton);
				}
				if (list != null && !list.isEmpty())
				{
					getLastNewsId(list);
					addDataList(list);
					// �ж��Ƿ����б�ײ����ϸ��ఴť
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
